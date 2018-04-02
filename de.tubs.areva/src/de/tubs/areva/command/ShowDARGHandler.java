package de.tubs.areva.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Graph;
import org.eclipse.gef4.layout.algorithms.SpringLayoutAlgorithm;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.javatuples.Triplet;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public class ShowDARGHandler extends AbstractHandler implements IHandler {
	private static int i;
	private int id = 0;
	protected static final String ID = ZestProperties.CSS_ID__NE;
	protected static final String LABEL = ZestProperties.LABEL__NE;
	
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ResourceSet dargRS = new ResourceSetImpl();
		
		org.eclipse.emf.ecore.resource.Resource selectedDARG = null;
		IFile file = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getActivePage().getSelection();
        if (selection != null & selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            for (Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
            	file = (IFile)iterator.next();
            	
            	selectedDARG = dargRS.getResource(org.eclipse.emf.common.util.URI.createURI(file.getLocationURI().toString()), true);
                System.out.println("" + file.getLocationURI());
                break;
            }
        }
        
        if(selectedDARG == null) {
        	System.out.println("ERROR: No selection found");
        }
        
        ARG arg = (ARG) selectedDARG.getContents().get(0);
		
		
		
		// generate zest tree
		
		Graph dargZestTree = generateTreeFromModel(arg);
		
		DARGZestView view = null;
		
		try {
			view = (DARGZestView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Mittwochsqadag.dargzestview", ZestViewCounter.get(), IWorkbenchPage.VIEW_ACTIVATE);
			int index = file.getLocationURI().toString().lastIndexOf('/');
			String fileName = file.getLocationURI().toString().substring(index + 1);
			view.changeTitle(fileName);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(view != null) {
			view.setGraph(dargZestTree);
		}
		
		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			
			try {
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	
	// general numbers text file
	private void addGeneralNumbers(
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
			int totalCosts
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
		
		attr = dom.createAttribute("passedNodes");
		attr.setValue("" + pathNodes);
		thisPathEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("totalCosts");
		attr.setValue("" + totalCosts);
		thisPathEle.setAttributeNode(attr);
		
		// source
		Element sourceEle = dom.createElement("source");
		thisPathEle.appendChild(sourceEle);
		
		attr = dom.createAttribute("id");
		attr.setValue("" + current.getId());
		sourceEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("quality");
		attr.setValue("" + current.getQuality());
		sourceEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("resourceCount");
		attr.setValue("" + current.getBoundResources().size());
		sourceEle.setAttributeNode(attr);
		
		Element usedResourcesEle = dom.createElement("usedResources");
		sourceEle.appendChild(usedResourcesEle);
		
		attr = dom.createAttribute("count");
		attr.setValue("" + current.getBoundResources().size());
		usedResourcesEle.setAttributeNode(attr);
		
		for(Resource resource: current.getBoundResources()) {
			e = dom.createElement("resource");
			e.appendChild(dom.createTextNode("" + resource.getName()));
			usedResourcesEle.appendChild(e);
		}
		
		// target
		if(nodeChanged) {
			
			Element targetEle = dom.createElement("target");
			thisPathEle.appendChild(targetEle);
			
			attr = dom.createAttribute("id");
		
			attr.setValue("" + target.getId());
			targetEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("quality");
			attr.setValue("" + target.getQuality());
			targetEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("resourceCount");
			attr.setValue("" + target.getBoundResources().size());
			targetEle.setAttributeNode(attr);
			
			usedResourcesEle = dom.createElement("usedResources");
			targetEle.appendChild(usedResourcesEle);
			
			attr = dom.createAttribute("count");
			attr.setValue("" + target.getBoundResources().size());
			usedResourcesEle.setAttributeNode(attr);
			
			for(Resource resource: target.getBoundResources()) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				usedResourcesEle.appendChild(e);
			}
		}
		
		// faultyResource
		
		e = dom.createElement("faultyResource");
		e.appendChild(dom.createTextNode("" + faultyResource.getName()));
		thisPathEle.appendChild(e);
		
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
			
			attr = dom.createAttribute("id");
			attr.setValue("" + architecture.getId());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("quality");
			attr.setValue("" + architecture.getQuality());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("resourceCount");
			attr.setValue("" + architecture.getBoundResources().size());
			architectureEle.setAttributeNode(attr);
			
			usedResourcesEle = dom.createElement("usedResources");
			architectureEle.appendChild(usedResourcesEle);
			
			attr = dom.createAttribute("count");
			attr.setValue("" + architecture.getBoundResources().size());
			usedResourcesEle.setAttributeNode(attr);
			
			for(Resource resource: architecture.getBoundResources()) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				usedResourcesEle.appendChild(e);
			}
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
			
			attr = dom.createAttribute("id");
			attr.setValue("" + architecture.getId());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("quality");
			attr.setValue("" + architecture.getQuality());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("resourceCount");
			attr.setValue("" + architecture.getBoundResources().size());
			architectureEle.setAttributeNode(attr);
			
			usedResourcesEle = dom.createElement("usedResources");
			architectureEle.appendChild(usedResourcesEle);
			
			for(Resource resource: architecture.getBoundResources()) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				usedResourcesEle.appendChild(e);
			}
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
			
			attr = dom.createAttribute("id");
			attr.setValue("" + architecture.getId());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("quality");
			attr.setValue("" + architecture.getQuality());
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("cost");
			attr.setValue("" + ResourceHelper.GetArchitectureDifferences(current, architecture));
			architectureEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("resourceCount");
			attr.setValue("" + architecture.getBoundResources().size());
			architectureEle.setAttributeNode(attr);
			
			usedResourcesEle = dom.createElement("usedResources");
			architectureEle.appendChild(usedResourcesEle);
			
			for(Resource resource: architecture.getBoundResources()) {
				e = dom.createElement("resource");
				e.appendChild(dom.createTextNode("" + resource.getName()));
				usedResourcesEle.appendChild(e);
			}
		}
		
		
	}
	
	private void setupGeneralNumbers(
			Document dom,
			Element rootEle,
			List<Resource> allResources, 
			int nodeCount,
			int edgeCount
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
	}
	
	private int getNodeCount(ARG dargFile) {
		
int 	size = 0;
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				size++;
			}
		}
		
		return size;
	}
	
	private int getEdgeCount(ARG dargFile) {
		
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
	
	// efficiency textfile (path node count)
	private String addEfficiency(String efficiencyString, int pathNodes, int qualityComparisons) {
		
		String output = efficiencyString;
		String nl = System.lineSeparator();
		
		output += nl + nl;
		output += "Nodes in Path: " + pathNodes + nl;
		output += "Sum of necessary Quality Comparisons: " + qualityComparisons;
		
		return output;
		
	}
	
	// domain density
	private String getStructuralDomainDensityCSV(ARG dargFile) {
		
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
	
	private String getQualityDomainDensityCSV(ARG dargFile) {
		
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
	
	private int getMinValue(ResourceOptions options) {
		
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

	private void processFaultyResources(ARG dargFile, List<Resource> faultyResources, Architecture startingArchitecture, List<org.javatuples.Triplet<Architecture, Architecture, String>> processedPath, org.eclipse.emf.ecore.resource.Resource selectedOpMode) {
		String timeStamp = new SimpleDateFormat("dd-MM_HH-mm-ss").format(new Date());
		
		
		String outputDirectory = selectedOpMode.getURI().toFileString();
		outputDirectory = outputDirectory.substring(0, outputDirectory.lastIndexOf('\\') + 1) + timeStamp + '\\';
		
		List<Resource> faultyResourcesOrder = new ArrayList<Resource>();
		
		Path filePath = Paths.get(outputDirectory);

	    // make sure the directories exist
	    try {
			Files.createDirectories(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    // create instance of DOM
	    dom = db.newDocument();
	    Element rootEle = dom.createElement("results");
	    dom.appendChild(rootEle);
	    
	    Element pathEle = dom.createElement("path");
		rootEle.appendChild(pathEle);
	    		
		// domain density
		String structuralDomainDensityOutput = getStructuralDomainDensityCSV(dargFile);
		String qualityDomainDensityOutput = getQualityDomainDensityCSV(dargFile);
		
		try {
		    Files.write(Paths.get(outputDirectory + "domaindensity_structural.csv"), structuralDomainDensityOutput.getBytes(StandardCharsets.UTF_8),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try {
		    Files.write(Paths.get(outputDirectory + "domaindensity_quality.csv"), qualityDomainDensityOutput.getBytes(StandardCharsets.UTF_8),
		            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		// efficiency
		String efficiencyOutput = "";
		int pathNodes = 1;
		int qualityComparisons = 0;
		int comparisons = 0;
		int unguidedComparisons = 0;
		int totalCosts = 0;
		
		Architecture start = startingArchitecture;
		List<Resource> activeResources = new ArrayList<Resource>();
		List<Resource> allResources = new ArrayList<Resource>();
		
		
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			
			for(ResourceOptions option: architecture.getBoundResourceOptions()) {
				getAllResources(option, activeResources);
			}
			
			architecture.setHidden(true);
		}
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			
			for(ResourceOptions option: architecture.getBoundResourceOptions()) {
				getAllResources(option, allResources);
			}
		}
		
		setupGeneralNumbers(dom, rootEle, allResources, nodeCount, edgeCount);
		
		Architecture current = start;
		Architecture oldCurrent = null;
		int faultyResourceCount = 0;
		
		for(Resource faultyResource: faultyResources) {
			faultyResourceCount++;
			faultyResourcesOrder.add(faultyResource);
			if(current == null) {
				if(oldCurrent != null) {
					oldCurrent.setMarked(-1f);
					//processedPath.add(new org.javatuples.Triplet<Architecture, Architecture, String>(oldCurrent, oldCurrent, "-" + faulty));
					
				}
				continue;
			}
			
			
			current.setHidden(false);
			
			Architecture nextNode = null;
			if(faultyResource != null) {
				System.out.println("Processing fault: " + faultyResource.getName());
			} else {
				System.out.println("Resource not found! (" + faultyResource.getName() + ")");
			}
			
			activeResources.remove(faultyResource);
			
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
				addGeneralNumbers(dom, pathEle, faultyResource, faultyResourcesOrder, allResources, activeResources, faultyResourceCount, current, true, pathNodes, qualityComparisons, qualityComparedArchitectures, unguidedComparisons, unguidedComparedArchitectures, comparisons, comparedArchitectures, current, totalCosts);
				continue;
			}
			
			for(Architecture following: current.getRelatedArchitectures()) {
				
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
				totalCosts += ResourceHelper.GetArchitectureDifferences(current, target);
			}
			
			efficiencyOutput += addEfficiency(efficiencyOutput, pathNodes, qualityComparisons);
			
			addGeneralNumbers(dom, pathEle, faultyResource, faultyResourcesOrder, allResources, activeResources, faultyResourceCount, current, false, pathNodes, qualityComparisons, qualityComparedArchitectures, unguidedComparisons, unguidedComparedArchitectures, comparisons, comparedArchitectures, target, totalCosts);
			
			oldCurrent = current;
			current = nextNode;
			
			
		}
		
		try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom), 
                                 new StreamResult(new FileOutputStream(outputDirectory + "result.xml")));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
		
		if(current != null) {
			current.setHidden(false);
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
	
	private void getAllResources(ResourceOptions options, List<Resource> allResources) {
		
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
	
	private Graph generateTreeFromModel(ARG archNodes) {
		List<org.eclipse.gef4.graph.Node> nodes = new ArrayList<>();
		List<org.eclipse.gef4.graph.Edge> edges = new ArrayList<>();
		HashMap<Architecture, org.eclipse.gef4.graph.Node> archToNode = new HashMap<>();
		
		// draw nodes
		for(Architecture architecture: archNodes.getArchitectures()) {
			
			if(!Double.isNaN(architecture.getQuality())) {
				
				org.eclipse.gef4.graph.Node newNode = null;
				DecimalFormat df = new DecimalFormat("0.000");
				newNode = n(architecture, ZestProperties.LABEL__NE, "Architecture " + architecture.getId() + ": " + df.format(architecture.getQuality()), ZestProperties.TOOLTIP__N, "Architecture " + architecture.getId());
				
				if(architecture.getQadag() != null) {
					Graph qadag = createSimpleQadagGraphFromModel(architecture.getQadag());
					newNode.setNestedGraph(qadag);
				}
				
				nodes.add(newNode);
				
				archToNode.put(architecture, newNode);
			}
		}
		
		//draw edges
		for(Architecture architecture: archNodes.getArchitectures()) {
				
			for(Architecture connectedArchitecture: architecture.getRelatedArchitectures()) {
				
				if(!Double.isNaN(connectedArchitecture.getQuality())) {

					
					Edge hiddenEdge = e(archToNode.get(architecture), archToNode.get(connectedArchitecture), "",
							ZestProperties.LABEL__NE,
							"+" + ResourceHelper.minus(connectedArchitecture.getBoundResources(), architecture.getBoundResources()).size() + ", -"
							+ ResourceHelper.minus(architecture.getBoundResources(), connectedArchitecture.getBoundResources()).size(), 
							
							ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
							ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: red;");
					
					
					
					edges.add(hiddenEdge);
					
					
				}
			}
			
		}
		
		HashMap<String, Object> attrs = new HashMap<>();
		attrs.put(ZestProperties.LAYOUT_ALGORITHM__G, new SpringLayoutAlgorithm());
		
		return new Graph(attrs, nodes, edges);
	}
	
	private Graph createSimpleQadagGraphFromModel(QADAG qadagFile) {
		
		List<org.eclipse.gef4.graph.Node> nodes = new ArrayList<>();
		List<org.eclipse.gef4.graph.Edge> edges = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.000");
		
		org.eclipse.gef4.graph.Node rootNode = nQ(qadagFile.getRoot(), ZestProperties.LABEL__NE, "" + qadagFile.getRoot().getName() + ": " + df.format(qadagFile.getRoot().getValue()), ZestProperties.TOOLTIP__N, "Node " + qadagFile.getRoot().getName());
		
		nodes.add(rootNode);
		
		for(Node qadagNode: qadagFile.getRoot().getNodes()) {
			
			org.eclipse.gef4.graph.Node node = nQ(qadagNode, ZestProperties.LABEL__NE, "" + qadagNode.getName() + ": " + df.format(qadagNode.getValue()), ZestProperties.TOOLTIP__N, "Node " + qadagNode.getName());
			
			nodes.add(node);
			edges.add(e(node, rootNode, "", ZestProperties.LABEL__NE, "" + qadagNode.getWeight()));
		}
		
		HashMap<String, Object> attrs = new HashMap<>();
		
		attrs.put(ZestProperties.LAYOUT_ALGORITHM__G, new SpringLayoutAlgorithm());
		return new Graph(attrs, nodes, edges);
	}
	
	protected static org.eclipse.gef4.graph.Node n(Architecture architecture, Object... attr) {
		
		Map<String, Object> attrs = new HashMap<>();
		
		String id = genId();
		attrs.put(ID, id);
		attrs.put(LABEL, id);
		for (int i = 0; i < attr.length; i += 2) {
			attrs.put(attr[i].toString(), attr[i + 1]);
		}
		DARGZestNode zestNode = new DARGZestNode(attrs);
		zestNode.setQuality(architecture.getQuality());
		zestNode.setId(architecture.getId());
		
		List<String> resources = new ArrayList<>();
		for(Resource resource: architecture.getBoundResources()) {
			resources.add(resource.getName());
		}
		zestNode.setBoundResources(resources);
		
		return zestNode;
	}
	
	protected static org.eclipse.gef4.graph.Node nQ(Node node, Object... attr) {
		
		Map<String, Object> attrs = new HashMap<>();
		
		String id = genId();
		attrs.put(ID, id);
		attrs.put(LABEL, id);
		for (int i = 0; i < attr.length; i += 2) {
			attrs.put(attr[i].toString(), attr[i + 1]);
		}
		QADAGZestNode zestNode = new QADAGZestNode(node.getName(), node.getValue(), node.getWeight(), attrs);
		
		return zestNode;
	}
	
	protected static Edge e(org.eclipse.gef4.graph.Node n,
		org.eclipse.gef4.graph.Node m, String label, Object... attr) {
	
		Map<String, Object> attrs = new HashMap<>();
		
		String id = genId();
		attrs.put(ID, id);
		attrs.put(LABEL, id);
		for (int i = 0; i < attr.length; i += 2) {
			attrs.put(attr[i].toString(), attr[i + 1]);
		}
		
		
		if((n instanceof DARGZestNode) && (m instanceof DARGZestNode)) {
			
			DARGZestEdge edge = new DARGZestEdge(attrs,(DARGZestNode)n,(DARGZestNode)m);
			
			return edge;
		} 
		
		Edge edge = new Edge(attrs, n, m);
		
		return edge;
	}
	
	private static String genId() {
		i++;
		return "" + i;
	}
}
