package de.tubs.areva.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef4.zest.fx.ZestProperties;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.BasicComponent;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.QualityAssignment;
import de.tubs.areva.emf.model.qadag.CompositeNode;
import de.tubs.areva.emf.model.qadag.QADAG;
import de.tubs.areva.emf.model.qadag.QadagFactory;
import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.ui.wizard.CreateDARGWizard;
import de.tubs.areva.util.CSVReader;
import de.tubs.areva.util.emf.ResourceManager;
import de.uka.ipd.sdq.pcm.designdecision.DecisionSpace;
import de.uka.ipd.sdq.pcm.designdecision.DegreeOfFreedomInstance;
import de.uka.ipd.sdq.pcm.designdecision.specific.AssembledComponentDegree;

public class OpenARGWizardHandler extends AbstractHandler implements IHandler {

	protected static final String ID = ZestProperties.CSS_ID__NE;
	protected static final String LABEL = ZestProperties.LABEL__NE;
	private double initQualityValue = -1.0; //qualities of invalid candidates are denoted by specific value
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		CreateDARGWizard wizard = new CreateDARGWizard();

		WizardDialog wizardDialog = new WizardDialog(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell(),
				wizard);
		
		if(wizardDialog.open() == Window.OK) {
			
			System.out.println("Ok pressed");
			
			setupExampleDARGZest(wizard);
			
		} else {
			System.out.println("Cancel pressed");
		}
		
		return null;
	}
	
	private HashMap<String, HashMap<String, String>> getCSVAllocation(String[][] candidatesFile) {
		
		HashMap<String, HashMap<String, String>> archs = new HashMap<>();
		
		for(int j = 1; j < candidatesFile.length; j++) {
			
			HashMap<String, String> changedComponents = new HashMap<>();
			
			for(int i = 0; i < (candidatesFile[0].length-1); i++) {
				
				if(candidatesFile[0][i].split(":")[0].equals("AssembledComponentDegreeImpl")) {
					
					String key = candidatesFile[0][i].split(":")[1];
					String value = candidatesFile[j][i].split("\\(")[0].trim();
					
					changedComponents.put(key, value);
				}
				
			}

			int keyIndex = candidatesFile[0].length-1;
			String key = candidatesFile[j][keyIndex];
			
			archs.put(key, changedComponents);
		}
		/*
		for(Map.Entry<String, HashMap<String, String>> qualityToArchs: archs.entrySet()) {
			System.out.println("Architecture: " + qualityToArchs.getKey());
			for(Map.Entry<String, String> archQuality: qualityToArchs.getValue().entrySet()) {
				System.out.println("" + archQuality.getKey() + ": " + archQuality.getValue());
			}
		}
		*/
		return archs;
		
	}
	
	private HashMap<String, HashMap<String, String>> getCSVQualities(String[][] candidatesFile) {
		
		HashMap<String, HashMap<String, String>> archs = new HashMap<>();
		
		for(int i = 0; i < (candidatesFile[0].length-1); i++) {
			
			if(!candidatesFile[0][i].split(":")[0].equals("AssembledComponentDegreeImpl")) {
				
				String qualityName = candidatesFile[0][i].split(":")[0];
				
				if(qualityName.charAt(0) == '-') {
					qualityName = qualityName.substring(1);
				}
				
				HashMap<String, String> archQuality = new HashMap<>();
				
				for(int j = 1; j < candidatesFile.length; j++) {
					int keyIndex = candidatesFile[0].length-1;
					String key = candidatesFile[j][keyIndex];
					String value = candidatesFile[j][i];
					archQuality.put(key, value);
				}
				
				archs.put(qualityName, archQuality);
			}
			
		}
		
		for(Map.Entry<String, HashMap<String, String>> qualityToArchs: archs.entrySet()) {
			System.out.println("Quality: " + qualityToArchs.getKey());
			for(Map.Entry<String, String> archQuality: qualityToArchs.getValue().entrySet()) {
				System.out.println("" + archQuality.getKey() + ": " + archQuality.getValue());
			}
		}
		
		return archs;
	}
	
	private List<String> getOptimalCandidates(String[][] candidatesFile) {
		
		List<String> optimals = new ArrayList<String>();
		
		for(int i = 1; i < candidatesFile.length; i++) {
			optimals.add(candidatesFile[i][candidatesFile[0].length-1]);
		}
		
		return optimals;
	}
	
	private HashMap<String, double[]> getMinMaxQualities(String[][] candidatesFile) {
		
		HashMap<String, double[]> qualities = new HashMap<>();
		
		for(int i = 0; i < (candidatesFile[0].length-1); i++) {
			
			if(!candidatesFile[0][i].split(":")[0].equals("AssembledComponentDegreeImpl")) {
				
				double min = Double.MAX_VALUE;
				double max = Double.MIN_VALUE;
				boolean inverse = false;
				
				double[] minMax = new double[2];
				
				String qualityName = candidatesFile[0][i].split(":")[0];
				
				if(qualityName.charAt(0) == '-') {  //minus as prefix represents minimalization
					qualityName = qualityName.substring(1);
					inverse = true;
				}
				
				for(int j = 1; j < candidatesFile.length; j++) {
					
					double value = Double.parseDouble(candidatesFile[j][i]);
					
					//if(Double.isInfinite(value)) {
					if(value == initQualityValue) { 
						continue; //skip invalid candidate
					}
					
					if(value < min) {
						min = value;
					} else if(value > max) {
						max = value;
					}
					
				}
				
				if(inverse) { //invert value for unification of optimization strategy; maximize all values
					minMax[0] = max;
					minMax[1] = min;
				} else {
					minMax[0] = min;
					minMax[1] = max;
				}
			
				qualities.put(qualityName, minMax);
			}
		}
		
		return qualities;
	}

	double normalize(double value, double min, double max) {
	
		double normalized = (value - min) / (max - min);
	
		return normalized;
	}
	
	private void setupExampleDARGZest(CreateDARGWizard wizard) {
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String outputDir = wizard.pageThree.outputDirectory.replace('\\', '/') + "/" + timeStamp + "/";
		
		Path filePath = Paths.get(outputDir);
		
	    // make sure the directories exist
	    try {
			Files.createDirectories(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// setup candidates file
		String csvFile = wizard.pageTwo.candidatesFile.toPlatformString(false);
		String csvPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + csvFile;
		
		String csvOptimalsFile = wizard.pageTwoTwo.candidatesFile.toPlatformString(false);
		String csvOptimalsPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + csvOptimalsFile;
		
		CSVReader csvReader = new CSVReader(csvPath, ";");
		String[][] candidatesFile = csvReader.getTable();
		
		System.out.println("Hier: " + csvOptimalsPath);
		
		CSVReader csvOptimalsReader = new CSVReader(csvOptimalsPath, ";");
		String[][] optimalCandidatesFile = csvOptimalsReader.getTable();
		
		HashMap<String, HashMap<String, String>> csvQualities = getCSVQualities(candidatesFile);

		HashMap<String, double[]> csvQualityMinMax = getMinMaxQualities(candidatesFile);
		
		HashMap<String, HashMap<String, String>> csvAllocation = getCSVAllocation(candidatesFile);
		
		// Find ALL BasicComponents that a specific architecture candidate uses
		HashMap<String, List<BasicComponent>> architectures = new HashMap<>();
		
		List<String> optimalCandidates = getOptimalCandidates(optimalCandidatesFile);
		
		ResourceSet pcmSystemRS = new ResourceSetImpl();
		org.palladiosimulator.pcm.system.System pcmSystem = 
				(org.palladiosimulator.pcm.system.System) pcmSystemRS.getResource(wizard.pageOneThree.systemFile, true).getContents().get(0);
		
		ResourceSet dseDesignDecisionsRS = new ResourceSetImpl();
		DecisionSpace dseDesignDecisions = 
				(DecisionSpace) dseDesignDecisionsRS.getResource(wizard.pageOneTwo.designdecisionsFile, true).getContents().get(0);
		
		for(Map.Entry<String, HashMap<String, String>> architecture: csvAllocation.entrySet()) {
			
			List<BasicComponent> selectedComponents = new ArrayList<>();
			
			for(AssemblyContext assemblyContext: pcmSystem.getAssemblyContexts__ComposedStructure()) {
				
				String contextName = assemblyContext.getEntityName();
				
				if(!architecture.getValue().containsKey(contextName)) {
					
					selectedComponents.add((BasicComponent) assemblyContext.getEncapsulatedComponent__AssemblyContext());
				
				} else {
					
					for(DegreeOfFreedomInstance dof: dseDesignDecisions.getDegreesOfFreedom()) {
						
						if(dof instanceof AssembledComponentDegree) {
							
							AssembledComponentDegree componentDegree = (AssembledComponentDegree) dof;
							
							String degreeId = ((AssemblyContext)componentDegree.getPrimaryChanged()).getId();
							String contextId = assemblyContext.getId();
							
							if(degreeId.equals(contextId)) {
								
								for(EObject component: componentDegree.getClassDesignOptions()) {
									
									BasicComponent basicComponent = (BasicComponent) component;
									
									String degreeName = architecture.getValue().get(((AssemblyContext)componentDegree.getPrimaryChanged()).getEntityName());
									
									String componentName = basicComponent.getEntityName();
									
									if(componentName.equals(degreeName)) {
										
										selectedComponents.add(basicComponent);
									}
								}
							}
						}
					}
					
				}
			}
			
			architectures.put(architecture.getKey(), selectedComponents);
		}
		
		// Extract used resources for each architecture candidate
		HashMap<String, List<Resource>> architectureResources = new HashMap<>();
		HashMap<String, List<ResourceOptions>> architectureResourceGroups = new HashMap<>();
		HashMap<String, List<BasicComponent>> architectureBasicComponents = new HashMap<>();
		
		for(Map.Entry<String, List<BasicComponent>> architecture: architectures.entrySet()) {
			
			List<Resource> usedResources = new ArrayList<>();
			List<ResourceOptions> usedResourceGroups = new ArrayList<>();
			
			ResourceSet arevaResourceRelationsRS = new ResourceSetImpl();
			Platform arevaResourceRelations = 
					(Platform) arevaResourceRelationsRS.getResource(wizard.pageOneOne.resourceRelationsFile, true).getContents().get(0);
			
			for(BasicComponent basicComponent: architecture.getValue()) {
				
				for(ResourceOptions group: arevaResourceRelations.getOptions()) {
					
					String groupComponentId = group.getReferencedComponent().getId();
					String basicComponentId = basicComponent.getId();
					
					if(groupComponentId.contentEquals(basicComponentId)) {
						usedResources.addAll(group.getResources());
						usedResourceGroups.add(group);
					}
				}
			}
			
			architectureResources.put(architecture.getKey(), usedResources);
			architectureResourceGroups.put(architecture.getKey(), usedResourceGroups);
		}
		
		// Load metaqadag
		
		if(wizard.pageFour.generateQadag) {
			
			QADAG metaQadag = QadagFactory.eINSTANCE.createQADAG();	
			metaQadag.setName("Meta-QADAG");
			metaQadag.setRoot(QadagFactory.eINSTANCE.createCompositeNode());
			metaQadag.getRoot().setName("Meta-QADAG");
		
			for(Map.Entry<String, HashMap<String,String>> quality: csvQualities.entrySet()) {
				
				CompositeNode node = QadagFactory.eINSTANCE.createCompositeNode();
				/*
				node.setName(candidatesFile[0][i].split(":")[0]);
				node.setWeight(0.333f);
				*/
				
			
				node.setName("" + quality.getKey());
				node.setWeight(1.0f/csvQualities.size());
				
				metaQadag.getRoot().getNodes().add(node);
			}
			
			// Save meta-qadag
			ResourceSet qadagRS = new ResourceSetImpl();
			org.eclipse.emf.ecore.resource.Resource metaqadagResource = ResourceManager.createAndAddResource(
				outputDir + "default.qadag", 
				new String[] {"qadag"}, 
				qadagRS);
			metaqadagResource.getContents().add(metaQadag);
			ResourceManager.saveResource(metaqadagResource);
		}
		
		// Generate DARG from Architecture->UsedResources HashMap
		ARG dargFile = de.tubs.areva.emf.model.darg.DargFactory.eINSTANCE.createARG();
		
		generateDARGFromResourceHashMap(architectures, architectureResources, architectureResourceGroups, optimalCandidates,dargFile, csvQualities, csvQualityMinMax);
		
		
		
		// save darg
		ResourceSet dargRS = new ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource architectureResource = ResourceManager.createAndAddResource(
				outputDir + "architectures.darg",
				new String[] {".darg"}, 
				dargRS);
		
		architectureResource.getContents().add(dargFile);
		ResourceManager.saveResource(architectureResource);
		
		for(IProject project: ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			try {
				project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void generateDARGFromResourceHashMap(
			HashMap<String, List<BasicComponent>> architectures, 
			HashMap<String, List<Resource>> architectureMap,
			HashMap<String, List<ResourceOptions>> architectureResourceGroupMap,
			List<String> optimalArchitectures,
			ARG dargFile, 
			HashMap<String, HashMap<String, String>> csvQualities,
			HashMap<String, double[]> csvMinMax
			) {
		
		
		int duplicates = 0;
		int architecturesCount = 0;
		for(Map.Entry<String, List<Resource>> architectureKV: architectureMap.entrySet()) {
			architecturesCount++;
			// check for duplicates
			List<ResourceOptions> selectedOptions = architectureResourceGroupMap.get(architectureKV.getKey());
			
			boolean duplicate = false;
			
			for(Architecture otherArchitecture: dargFile.getArchitectures()) {
				boolean contains = true;
				for(ResourceOptions option: selectedOptions) {
					for(ResourceOptions otherOption: otherArchitecture.getBoundResourceOptions())
						contains = contains && (option.getName().equals(otherOption.getName()));
				}
				duplicate = duplicate || contains;
			}
			
			if(duplicate) {
				duplicates++;
				continue;
			}
			
			de.tubs.areva.emf.model.darg.Architecture architecture = de.tubs.areva.emf.model.darg.DargFactory.eINSTANCE.createArchitecture();
			
			architecture.setId(architectureKV.getKey());
			
			architecture.getBoundBasicComponents().addAll(architectures.get(architectureKV.getKey()));
			
			for(Map.Entry<String, HashMap<String,String>> quality: csvQualities.entrySet()) {
				
				for(Map.Entry<String, String> architectureQuality: quality.getValue().entrySet()) {
					
					if(architecture.getId().equals(architectureQuality.getKey())) {
						
						QualityAssignment architectureQualityEntry = de.tubs.areva.emf.model.darg.DargFactory.eINSTANCE.createQualityAssignment();
						architectureQualityEntry.setAttribute(quality.getKey());
						
						double normalizedValue = normalize(
								Double.parseDouble(architectureQuality.getValue()), 
								csvMinMax.get(quality.getKey())[0],
								csvMinMax.get(quality.getKey())[1]
										);
								
						architectureQualityEntry.setValue(normalizedValue);
						
						architecture.getQualityassignments().add(architectureQualityEntry);
						
						QualityAssignment architectureRawQualityEntry = de.tubs.areva.emf.model.darg.DargFactory.eINSTANCE.createQualityAssignment();
						architectureRawQualityEntry.setAttribute(quality.getKey());
						architectureRawQualityEntry.setValue(Double.parseDouble(architectureQuality.getValue()));
						architecture.getRawQualityAssignments().add(architectureRawQualityEntry);
					}
				}	
			}
			
			architecture.getBoundResources().addAll(architectureKV.getValue());
			architecture.getBoundResourceOptions().addAll(architectureResourceGroupMap.get(architectureKV.getKey()));
			architecture.setBoundResourcesCount(architecture.getBoundResources().size());
			
			for(String optimalArchitecture: optimalArchitectures) {
				if(architecture.getId().equals(optimalArchitecture)) {
					architecture.setOptimal(true);
					
				}
			}
			
			// check for duplicate
			
			dargFile.getArchitectures().add(architecture);
			
		}
		
		Comparator<Architecture> byResources = new Comparator<Architecture>() {
			@Override
			public int compare(Architecture a1, Architecture a2) {
				int a1Count = a1.getBoundResources().size();
				int a2Count = a2.getBoundResources().size();
				return a2Count - a1Count;
			}
		};
		
		org.eclipse.emf.common.util.ECollections.sort(dargFile.getArchitectures(), byResources);
		System.out.println("Architectures: " + architecturesCount);
		System.out.println("Duplicates: " + duplicates);
	}
}
