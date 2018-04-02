package de.tubs.forjahn.bachelor.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.QualityAssignment;
import de.tubs.areva.emf.model.opmode.OperatingModeSelection;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.areva.util.emf.ResourceManager;
import de.tubs.forjahn.bachelor.calculations.Centrality;
import de.tubs.forjahn.bachelor.calculations.FaultInjection;
import de.tubs.forjahn.bachelor.calculations.IdealJumpArchitectures;
import de.tubs.forjahn.bachelor.calculations.IdealOpModeOrder;
import de.tubs.forjahn.bachelor.calculations.Numbers;
import de.tubs.forjahn.bachelor.model.cps.CalculationParameterSelection;
import de.tubs.forjahn.bachelor.utils.Permutations;

public class CalculationParameterSelectionHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ResourceSet cpsRS = new ResourceSetImpl();
		
		org.eclipse.emf.ecore.resource.Resource selectedCPS = null;
		
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
                .getActivePage().getSelection();
        if (selection != null & selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelection = (IStructuredSelection) selection;
            for (Iterator<Object> iterator = strucSelection.iterator(); iterator.hasNext();) {
            	IFile file = (IFile)iterator.next();
            	
            	selectedCPS = cpsRS.getResource(org.eclipse.emf.common.util.URI.createURI(file.getLocationURI().toString()), true);
                System.out.println("" + file.getLocationURI());
            }
        }
        
        if(selectedCPS == null) {
        	System.out.println("ERROR: No selection found");
        }
		
        CalculationParameterSelection cpsSelection = (CalculationParameterSelection)selectedCPS.getContents().get(0);
        
        // acquire parameter selection
        List<Resource> faultyResources = cpsSelection.getSelectedResourceFailures();
        List<ARG> domains = cpsSelection.getSelectedDomains();
        
        double c1 = cpsSelection.getQualityWeight();
        double c2 = cpsSelection.getCentralityWeight();
        double c3 = cpsSelection.getResourceWeight();
        
        int threshold = cpsSelection.getDifferenceThreshold();
        
        // setup local experiment directory
        String timeStamp = new SimpleDateFormat("MM-dd_HH-mm-ss").format(new Date());
        String outputDirectory = selectedCPS.getURI().toFileString();
		outputDirectory = outputDirectory.substring(0, outputDirectory.lastIndexOf('\\') + 1) + timeStamp + '\\';
		
		Path filePath = Paths.get(outputDirectory);
		
	    try {
			Files.createDirectories(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    calculateAndPrintNumbers(outputDirectory, "result_degree.xml", domains, faultyResources, c1, c2, c3, 0, threshold, Centrality.DEGREE);
	    calculateAndPrintNumbers(outputDirectory, "result_betweenness.xml", domains, faultyResources, c1, c2, c3, 0, threshold, Centrality.BETWEEN);
	    calculateAndPrintNumbers(outputDirectory, "result_closeness.xml", domains, faultyResources, c1, c2, c3, 0, threshold, Centrality.CLOSENESS);
        
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
	
	public void calculateAndPrintNumbers(String directory, String file, List<ARG> domains, List<Resource> faultyResources, double c1, double c2, double c3, double c4, int threshold, Centrality centrality) {
		
		ResourceSet cpsRS = new ResourceSetImpl();
		
		List<Resource> activeResources = new ArrayList<>();
		
		// acquire active resources
		for(ARG domain: domains) {
			for(Architecture architecture: domain.getArchitectures()) {
				
				for(ResourceOptions option: architecture.getBoundResourceOptions()) {
					getAllResources(option, activeResources);
				}
			}
		}
		
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
	    
	    // weights
	    Attr attr = dom.createAttribute("c1_qualityWeight");
		attr.setValue("" + c1);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("c2_centralityWeight");
		attr.setValue("" + c2);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("c3_resourceWeight");
		attr.setValue("" + c3);
		rootEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("centrality");
		if(centrality == Centrality.DEGREE) {
			attr.setValue("degree");
		} else if(centrality == Centrality.BETWEEN) {
			attr.setValue("betweenness");
		} else {
			attr.setValue("closeness");
		}
		rootEle.setAttributeNode(attr);
		
		Map<Resource,List<ARG>> faultyDomainMatrix = FaultInjection.injectFaults(faultyResources, domains);
	    
		
	    Element pathEle = dom.createElement("steps");
	    rootEle.appendChild(pathEle);
	    
		Element thisPathEle;
		Element e;
		
		int segmentId = -1;
		
		// segment
		
		for(Entry<Resource, List<ARG>> entry: faultyDomainMatrix.entrySet()) {
			
			segmentId++;
			
			if(entry.getKey() != null) {
				
				for(ARG domain: entry.getValue()) {
					
					org.eclipse.emf.ecore.resource.Resource architectureResource = ResourceManager.createAndAddResource(
							directory + "\\" + segmentId + "_" + domain.getName() + ".darg",
							new String[] {".darg"}, 
							cpsRS);
					
					architectureResource.getContents().add(domain);
					
					ResourceManager.saveResource(architectureResource);
				}
			}
			
			thisPathEle = dom.createElement("segment");
			pathEle.appendChild(thisPathEle);
			
			attr = dom.createAttribute("id");
			attr.setValue("" + segmentId);
			thisPathEle.setAttributeNode(attr);
			
			attr = dom.createAttribute("faultyResource");
			
			if(entry.getKey() != null) {
				attr.setValue("" + entry.getKey().getName());
			} else {
				attr.setValue("");
			}
			thisPathEle.setAttributeNode(attr);
			
			Element domainsEle = dom.createElement("domains");
			thisPathEle.appendChild(domainsEle);
			
			for(int i = 0; i < entry.getValue().size(); i++) {
				
				ARG domain = entry.getValue().get(i);
				
				Element domainEle = dom.createElement("domain");
				domainsEle.appendChild(domainEle);
				
				attr = dom.createAttribute("id");
				attr.setValue("" + domain.getName());
				domainEle.setAttributeNode(attr);
				
				attr = dom.createAttribute("size");
				attr.setValue("" + Numbers.getDomainSize(domain));
				domainEle.setAttributeNode(attr);
				
				attr = dom.createAttribute("interconnections");
				attr.setValue("" + Numbers.getDomainInterconnections(domain));
				domainEle.setAttributeNode(attr);
				
				Element architecturesEle = dom.createElement("architectures");
				domainEle.appendChild(architecturesEle);
				
				for(Architecture architecture: domain.getArchitectures()) {
					
					appendArchitectureData(domain, architecture, architecturesEle, dom, c1, c2, c3, centrality);
				}
				
				Element domainDifferencesEle = dom.createElement("domainDifferences");
				domainEle.appendChild(domainDifferencesEle);
				
				for(int j = 0; j < entry.getValue().size(); j++) {
					
					ARG comparedDomain = entry.getValue().get(j);
					
					if(comparedDomain == domain) {
						continue;
					}
					
					Element domainDifferenceEle = dom.createElement("domain");
					domainDifferencesEle.appendChild(domainDifferenceEle);
					
					attr = dom.createAttribute("id");
					attr.setValue("" + comparedDomain.getName());
					domainDifferenceEle.setAttributeNode(attr);
					
					attr = dom.createAttribute("differences");
					attr.setValue("" + IdealOpModeOrder.getDomainOrderDifference(entry.getValue(), threshold));
					domainDifferenceEle.setAttributeNode(attr);
					
					List<Architecture> jumpArchitectures = IdealJumpArchitectures.getJumpArchitectures(domain, comparedDomain);
					
					if(jumpArchitectures.size() > 0) {
					
						Element jumpArchitecturesEle = dom.createElement("jumpArchitectures");
						domainDifferenceEle.appendChild(jumpArchitecturesEle);
						
						Architecture idealJumpArchitecture = null;
						double idealJumpArchitectureValue = Double.MIN_VALUE;
						
						
						for(Architecture architecture: jumpArchitectures) {
							
							appendArchitectureData(comparedDomain, architecture, jumpArchitecturesEle, dom, c1, c2, c3, centrality);
							
							
							double stValue =  (
									(c1 * architecture.getQuality())
									+ (c2 * Numbers.getDegreeCentrality(architecture))
									+ (c3 * architecture.getBoundResources().size())
									);
							
							if(stValue > idealJumpArchitectureValue) {
								idealJumpArchitectureValue = stValue;
								idealJumpArchitecture = architecture;
							}
						}
						
						Element idealJumpArchitecturesEle = dom.createElement("idealJumpArchitectures");
						domainDifferenceEle.appendChild(idealJumpArchitecturesEle);
						
						appendArchitectureData(comparedDomain, idealJumpArchitecture, idealJumpArchitecturesEle, dom, c1, c2, c3, Centrality.DEGREE);
						
					}
				}
			}
			
			Element opmodeOrdersEle = dom.createElement("opmodeOrders");
			thisPathEle.appendChild(opmodeOrdersEle);
			
			List<ARG> idealOrder = null;
			int idealOrderNumber = 0;
			int idealOrderDif = Integer.MAX_VALUE;
			
			int j = 0;
			
			Permutations<ARG> permutationsObj = new Permutations<>();
			
			for(List<ARG> permutation: permutationsObj.permute(entry.getValue())) {
				
				j++;
				
				Element opmodeOrderEle = dom.createElement("order");
				opmodeOrdersEle.appendChild(opmodeOrderEle);
				
				attr = dom.createAttribute("id");
				attr.setValue("" + j);
				opmodeOrderEle.setAttributeNode(attr);
				
				int difference = 0;
				
				for(int i = 0; i < permutation.size(); i++) {
					
					ARG source = permutation.get(i);
					
					Element domainEle = dom.createElement("domain");
					opmodeOrderEle.appendChild(domainEle);
					
					attr = dom.createAttribute("id");
					attr.setValue("" + source.getName());
					domainEle.setAttributeNode(attr);
					
					if(i > 0) {
	
						ARG target = permutation.get(i-1);
					
						int localDifference = IdealOpModeOrder.getDomainPairDifference(source, target);
						
						difference += localDifference;
						
						attr = dom.createAttribute("cost");
						attr.setValue("" + localDifference);
						domainEle.setAttributeNode(attr);
					}
				}
				
				attr = dom.createAttribute("differences");
				attr.setValue("" + difference);
				opmodeOrderEle.setAttributeNode(attr);
				
				if(difference < idealOrderDif) {
					idealOrder = permutation;
					idealOrderDif = difference;
					idealOrderNumber = j;
				}
			}
			
			Element idealOrderEle = dom.createElement("idealOrder");
			thisPathEle.appendChild(idealOrderEle);
			
			attr = dom.createAttribute("id");
			attr.setValue("" + idealOrderNumber);
			idealOrderEle.setAttributeNode(attr);
			
			int difference = 0;
			
			for(int i = 0; i < idealOrder.size(); i++) {
				
				ARG source = idealOrder.get(i);
				
				Element domainEle = dom.createElement("domain");
				idealOrderEle.appendChild(domainEle);
				
				attr = dom.createAttribute("id");
				attr.setValue("" + source.getName());
				domainEle.setAttributeNode(attr);
				
				if(i > 0) {
	
					ARG target = idealOrder.get(i-1);
				
					int localDifference = IdealOpModeOrder.getDomainPairDifference(source, target);
					
					difference += localDifference;
					
					attr = dom.createAttribute("cost");
					attr.setValue("" + localDifference);
					domainEle.setAttributeNode(attr);
				}
			}
			
			attr = dom.createAttribute("differences");
			attr.setValue("" + difference);
			idealOrderEle.setAttributeNode(attr);
			
		}
		
		
		
		try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom), 
                                 new StreamResult(new FileOutputStream(directory + file)));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
	}
	
	private void appendArchitectureData(ARG domain, Architecture architecture, Element architecturesEle, Document dom, double c1, double c2, double c3, Centrality centrality) {
		Attr attr = null;
		Element architectureEle = dom.createElement("architecture");
		architecturesEle.appendChild(architectureEle);
		
		attr = dom.createAttribute("id");
		attr.setValue("" + architecture.getId());
		architectureEle.setAttributeNode(attr);
		
		// quality 
		attr = dom.createAttribute("quality");
		attr.setValue("" + architecture.getQuality());
		architectureEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("weightedQuality");
		attr.setValue("" + (c1 * architecture.getQuality()));
		architectureEle.setAttributeNode(attr);
		
		// centrality
		if(centrality == Centrality.DEGREE) {
			attr = dom.createAttribute("centrality");
			attr.setValue("" + Numbers.getDegreeCentrality(architecture));
			architectureEle.setAttributeNode(attr);
			attr = dom.createAttribute("weightedCentrality");
			attr.setValue("" + (c2 * Numbers.getDegreeCentrality(architecture)));
			architectureEle.setAttributeNode(attr);
			
		} else if(centrality == Centrality.BETWEEN) {
		
			attr = dom.createAttribute("centrality");
			attr.setValue("" + Numbers.getBetweenCentrality(domain, architecture));
			architectureEle.setAttributeNode(attr);
			attr = dom.createAttribute("weightedCentrality");
			attr.setValue("" + (c2 * Numbers.getBetweenCentrality(domain, architecture)));
			architectureEle.setAttributeNode(attr);
			
		} else {
		
			attr = dom.createAttribute("centrality");
			attr.setValue("" + Numbers.getClosenessCentrality(domain, architecture));
			architectureEle.setAttributeNode(attr);
			attr = dom.createAttribute("weightedCentrality");
			attr.setValue("" + (c2 * Numbers.getClosenessCentrality(domain, architecture)));
			architectureEle.setAttributeNode(attr);
		}
		
		// used resources
		attr = dom.createAttribute("usedResourcesCount");
		attr.setValue("" + architecture.getBoundResources().size());
		architectureEle.setAttributeNode(attr);
		
		attr = dom.createAttribute("weightedUsedResourcesCount");
		attr.setValue("" + (c3 * architecture.getBoundResources().size()));
		architectureEle.setAttributeNode(attr);
		
		Element usedResourcesEle = dom.createElement("usedResources");
		architectureEle.appendChild(usedResourcesEle);
		
		for(Resource usedResource: architecture.getBoundResources()) {
			
			Element usedResourceEle = dom.createElement("Resource");
			usedResourcesEle.appendChild(usedResourceEle);
			
			attr = dom.createAttribute("id");
			attr.setValue("" + usedResource.getName());
			usedResourceEle.setAttributeNode(attr);
		}
		
		if(centrality == Centrality.DEGREE) {
		
			attr = dom.createAttribute("staticJumpValue");
			attr.setValue("" + (
					(c1 * architecture.getQuality())
					+ (c2 * Numbers.getDegreeCentrality(architecture))
					+ (c3 * architecture.getBoundResources().size())
					));
			architectureEle.setAttributeNode(attr);
			
		} else if(centrality == Centrality.BETWEEN) {
		
			attr = dom.createAttribute("staticJumpValue");
			attr.setValue("" + (
					(c1 * architecture.getQuality())
					+ (c2 * Numbers.getBetweenCentrality(domain, architecture))
					+ (c3 * architecture.getBoundResources().size())
					));
			architectureEle.setAttributeNode(attr);
			
		} else {
		
			attr = dom.createAttribute("staticJumpValue");
			attr.setValue("" + (
					(c1 * architecture.getQuality())
					+ (c2 * Numbers.getClosenessCentrality(domain, architecture))
					+ (c3 * architecture.getBoundResources().size())
					));
			architectureEle.setAttributeNode(attr);
		
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

}
