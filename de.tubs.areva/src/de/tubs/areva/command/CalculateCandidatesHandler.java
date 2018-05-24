package de.tubs.areva.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.Specification;
import de.tubs.areva.ui.wizard.CalculateCandidatesWizard;
import de.tubs.areva.util.ArevaIndividual;
import de.tubs.areva.util.DoFBasicComponent;
import de.tubs.areva.util.RuleChecker;
import de.uka.ipd.sdq.dsexplore.helper.FilterParetoOptimalIndividuals;
import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer;
import de.uka.ipd.sdq.pcm.designdecision.DecisionSpace;
import de.uka.ipd.sdq.pcm.designdecision.DegreeOfFreedomInstance;
import de.uka.ipd.sdq.pcm.designdecision.specific.AssembledComponentDegree;

public class CalculateCandidatesHandler extends AbstractHandler implements IHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		CalculateCandidatesWizard wizard = new CalculateCandidatesWizard();
		WizardDialog wizardDialog = new WizardDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), wizard);

		if(wizardDialog.open() == Window.OK)
			calculate(wizard);
		
		return null;
	}

	private void calculate(CalculateCandidatesWizard wizard) {
		ResourceSet resourceSet = new ResourceSetImpl();
		org.palladiosimulator.pcm.system.System  system = (org.palladiosimulator.pcm.system.System) resourceSet.getResource(wizard.systemPage.systemFile, true).getContents().get(0);
		DecisionSpace designDecisions = (DecisionSpace) resourceSet.getResource(wizard.designDescisionsPage.designdecisionsFile, true).getContents().get(0);
		Platform resourceRelations = (Platform) resourceSet.getResource(wizard.resourceRelationsPage.resourceRelationsFile, true).getContents().get(0);
		String usageScenarioName = wizard.usageModelPage.usageScenarioName;
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date());
		String outputDir = wizard.outputPage.outputDirectory.replace('\\', '/') + "/";
	    String allCandPath = outputDir + DSEConstantsContainer.ALL_CANDIDATES + "_" + timeStamp + ".csv";
	    String allCandPathInverted = outputDir + DSEConstantsContainer.ALL_CANDIDATES + "_" + timeStamp + "_inverted.csv";
	    String archiveCandPath = outputDir + DSEConstantsContainer.ARCHIVE_CANDIDATES + "_" + timeStamp + ".csv";
		
		//Preprocessing: Cache all links of annotated BasicComponents to resourcegroups to increase performance 
	    Map<String,Set<ResourceOptions>> basicCompID2ResourceGrp = new HashMap<String, Set<ResourceOptions>>();
		for (ResourceOptions rg : resourceRelations.getOptions()){
			BasicComponent compRes = rg.getReferencedComponent();
			Set<ResourceOptions> rgSet = basicCompID2ResourceGrp.get(compRes.getId());
			if(rgSet == null){	
				rgSet =	new HashSet<ResourceOptions>();
				basicCompID2ResourceGrp.put(compRes.getId(), rgSet);
			}
			rgSet.add(rg);
		}

		EList<DegreeOfFreedomInstance> dofs = designDecisions.getDegreesOfFreedom();
		AssembledComponentDegree[] adofArray = new AssembledComponentDegree[dofs.size()];
		
		//Store Instances of AssembledComponentDegree as array for further processing
		int c = 0;
		for(DegreeOfFreedomInstance dof: dofs) { 
			if(dof instanceof AssembledComponentDegree) {
				AssembledComponentDegree adof = (AssembledComponentDegree)dof;
				adofArray[c] = adof;
				c++;
			}
		}
		
		//Collect core basic components and referenced resources (e.g. IMU)
		Set<BasicComponent> coreComponents = new HashSet<BasicComponent>();
		for (AssemblyContext ac : system.getAssemblyContexts__ComposedStructure()){
			RepositoryComponent repoComp = ac.getEncapsulatedComponent__AssemblyContext();
			if(repoComp instanceof BasicComponent){
				BasicComponent basicCompSys = (BasicComponent)repoComp;
				//check if basic comp is an option of a DoF; if not it's a core element
				boolean compInDof = false;
				check: for(int i=0;i<adofArray.length;i++) {
					AssembledComponentDegree aDof = adofArray[i];
					if(aDof != null){
						for(EObject option : aDof.getClassDesignOptions()){
							BasicComponent basicCompDof = (BasicComponent)option;
							if(basicCompSys == basicCompDof){
								compInDof = true;
								break check;
							}
						}
					}
				}
				if(!compInDof)
					coreComponents.add(basicCompSys);
			}
		}
		
		List<List<DoFBasicComponent>> permutations = new ArrayList<List<DoFBasicComponent>>();
		permute(adofArray, new ArrayList<DoFBasicComponent>(), permutations, -1); //permute all design options of platform
		
		List<ArevaIndividual> individuals = new ArrayList<ArevaIndividual>();
		long canditateId=0;
		int countValid = 0;
		int countInvalid = 0;
		for(List<DoFBasicComponent> permutation: permutations) {
			ArevaIndividual individual = new ArevaIndividual(canditateId);
			canditateId++;
			
			//Add Core Components to permutation
			for(BasicComponent basicComp : coreComponents){
				permutation.add(new DoFBasicComponent(null, null, basicComp));
			}
						
			//Each permutation = new candidate
			for(DoFBasicComponent dofcomp: permutation) {
				BasicComponent comp = dofcomp.getComp();
				Set<ResourceOptions> rgSet = basicCompID2ResourceGrp.get(comp.getId()); //Check if basicComponent is annotated to 1 or more resource groups
				if(rgSet != null){ 
					//Proceed only with  relevant BasicCompents (addressed in platform model...):
					individual.addUsedBasicComponents(dofcomp);
					individual.addAffectedResourceOptions(rgSet);
				}
			}
						
			boolean valid = RuleChecker.checkAllRules(resourceRelations, individual); //check platform constraints
			
			if(valid){countValid++;
				individuals.add(individual);
				//2.B If valid: Do scoring
				//1. Get all specs for each Resource
				//2.1. Get Property-Dimensions for each Resources
				//2.2. Compare Dimensions with dimensions of QML declaration
				//3. Get values for dimension
				//4. Sum up (before: make mean) of all values per dimension
				//5. Store Results as analysis results
				for(ResourceOptions rg : individual.getAffectedResourceGroups()){
					Set<Resource> redundantRes = new HashSet<Resource>();
					int numberOfElements = Integer.parseInt(rg.getMinElements()); //FIXME: Repair emf model, no min/max elements, just number of necessary elements for execution
					int sizeOfResourceSet = rg.getResources().size();
					for(Resource res : rg.getResources()){
						for(Specification spec : res.getSpecs()){			
							//Respect number of resources and redundancy-relations between them, i.e., hot or cold redundancies.
							if(numberOfElements == sizeOfResourceSet){	//1. Hot redundancy: #Elements == rg.getResources()
								individual.addValueToDimension(spec);
							}
							else{ 										//2. Cold redundancy: Leave out redundant elements, i.e., just select first resource of the redundancy options
								if(!redundantRes.containsAll(res.getRedundant())){redundantRes.add(res);
									individual.addValueToDimension(spec);
								}
							}
						}
					}
				}
			}
			else{
				countInvalid++;
			}
		}
		
		System.out.println("#valid: " + countValid + ", #invalid:" + countInvalid);
		
		List<ArevaIndividual> individualsOptimal = new ArrayList<ArevaIndividual>(individuals.size());
		individualsOptimal.addAll(individuals);
		Set<ArevaIndividual> toBeRemoved = new HashSet<ArevaIndividual>();
		
		//efficient iteration of two loops with check in both directions
		for(int i = 0; i < individuals.size(); i++){
			ArevaIndividual individual = individuals.get(i);
			for (int j = i + 1; j < individuals.size(); j++){
				ArevaIndividual opponent = individuals.get(j);
				if(individual != opponent){
					if(individual.dominates(opponent)){
						toBeRemoved.add(opponent);
					}
					else if(opponent.dominates(individual)){
						toBeRemoved.add(individual);
					}
				}
			}
		}

		individualsOptimal.removeAll(toBeRemoved);
		
		writeResults(allCandPath, individuals, usageScenarioName, false); //write all candidates 
		writeResults(archiveCandPath, individualsOptimal, usageScenarioName, false); //write optimal candidates
		
		//Find and write Pareto eff. candidates with Opt4J backend of DSE
		writeResults(allCandPathInverted, individuals, usageScenarioName, true); //write all candidates with inverted signs for max. dimensions (filter only minimizes!)
		try {
			FilterParetoOptimalIndividuals.main(new String[]{""+individuals.get(0).getDimensionToValue().size(), allCandPathInverted});
		} catch (CoreException e) {
			e.printStackTrace();
		}	
		

	}	
	
	void permute(AssembledComponentDegree[] adofArray, List<DoFBasicComponent> currentPermutation, List<List<DoFBasicComponent>> permutations, int index) {
		index++;
		if(index < adofArray.length) {
			AssembledComponentDegree aDof = adofArray[index];
			for(EObject option : aDof.getClassDesignOptions()){
				DoFBasicComponent dofComp = new DoFBasicComponent(aDof.getClass().getSimpleName(), aDof.getEntityName(), (BasicComponent)option);
				List<DoFBasicComponent> newPermutation = new ArrayList<DoFBasicComponent>(currentPermutation);	
				newPermutation.add(dofComp);	
				permute(adofArray, newPermutation, permutations, index);
			}
		} else {
			permutations.add(currentPermutation);
		}
	}
	
//	/**
//	 * Convert all maximalisation dimensions to minimalisation dimensions (w/o negative values).
//	 * 1) Determine max. value for each dimensione
//	 * 2) update each value by: max. value - value
//	 * @param individuals
//	 */
//	private void convertMaxToMinProblem(List<ArevaIndividual> individuals){
//		//get max. values for dimensions
//		Map<String, Double> dimensionToMaxValue	= new HashMap<String, Double>();
//		Set<String> dimensions = individuals.get(0).getDimensionToValue().keySet();
//		for(String dim : dimensions){
//			if(!dim.startsWith("-")){
//				double maxValue = 0.0;
//				for (ArevaIndividual indiv : individuals) {
//					double value = indiv.getDimensionToValue().get(dim).doubleValue();
//					if(value > maxValue)
//						maxValue = value;
//				}
//				dimensionToMaxValue.put(dim, maxValue); //store max value
//			}
//		}
//		
//		//substract value from max. values
//		for (ArevaIndividual indiv : individuals) {
//			Map<String, Double> dimensionToValue = indiv.getDimensionToValue();
//			for(String dim : dimensionToMaxValue.keySet()){
//				double maxValue = dimensionToMaxValue.get(dim);
//				double value = dimensionToValue.get(dim).doubleValue();
////				dimensionToValue.replace(dim, maxValue-value);
//				dimensionToValue.replace(dim, maxValue/value);
////				System.out.println(indiv.getId() + "(" + dim + "): " + value + " -> " + (maxValue/value));
//			}
//		}		
//	}
	
	private void writeResults(String filePath, List<ArevaIndividual> individuals, String usageScenarioName, boolean flipSign) {
		File file = new File(filePath);
		
		if(individuals.size() > 0){
			try {
				BufferedWriter w = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file)));

				String[] dimensionHeaders = new String[individuals.get(0).getDimensionToValue().keySet().size()]; //remain order of dimensions
				String[] adofHeaders = new String[individuals.get(0).getUsedBasicComponents().size()]; //remain order of dimensions
				//write header
				int i = 0;
				for ( String dim : individuals.get(0).getDimensionToValue().keySet()){
					dimensionHeaders[i] = dim;
					i++;
					w.write("'" + dim + ":" + usageScenarioName + "';");
				}
				int j = 0;
				for ( DoFBasicComponent adofComp : individuals.get(0).getUsedBasicComponents()){
					String type = adofComp.getDofType();
					String aDofName = adofComp.getDofName();
					if(type != null && aDofName != null){
						adofHeaders[j] = type + ":" + aDofName;
						j++;
						w.write(adofComp.getDofType() + ":" + adofComp.getDofName() + ";");
					}
				}
				w.write("Candidate ID;");
				w.newLine();

				//write Data
				for (ArevaIndividual indiv : individuals) {
					for ( int d=0; d<dimensionHeaders.length; d++){
						String dim = dimensionHeaders[d];
						double value = indiv.getDimensionToValue().get(dim).doubleValue();
						
						//Flip sign of value in maximization dimension
						if(flipSign && !dim.startsWith("-"))
							value = -value;
						
						w.write(value + ";");
					}
					for ( int a=0; a<adofHeaders.length; a++){
						String head = adofHeaders[a];
						for(DoFBasicComponent adofComp : indiv.getUsedBasicComponents()){
							String type = adofComp.getDofType();
							String aDofName = adofComp.getDofName();
							if(head != null && type != null && aDofName != null && head.equals(type + ":" + aDofName)){
								w.write(adofComp.getComp().getEntityName() + " (ID: " + adofComp.getComp().getId() + ");");
								break;
							}
						}
					}
					w.write(indiv.getId()+";");
					w.newLine();
				}

				w.flush();

				w.close();

				System.out.println("Written results to "+file.getAbsolutePath());
							
			} catch (FileNotFoundException e) {
				System.out.println("Writing failed.");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Writing failed.");
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Writing failed. Empty list of individuals.");
		}
		
	}
	
}
