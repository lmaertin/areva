package de.tubs.areva.command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Graph;
import org.eclipse.gef4.layout.algorithms.SpringLayoutAlgorithm;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.javatuples.Triplet;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.opmode.OperatingModeSelection;
import de.tubs.areva.emf.model.qadag.QADAG;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.ui.module.DARGZestEdge;
import de.tubs.areva.ui.module.DARGZestNode;
import de.tubs.areva.ui.module.QADAGZestNode;
import de.tubs.areva.ui.view.DARGZestView;
import de.tubs.areva.ui.wizard.SelectOperatingModeWizard;
import de.tubs.areva.ui.wizard.VisualizeWizard;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.areva.util.ZestViewCounter;
import de.tubs.areva.util.emf.ResourceManager;
import de.tubs.areva.xml.XMLStaticCache;

public class ProcessResourceFailureDetailed extends AbstractHandler implements IHandler {

	protected static final String ID = ZestProperties.CSS_ID__NE;
	protected static final String LABEL = ZestProperties.LABEL__NE;
	private static int i;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ResourceSet operatingModeRS = new ResourceSetImpl();
		
		org.eclipse.emf.ecore.resource.Resource selectedOpMode = null;
		IFile file = null;
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getActivePage().getSelection();
        if (selection != null & selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            for (@SuppressWarnings("unchecked")
			Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
            	file = (IFile)iterator.next();
            	
                System.out.println("" + file.getLocationURI().toString().substring(6));
                
                break;
            }
        }
        
        XMLStaticCache.load(file.getLocationURI().toString().substring(6));
        Document resultXml = XMLStaticCache.getDocument();
        
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = null;
        NodeList nl = null;
        Node n = null;
        
        List<String> defaultPath = new ArrayList<String>();
        try {
        	/*
        	expr = xpath.compile("/results/path/segments[1]/segment");
        	nl = (NodeList) expr.evaluate(resultXml, XPathConstants.NODESET);
    		for(int i = 0; i < nl.getLength(); i++) {
    			Node segment = nl.item(i);
    			defaultPath.add(segment.getAttributes().getNamedItem("faultyResource").getNodeValue());
    			System.out.println("Default: " + segment.getAttributes().getNamedItem("faultyResource").getNodeValue());
    		}
    		*/
        	expr = xpath.compile("/results/path/segments[1]");
        	n = (Node) expr.evaluate(resultXml, XPathConstants.NODE);
        	String nodeValue = n.getAttributes().getNamedItem("sequenceTag").getNodeValue();
        	nodeValue = nodeValue.substring(0, nodeValue.length()-1);
        	
        	defaultPath = new ArrayList<String>(Arrays.asList(nodeValue.split(";")));
        	
        	for(String segment: defaultPath) {
        		System.out.println("Default: " + segment);
        	}
        	
        } catch (XPathExpressionException e) {
			e.printStackTrace();
		}
        
        List<String> allResources = new ArrayList<String>();
        allResources.addAll(defaultPath);
        try {
        	expr = xpath.compile("/results/path/segments[@index='1']//segment[@id='0']");
        	nl = (NodeList) expr.evaluate(resultXml, XPathConstants.NODESET);
    		for(int i = 0; i < nl.getLength(); i++) {
    			Node segment = nl.item(i);
    			String resource = segment.getAttributes().getNamedItem("faultyResource").getNodeValue();
    			if(!allResources.contains(resource)) {
    				allResources.add(resource);
    			}
    		}
        } catch (XPathExpressionException e) {
			e.printStackTrace();
		}
        
        List<String> architectureOptions = new ArrayList<String>();
        try {
			expr = xpath.compile("/results/path/segments[1]/segment");
			nl = (NodeList) expr.evaluate(resultXml, XPathConstants.NODESET);
	        for(int i = 0; i < nl.getLength(); i++) {
	        	Node segment = nl.item(i);
	        	expr = xpath.compile("/results/path/segments[1]/segment[" + (i+1) + "]/source[1]");
	        	System.out.println("Parse: /results/path/segments[1]/segment[" + (i+1) + "]/source[1]");
	        	Node sourceNode = (Node) expr.evaluate(resultXml, XPathConstants.NODE);
	        	architectureOptions.add("" + i + ": Architecture " + sourceNode.getAttributes().getNamedItem("id").getNodeValue()
	        			+ " (" + segment.getAttributes().getNamedItem("faultyResource").getNodeValue() + ")" );
	        }
        } catch (XPathExpressionException e) {
			e.printStackTrace();
		}
        
        VisualizeWizard wizard = new VisualizeWizard(allResources, defaultPath, architectureOptions);
		
		WizardDialog wizardDialog = new WizardDialog(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				wizard);
		
		if(wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
			
			setupDARGZest(wizard, resultXml, xpath, file);
			
		} else {
			System.out.println("Cancel pressed");
		}
		
		return null;
	}

	private void setupDARGZest(VisualizeWizard wizard, Document resultXml, XPath xPath, IFile file) {
		int viewMode = wizard.visualizePage.selectedMode;
		Graph zestTree = generateTreeFromXml(viewMode, wizard, resultXml, xPath);
		
		DARGZestView view = null;
		
		try {
			view = (DARGZestView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Mittwochsqadag.dargzestview", ZestViewCounter.get(), IWorkbenchPage.VIEW_ACTIVATE);
			int index = file.getLocationURI().toString().lastIndexOf('/');
			String fileName = file.getLocationURI().toString().substring(index + 1);
			if(viewMode == 1)
				view.changeTitle(fileName + " (Architecture)");
			if(viewMode == 2)
				view.changeTitle(fileName + " (tailored)");
			if(viewMode == 3)
				view.changeTitle(fileName + " (full)");
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		if(view != null) {
			view.setGraph(zestTree);
		}
		
		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			
			try {
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private Graph generateTreeFromXml(int mode, VisualizeWizard wizard, Document resultXml, XPath xPath) {
		
		List<org.eclipse.gef4.graph.Node> nodeList = new ArrayList<>();
		List<Edge> edgeList = new ArrayList<>();
		
		try {
			XPathExpression expr = null;
			
			if(mode == 1) {
				// architecture view
				
				String selection = wizard.visualizePage.architectureSelection;
				int step = Integer.parseInt(selection.split(":")[0]);
				
				expr = xPath.compile("results/path/segments[@index='" + (step+1) + "']/segment[@id='" + step + "']");
				
				NodeList segmentNodes = (NodeList) expr.evaluate(resultXml, XPathConstants.NODESET);
				
				Node sourceNode = segmentNodes.item(0).getChildNodes().item(1);
				String sourceName = sourceNode.getAttributes().getNamedItem("id").getNodeValue();
				float sourceQuality = Float.parseFloat(sourceNode.getAttributes().getNamedItem("quality").getNodeValue());
				
				List<String> boundResources = new ArrayList<>();
				NodeList resourceNodes = sourceNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
				for(int k = 0; k < resourceNodes.getLength(); k++) {
					Node resource = resourceNodes.item(k);
					if (resource.getNodeType() != Node.TEXT_NODE) {
						boundResources.add(resource.getFirstChild().getNodeValue());
					}
				}
				
				org.eclipse.gef4.graph.Node graphicalNode = n(
						sourceName, 
						sourceQuality, 
						boundResources, 
						ZestProperties.LABEL__NE, "Architecture " + sourceName + ": " + sourceQuality, 
						ZestProperties.TOOLTIP__N, "Architecture " + sourceName, 
						ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: green; -fx-border-width: 200;", 
						ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: green;"
						);
				nodeList.add(graphicalNode);
				
				Graph qadagGraph = generateQadag(sourceNode);
				graphicalNode.setNestedGraph(qadagGraph);
				
				for(int i = 0; i < segmentNodes.getLength(); i++) {
					Node segmentNode = segmentNodes.item(i);
					
					if(segmentNode.getNodeType() != Node.TEXT_NODE) {
						
						String faultyResource = segmentNode.getAttributes().getNamedItem("faultyResource").getNodeValue();
						
						Node targetNode = null;
						for(int j = 0; j < segmentNode.getChildNodes().getLength(); j++) {
							Node childNode = segmentNode.getChildNodes().item(j);
							if(childNode.getNodeType() != Node.TEXT_NODE) {
								String nodeName = childNode.getNodeName();
								if(nodeName.equals("target")) {
									targetNode = childNode;
								}
							}
						}
						
						if(targetNode != null) {
							
							String targetName = targetNode.getAttributes().getNamedItem("id").getNodeValue();
							float targetQuality = Float.parseFloat(targetNode.getAttributes().getNamedItem("quality").getNodeValue());
							
							org.eclipse.gef4.graph.Node targetGraphicalNode = n(
									targetName, 
									targetQuality, 
									boundResources, 
									ZestProperties.LABEL__NE, "Architecture " + targetName + ": " + targetQuality, 
									ZestProperties.TOOLTIP__N, "Architecture " + targetName, 
									ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: green; -fx-border-width: 200;", 
									ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: green;"
									);
							nodeList.add(targetGraphicalNode);
							
							qadagGraph = generateQadag(targetNode);
							targetGraphicalNode.setNestedGraph(qadagGraph);
							
							Edge edge = e(graphicalNode, targetGraphicalNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
									ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
							edgeList.add(edge);
						}
					}
				}

			} else if(mode == 2) {
				// tailored view
				
				String sequenceTag = "";
				for(String selection: wizard.visualizePage.pathSelection) {
					sequenceTag += selection + ";";
				}
				
				expr = xPath.compile("results/path/segments[@sequenceTag='" + sequenceTag + "']");
				
				Node segmentsNode = (Node) expr.evaluate(resultXml, XPathConstants.NODE);
				
				NodeList segmentNodes = segmentsNode.getChildNodes();
				
				boolean oldNodeChanged = true;
				String oldFaultyResource = "";
				org.eclipse.gef4.graph.Node oldNode = null;
				int steps = 0;
				for(int i = 0; i < segmentNodes.getLength(); i++) {
					
					Node segmentNode = segmentNodes.item(i);
					if (segmentNode.getNodeType() != Node.TEXT_NODE) {
						org.eclipse.gef4.graph.Node graphicalNode = null;
						
						boolean nodeChanged = Boolean.parseBoolean(segmentNode.getAttributes().getNamedItem("nodeChanged").getNodeValue());
						
						Node sourceNode = segmentNode.getChildNodes().item(1);
						String sourceArchName = sourceNode.getAttributes().getNamedItem("id").getNodeValue();
						double quality = Double.parseDouble(sourceNode.getAttributes().getNamedItem("quality").getNodeValue());
						boolean optimal = Boolean.parseBoolean(sourceNode.getAttributes().getNamedItem("optimal").getNodeValue());
						
						List<String> boundResources = new ArrayList<>();
						NodeList resourceNodes = sourceNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
						for(int j = 0; j < resourceNodes.getLength(); j++) {
							Node resource = resourceNodes.item(j);
							if (resource.getNodeType() != Node.TEXT_NODE) {
								boundResources.add(resource.getFirstChild().getNodeValue());
							}
						}
						
						boolean failed = Boolean.parseBoolean(segmentNode.getAttributes().getNamedItem("searchFailed").getNodeValue());

						String faultyResource = segmentNode.getAttributes().getNamedItem("faultyResource").getNodeValue();
						if(!failed) {
							if(oldNodeChanged) {
								graphicalNode = n(
										sourceArchName, 
										quality, 
										boundResources, 
										ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
										ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName
										);
								nodeList.add(graphicalNode);
							} else {
								graphicalNode = n(
										sourceArchName, 
										quality, 
										boundResources, 
										ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
										ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName, 
										ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: red;"
										);
								nodeList.add(graphicalNode);
							}
							
							if(optimal) {
								ZestProperties.setShapeCssStyle(graphicalNode, "-fx-fill: green;");
							}
							
							
							if(steps == (wizard.visualizePage.pathSelection.size()-1)) {
								
								Node targetNode = null;
								for(int j = 0; j < segmentNode.getChildNodes().getLength(); j++) {
									Node childNode = segmentNode.getChildNodes().item(j);
									if(childNode.getNodeType() != Node.TEXT_NODE) {
										String nodeName = childNode.getNodeName();
										if(nodeName.equals("target")) {
											targetNode = childNode;
										}
									}
								}
								
								if(targetNode != null) {
									
									String targetName = targetNode.getAttributes().getNamedItem("id").getNodeValue();
									float targetQuality = Float.parseFloat(targetNode.getAttributes().getNamedItem("quality").getNodeValue());
									boolean targetOptimal = Boolean.parseBoolean(targetNode.getAttributes().getNamedItem("optimal").getNodeValue());
									
									org.eclipse.gef4.graph.Node targetGraphicalNode = n(
											targetName, 
											targetQuality, 
											boundResources, 
											ZestProperties.LABEL__NE, "(" + (steps+1) + ")" + "Architecture " + targetName + ": " + targetQuality, 
											ZestProperties.TOOLTIP__N, "(" + (steps+1) + ")" + "Architecture " + targetName
											);
									nodeList.add(targetGraphicalNode);
									if(targetOptimal) {
										ZestProperties.setShapeCssStyle(targetGraphicalNode, "-fx-fill: green;");
									}
									
									Graph qadagGraph = generateQadag(targetNode);
									targetGraphicalNode.setNestedGraph(qadagGraph);
									
									Edge edge = e(graphicalNode, targetGraphicalNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
											ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
									edgeList.add(edge);
								} else {
									graphicalNode = n(
											sourceArchName, 
											quality, 
											boundResources, 
											ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
											ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName
											);
									nodeList.add(graphicalNode);
									if(optimal) {
										ZestProperties.setShapeCssStyle(graphicalNode, "-fx-fill: green;");
									}
								}
							}
						} else {
							
							graphicalNode = n(
									sourceArchName, 
									quality, 
									boundResources, 
									ZestProperties.LABEL__NE, "(" + (steps) + ")" + "Architecture " + sourceArchName + ": " + quality, 
									ZestProperties.TOOLTIP__N, "(" + (steps) + ")" + "Architecture " + sourceArchName
									);
							nodeList.add(graphicalNode);
							ZestProperties.setShapeCssStyle(graphicalNode, "-fx-background-color: red; -fx-border-width: 200; -fx-fill: red;");
							ZestProperties.setLabelCssStyle(graphicalNode, "-fx-font-color: red;");
							
							Edge edge = e(graphicalNode, graphicalNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
									ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
							edgeList.add(edge);
						}
						
						// create qadag
						Graph qadagGraph = generateQadag(sourceNode);
						
						graphicalNode.setNestedGraph(qadagGraph);
						
						
						if(steps != 0) {
							/*
							Edge edge = e(oldNode, sourceNode, "-" + faultyResource,
									ZestProperties.LABEL__NE, "-" + faultyResource, 
									ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
									ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
									*/
							Edge edge = e(oldNode, graphicalNode, "-" + oldFaultyResource, ZestProperties.LABEL__NE, "-" + oldFaultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
									ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
							edgeList.add(edge);
							
						}
						
						steps++;
						
						oldNodeChanged = nodeChanged;
						oldNode = graphicalNode;
						oldFaultyResource = faultyResource;
					}
				}
				
			} else {
				
				String sequenceTag = "";
				for(String selection: wizard.visualizePage.pathSelection) {
					sequenceTag += selection + ";";
				}
				
				expr = xPath.compile("results/path/segments[@sequenceTag='" + sequenceTag + "']");
				
				Node segmentsNode = (Node) expr.evaluate(resultXml, XPathConstants.NODE);
				
				NodeList segmentNodes = segmentsNode.getChildNodes();
				
				boolean oldNodeChanged = true;
				org.eclipse.gef4.graph.Node oldNode = null;
				int steps = 0;
				for(int i = 0; i < segmentNodes.getLength(); i++) {
					
					Node segmentNode = segmentNodes.item(i);
					if (segmentNode.getNodeType() != Node.TEXT_NODE) {
						org.eclipse.gef4.graph.Node graphicalNode = null;
						String faultyResource = segmentNode.getAttributes().getNamedItem("faultyResource").getNodeValue();
						boolean nodeChanged = Boolean.parseBoolean(segmentNode.getAttributes().getNamedItem("nodeChanged").getNodeValue());
						steps++;
						Node sourceNode = segmentNode.getChildNodes().item(1);
						String sourceArchName = sourceNode.getAttributes().getNamedItem("id").getNodeValue();
						double quality = Double.parseDouble(sourceNode.getAttributes().getNamedItem("quality").getNodeValue());
						
						List<String> boundResources = new ArrayList<>();
						NodeList resourceNodes = sourceNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
						for(int j = 0; j < resourceNodes.getLength(); j++) {
							Node resource = resourceNodes.item(j);
							if (resource.getNodeType() != Node.TEXT_NODE) {
								boundResources.add(resource.getFirstChild().getNodeValue());
							}
						}
						
						boolean failed = Boolean.parseBoolean(segmentNode.getAttributes().getNamedItem("searchFailed").getNodeValue());
						if(!failed) {
							if(oldNodeChanged) {
								graphicalNode = n(
										sourceArchName, 
										quality, 
										boundResources, 
										ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
										ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName, 
										ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: green; -fx-border-width: 200;", 
										ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: green;"
										);
								ZestProperties.setShapeCssStyle(graphicalNode, "-fx-background-color: green; -fx-border-width: 200; -fx-fill: blue;");
								ZestProperties.setLabelCssStyle(graphicalNode, "-fx-font-color: green;");
								nodeList.add(graphicalNode);
								
								// other comparisons
								if(segmentNode.getChildNodes().getLength() > 10) {
									
									Node comparisonsNode = segmentNode.getChildNodes().item(9);
									Node overallNode = comparisonsNode.getChildNodes().item(1);
									Node unguidedNode = comparisonsNode.getChildNodes().item(3);
									Node qualitiesNode = comparisonsNode.getChildNodes().item(5);
									
									List<String> alreadyPrinted = new ArrayList<>();
									
									for(int j = 0; j < qualitiesNode.getChildNodes().getLength(); j++) {
										Node qualityNode = qualitiesNode.getChildNodes().item(j);
										if(qualityNode.getNodeType() != Node.TEXT_NODE) {
											
											float compQuality = Float.parseFloat(qualityNode.getAttributes().getNamedItem("quality").getNodeValue());
											if(Float.isNaN(compQuality)) 
												continue;
											
											String compName = qualityNode.getAttributes().getNamedItem("id").getNodeValue();
											if(alreadyPrinted.contains(compName))
												continue;
											
											boundResources = new ArrayList<>();
											resourceNodes = qualityNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
											for(int k = 0; k < resourceNodes.getLength(); k++) {
												Node resource = resourceNodes.item(k);
												if (resource.getNodeType() != Node.TEXT_NODE) {
													boundResources.add(resource.getFirstChild().getNodeValue());
												}
											}
											
											alreadyPrinted.add(compName);
											
											org.eclipse.gef4.graph.Node extraNode = n(
													sourceArchName, 
													compQuality, 
													boundResources, 
													ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + compName + ": " + compQuality + " (quality)", 
													ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + compName, 
													ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: grey; -fx-border-width: 200;", 
													ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: grey;"
													);
											
											ZestProperties.setShapeCssStyle(extraNode, "-fx-background-color: grey; -fx-border-width: 200; -fx-fill: grey;");
											ZestProperties.setLabelCssStyle(extraNode, "-fx-font-color: grey;");
											nodeList.add(extraNode);
											
											Graph qadagGraph = generateQadag(qualityNode);
											
											extraNode.setNestedGraph(qadagGraph);
											
											Edge edge = e(graphicalNode, extraNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
													ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
											edgeList.add(edge);
										}
									}
									
									for(int j = 0; j < unguidedNode.getChildNodes().getLength(); j++) {
										Node qualityNode = unguidedNode.getChildNodes().item(j);
										if(qualityNode.getNodeType() != Node.TEXT_NODE) {
											
											float compQuality = Float.parseFloat(qualityNode.getAttributes().getNamedItem("quality").getNodeValue());
											if(Float.isNaN(compQuality)) 
												continue;
											
											String compName = qualityNode.getAttributes().getNamedItem("id").getNodeValue();
											if(alreadyPrinted.contains(compName))
												continue;
											
											alreadyPrinted.add(compName);
											
											boundResources = new ArrayList<>();
											resourceNodes = qualityNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
											for(int k = 0; k < resourceNodes.getLength(); k++) {
												Node resource = resourceNodes.item(k);
												if (resource.getNodeType() != Node.TEXT_NODE) {
													boundResources.add(resource.getFirstChild().getNodeValue());
												}
											}
											
											org.eclipse.gef4.graph.Node extraNode = n(
													sourceArchName, 
													compQuality, 
													boundResources, 
													ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + compName + ": " + compQuality + " (unguided)", 
													ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + compName, 
													ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: grey; -fx-border-width: 200;", 
													ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: grey;"
													);
											
											ZestProperties.setShapeCssStyle(extraNode, "-fx-background-color: grey; -fx-border-width: 200; -fx-fill: grey;");
											ZestProperties.setLabelCssStyle(extraNode, "-fx-font-color: grey;");
											nodeList.add(extraNode);
											
											Graph qadagGraph = generateQadag(qualityNode);
											
											extraNode.setNestedGraph(qadagGraph);
											
											Edge edge = e(graphicalNode, extraNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
													ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
											edgeList.add(edge);
										}
									}
									
									for(int j = 0; j < overallNode.getChildNodes().getLength(); j++) {
										Node qualityNode = overallNode.getChildNodes().item(j);
										if(qualityNode.getNodeType() != Node.TEXT_NODE) {
											
											float compQuality = Float.parseFloat(qualityNode.getAttributes().getNamedItem("quality").getNodeValue());
											if(Float.isNaN(compQuality)) 
												continue;
											
											String compName = qualityNode.getAttributes().getNamedItem("id").getNodeValue();
											if(alreadyPrinted.contains(compName))
												continue;
											
											alreadyPrinted.add(compName);
											
											boundResources = new ArrayList<>();
											resourceNodes = qualityNode.getChildNodes().item(1).getChildNodes().item(1).getChildNodes();
											for(int k = 0; k < resourceNodes.getLength(); k++) {
												Node resource = resourceNodes.item(k);
												if (resource.getNodeType() != Node.TEXT_NODE) {
													boundResources.add(resource.getFirstChild().getNodeValue());
												}
											}
											
											org.eclipse.gef4.graph.Node extraNode = n(
													sourceArchName, 
													compQuality, 
													boundResources, 
													ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + compName + ": " + compQuality + " (overall)", 
													ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + compName, 
													ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: grey; -fx-border-width: 200;", 
													ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: grey;"
													);
											
											ZestProperties.setShapeCssStyle(extraNode, "-fx-background-color: grey; -fx-border-width: 200; -fx-fill: grey;");
											ZestProperties.setLabelCssStyle(extraNode, "-fx-font-color: grey;");
											nodeList.add(extraNode);
											
											Graph qadagGraph = generateQadag(qualityNode);
											
											extraNode.setNestedGraph(qadagGraph);
											
											Edge edge = e(graphicalNode, extraNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
													ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
											edgeList.add(edge);
										}
									}
								}
							} else {
								graphicalNode = n(
										sourceArchName, 
										quality, 
										boundResources, 
										ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
										ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName, 
										ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: red; -fx-border-width: 200;", 
										ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: red;"
										);
								nodeList.add(graphicalNode);
							}
							
							
						} else {
							
							graphicalNode = n(
									sourceArchName, 
									quality, 
									boundResources, 
									ZestProperties.LABEL__NE, "(" + steps + ")" + "Architecture " + sourceArchName + ": " + quality, 
									ZestProperties.TOOLTIP__N, "(" + steps + ")" + "Architecture " + sourceArchName, 
									ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: red; -fx-border-width: 200;", 
									ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: red;"
									);
							nodeList.add(graphicalNode);
							ZestProperties.setShapeCssStyle(graphicalNode, "-fx-background-color: red; -fx-border-width: 200; -fx-fill: red;");
							ZestProperties.setLabelCssStyle(graphicalNode, "-fx-font-color: red;");
						}
						
						// create qadag
						Graph qadagGraph = generateQadag(sourceNode);
						
						graphicalNode.setNestedGraph(qadagGraph);
						
						if(steps != 0) {
							Edge edge = e(oldNode, graphicalNode, "-" + faultyResource, ZestProperties.LABEL__NE, "-" + faultyResource, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
									ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
							edgeList.add(edge);
							
						}
						
						oldNodeChanged = nodeChanged;
						oldNode = graphicalNode;
					}
				}
			}
		} catch(Throwable e) {
			e.printStackTrace();
		}
		HashMap<String, Object> attrs = new HashMap<>();
		attrs.put(ZestProperties.LAYOUT_ALGORITHM__G, new SpringLayoutAlgorithm());
		
		return new Graph(attrs, nodeList, edgeList); 
	}

	private Graph generateQadag(Node sourceNode) {
		
		List<org.eclipse.gef4.graph.Node> nodeList = new ArrayList<>();
		List<org.eclipse.gef4.graph.Edge> edgeList = new ArrayList<>();
		
		String name = "Architecture " + sourceNode.getAttributes().getNamedItem("id").getNodeValue();
		double quality = Double.parseDouble(sourceNode.getAttributes().getNamedItem("quality").getNodeValue());
		
		NodeList qualities = sourceNode.getChildNodes().item(3).getChildNodes();
		
		org.eclipse.gef4.graph.Node rootNode = nQ(name, quality, 1,
				ZestProperties.LABEL__NE, name + ": " + quality, 
				ZestProperties.TOOLTIP__N, name, 
				ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: green; -fx-border-width: 200;", 
				ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: green;");
		
		nodeList.add(rootNode);
		
		int qualitiesCount = qualities.getLength() / 2;
		
		for(int i = 0; i < qualities.getLength(); i++) {
			
			Node qualityNode = qualities.item(i);
		
			if (qualityNode.getNodeType() != Node.TEXT_NODE) {
				name = qualityNode.getAttributes().getNamedItem("name").getNodeValue();
				quality = Double.parseDouble(qualityNode.getFirstChild().getNodeValue());
				double weight = 1d/qualitiesCount;
				
				org.eclipse.gef4.graph.Node graphicalNode = nQ(name, quality, 1,
						ZestProperties.LABEL__NE, name + ": " + quality, 
						ZestProperties.TOOLTIP__N, name, 
						ZestProperties.SHAPE_CSS_STYLE__N, "-fx-border-color: green; -fx-border-width: 200;", 
						ZestProperties.LABEL_CSS_STYLE__NE, "-fx-font-color: green;");
				
				nodeList.add(graphicalNode);
				
				Edge edge = e(graphicalNode, rootNode, "" + weight, 
						ZestProperties.LABEL__NE, "" + weight, ZestProperties.TARGET_DECORATION__E, new javafx.scene.shape.Polygon(0, 0, 10, 3, 10, -3),
						ZestProperties.TARGET_DECORATION_CSS_STYLE__E, "-fx-fill: green;");
				
				edgeList.add(edge);
			}
		}
		
		HashMap<String, Object> attrs = new HashMap<>();
		attrs.put(ZestProperties.LAYOUT_ALGORITHM__G, new SpringLayoutAlgorithm());
		
		return new Graph(attrs, nodeList, edgeList); 
	}
	
	protected static org.eclipse.gef4.graph.Node n(String name, double quality, List<String> boundResources, Object... attr) {
		
		Map<String, Object> attrs = new HashMap<>();
		
		String id = genId();
		attrs.put(ID, id);
		attrs.put(LABEL, id);
		for (int i = 0; i < attr.length; i += 2) {
			attrs.put(attr[i].toString(), attr[i + 1]);
		}
		DARGZestNode zestNode = new DARGZestNode(attrs);
		zestNode.setId(name);
		zestNode.setQuality(quality);
		zestNode.setBoundResources(boundResources);
		
		return zestNode;
	}
	
	protected static org.eclipse.gef4.graph.Node nQ(String name, double quality, double weight, Object... attr) {
		
		Map<String, Object> attrs = new HashMap<>();
		
		String id = genId();
		attrs.put(ID, id);
		attrs.put(LABEL, id);
		for (int i = 0; i < attr.length; i += 2) {
			attrs.put(attr[i].toString(), attr[i + 1]);
		}
		QADAGZestNode zestNode = new QADAGZestNode(name, quality, weight, attrs);
		
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
