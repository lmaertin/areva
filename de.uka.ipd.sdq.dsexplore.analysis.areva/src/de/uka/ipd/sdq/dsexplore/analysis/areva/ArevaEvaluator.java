package de.uka.ipd.sdq.dsexplore.analysis.areva;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.opt4j.core.Criterion;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.solver.models.PCMInstance;

import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.Specification;
import de.tubs.areva.util.ArevaIndividual;
import de.tubs.areva.util.DoFBasicComponent;
import de.tubs.areva.util.RuleChecker;
import de.uka.ipd.sdq.dsexplore.analysis.AbstractAnalysis;
import de.uka.ipd.sdq.dsexplore.analysis.AnalysisFailedException;
import de.uka.ipd.sdq.dsexplore.analysis.IAnalysis;
import de.uka.ipd.sdq.dsexplore.analysis.IAnalysisResult;
import de.uka.ipd.sdq.dsexplore.analysis.PCMPhenotype;
import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer;
import de.uka.ipd.sdq.dsexplore.launch.DSEWorkflowConfiguration;
import de.uka.ipd.sdq.dsexplore.qml.contracttype.QMLContractType.Dimension;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ArevaEvaluator extends AbstractAnalysis implements IAnalysis{

	/** Logger for log4j. */
	private static Logger logger = Logger.getLogger("de.uka.ipd.sdq.dsexplore.analysis.areva");
	private double initQualityValue = -1.0;
	
	private Platform platformModel;
	
	private Map<String,Set<ResourceOptions>> basicCompID2ResourceGrp;
//	private Set<ResourceOptions> affectedResourceGroups;
	
	private ResourceSet arevaSet;
	
	private int validConfCounter;
	private int invalidConfCounter;

	//Map of Results for pheno.getNumericID()
	private Map<Long, ArevaAnalysisResult> previousArevaResults;
	
	public ArevaEvaluator() {
		super(new ArevaSolverQualityAttributeDeclaration());
		previousArevaResults = new HashMap<Long, ArevaAnalysisResult>();
	}
	
	protected boolean fileExists(String path) {
		// if this is a platform URL, first resolve it to an absolute path
		if (path.startsWith("platform:")){
			try {
				URL solvedURL = FileLocator.resolve(new URL(path));
				path =  solvedURL.getPath();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		} 
		File f = new File(path);
		return f.exists();
	}

	@Override
	public List<Criterion> getCriterions() throws CoreException {
		List<Criterion> criterions = new ArrayList<Criterion>();	 
		criterions.addAll(criteriaList);
		
		return criterions;
	}

	@Override
	public IAnalysisResult retrieveResultsFor(PCMPhenotype pheno, Criterion criterion) throws CoreException, AnalysisFailedException {
		return previousArevaResults.get(pheno.getNumericID());
	}
	
	@Override
	public boolean hasObjectivePerUsageScenario() throws CoreException {
		return false;
	}


	@Override
	public void setBlackboard(MDSDBlackboard blackboard) {
		this.blackboard = blackboard;
	}
	
	@Override
	public void initialise(DSEWorkflowConfiguration configuration) throws CoreException {		
		String arevaModelFileName = configuration.getRawConfiguration().getAttribute(DSEConstantsContainer.AREVA_FILE, "");
		arevaSet = new ResourceSetImpl();
		if(!fileExists(arevaModelFileName)) {
			System.err.println("Could not load areva model! Please check the path!");
			return;
		}
		URI uri;
		if (URI.createURI(arevaModelFileName).isPlatform() || arevaModelFileName.indexOf("://") >= 0) { 
			uri = URI.createURI(arevaModelFileName);
		} else {
			uri = URI.createFileURI(arevaModelFileName);
		}

		platformModel = (Platform)arevaSet.getResource(uri, true).getContents().get(0);		
		
		//Preprocessing: Cache all links of annotated BasicComponents to resourcegroups to increase performance 
		basicCompID2ResourceGrp = new HashMap<String, Set<ResourceOptions>>();
		for (ResourceOptions rg : platformModel.getOptions()){
			BasicComponent compRes = rg.getReferencedComponent();
			Set<ResourceOptions> rgSet = basicCompID2ResourceGrp.get(compRes.getId());
			if(rgSet == null){	
				rgSet =	new HashSet<ResourceOptions>();
				basicCompID2ResourceGrp.put(compRes.getId(), rgSet);
			}
			rgSet.add(rg);
		}

		initialiseCriteria(configuration);
		
		invalidConfCounter = 0;
		validConfCounter = 0;
    }
	
	@Override
	/**
	 * AREva analysis
	 */
	public void analyse(PCMPhenotype pheno, IProgressMonitor monitor) throws CoreException, UserCanceledException, JobFailedException, AnalysisFailedException {
		PCMInstance pcm = pheno.getPCMInstance();	
		ArevaIndividual individual = new ArevaIndividual(pheno.getNumericID());
		if(monitor != null)
			monitor.beginTask("ArEva " + Long.toString(pheno.getNumericID()), IProgressMonitor.UNKNOWN);
		
	
		//Collect used BasicComponents and resources, that are affected by executing these components.
		//1. Get all Annotated BasicComponents from the resources in ResourceGroups
		//2. Get AssemblyContext for each BasicComponent
		//3. Check whether context is used by current system variant
		//4. Store context as used in a list	
		Set<BasicComponent> usedBasicComponents = new HashSet<BasicComponent>(); //BasicComponents used by current system variant
		for (AssemblyContext ac : pcm.getSystem().getAssemblyContexts__ComposedStructure()){
			RepositoryComponent repoComp = ac.getEncapsulatedComponent__AssemblyContext();
			if(repoComp instanceof BasicComponent){
				BasicComponent basicComp = (BasicComponent)repoComp;
				DoFBasicComponent dofComp = new DoFBasicComponent(null, null, basicComp);
				Set<ResourceOptions> rgSet = basicCompID2ResourceGrp.get(basicComp.getId()); //Check if basicComponent is annotated to 1 or more resource groups
				if(rgSet != null){ 
					//Proceed only with  relevant BasicCompents (addressed in platform model...):
					usedBasicComponents.add(basicComp);	
					individual.addUsedBasicComponents(dofComp);
					individual.addAffectedResourceOptions(rgSet);
				}
			}
		}
		
		//Check constraints between groups in platform model (CNF)
		//i.e., check whether all requires and/or excludes holds for ResourceGroups and resources
		//Propositional logical checking of constraints based on the rules in the platform model
		boolean isValid =  RuleChecker.checkAllRules(platformModel, individual);
		
		//collect quality values for each dimension addressed in candidate
		if(isValid){
			//System.out.println("Valid configuration " + validConfCounter);
			validConfCounter++;
//			System.out.println(validConfCounter + ". valid configuration (ID: " + pheno.getNumericID() + ")");
			
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
//							System.out.println("hot redundancy");
							individual.addValueToDimension(spec);
						}
						else{ 										//2. Cold redundancy: Leave out redundant elements, i.e., just select first resource of the redundancy options
							if(!redundantRes.containsAll(res.getRedundant())){
//								System.out.println("cold redundancy: new element");
								redundantRes.add(res);
								individual.addValueToDimension(spec);
							}
//							else{
//								System.out.println("cold redundancy: redundant element (ignored)");
//							}
						}
					}
				}
			}
		}
		else{
			invalidConfCounter++;
//			System.out.println(invalidConfCounter + ". invalid configuration (ID: " + pheno.getNumericID() + ")");

			//2.A If invalid: set all dimensions to default value
			for(Dimension dim : qualityAttribute.getDimensions()){
				String dimName = dim.getEntityName().toLowerCase();
//				if(dim.getType().getRelationSemantics().getRelSem() == EnumRelationSemantics.DECREASING)
//					individual.getDimensionToValue().put(dimName, Double.POSITIVE_INFINITY); //dimensionToValue.put(dimName, 0.0);
//				else
//					individual.getDimensionToValue().put(dimName, -1.0); //dimensionToValue.put(dimName, 0.0);
				individual.getDimensionToValue().put(dimName, initQualityValue); //init value
			}
		}
		
		//write results
		
		//add conf. to result Map
		previousArevaResults.put(pheno.getNumericID(), new ArevaAnalysisResult(individual.getDimensionToValue(), pcm, criterionToAspect, (ArevaSolverQualityAttributeDeclaration)qualityAttribute));	
				
		if(monitor != null)
			monitor.done();
	}
	
}
