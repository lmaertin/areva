package de.tubs.areva.command;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Graph;
import org.eclipse.gef4.layout.algorithms.SpringLayoutAlgorithm;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.javatuples.Triplet;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.QualityAssignment;
import de.tubs.areva.emf.model.opmode.OperatingModeSelection;
import de.tubs.areva.emf.model.qadag.Node;
import de.tubs.areva.emf.model.qadag.QADAG;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.ui.module.DARGZestEdge;
import de.tubs.areva.ui.module.DARGZestNode;
import de.tubs.areva.ui.module.QADAGZestNode;
import de.tubs.areva.ui.view.DARGZestView;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.areva.util.ZestViewCounter;
import de.tubs.areva.util.emf.ResourceManager;
import de.tubs.forjahn.bachelor.utils.ArchitectureGraph;
import de.tubs.forjahn.bachelor.utils.DijkstraAlgorithm;
import sun.security.tools.policytool.Resources;

public class ProcessResourceFailure extends AbstractHandler implements IHandler {

	private static int i;
	protected static final String ID = ZestProperties.CSS_ID__NE;
	protected static final String LABEL = ZestProperties.LABEL__NE;
	
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ResourceSet operatingModeRS = new ResourceSetImpl();
		
		org.eclipse.emf.ecore.resource.Resource selectedOpModeLocal = null;
		IFile fileLocal = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
	            .getActivePage().getSelection();
	    if (selection != null & selection instanceof IStructuredSelection) {
	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
	        for (@SuppressWarnings("unchecked")
			Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
	        	fileLocal = (IFile)iterator.next();
	        	
	        	selectedOpModeLocal = operatingModeRS.getResource(org.eclipse.emf.common.util.URI.createURI(fileLocal.getLocationURI().toString()), true);
	            System.out.println("" + fileLocal.getLocationURI());
	            
	            break;
	        }
	    }
	    
	    final org.eclipse.emf.ecore.resource.Resource selectedOpMode = selectedOpModeLocal;
		final IFile file = fileLocal;
		Job job = new Job("Analyze Fault Paths") {
		    @Override 
		    protected IStatus run(IProgressMonitor monitor) { 
		        monitor.beginTask("Setting up fault paths", IProgressMonitor.UNKNOWN);
				
		        setName("Initialize");
			    
			    if(selectedOpMode == null) {
			    	System.out.println("ERROR: No selection found");
			    }
				
			    OperatingModeSelection opModeSelection = (OperatingModeSelection)selectedOpMode.getContents().get(0);
			    
			    ARG arg = (ARG) opModeSelection.getStartArchitecture().eContainer();
			    String opmodeName = opModeSelection.getName();
				
				// process arg
				
				List<org.javatuples.Triplet<Architecture, Architecture, String>> processedPath = new ArrayList<org.javatuples.Triplet<Architecture, Architecture, String>>();
				processFaultyResources(arg, opModeSelection.getFailingResources(), opModeSelection.getStartArchitecture(), processedPath, selectedOpMode, opmodeName, this, monitor);
				
				ResourceManager.saveResource(arg.eResource());
				
				for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
					
					try {
						project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
				
				return Status.OK_STATUS;
		    }
		};
		
		job.schedule();
		
		return null;
	}
	
	public static Element attachArchitectureInfo(Element target, Architecture architecture, Document dom, List<Resource> faultyResources) {
		
		Attr attr = dom.createAttribute("id");
		
		attr.setValue("" + architecture.getId());
		target.setAttributeNode(attr);
		
		attr = dom.createAttribute("quality");
		attr.setValue("" + architecture.getQuality());
		target.setAttributeNode(attr);
		
		attr = dom.createAttribute("optimal");
		attr.setValue("" + architecture.isOptimal());
		target.setAttributeNode(attr);
		
		List<Resource> usedHealthyResources = ResourceHelper.minus(architecture.getBoundResources(), faultyResources);
		List<Resource> usedFaultyResources = ResourceHelper.intersect(architecture.getBoundResources(), faultyResources);
		
		attr = dom.createAttribute("resourceCount");
		attr.setValue("" + usedHealthyResources.size());
		target.setAttributeNode(attr);
		
		Element usedResourcesEle = dom.createElement("resources");
		target.appendChild(usedResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + (usedFaultyResources.size() + usedHealthyResources.size()));
		usedResourcesEle.setAttributeNode(attr);
		
		// healthy resources
		Element usedHealthyResourcesEle = dom.createElement("healthyResources");
		usedResourcesEle.appendChild(usedHealthyResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + usedHealthyResources.size());
		usedHealthyResourcesEle.setAttributeNode(attr);
		
		for(Resource resource: usedHealthyResources) {
			Element e = dom.createElement("resource");
			e.appendChild(dom.createTextNode("" + resource.getName()));
			usedHealthyResourcesEle.appendChild(e);
		}
		
		// faulty resources
		Element usedFaultyResourcesEle = dom.createElement("faultyResources");
		usedResourcesEle.appendChild(usedFaultyResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + usedFaultyResources.size());
		usedFaultyResourcesEle.setAttributeNode(attr);
		
		for(Resource resource: usedFaultyResources) {
			Element e = dom.createElement("resource");
			e.appendChild(dom.createTextNode("" + resource.getName()));
			usedFaultyResourcesEle.appendChild(e);
		}
		
		Element qualitiesEle = dom.createElement("qualities");
		target.appendChild(qualitiesEle);
		
		for(Node quality: architecture.getQadag().getRoot().getNodes()) {
			
			Element e = dom.createElement("quality");
			e.appendChild(dom.createTextNode("" + quality.getValue()));
			qualitiesEle.appendChild(e);
			
			attr = dom.createAttribute("name");
			attr.setValue("" + quality.getName());
			e.setAttributeNode(attr);
		}
		
		return target;
	}
	
	public static double getGraphCostCohesion(ARG dargFile) {
		
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(dargFile);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		
		int pairs = 0;
		double costSum = 0;
		
		for(Architecture source: graph.getVertexes()) {
			
			shortestPathAlg.execute(source);
			for(Architecture target: graph.getVertexes()) {
				
				if(source != target) {
					
					List<Architecture> shortestPath = shortestPathAlg.getPath(target);
					int pathSize = 0;
					if(shortestPath != null)
						pathSize = shortestPath.size();
					
					if(pathSize > 0) {
						pairs++;
						
						double cost = ResourceHelper.GetArchitectureDifferences(source, target);
						costSum += cost;
					}
				}
			}
		}
		
		double averageCost = costSum / ((double)pairs);
		
		return averageCost;
	}
	
	public static double getGraphQualityCohesion(ARG dargFile) {
		
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(dargFile);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		
		int pairs = 0;
		double qualityDifferenceSum = 0;
		
		for(Architecture source: graph.getVertexes()) {
			
			shortestPathAlg.execute(source);
			for(Architecture target: graph.getVertexes()) {
				
				if(source != target) {
					
					List<Architecture> shortestPath = shortestPathAlg.getPath(target);
					int pathSize = 0;
					if(shortestPath != null)
						pathSize = shortestPath.size();
					
					if(pathSize > 0) {
						pairs++;
						
						double qualityDifference = Math.abs(source.getQuality() - target.getQuality());
						qualityDifferenceSum += qualityDifference;
					}
				}
			}
		}
		
		double averageQualityDifference = qualityDifferenceSum / ((double)pairs);
		
		return averageQualityDifference;
	}
	
	public static int getGraphDiameter(ARG dargFile) {
		
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(dargFile);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		
		int max = 0;
		
		for(Architecture source: graph.getVertexes()) {
			shortestPathAlg.execute(source);
			for(Architecture target: graph.getVertexes()) {
				if(source != target) {
					List<Architecture> shortestPath = shortestPathAlg.getPath(target);
					int pathSize = 0;
					if(shortestPath != null)
						pathSize = shortestPath.size();
					if(pathSize > max) {
						max = pathSize;
					}
				}
			}
		}
		
		return max;
	}
	
	public static int getGraphRadius(ARG dargFile) {
		
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(dargFile);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		
		int radius = Integer.MAX_VALUE;
		
		for(Architecture source: graph.getVertexes()) {
			shortestPathAlg.execute(source);
			int eccentricity = Integer.MIN_VALUE;
			for(Architecture target: graph.getVertexes()) {
				if(source != target) {
					List<Architecture> shortestPath = shortestPathAlg.getPath(target);
					int pathSize = 0;
					if(shortestPath != null)
						pathSize = shortestPath.size();
					eccentricity = Math.max(eccentricity, pathSize);
				}
			}
			
			radius = Math.min(radius, eccentricity);
		}
		
		return radius;
	}
	
	// general numbers text file
	public static void addGeneralNumbers(
			Document dom,
			Element pathEle, 
			Resource faultyResource, 
			List<Resource> faultyResources,
			List<Resource> allResources, 
			List<Resource> healthyResources,
			int faultyResourceCount, 
			Architecture current, 
			boolean conditionsMet, 
			int pathNodes,
			int qualityComparisons, 
			List<Architecture> qualityComparedArchitectures,
			int unguidedComparisons, 
			List<Architecture> unguidedComparedArchitectures,
			int comparisons,
			List<Architecture> comparedArchitectures,
			Architecture target,
			int totalCosts,
			boolean selected
			) {
		
		Element thisPathEle;
		Element e;
		
		// segment
		thisPathEle = dom.createElement("segment");
		pathEle.appendChild(thisPathEle);
		
		Attr attr = dom.createAttribute("id");
		attr.setValue("" + (faultyResourceCount - 1));
		thisPathEle.setAttributeNode(attr);
		
		boolean end;
		if(target == null) {
			end = true;
		} else {
			end = false;;
		}
		
		boolean nodeChanged = !conditionsMet && !end;
		attr = dom.createAttribute("nodeChanged");
		attr.setValue("" + nodeChanged);
		thisPathEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("searchFailed");
		attr.setValue("" + end);
		thisPathEle.setAttributeNode(attr);
		
		// faultyResource
		attr = dom.createAttribute("faultyResource");
		attr.setValue("" + faultyResource.getName());
		thisPathEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("passedNodes");
		attr.setValue("" + pathNodes);
		thisPathEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("totalCosts");
		attr.setValue("" + totalCosts);
		thisPathEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("selected");
		attr.setValue("" + selected);
		thisPathEle.setAttributeNode(attr);
		
		// source
		Element sourceEle = dom.createElement("source");
		thisPathEle.appendChild(sourceEle);
		
		attachArchitectureInfo(sourceEle, current, dom, faultyResources);
		
		// target
		if(nodeChanged) {
			
			Element targetEle = dom.createElement("target");
			thisPathEle.appendChild(targetEle);
			
			attachArchitectureInfo(targetEle, target, dom, faultyResources);
		}
		
		// resources
		
		Element resourcesEle = dom.createElement("resources");
		thisPathEle.appendChild(resourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + (faultyResources.size() + healthyResources.size()));
		resourcesEle.setAttributeNode(attr);
		
		// faultyResources
		
		Element faultyResourcesEle = dom.createElement("faultyResources");
		resourcesEle.appendChild(faultyResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + faultyResources.size());
		faultyResourcesEle.setAttributeNode(attr);
		
		for(Resource resource: faultyResources) {
			e = dom.createElement("resource");
			e.appendChild(dom.createTextNode("" + resource.getName()));
			faultyResourcesEle.appendChild(e);
		}
		
		// healtyResources
		
		Element healthyResourcesEle = dom.createElement("healthyResources");
		resourcesEle.appendChild(healthyResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + healthyResources.size());
		healthyResourcesEle.setAttributeNode(attr);
		
		for(Resource resource: healthyResources) {
			e = dom.createElement("resource");
			e.appendChild(dom.createTextNode("" + resource.getName()));
			healthyResourcesEle.appendChild(e);
		}
		
		
		
		if(nodeChanged) {
			
			// difference
			
			Element differenceEle = dom.createElement("difference");
			thisPathEle.appendChild(differenceEle);
			
			List<Resource> resourceDifference = ResourceHelper.GetArchitectureDifferencesResourcesList(current, target);
			
			attr = dom.createAttribute("quality");
			String qualityDifference = String.format(java.util.Locale.US,"%.5f",current.getQuality() - target.getQuality());
			attr.setValue("" + qualityDifference);
			differenceEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("resources");
			attr.setValue("" + resourceDifference.size());
			differenceEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, target));
			differenceEle.setAttributeNode(attr);
			
			// changedResources
			
			Element changedResourcesEle = dom.createElement("changedResources");
			differenceEle.appendChild(changedResourcesEle);
			
			for(Resource resource: ResourceHelper.minus(current.getBoundResources(), target.getBoundResources())) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				changedResourcesEle.appendChild(e);
				
				attr = dom.createAttribute("changed");
				attr.setValue("removed");
				e.setAttributeNode(attr);
			}
			
			for(Resource resource: ResourceHelper.minus(target.getBoundResources(), current.getBoundResources())) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				changedResourcesEle.appendChild(e);
				
				attr = dom.createAttribute("changed");
				attr.setValue("added");
				e.setAttributeNode(attr);
			}
			
			
			// changedQualities
			
			Element changedQualitiesEle = dom.createElement("changedQualities");
			differenceEle.appendChild(changedQualitiesEle);
			
			for(QualityAssignment sourceQA: current.getQualityassignments()) {
				
				String sourceValue = "" + sourceQA.getValue();
				String targetValue = "";
				
				for(QualityAssignment targetQA: target.getQualityassignments()) {
					if(sourceQA.getAttribute().equals(targetQA.getAttribute())) {
						targetValue = "" + targetQA.getValue();
					}
				}
				
				e = dom.createElement("quality");
				changedQualitiesEle.appendChild(e);

				e.appendChild(dom.createTextNode("" + sourceQA.getAttribute()));
				
				attr = dom.createAttribute("sourceValue");
				attr.setValue("" + sourceValue);
				e.setAttributeNode(attr);
				
				attr = dom.createAttribute("targetValue");
				attr.setValue("" + targetValue);
				e.setAttributeNode(attr);
			}
			
		}

		// comparisons
		Element comparisonsEle = dom.createElement("comparisons");
		thisPathEle.appendChild(comparisonsEle);
		
		// overall
		Element overallEle = dom.createElement("overall");
		comparisonsEle.appendChild(overallEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + comparedArchitectures.size());
		overallEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("total");
		attr.setValue("" + comparisons);
		overallEle.setAttributeNode(attr);
		
		// architecture
		for(Architecture architecture: comparedArchitectures) {
			
			Element architectureEle = dom.createElement("architecture");
			overallEle.appendChild(architectureEle);
			
			attachArchitectureInfo(architectureEle, architecture, dom, faultyResources);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
		}
		
		// unguided
		Element unguidedEle = dom.createElement("unguided");
		comparisonsEle.appendChild(unguidedEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + unguidedComparedArchitectures.size());
		unguidedEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("total");
		attr.setValue("" + unguidedComparisons);
		unguidedEle.setAttributeNode(attr);
		
		// architecture
		for(Architecture architecture: unguidedComparedArchitectures) {
			
			Element architectureEle = dom.createElement("architecture");
			unguidedEle.appendChild(architectureEle);
			
			attachArchitectureInfo(architectureEle, architecture, dom, faultyResources);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
		}
		
		// quality
		Element qualityEle = dom.createElement("quality");
		comparisonsEle.appendChild(qualityEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + qualityComparedArchitectures.size());
		qualityEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("total");
		attr.setValue("" + qualityComparisons);
		qualityEle.setAttributeNode(attr);
		
		// architecture
		for(Architecture architecture: qualityComparedArchitectures) {
			
			Element architectureEle = dom.createElement("architecture");
			qualityEle.appendChild(architectureEle);
			
			attachArchitectureInfo(architectureEle, architecture, dom, faultyResources);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
		}
		
		
	}
	
	public static void setupGeneralNumbers(
			Document dom,
			Element rootEle,
			List<Resource> allResources,
			int nodeCount,
			int edgeCount,
			int radius,
			int diameter,
			double costCohesion,
			double qualityCohesion
			) {
		
		Element allResourcesNode = null;
		Element e = null;
		
		allResourcesNode = dom.createElement("allResources");
		Attr attr;
		attr = dom.createAttribute("count");
		attr.setValue("" + allResources.size());
		allResourcesNode.setAttributeNode(attr);
		
		for(Resource resource: allResources) {
			e = dom.createElement("resource");
			e.appendChild(dom.createTextNode(resource.getName()));
			allResourcesNode.appendChild(e);
		}
		
		rootEle.appendChild(allResourcesNode);
		
		attr = dom.createAttribute("domainsize");
		attr.setValue("" + nodeCount);
		rootEle.setAttributeNode(attr);
		  
		attr = dom.createAttribute("interconnections");
		attr.setValue("" + edgeCount);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("radius");
		attr.setValue("" + radius);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("diameter");
		attr.setValue("" + diameter);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("costCohesion");
		attr.setValue("" + costCohesion);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("qualityCohesion");
		attr.setValue("" + qualityCohesion);
		rootEle.setAttributeNode(attr);
	}
	
	public static int getNodeCount(ARG dargFile) {
		
		int size = 0;
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		return size;
	}
	
	public static int getEdgeCount(ARG dargFile) {
		
		int edgeCount = 0;
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			
			if(Double.isNaN(architecture.getQuality())) {
				continue;
			}
			
			for(Architecture targetArchitecture: architecture.getRelatedArchitectures()) {
				
				if(!Double.isNaN(targetArchitecture.getQuality())) {
					edgeCount++;
				}
			}
		}
		
		return edgeCount;
	}
	
	// domain density
	public static String getStructuralDomainDensityCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		int[][] table = new int[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new int[size];
		}
		
		String[] validArchitectures = new String[size];
		
		for(int i = 0, ix = 0; i <  dargFile.getArchitectures().size(); i++) {
			
			Architecture target = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(target.getQuality())) {
				continue;
			} 
			
			for(int j = 0, jx = 0; j <  dargFile.getArchitectures().size(); j++) {
				
				Architecture source = dargFile.getArchitectures().get(j);
				
				if(Double.isNaN(source.getQuality())) {
					continue;
				} 
				
				int difference = 0;
				
				if(!target.getId().equals(source.getId())) {
					difference = ResourceHelper.GetArchitectureDifferences(source, target);
				}
				
				table[ix][jx] = difference;
				
				jx++;
			}
			validArchitectures[ix] = target.getId();
			ix++;
		}
		
		for(int j = 0; j <  size; j++) {
			
			output += ";" + validArchitectures[j];
		}
		
		for(int i = 0; i <  size; i++) {
			
			output += nl + validArchitectures[i];
			
			for(int j = 0; j <  size; j++) {
				
				output += ";" + table[i][j];
			}
		}
		
		return output;
	}
	
	public static String getArchitecturesCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		String[][] table = new String[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new String[3];
		}
		
		for(int i = 0, ix = 0; i < dargFile.getArchitectures().size(); i++) {
			
			Architecture architecture = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(architecture.getQuality())) {
				continue;
			} 
			
			table[ix][0] = architecture.getId();
			table[ix][1] = "" + architecture.getBoundResources().size();
			table[ix][2] = String.format(java.util.Locale.US,"%.5f", architecture.getQuality());
			
			ix++;
		}
		
		output += "id;#resources;quality";
		for(int i = 0; i < size; i++) {
			if(table[i][0] != null)
			output += nl + table[i][0] + ";" + table[i][1] + ";" + table[i][2];
		}
		
		return output;
	}
	
	public static String getArchitectureQualitiesCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		Architecture sampleArchitecture = dargFile.getArchitectures().get(0);
		int qualityCount = sampleArchitecture.getQualityassignments().size();
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		String[][] table = new String[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new String[qualityCount];
		}
		
		for(int i = 0, ix = 0; i < dargFile.getArchitectures().size(); i++) {
			
			Architecture architecture = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(architecture.getQuality())) {
				continue;
			} 
			for(int j = 0; j < qualityCount; j++) {
				QualityAssignment qualityAssignment = architecture.getQualityassignments().get(j);
				table[ix][j] = String.format(java.util.Locale.US,"%.5f", qualityAssignment.getValue());
			}
			
			ix++;
		}
		
		output += "id";
		for(int i = 0; i < qualityCount; i++) {
			output += ";" + sampleArchitecture.getQualityassignments().get(i).getAttribute();
		}
		
		for(int i = 0; i < size; i++) {
			Architecture architecture = dargFile.getArchitectures().get(i);
			if(!Double.isNaN(architecture.getQuality())) {
				output += nl + architecture.getId();
				for(int j = 0; j < qualityCount; j++) {
					output += ";" + table[i][j];
				}
			}
		}
		
		return output;
	}
	
	public static String getRawArchitectureQualitiesCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		Architecture sampleArchitecture = dargFile.getArchitectures().get(0);
		int qualityCount = sampleArchitecture.getRawQualityAssignments().size();
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		String[][] table = new String[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new String[qualityCount];
		}
		
		for(int i = 0, ix = 0; i < dargFile.getArchitectures().size(); i++) {
			
			Architecture architecture = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(architecture.getQuality())) {
				continue;
			} 
			for(int j = 0; j < qualityCount; j++) {
				QualityAssignment qualityAssignment = architecture.getRawQualityAssignments().get(j);
				table[ix][j] = String.format(java.util.Locale.US,"%.5f", qualityAssignment.getValue());
			}
			
			ix++;
		}
		
		output += "id";
		for(int i = 0; i < qualityCount; i++) {
			output += ";" + sampleArchitecture.getRawQualityAssignments().get(i).getAttribute();
		}
		
		for(int i = 0; i < size; i++) {
			Architecture architecture = dargFile.getArchitectures().get(i);
			if(!Double.isNaN(architecture.getQuality())) {
				output += nl + architecture.getId();
				for(int j = 0; j < qualityCount; j++) {
					output += ";" + table[i][j];
				}
			}
		}
		
		return output;
	}
	
	public static String getDomainResourcesCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		List<Resource> allResources = new ArrayList<Resource>();
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			
			for(ResourceOptions option: architecture.getBoundResourceOptions()) {
				getAllResources(option, allResources);
			}
		}
		int resourceCount = allResources.size();
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		String[][] table = new String[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new String[resourceCount];
		}
		
		for(int i = 0, ix = 0; i < dargFile.getArchitectures().size(); i++) {
			
			Architecture architecture = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(architecture.getQuality())) {
				continue;
			} 
			for(int j = 0; j < resourceCount; j++) {
				
				Resource resource = allResources.get(j);
				
				if(architecture.getBoundResources().contains(resource)) {
					table[ix][j] = "1";
				} else {
					table[ix][j] = "0";
				}
			}
			
			ix++;;
		}
		
		output += "id";
		for(int i = 0; i < resourceCount; i++) {
			output += ";" + allResources.get(i).getName();
		}
		
		for(int i = 0; i < size; i++) {
			Architecture architecture = dargFile.getArchitectures().get(i);
			if(!Double.isNaN(architecture.getQuality())) {
				output += nl + architecture.getId();
				for(int j = 0; j < resourceCount; j++) {
					output += ";" + table[i][j];
				}
			}
		}
		
		return output;
	}
	
	public static String getQualityDomainDensityCSV(ARG dargFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = 0;
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		String[][] table = new String[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new String[size];
		}
		
		String[] validArchitectures = new String[size];
		
		for(int i = 0, ix = 0; i <  dargFile.getArchitectures().size(); i++) {
			
			Architecture target = dargFile.getArchitectures().get(i);
			
			if(Double.isNaN(target.getQuality())) {
				continue;
			} 
			
			for(int j = 0, jx = 0; j <  dargFile.getArchitectures().size(); j++) {
				
				Architecture source = dargFile.getArchitectures().get(j);
				
				if(Double.isNaN(source.getQuality())) {
					continue;
				} 
				
				String qualityDifference = "";
				qualityDifference = String.format(java.util.Locale.US,"%.5f", source.getQuality()) + ":" + String.format(java.util.Locale.US,"%.5f",target.getQuality());
				
				table[ix][jx] = qualityDifference;
				
				jx++;
			}
			validArchitectures[ix] = target.getId();
			ix++;
		}
		
		for(int j = 0; j <  size; j++) {
			
			output += ";" + validArchitectures[j];
		}
		
		for(int i = 0; i <  size; i++) {
			
			output += nl + validArchitectures[i];
			
			for(int j = 0; j <  size; j++) {
				
				output += ";" + table[i][j];
			}
		}
		
		return output;
	}
	
	public static int getMinValue(ResourceOptions options) {
		
		if(options.getSubGroups().size() > 0) {
			
			int minValue = 0;
			
			for(ResourceOptions option: options.getSubGroups()) {
				minValue += getMinValue(option);
			}
			
			return minValue;
			
		} else {
			return Integer.parseInt(options.getMinElements());
		}
	}
	
	public static void processFaultSequence(Document dom, Element pathEle, List<Resource> allResources, ARG dargFile, List<Resource> faultyResources, Architecture startingArchitecture, List<org.javatuples.Triplet<Architecture, Architecture, String>> processedPath, String opmodeName, int index) {
		// efficiency
		
		int pathNodes = 1;
		int qualityComparisons = 0;
		int comparisons = 0;
		int unguidedComparisons = 0;
		int totalCosts = 0;
		
		Architecture start = startingArchitecture;
		List<Resource> activeResources = new ArrayList<Resource>();
		List<Resource> faultyResourcesOrder = new ArrayList<Resource>();
		
		
		Architecture current = start;
		Architecture oldCurrent = null;
		int faultyResourceCount = 0;
		
		Element segmentsEle = dom.createElement("segments");
		pathEle.appendChild(segmentsEle);
		
		Attr attr = dom.createAttribute("opMode");
		if(opmodeName.isEmpty()) {
			attr.setValue("null");
		} else {
			attr.setValue(opmodeName);
		}
		segmentsEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("index");
		attr.setValue("" + index);
		segmentsEle.setAttributeNode(attr);
		
		String sequenceTag = "";
		
		for(Resource faultyResource: faultyResources) {
			
			sequenceTag += faultyResource.getName() + ";";
			int addedCosts = 0;
			faultyResourceCount++;
			faultyResourcesOrder.add(faultyResource);
			if(current == null) {
				if(oldCurrent != null) {
					oldCurrent.setMarked(-1f);
				}
				continue;
			}
			
			
			
			current.setHidden(false);
			
			Architecture nextNode = null;
			System.out.println("Processing fault: " + faultyResource.getName());
			
			activeResources = ResourceHelper.minus(allResources, faultyResourcesOrder);
			
			boolean conditionsMet = true;
			
			for(ResourceOptions options: current.getBoundResourceOptions()) {
				conditionsMet = conditionsMet && conditionsMet(options, activeResources);
			}
			
			List<Architecture> qualityComparedArchitectures = new ArrayList<Architecture>();
			List<Architecture> unguidedComparedArchitectures = new ArrayList<Architecture>();
			List<Architecture> comparedArchitectures = new ArrayList<Architecture>();
			Architecture target = null;
			
			if(conditionsMet) {
				processedPath.add(new org.javatuples.Triplet<Architecture, Architecture, String>(current, current, "-" + faultyResource.getName()));
				addGeneralNumbers(dom, segmentsEle, faultyResource, faultyResourcesOrder, allResources, activeResources, faultyResourceCount, current, true, pathNodes, qualityComparisons, qualityComparedArchitectures, unguidedComparisons, unguidedComparedArchitectures, comparisons, comparedArchitectures, current, totalCosts, true);
			} else {
				
				List<Architecture> relatedArchitectures = new ArrayList<Architecture>(current.getRelatedArchitectures());
				Collections.sort(relatedArchitectures, new Comparator<Architecture>() {
				    @Override
				    public int compare(Architecture o1, Architecture o2) {
				    	if(Double.isNaN(o1.getQuality()) && Double.isNaN(o2.getQuality()) ) {
				    		return 0;
				    	}
				    	if(Double.isNaN(o1.getQuality()) && !Double.isNaN(o2.getQuality()) ) {
				    		return -1;
				    	}
				    	if(!Double.isNaN(o1.getQuality()) && Double.isNaN(o2.getQuality()) ) {
				    		return 1;
				    	}
				    	if(o1.getQuality() < o2.getQuality()) {
				    		return 1;
				    	} 
				    	if(o1.getQuality() > o2.getQuality()) {
				    		return -1;
				    	} 
				    	else {
				    		return 0;
				    	}
				    }
				});
				
				for(Architecture following: relatedArchitectures) {
					
					comparisons++;
					
					comparedArchitectures.add(following);
					
					conditionsMet = true;
					
					for(ResourceOptions options: following.getBoundResourceOptions()) {
						conditionsMet = conditionsMet && conditionsMet(options, activeResources);
					}
					
					if(conditionsMet) {
						
						unguidedComparisons++;
						
						unguidedComparedArchitectures.add(following);
						
						if(!Double.isNaN(following.getQuality())) {
						
							qualityComparedArchitectures.add(following);
							
							qualityComparisons++;
							
							if(nextNode == null) {
								nextNode = following;
							} else {
								if(nextNode.getQuality() < following.getQuality()) {
									nextNode = following;
								}
							}
						}
					}
				}
				
				if(nextNode != null) {
					processedPath.add(new org.javatuples.Triplet<Architecture, Architecture, String>(current, nextNode, "-" + faultyResource.getName()));
					pathNodes++;
					
					target = nextNode;
				} else {
					processedPath.add(new org.javatuples.Triplet<Architecture, Architecture, String>(current, current, "-" + faultyResource.getName()));
					
					target = null;
				}
				
				if(target != null) {
					addedCosts = ResourceHelper.GetArchitectureDifferences(current, target);
					totalCosts += addedCosts;
				}
				
				addGeneralNumbers(dom, segmentsEle, faultyResource, faultyResourcesOrder, allResources, activeResources, faultyResourceCount, current, false, pathNodes, qualityComparisons, qualityComparedArchitectures, unguidedComparisons, unguidedComparedArchitectures, comparisons, comparedArchitectures, target, totalCosts, true);

				oldCurrent = current;
				current = nextNode;
			}
			
		}
		
		attr = dom.createAttribute("sequenceTag");
		attr.setValue(sequenceTag);
		segmentsEle.setAttributeNode(attr);
	}

	private void processFaultyResources(ARG dargFile, List<Resource> faultyResources, Architecture startingArchitecture, List<org.javatuples.Triplet<Architecture, Architecture, String>> processedPath, org.eclipse.emf.ecore.resource.Resource selectedOpMode, String opmodeName, Job job, IProgressMonitor monitor) {
		
		
				String timeStamp = new SimpleDateFormat("dd-MM_HH-mm-ss").format(new Date());
				
				String outputDirectory = selectedOpMode.getURI().toFileString();
				outputDirectory = outputDirectory.substring(0, outputDirectory.lastIndexOf('\\') + 1) + timeStamp + '\\';
				
				Path filePath = Paths.get(outputDirectory);
		
			    // make sure the directories exist
			    try {
					Files.createDirectories(filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				int nodeCount = getNodeCount(dargFile);
				int edgeCount = getEdgeCount(dargFile);
				
				Document dom;
		
			    // instance of a DocumentBuilderFactory
			    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			    
			    // use factory to get an instance of document builder
			    DocumentBuilder db = null;
				try {
					db = dbf.newDocumentBuilder();
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				}
			    // create instance of DOM
			    dom = db.newDocument();
			    Element rootEle = dom.createElement("results");
			    dom.appendChild(rootEle);
			    
			    Element pathEle = dom.createElement("path");
				rootEle.appendChild(pathEle);
				
				// QADAG
				Element qadagEle = dom.createElement("mode");
				rootEle.appendChild(qadagEle);
				
				Attr attr = dom.createAttribute("name");
				attr.setValue("" + dargFile.getQadag().getName());
				qadagEle.setAttributeNode(attr);
				
				for(Node qadagNode: dargFile.getQadag().getRoot().getNodes()) {
					
					Element qadagNodeEle = dom.createElement("qa");
					qadagEle.appendChild(qadagNodeEle);
					qadagNodeEle.appendChild(dom.createTextNode("" + qadagNode.getName()));
					
					attr = dom.createAttribute("weigth");
					attr.setValue("" + qadagNode.getWeight());
					qadagNodeEle.setAttributeNode(attr);
					
					attr = dom.createAttribute("min");
					attr.setValue("" + qadagNode.getLowerLimit());
					qadagNodeEle.setAttributeNode(attr);
				}
			    		
				// domain density
				String structuralDomainDensityOutput = getStructuralDomainDensityCSV(dargFile);
				String qualityDomainDensityOutput = getQualityDomainDensityCSV(dargFile);
				String architecturesOutput = getArchitecturesCSV(dargFile);
				String architectureQualityOutput = getArchitectureQualitiesCSV(dargFile);
				String architectureRawQualityOutput = getRawArchitectureQualitiesCSV(dargFile);
				String domainResourcesOutput = getDomainResourcesCSV(dargFile);
				
				String suffix = "";
				if(dargFile.getThreshold() > 0) {
					suffix = "_minimized";
				} else {
					suffix = "_full";
				}
				
				try {
					
				    Files.write(Paths.get(outputDirectory + "domaindensity_structural" + suffix + ".csv"), structuralDomainDensityOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				try {
				    Files.write(Paths.get(outputDirectory + "domaindensity_quality" + suffix + ".csv"), qualityDomainDensityOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				try {
				    Files.write(Paths.get(outputDirectory + "domain_absolutes" + suffix + ".csv"), architecturesOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				try {
				    Files.write(Paths.get(outputDirectory + "domain_individual_qualities_raw" + suffix + ".csv"), architectureRawQualityOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				try {
				    Files.write(Paths.get(outputDirectory + "domain_individual_qualities" + suffix + ".csv"), architectureQualityOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				try {
				    Files.write(Paths.get(outputDirectory + "domain_resources" + suffix + ".csv"), domainResourcesOutput.getBytes(StandardCharsets.UTF_8),
				            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (IOException e) {
				    e.printStackTrace();
				}
				
				//process paths
				List<Resource> allResources = new ArrayList<Resource>();
				
				for(Architecture architecture: dargFile.getArchitectures()) {
					
					for(ResourceOptions option: architecture.getBoundResourceOptions()) {
						getAllResources(option, allResources);
					}
				}
				
				setupGeneralNumbers(
						dom, 
						rootEle, 
						allResources, 
						nodeCount, 
						edgeCount, 
						getGraphRadius(dargFile), 
						getGraphDiameter(dargFile),
						getGraphCostCohesion(dargFile),
						getGraphQualityCohesion(dargFile)
						);
				
				// user sequence
				
				ProcessResourceFailure.processFaultSequence(dom, pathEle, allResources, dargFile, faultyResources, startingArchitecture, processedPath, opmodeName, 1);
				
				List<List<List<Resource>>> faultPaths = new ArrayList<List<List<Resource>>>();
				
				generateFaultPaths(allResources, faultyResources, faultPaths);
			
				monitor.done();
				job.setName("Analyzing");
				monitor.beginTask("Analyzing fault paths...", faultPaths.size());
				
				int index = 1;
		        for(List<List<Resource>> faultPathIndex: faultPaths) {
		        	for(List<Resource> faultPath: faultPathIndex) {
						ProcessResourceFailure.processFaultSequence(dom, pathEle, allResources, dargFile, faultPath, startingArchitecture, processedPath, "", index);
						monitor.worked(1);
		        	}
		        	index++;
		        }
		        
					// write to result.xml
		        FileOutputStream stream = null;
				try {
		            Transformer tr = TransformerFactory.newInstance().newTransformer();
		            tr.setOutputProperty(OutputKeys.INDENT, "yes");
		            tr.setOutputProperty(OutputKeys.METHOD, "xml");
		            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		            
		            stream = new FileOutputStream(outputDirectory + "result" + suffix + ".xml");
		            // send DOM to file
		            tr.transform(new DOMSource(dom), 
		                                 new StreamResult(stream));
		
		        } catch (TransformerException te) {
		            System.out.println(te.getMessage());
		        } catch (IOException ioe) {
		            System.out.println(ioe.getMessage());
		        }
				finally {
					if(stream != null) {
						try {
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
				monitor.done();
				
	}
	
	private void generateFaultPaths(List<Resource> allResources, List<Resource> faultyResources,
			List<List<List<Resource>>> faultPaths) {
		
		List<Resource> faultSequence = new ArrayList<Resource>();
		
		for(int i = 0; i < faultyResources.size(); i++) {
			
			List<List<Resource>> indexList = new ArrayList<List<Resource>>();
			
			Resource originalFaultyResource = faultyResources.get(i);
			
			List<Resource> remainingFaults = new ArrayList<Resource>();
			for(int k = i+1; k < faultyResources.size(); k++) {
				remainingFaults.add(faultyResources.get(k));
			}
			
			List<Resource> activeResources = ResourceHelper.minus(ResourceHelper.minus(allResources, faultSequence), remainingFaults);
			activeResources.remove(originalFaultyResource);
			for(int j = 0; j < activeResources.size(); j++) {
				
				Resource faultyResource = activeResources.get(j);
				List<Resource> faultPath = new ArrayList<Resource>(faultSequence);
				faultPath.add(faultyResource);
				for(int k = i+1; k < faultyResources.size(); k++) {
					faultPath.add(faultyResources.get(k));
				}
				boolean exists = false;
				for(List<List<Resource>> existingIndexList: faultPaths) {
					if(existingIndexList.contains(faultPath)) {
						exists = true;
					}
				}
				if(!exists) {
					indexList.add(faultPath);
				}
			}
			
			faultPaths.add(indexList);
			faultSequence.add(originalFaultyResource);
		}
	}

	public static boolean conditionsMet(ResourceOptions options, List<Resource> activeResources) {
		
		if(options.getSubGroups().size() > 0) {
			
			boolean conditionsMet = true;
			
			for(ResourceOptions subOptions: options.getSubGroups()) {
				conditionsMet = conditionsMet && conditionsMet(subOptions, activeResources);
			}
			
			return conditionsMet;
			
		} else {
			
			int containedResources = 0;
			
			for(Resource resource: options.getResources()) {
				for(Resource activeResource: activeResources) {
					if(resource.getName().equals(activeResource.getName())) {
						containedResources++;
					}
				}
			}
			
			if(containedResources < Integer.parseInt(options.getMinElements())) {
				return false;
			}
			
			return true;
		}
	}
	
	public static void getAllResources(ResourceOptions options, List<Resource> allResources) {
		
		if(options.getSubGroups().size() > 0) {
			for(ResourceOptions subOptions: options.getSubGroups()) {
				getAllResources(subOptions, allResources);
			}
		} else {
			for(Resource resource: options.getResources()) {
				boolean contains = false;
				for(Resource allResource: allResources) {
					if(resource.getName().equals(allResource.getName())) {
						contains = true;
						break;
					}
				}
				
				if(!contains) {
					allResources.add(resource);
				}
			}
		}
	}
}
