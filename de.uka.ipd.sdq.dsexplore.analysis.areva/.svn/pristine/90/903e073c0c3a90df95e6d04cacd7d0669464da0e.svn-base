package de.uka.ipd.sdq.dsexplore.analysis.areva;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.monitor.Monitor;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opt4j.core.Criterion;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.allocation.util.AllocationResourceFactoryImpl;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.util.CompositionResourceFactoryImpl;
import org.palladiosimulator.pcm.core.entity.util.EntityResourceFactoryImpl;
import org.palladiosimulator.pcm.core.util.CoreResourceFactoryImpl;
import org.palladiosimulator.pcm.parameter.util.ParameterResourceFactoryImpl;
import org.palladiosimulator.pcm.protocol.util.ProtocolResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.qos_performance.util.QosPerformanceResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.qos_reliability.util.QosReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.util.QosannotationsResourceFactoryImpl;
import org.palladiosimulator.pcm.reliability.util.ReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.InfrastructureInterface;
import org.palladiosimulator.pcm.repository.InfrastructureRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceFactoryImpl;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;
import org.palladiosimulator.pcm.resourcetype.util.ResourcetypeResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.seff_performance.util.SeffPerformanceResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.seff_reliability.util.SeffReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.util.SeffResourceFactoryImpl;
import org.palladiosimulator.pcm.subsystem.util.SubsystemResourceFactoryImpl;
import org.palladiosimulator.pcm.system.util.SystemResourceFactoryImpl;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelResourceFactoryImpl;
import org.palladiosimulator.pcm.util.PcmResourceFactoryImpl;
import org.palladiosimulator.solver.models.PCMInstance;

import de.tubs.areva.resourcerelations.And;
import de.tubs.areva.resourcerelations.Expression;
import de.tubs.areva.resourcerelations.Implies;
import de.tubs.areva.resourcerelations.Not;
import de.tubs.areva.resourcerelations.Or;
import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceGroupVariable;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.ResourceVariable;
import de.tubs.areva.resourcerelations.ResourcerelationsPackage;
import de.tubs.areva.resourcerelations.Rule;
import de.tubs.areva.resourcerelations.Specification;
import de.tubs.areva.resourcerelations.impl.ResourcerelationsFactoryImpl;
import de.tubs.areva.resourcerelations.util.ResourcerelationsResourceFactoryImpl;
import de.uka.ipd.sdq.dsexplore.analysis.AbstractAnalysis;
import de.uka.ipd.sdq.dsexplore.analysis.AnalysisFailedException;
import de.uka.ipd.sdq.dsexplore.analysis.IAnalysis;
import de.uka.ipd.sdq.dsexplore.analysis.IAnalysisResult;
import de.uka.ipd.sdq.dsexplore.analysis.PCMPhenotype;
import de.uka.ipd.sdq.dsexplore.helper.EMFHelper;
import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer;
import de.uka.ipd.sdq.dsexplore.launch.DSELaunch;
import de.uka.ipd.sdq.dsexplore.launch.DSEWorkflowConfiguration;
import de.uka.ipd.sdq.dsexplore.qml.contract.QMLContract.util.QMLContractResourceFactoryImpl;
import de.uka.ipd.sdq.dsexplore.qml.contracttype.QMLContractType.Dimension;
import de.uka.ipd.sdq.dsexplore.qml.contracttype.QMLContractType.EnumRelationSemantics;
import de.uka.ipd.sdq.dsexplore.qml.declarations.QMLDeclarations.QMLDeclarationsPackage;
import de.uka.ipd.sdq.dsexplore.qml.pcm.datastructures.EvaluationAspectWithContext;
import de.uka.ipd.sdq.pcm.cost.ComponentCostPerInstance;
import de.uka.ipd.sdq.pcm.cost.ComponentCostPerType;
import de.uka.ipd.sdq.pcm.cost.Cost;
import de.uka.ipd.sdq.pcm.cost.CostRepository;
import de.uka.ipd.sdq.pcm.cost.FixedProcessingResourceCost;
import de.uka.ipd.sdq.pcm.cost.ProcessingResourceCost;
import de.uka.ipd.sdq.pcm.cost.VariableProcessingResourceCost;
import de.uka.ipd.sdq.pcm.cost.costPackage;
import de.uka.ipd.sdq.pcm.cost.helper.CostUtil;
import de.uka.ipd.sdq.workflow.blackboard.Blackboard;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ArevaEvaluator extends AbstractAnalysis implements IAnalysis{

	/** Logger for log4j. */
	private static Logger logger = Logger.getLogger("de.uka.ipd.sdq.dsexplore.analysis.areva");
	
	private Platform platformModel;
	
	private Map<String,Set<ResourceOptions>> basicCompID2ResourceGrp;
	private Set<ResourceOptions> affectedResourceGroups;
	
	private ResourceSet arevaSet;
	
	private long validConfCounter;
	private long invalidConfCounter;

	//Map of Results for pheno.getNumericID()
	//It is always the cost value, i.e. objective and constraint always have to refer to the SimpleValue (-> no statistical requirements atm)
	//If more possible aspects are added, the criterion needs to be examined here
	private Map<Long, ArevaAnalysisResult> previousArevaResults = new HashMap<Long, ArevaAnalysisResult>();
	
	public ArevaEvaluator() {
		super(new ArevaSolverQualityAttributeDeclaration());
	}
	
//	/** 
//	 * Sums up the initial cost of the PCM elements present in the given PCM instance.
//	 * TODO For now, all cost in the internal costRepository are considered. Thus, only 
//	 * variable cost lead to a change in cost, e.g. the variable cost for 
//	 * changing processing rates. 
//	 * 
//	 * Careful: This must point to the right pcm instance first. 
//	 * @param pcmInstance the PCM instance
//	 * @return 
//	 */
//	private double getInitialCost(PCMInstance pcmInstance){
//		List<Cost> costs = costModel.getCost();
//		double sum = 0;
//		for (Iterator<Cost> iterator = costs.iterator(); iterator.hasNext();) {
//			Cost cost = iterator.next();
//			if (doesCostApply(cost,pcmInstance)){
//				sum += cost.getInitialCost();
//			}
//		}
//		
//		return sum;
//	}

//	/**
//	 * Only checks uses in system (for components) and in the allocation (for processing resources)
//	 * @param cost
//	 * @param pcmInstance
//	 * @return
//	 */
//	private boolean doesCostApply(Cost cost, PCMInstance pcmInstance) {
//		if (VariableProcessingResourceCost.class.isInstance(cost)){
//			VariableProcessingResourceCost vc = (VariableProcessingResourceCost)cost;
//			ResourceContainer rc = (ResourceContainer)vc.getProcessingresourcespecification().eContainer();
//			return checkWhetherResourceContainerIsUsed(pcmInstance, rc);
//			//No usage of resource container found, return false. 
//		} else if (cost instanceof ComponentCostPerType){
//			ComponentCostPerType cc = (ComponentCostPerType)cost;
//			RepositoryComponent rc = cc.getRepositoryComponent();
//			//List<AssemblyContext> asctx = pcmInstance.getSystem().getAssemblyContexts__ComposedStructure();
//			//TODO: also retrieve inner assembly contexts of deployed composite components. Cost currently need to be specified separately.
//			
//			List<AssemblyContext> asctx =  getAllContainedAssemblyContexts(pcmInstance.getSystem().getAssemblyContexts__ComposedStructure());
//			
//			
//			for (AssemblyContext assemblyContext : asctx) {
//				if (EMFHelper.checkIdentity(assemblyContext.getEncapsulatedComponent__AssemblyContext(), rc)){
//					return true;
//				}
//			}
//			return false;
//		} else if (cost instanceof FixedProcessingResourceCost){
//			FixedProcessingResourceCost fc = (FixedProcessingResourceCost)cost;
//			ResourceContainer rc = (ResourceContainer)fc.getProcessingresourcespecification().eContainer();
//			return checkWhetherResourceContainerIsUsed(pcmInstance, rc);
//		} else 
//			return true;
//	}

//	/** 
//	 * Get all contained ones recursively
//	 * @param assemblyContextsComposedStructure
//	 * @return
//	 */
//	private List<AssemblyContext> getAllContainedAssemblyContexts(
//			EList<AssemblyContext> assemblyContextsComposedStructure) {
//		List<AssemblyContext> list = new ArrayList<AssemblyContext>();
//		list.addAll(assemblyContextsComposedStructure);
//		for (AssemblyContext assemblyContext : assemblyContextsComposedStructure) {
//			if (assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof ComposedStructure){
//				ComposedStructure composite = (ComposedStructure)assemblyContext.getEncapsulatedComponent__AssemblyContext();
//				list.addAll(getAllContainedAssemblyContexts(composite.getAssemblyContexts__ComposedStructure()));
//			}
//		}
//		return list;
//	}

//	private boolean checkWhetherResourceContainerIsUsed(PCMInstance pcmInstance,
//			ResourceContainer rc) {
//		List<AllocationContext> alloc = pcmInstance.getAllocation().getAllocationContexts_Allocation();
//		for (AllocationContext allocationContext : alloc) {
//			if (EMFHelper.checkIdentity(allocationContext.getResourceContainer_AllocationContext(), rc)){
//				return true;
//			}
//		}
//		return false;
//	}

//	/**
//	 * Careful: This must point to the right pcm instance first. 
//	 * @param pcmInstance
//	 * @return
//	 */
//	private double getOperatingCost(PCMInstance pcmInstance){
//		List<Cost> costs = costModel.getCost();
//		double sum = 0;
//		for (Iterator<Cost> iterator = costs.iterator(); iterator.hasNext();) {
//			Cost cost = iterator.next();
//			if (doesCostApply(cost,pcmInstance)){
//				sum += cost.getOperatingCost();
//			}
//		}
//		
//		return sum;
//	}
	
//	private void updateCostModel(PCMInstance pcmInstance) {
//
//		//List<Cost> allCosts = this.costModel.getCost();
//		List<Cost> allCosts =null; //FIXME Parser Fix
//		createCostsForReplicas(allCosts, pcmInstance);
//		
//		for (Cost cost : allCosts) {
//
//			// fix links between model elements (maybe this is not needed anymore...)
//			if (cost instanceof ComponentCostPerInstance){
//				((ComponentCostPerInstance) cost).setAllocation(pcmInstance.getAllocation());
//			} else 	if (VariableProcessingResourceCost.class.isInstance(cost)) {
//				
//				VariableProcessingResourceCost varCost = (VariableProcessingResourceCost)cost; 
//
//				ProcessingResourceSpecification old_prs = ((VariableProcessingResourceCost) cost)
//						.getProcessingresourcespecification();
//				ResourceContainer old_rc = (ResourceContainer) old_prs
//						.eContainer();
//				ProcessingResourceType ars = old_prs
//						.getActiveResourceType_ActiveResourceSpecification();
//
//				List<ResourceContainer> all_new_rcs = pcmInstance.getResourceEnvironment()
//						.getResourceContainer_ResourceEnvironment();
//				
//				for (ResourceContainer resourceContainer : all_new_rcs) {
//					
//					if (resourceContainer.getId().equals(old_rc.getId())) {
//						
//						List<ProcessingResourceSpecification> new_resources = resourceContainer
//								.getActiveResourceSpecifications_ResourceContainer();
//						
//						boolean resourceTypeFound = false;
//						
//						for (ProcessingResourceSpecification new_prs : new_resources) {
//							
//							if (new_prs
//									.getActiveResourceType_ActiveResourceSpecification()
//									.getId()
//									.equals(ars.getId())) {
//								if (!resourceTypeFound){
//									//Reset the processing rate with the first matching one found
//									varCost.setProcessingresourcespecification(new_prs);
//									resourceTypeFound = true;
//								} else {
//									throw new RuntimeException("There are two processing resources with the same resource type within one resource container, this cannot be handled by the optimisation yet. Please change your model.");
//								}
//							}
//						}
//						break;
//					}
//
//					/*
//					 * Resource resource = prs.eResource(); if (resource !=
//					 * null){ URI oldURI = resource.getURI();
//					 * resource.setURI(resEnvFileURI); } else {
//					 * System.out.println
//					 * ("Resource of ProcessingResourceSpecification "
//					 * +prs.toString()+" has a null eResource!"); }
//					 */
//				}
//			}
//		}
//
//	}
	
//	/**
//	 * FIXME: this should be more elegantly handled by separating a resource repository with costs specification from 
//	 * the actually used resources
//	 * @param allCosts
//	 * @param pcmInstance
//	 */
//	private void createCostsForReplicas(List<Cost> allCosts,
//			PCMInstance pcmInstance) {
//		
//		List<ResourceContainer> containers = pcmInstance.getResourceEnvironment().getResourceContainer_ResourceEnvironment();
//		List<Cost> replicaCosts = new ArrayList<Cost>(); 
//		
//		// also remove old replica costs from previous candidates
//		List<Cost> oldReplicaCosts = new ArrayList<Cost>();
//		
//		for (Cost anyCost : allCosts) {
//			
//			// iterate through costs, look at all VariableProcessingResourceCost or FixedProcessingResourceCost and in particular at their resourcecontainer.
//			ResourceContainer originalContainer = null;
//			ProcessingResourceType procResourceType = null;
//			ProcessingResourceCost cost = null;
//			if (anyCost instanceof ProcessingResourceCost){
//				cost = ((ProcessingResourceCost)anyCost);
//				originalContainer = cost.getProcessingresourcespecification().getResourceContainer_ProcessingResourceSpecification();
//				procResourceType = cost.getProcessingresourcespecification().getActiveResourceType_ActiveResourceSpecification();
//			} else {
//				// look at next cost model element
//				continue;
//			}
//			
//			// check if this is a cost model element for a replica, if yes delete it if its server is no longer in the resource environment
//			if (originalContainer.getEntityName().contains("Replica")  && !containers.contains(originalContainer)){
//				oldReplicaCosts.add(cost);
//			}
//
//			// find replicated servers and their original
//			for (ResourceContainer resourceContainer : containers) {
//				if (resourceContainer.getEntityName().contains("Replica") && resourceContainer.getId().contains(originalContainer.getId())){
//					// resourceContainer is a replica of originalResourceContainer
//					
//					// check if there already is a cost model element for the replica. If not, create a new one.
//					boolean replicaAlreadyAnnotated = false;
//					for (Cost existingCost : allCosts) {
//						if (existingCost instanceof ProcessingResourceCost){
//							ProcessingResourceCost existingProcRateCost = (ProcessingResourceCost)existingCost;
//							if (existingProcRateCost.getProcessingresourcespecification().getResourceContainer_ProcessingResourceSpecification().getId()
//								.equals(resourceContainer.getId())){
//								// there already is a cost model element annotating this replica, so continue;
//								replicaAlreadyAnnotated = true;
//								break; // inner for loop 
//							}
//						}
//					}
//					if (replicaAlreadyAnnotated){
//						continue;
//					}
//					
//					// get the processing resource spec that corresponds to the annotated one
//					ProcessingResourceSpecification replicaProcSpec = null;
//					for (ProcessingResourceSpecification procRes : resourceContainer.getActiveResourceSpecifications_ResourceContainer()) {
//						if (procRes.getActiveResourceType_ActiveResourceSpecification().getId().equals(procResourceType.getId())){
//							replicaProcSpec = procRes;
//							break;
//						}
//					}
//					if (replicaProcSpec == null){
//						logger.warn("Could not find processing resource type "+procResourceType.getEntityName()+" in container "+resourceContainer.getEntityName()+", assuming that there are no costs for it in this replica");
//						return;
//					}
//
//					// replicate cost element, too.
//					ProcessingResourceCost replicaCost = (ProcessingResourceCost)EcoreUtil.copy(cost);
//					replicaCost.setProcessingresourcespecification(replicaProcSpec);
//					replicaCosts.add(replicaCost);
//				}
//			}
//		}
//		allCosts.removeAll(oldReplicaCosts);
//		allCosts.addAll(replicaCosts);
//	}

//	/**
//	 * returns a cost model or throws an exception. 
//	 * @param configuration.getRawConfiguration()
//	 * @return a CostRepository which is not null
//	 * @throws CoreException if the model could not be loaded.  
//	 */
//	private CostRepository getArevaModel(DSEWorkflowConfiguration configuration) throws CoreException {
//		String costModelFileName = configuration.getRawConfiguration().getAttribute(DSEConstantsContainer.AREVA_FILE, "");
//		CostRepository cr =  (CostRepository)EMFHelper.loadFromXMIFile(costModelFileName, costPackage.eINSTANCE);
//		if (cr == null){
//			throw new CoreException(new Status(Status.ERROR, "de.uka.ipd.sdq.dsexplore", 0, "Cost model "+costModelFileName+" could not be loaded.", null));
//		}
//		return cr;
//	}
	
//	/**
//	 * Checks whether AssemblyContext ac is used in the current system model
//	 * @param pcmInstance
//	 * @param ac
//	 * @return
//	 */
//	private boolean checkWhetherAssemblyContextIsUsed(PCMInstance pcmInstance, AssemblyContext ac) {
//		List<AssemblyContext> assemblyContexts = pcmInstance.getSystem().getAssemblyContexts__ComposedStructure();
//		for (AssemblyContext assemblyContext : assemblyContexts) {
//			if (EMFHelper.checkIdentity(assemblyContext, ac)){
//				return true;
//			}
//		}
//		return false;
//	}
	
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
	public IAnalysisResult retrieveResultsFor(PCMPhenotype pheno, Criterion criterion)
			throws CoreException, AnalysisFailedException {
		//It is always the quality value, i.e. objective and constraint always have to refer to the SimpleValue (-> no statistical requirements atm)
		//If more possible aspects are added, the criterion needs to be examined here
		
		return this.previousArevaResults.get(pheno.getNumericID());
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
		//Suche im Ressource Set
		//justPrint("Init Ressource Set for AREVA");
		String arevaModelFileName = configuration.getRawConfiguration().getAttribute(DSEConstantsContainer.AREVA_FILE, "");
		//justPrint("arevaModelFileName: " + arevaModelFileName);
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
			}
			rgSet.add(rg);
			basicCompID2ResourceGrp.put(compRes.getId(), rgSet);
		}
		
		initialiseCriteria(configuration);
		
		invalidConfCounter = 0;
		validConfCounter = 0;
				
//		previousArevaResults.clear();
    }
	
	@Override
	public void analyse(PCMPhenotype pheno, IProgressMonitor monitor) throws CoreException, UserCanceledException, JobFailedException, AnalysisFailedException {
		PCMInstance pcm = pheno.getPCMInstance();	
		if(monitor != null)
			monitor.beginTask("ArEva " + Long.toString(pheno.getNumericID()), IProgressMonitor.UNKNOWN);
	
		//Collect used BasicComponents and resources, that are affected by executing these components.
		//1. Get all Annotated BasicComponents from the resources in ResourceGroups
		//2. Get AssemblyContext for each BasicComponent
		//3. Check whether context is used in current system
		//4. Store context as used in a list		
		Set<BasicComponent> usedBasicComponents = new HashSet<BasicComponent>(); //BasicComponents used in current system design
		affectedResourceGroups = new HashSet<ResourceOptions>();
		for (AssemblyContext ac : pcm.getSystem().getAssemblyContexts__ComposedStructure()){
			RepositoryComponent repoComp = ac.getEncapsulatedComponent__AssemblyContext();
			if(repoComp instanceof BasicComponent){
				BasicComponent basicComp = (BasicComponent)repoComp;
//				justPrint("Check BasicComponent " + basicComp.getEntityName());	
				Set<ResourceOptions> rgSet = basicCompID2ResourceGrp.get(basicComp.getId()); //Check if basicComponent is annotated to 1 or more resource groups
				if(rgSet != null){ 
					//Proceed only with  relevant BasicCompents (addressed in platform model...):
//					justPrint("Found BasicComponent " + basicComp.getEntityName() + " in platform model.");					
					usedBasicComponents.add(basicComp);	
					affectedResourceGroups.addAll(rgSet);
				}
			}
		}

//		Test output
//		for(BasicComponent bc : usedBasicComponents){
//		justPrint("Found BasicComponent " + bc.getEntityName() + " in instance and platform model.");		
//	}	
		
		//Init. dimensionToValue Map according to the relations semantics in the qml files
		Map<String,Double> dimensionToValue = initDimensionToValueMap();
		
		//2. Check constraints between groups in platform model (CNF)
		//i.e., check whether all requires and/or excludes holds for ResourceGroups and resources
		//Propositional logical checking of constraints based on the rules in the platform model
		justPrintLine("Affected ResGrps:");
		for(ResourceOptions rq : affectedResourceGroups){
			justPrintLine(rq.getName());
		}		
		boolean isValid = checkAllRules();		
		//boolean isValid = true;
		
		//collect quality values for each dimension, addressed in candidate
		if(isValid){
			justPrintLineForce("Valid configuration " + validConfCounter);
			validConfCounter++;
			//2.B If valid: Do scoring
			//1. Get all specs for each Resource
			//2.1. Get Property-Dimensions for each Resources
			//2.2. Compare Dimensions with dimensions of QML declaration
			//3. Get values for dimension
			//4. Sum up / make mean of all values per dimension
			//5. Store Results as analysis results
			for(ResourceOptions rg : affectedResourceGroups){
				justPrintLine("Check ResourceGrp " + rg.getName());
				Set<Resource> redundantRes = new HashSet<Resource>();
				int numberOfElements = Integer.parseInt(rg.getMinElements()); //FIXME: Repair emf model, no min/max elements, just number of necessary elements for execution
				int sizeOfResourceSet = rg.getResources().size();
				for(Resource res : rg.getResources()){
					for(Specification spec : res.getSpecs()){			
						//Respect number of resources and redundancy-relations between them, i.e., hot or cold redandancies.
						if(numberOfElements == sizeOfResourceSet){ //1. Hot: #Elements == rg.getResources()
							justPrintLine("hot redundancy");
							addValueToDimension(spec, dimensionToValue);
						}
						else{ //2. Cold: Leave out redundant elements, i.e., just select first of the redundancy options
							if(!redundantRes.containsAll(res.getRedundant())){
								justPrintLine("cold redundancy: new element");
								redundantRes.add(res);
								addValueToDimension(spec, dimensionToValue);
							}
							else{
								justPrintLine("cold redundancy: redundant element (ignored)");
							}
						}
					}
				}
			}
		}
		else{
			justPrintLineForce("Invalid configuration " + invalidConfCounter);
			invalidConfCounter++;
			//2.A If invalid: set all dimensions from qml declaration to Double.NEGATIVE_INFINITY (max. values) or Double.POSITIVE_INFINITY (min. values)
			//1. just leave default values
			//Testing: Setze irgendwas
			//for(String dim : dimensionToValue.keySet()){
			//	dimensionToValue.put(dim, new Double(0));
			//}
		}

		//write results
//		justPrintLine("Add to results:");
//		justPrintLine("ID: " + pheno.getNumericID());
//		for(String dim : dimensionToValue.keySet()){
//			justPrint(dim + "=");
//			justPrint(dimensionToValue.get(dim) + "; ");
//		}
//		justPrintLine("");
		
		justPrintLine("Genotype ID : " + pheno.getGenotypeID());
		justPrintLine("PCM instance: " + pheno.getPCMInstance());
		
		this.previousArevaResults.put(pheno.getNumericID(), new ArevaAnalysisResult(dimensionToValue, pcm, this.criterionToAspect, (ArevaSolverQualityAttributeDeclaration)qualityAttribute));	
		
		//Test output
//		justPrint("Measurement for Candidate:");
//		for(Dimension dim : qualityAttribute.getDimensions()){
//			String dimName = dim.getEntityName();	
//			justPrint(dimName + ": " + dimensionToValue.get(dimName.toLowerCase()));
//		}
		if(monitor != null)
			monitor.done();
	}
	
	/**
	 * Init. map dimensionToValue with the worst possible values wrt. the relation semantics from the qml declaration.
	 */
	private Map<String,Double> initDimensionToValueMap(){
		Map<String,Double> dimensionToValue = new HashMap<String,Double>();
		for(Dimension dim : qualityAttribute.getDimensions()){
			String dimName = dim.getEntityName().toLowerCase();
			if(dim.getType().getRelationSemantics().getRelSem() == EnumRelationSemantics.DECREASING)
				dimensionToValue.put(dimName, Double.POSITIVE_INFINITY); 
			else
				dimensionToValue.put(dimName, Double.NEGATIVE_INFINITY);
		}
		return dimensionToValue;
	}
	
	/**
	 * Check all constraints in the rules of the platform model.
	 * @return
	 */
	private boolean checkAllRules(){
		boolean result = true;
		//Example:
		//<Rules Name="CSS_dummy -> ASC low OR ASC high">
		//  <Expression LeftHandSideModifier="true">
		//	  <LeftHandSide ResourceGroup="//@groups.7"/>
		//    <RightHandSide xsi:type="arr:Or">
		//	    <LeftHandSide xsi:type="arr:ResourceGroupVariable" ResourceGroup="//@groups.5"/>
		//	    <RightHandSide xsi:type="arr:ResourceGroupVariable" ResourceGroup="//@groups.6"/>
		//	  </RightHandSide>
		//	</Expression>
		//</Rules>
		for(Rule rule : platformModel.getRules()){
			justPrintLine("Rule " + rule.getName() + ":");
			Implies implies = rule.getExpression();
			ResourceOptions rgLeft = implies.getLeftHandSide().getResourceOptions();
			Expression expr = implies.getRightHandSide();
			if(affectedResourceGroups.contains(rgLeft)){
				justPrint(rgLeft.getName() + "->");
				result = checkExpression(expr); //Check right hand side for "rgLeft -> ..."
			}
			else{
				if(implies.isLeftHandSideModifier()){
					result = true; //rule is irrelevant, this resource group is not considered
					justPrintLine("(not checked, " + rgLeft.getName() + " is not considered in candidate here.)");
				}
				else{ //false is Not
					justPrint("!" + rgLeft.getName() + "->");
					result = checkExpression(expr); //Check right hand side for "!rgLeft -> ..."
				}
			}
			justPrintLine(" ==> " + result);

			if(!result){
				justPrintLine("stop checking; at least 1 rule failed.");
				break; 
			}
		}
		
		return result;
	}
	
	/**
	 * Recursively check each constraint
	 * @param expr
	 * @return
	 */
	private boolean checkExpression(Expression expr){
		if(expr instanceof And){
			And and = (And)expr;
			boolean left = checkExpression(and.getLeftHandSide());
			justPrint(" && ");
			boolean right = checkExpression(and.getRightHandSide());
			return left && right;
		}
		else if(expr instanceof Or){
			Or or = (Or)expr;
			boolean left = checkExpression(or.getLeftHandSide());
			justPrint(" || ");
			boolean right = checkExpression(or.getRightHandSide());
			return left || right;
		}
		else if (expr instanceof Not){
			Not not = (Not)expr;
			justPrint(" !");
			return !checkExpression(not.getExpression());
		}
		else if (expr instanceof ResourceGroupVariable){
			ResourceGroupVariable rqVarExpr = (ResourceGroupVariable)expr;
			ResourceOptions varRq = rqVarExpr.getResourceOptions();
			if(affectedResourceGroups.contains(varRq)){
				justPrint(varRq.getName() + "(true)");
				return true;
			}
			else{
				justPrint(varRq.getName() + "(false)");
				return false;
			}
		}
		else if (expr instanceof ResourceVariable){
			ResourceVariable resVarExpr = (ResourceVariable)expr;
			Resource varRes = resVarExpr.getResource();
			boolean isInSet = false;
			for (ResourceOptions rq : affectedResourceGroups){
				for(Resource res : rq.getResources()){
					if(varRes == res){
						isInSet = true;
					}
					else{
						isInSet = false;
					}
				}
			}
			justPrint(varRes.getName()  + "("+ isInSet + ")");
			return isInSet;
		}
		else{
			justPrint("false");
			return false;
		}
	}
	
	/**
	 * Read and update values for each dimension wrt. a matching property from the given specification.
	 * @param spec
	 */
	private void addValueToDimension(Specification spec, Map<String,Double> dimensionToValue){
		String propName = spec.getAssignedProp().getName().toLowerCase();
		for(Dimension dim : qualityAttribute.getDimensions()){
			String dimName = dim.getEntityName().toLowerCase();
			if(propName.equals(dimName)){
				double oldValue = dimensionToValue.get(dimName).doubleValue();
				double newValue = spec.getValue();
				//init. value upon first match
				if(oldValue > Double.NEGATIVE_INFINITY && oldValue < Double.POSITIVE_INFINITY){
					newValue += oldValue; //oldValue is not an init value => sum up new and old value
				}
				dimensionToValue.replace(dimName, newValue);
			}
		}
	}
	
//	/**
//	 * Check Resource Constraints.
//	 * This is only possible during runtime with faults.
//	 * At design time all resources are expected as available.
//	 * 
//	 * TODO: Test here with some random faults
//	 * @param rg
//	 */
//	private void checkResourceConstraintsTest(ResourceGroup rg){
//		//Laufbeispiel:
//		//Optionen fuer Basic Component MCS Control: MCS Control simple oder MCS Control full
//		//Benoetigte Ressourcen werden im platform model in den jeweils korrespondierenden Gruppen
//		//In dieser Phase wird von 100% verfuegbarkeit ausgegangen (keine Min/Max Violations bzw. cardinality constraints)
//		//Diese Ueberpruefung erfolgt spater im ARG, wenn Fehler injektziert werden
//		
//		Boolean isValid = true;
//		//For each resource group:
//		for(Resource res : rg.getResources()){
//			List<Resource> res_excluding = res.getExcludes();
//			for(Resource r_exc : res_excluding){
//				if(r_exc != null && rg.getResources().contains(r_exc)){
//					isValid = false;
//					justPrintLine("Invalid: Exclude constrain not holding for resource " + r_exc.getName());
//					break;
//				}
//			}
//
//			List<Resource> res_including = res.getImplies();
//			for(Resource r_inc : res_including){
//				if(r_inc != null && !rg.getResources().contains(r_inc)){
//					isValid = false;
//					justPrintLine("Invalid: Include constrain not holding for resource " + r_inc.getName());
//					break;
//				}
//			}
//		}
//		
//		justPrintLine("Candidate still valid? " + isValid);
//	}
	
	private void justPrint(String s){
		//TODO turn printing on/off
//		System.out.print(s);
	}
	
	private void justPrintLine(String s){
		//TODO turn printing line on/off
//		System.out.println(s);
	}
	
	private void justPrintLineForce(String s){
		//TODO turn printing line on/off
//		System.out.println(s);
	}
	
}
