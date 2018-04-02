package de.tubs.areva.command;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Graph;
import org.eclipse.gef4.layout.algorithms.SpringLayoutAlgorithm;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.nfunk.jep.JEP;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.QualityAssignment;
import de.tubs.areva.emf.model.qadag.CompositeNode;
import de.tubs.areva.emf.model.qadag.Node;
import de.tubs.areva.emf.model.qadag.QADAG;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.ui.module.DARGZestEdge;
import de.tubs.areva.ui.module.DARGZestNode;
import de.tubs.areva.ui.module.QADAGZestNode;
import de.tubs.areva.ui.view.DARGZestView;
import de.tubs.areva.ui.wizard.SelectOperatingModeWizard;
import de.tubs.areva.util.IntervalUtils;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.areva.util.emf.ResourceManager;
import de.uka.ipd.sdq.pcm.designdecision.DecisionSpace;

public class SelectOperatingModeWizardHandler extends AbstractHandler implements IHandler {

	protected static final String ID = ZestProperties.CSS_ID__NE;
	protected static final String LABEL = ZestProperties.LABEL__NE;
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// get selected file
		ResourceSet dargRS = new ResourceSetImpl();
		
		IFile file = null;
		
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
	            .getActivePage().getSelection();
	    if (selection != null & selection instanceof IStructuredSelection) {
	        IStructuredSelection strucSelection = (IStructuredSelection) selection;
	        for (@SuppressWarnings("unchecked")
			Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
	        	file = (IFile)iterator.next();
	        	
	            System.out.println("" + file.getLocationURI());
	            
	            break;
	        }
	    }
		
		// setup and open wizard
		SelectOperatingModeWizard wizard = new SelectOperatingModeWizard(file);
		
		WizardDialog wizardDialog = new WizardDialog(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				wizard);
		
		if(wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
			
			// process wizard input
			setupOperatingMode(wizard);
			
		} else {
			System.out.println("Cancel pressed");
		}
		
		return null;
	}
	
	public static String getNormalizedArchitectureQualitiesCSV(ARG argFile) {
		
		String nl = System.lineSeparator();
		
		String output = "";
		
		int size = argFile.getArchitectures().size();
		
		double[][] table = new double[size][];
		
		for(int i = 0; i < size; i++) {
			table[i] = new double[argFile.getArchitectures().get(0).getQualityassignments().size()];
		}
		
		for(int i = 0; i <  argFile.getArchitectures().size(); i++) {
			
			Architecture architecture = argFile.getArchitectures().get(i);
			
			for(int j = 0; j < architecture.getQualityassignments().size(); j++) {
				
				table[i][j] = architecture.getQualityassignments().get(j).getValue();
			}
		}
		
		output += "Candidate ID";
		
		for(int i = 0; i < argFile.getArchitectures().get(0).getQualityassignments().size(); i++) {
			
			output += ";" + argFile.getArchitectures().get(0).getQualityassignments().get(i).getAttribute();
		}
		
		for(int i = 0; i <  size; i++) {
			
			output += nl + argFile.getArchitectures().get(i).getId();
			
			for(int j = 0; j < argFile.getArchitectures().get(i).getQualityassignments().size(); j++) {
				
				output += ";" + table[i][j];
			}
		}
		
		return output;
	}
	
	private void setupOperatingMode(SelectOperatingModeWizard wizard) {
		
		System.out.println("Operating modes selected. Proceeding with DARG creation.");
		
		ResourceSet operatingModeRS = new ResourceSetImpl();
		
		String outputDirectory = wizard.outputPage.outputDirectory;
		
		ARG argFile = 
				(ARG) operatingModeRS.getResource(wizard.argPage.getArgFile(), true).getContents().get(0);
		
		for(int i = 0; i < wizard.qadagPage.getQadagFiles().size(); i++) {
			

			System.out.println("Creating DARG #" + i);
			
			URI metaQadagURI = wizard.qadagPage.getQadagFiles().get(i);
			String[] dargNameSplit = metaQadagURI.toString().split("/");
			String dargNameEnding = dargNameSplit[dargNameSplit.length-1];
			String[] dargNameSplit2 = dargNameEnding.split("\\.");
			String dargName = dargNameSplit2[0];
			
			int threshold = 0;
			
			if(!wizard.thresholdPage.getInput().isEmpty()) {
				threshold = Integer.parseInt(wizard.thresholdPage.getInput());
			}
			
			ARG dargFile = 
					EcoreUtil.copy(argFile);
			
			QADAG metaQadagFile = 
					(QADAG) operatingModeRS.getResource(metaQadagURI, true).getContents().get(0);
			
			dargFile.setQadag(metaQadagFile);
			dargFile.setName(metaQadagFile.getName());
			
			for(Architecture dargNode: dargFile.getArchitectures()) {
				
				if(dargNode instanceof Architecture) {
					Architecture architecture = (Architecture) dargNode;
				
					generateQadag(dargFile.getQadag(), architecture);
				}
			}
			
			dargFile.setThreshold(threshold);
			
			// create valid edges
			if(threshold != 0) {
				calculateEdges(dargFile, Integer.parseInt(wizard.thresholdPage.getInput()));
			} else {
				calculateEdges(dargFile, Integer.MAX_VALUE);
			}
			
			// save result darg
			ResourceSet dargRS = new ResourceSetImpl();
			org.eclipse.emf.ecore.resource.Resource architectureResource;
			if(threshold != 0) {
				architectureResource = ResourceManager.createAndAddResource(
						
					outputDirectory + "\\" + dargName + "_minimized.darg",
					new String[] {".darg"}, 
					dargRS);
			} else {
				architectureResource = ResourceManager.createAndAddResource(
						wizard.outputPage.outputDirectory + "\\" + dargName + "_full.darg",
						new String[] {".darg"}, 
						dargRS);
			}
			
			architectureResource.getContents().add(dargFile);
			ResourceManager.saveResource(architectureResource);
			
			// if there is a threshold set, also create the full, unminimized darg
			if(threshold != 0) {
				
				dargFile = 
						EcoreUtil.copy(argFile);
				
				dargFile.setQadag(metaQadagFile);
				
				for(Architecture dargNode: dargFile.getArchitectures()) {
					
					if(dargNode instanceof Architecture) {
						Architecture architecture = (Architecture) dargNode;
					
						generateQadag(dargFile.getQadag(), architecture);
					}
				}
				
				dargFile.setThreshold(0);
				
				calculateEdges(dargFile, Integer.MAX_VALUE);
				
				// save result darg
				dargRS = new ResourceSetImpl();
				architectureResource = ResourceManager.createAndAddResource(
						wizard.outputPage.outputDirectory + "\\" + dargName + "_full.darg",
						new String[] {".darg"}, 
						dargRS);
				
				architectureResource.getContents().add(dargFile);
				
				ResourceManager.saveResource(architectureResource);
			}
		}
		
		if(wizard.qadagPage.getQadagFiles().size() == 0) {
			
			String normalizedArchitectureQualities = getNormalizedArchitectureQualitiesCSV(argFile);
			
			try {
				
			    Files.write(Paths.get(outputDirectory + "\\" + "architectures_normalized.csv"), normalizedArchitectureQualities.getBytes(StandardCharsets.UTF_8),
			            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
		
		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			
			try {
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void calculateEdges(ARG dargFile, int threshold) {
		
		for(Architecture arch1: dargFile.getArchitectures()) {
			
			for(Architecture arch2: dargFile.getArchitectures()) {
				
				if(arch1 == arch2) 
					continue;
				
				int difference = ResourceHelper.GetArchitectureDifferences(arch1, arch2);
				
				if(difference <= threshold) {
					arch1.getRelatedArchitectures().add(arch2);
				}
			}
		}
		
		for(Architecture architecture: dargFile.getArchitectures()) {
			if(architecture.getRelatedArchitectures().size() == 0) {
				EcoreUtil.delete(architecture);
			}
		}
		
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

	private double calculateQadag(Node node) {
		
		double result = 0f;
		if(node.getNodes().size() > 0) {
		
			for(Node child: node.getNodes()) {
				calculateQadag(child);
				result += child.getValue() * ((CompositeNode) child).getWeight();
			}
			
			if(result < node.getLowerLimit()) {
				result = Double.NaN;
			}
			
			node.setValue(result);
			
			
		} else {
			result = node.getValue();
			if(node.getValue() < node.getLowerLimit()) {
				result = Double.NaN;
			}
			
			node.setValue(result);
		}
		
		return result;
	}
	
	private void generateQadag(QADAG metaQadag, Architecture architecture) {
		
		
		QADAG qadag = EcoreUtil.copy(metaQadag);
		qadag.setName(architecture.getId());
		qadag.getRoot().setName(architecture.getId());
		
		architecture.setQadag(qadag);
		
		
		for(Node node: qadag.getRoot().getNodes()) {
			
			for(QualityAssignment quality: architecture.getQualityassignments()) {
			
				if(quality.getAttribute().equals(node.getName())) {
					
					node.setValue(quality.getValue());
					
				}
			}
		}
		
		
		architecture.setQuality(calculateQadag(architecture.getQadag().getRoot()));
	}
	
	float normalize(float value, float min, float max) {
		
		float normalized = (value - min) / (max - min);
		
		return normalized;
	}
	
}
