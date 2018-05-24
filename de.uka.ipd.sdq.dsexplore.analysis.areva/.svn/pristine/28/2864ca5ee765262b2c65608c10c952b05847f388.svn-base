package de.uka.ipd.sdq.dsexplore.analysis.areva;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opt4j.core.Criterion;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.pcm.allocation.util.AllocationResourceFactoryImpl;
import org.palladiosimulator.pcm.core.composition.util.CompositionResourceFactoryImpl;
import org.palladiosimulator.pcm.core.entity.util.EntityResourceFactoryImpl;
import org.palladiosimulator.pcm.core.util.CoreResourceFactoryImpl;
import org.palladiosimulator.pcm.parameter.util.ParameterResourceFactoryImpl;
import org.palladiosimulator.pcm.protocol.util.ProtocolResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.qos_performance.util.QosPerformanceResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.qos_reliability.util.QosReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.qosannotations.util.QosannotationsResourceFactoryImpl;
import org.palladiosimulator.pcm.reliability.util.ReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.repository.util.RepositoryResourceFactoryImpl;
import org.palladiosimulator.pcm.resourceenvironment.util.ResourceenvironmentResourceFactoryImpl;
import org.palladiosimulator.pcm.resourcetype.util.ResourcetypeResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.seff_performance.util.SeffPerformanceResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.seff_reliability.util.SeffReliabilityResourceFactoryImpl;
import org.palladiosimulator.pcm.seff.util.SeffResourceFactoryImpl;
import org.palladiosimulator.pcm.subsystem.util.SubsystemResourceFactoryImpl;
import org.palladiosimulator.pcm.system.util.SystemResourceFactoryImpl;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelResourceFactoryImpl;
import org.palladiosimulator.pcm.util.PcmResourceFactoryImpl;
import org.palladiosimulator.solver.models.PCMInstance;

import de.tubs.areva.resourcerelations.ResourcerelationsPackage;
import de.tubs.areva.resourcerelations.util.ResourcerelationsResourceFactoryImpl;
import de.uka.ipd.sdq.dsexplore.analysis.AnalysisFailedException;
import de.uka.ipd.sdq.dsexplore.analysis.PCMPhenotype;
import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer;
import de.uka.ipd.sdq.dsexplore.launch.DSEWorkflowConfiguration;
import de.uka.ipd.sdq.dsexplore.qml.contract.QMLContract.util.QMLContractResourceFactoryImpl;
import de.uka.ipd.sdq.dsexplore.qml.declarations.QMLDeclarations.QMLDeclarationsPackage;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ArevaEvaluatorTest {

	//change example settings
	private final static String folderName = "aocs/";
	final static String projectName = "aocs";
//	final static String folderName = "simpletactics/";
//	final static String projectName = "default";
	
//	Configure paths and project name.
	private static final String root = new File("").getAbsolutePath() + "/../";
	private static final String PCMDefaultModelsPath = root + "org.palladiosimulator.pcm.resources/defaultModels/";
	private static final String path = root + "de.tubs.areva.testprojects.test1/" + folderName;

	private static final String qmlDeclarationFile = path+projectName+".qmldeclarations";
	private static final String usaggeModel = path+projectName+".usagemodel";
	private static final String allocationModel = path+projectName+".allocation";
	private final static String resourceRelationsModel = path+projectName+".resourcerelations";
	
	private static final String analysisModelFileType = DSEConstantsContainer.AREVA_FILE;
	private static final String analysisModelFileExtension = "resourcerelations";
	private static final String analysisNamespaceURI = "http://ips.tu-bs.de/ResourceRelationsModel/1.0";
	private static final EPackage analysisPackage = ResourcerelationsPackage.eINSTANCE;
	private static final ResourceFactoryImpl analysisFactory = new ResourcerelationsResourceFactoryImpl();
	 
	private static ArevaEvaluator evaluator;
	private static PCMInstance pcmInstance;
	private static PCMPhenotype pheno;
	
	@BeforeClass
	public static void initEvaluatorAndPCMInstance() {
		System.out.println("Init evaluator");
		evaluator = new ArevaEvaluator();
		PCMResourceSetPartition resourceSetPartition = createResourceSetPartition();
		
		//Blackboard:
		MDSDBlackboard blackboard = new MDSDBlackboard();
		blackboard.addPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID, resourceSetPartition);
		evaluator.setBlackboard(blackboard); 
			
		DSEWorkflowConfiguration conf = initDSEWorkflowConfiguration();
		try {
			evaluator.initialise(conf);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		//PCM Instance
		pcmInstance = new PCMInstance(resourceSetPartition);
	}

	@Test
	public void testCriterionList(){
		try {
			assert(!evaluator.getCriterions().isEmpty());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPCMValidity(){
		assert(!pcmInstance.getSystem().getAssemblyContexts__ComposedStructure().isEmpty());
	}
	
	@Test
	public void testCreatePheno(){
		pheno = new PCMPhenotype(pcmInstance, "anyGenotypeID", 1);
		assert(pheno.getPCMInstance() == pcmInstance);
	}
	
	@Test
	public void testAnalysis() {
		try {
			evaluator.analyse(pheno, null); 	
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (UserCanceledException e) {
			e.printStackTrace();
		} catch (JobFailedException e) {
			e.printStackTrace();
		} catch (AnalysisFailedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkResults(){
		try {
			for(Criterion criterion : evaluator.getCriterions()){
//				System.out.println(pheno);
//				System.out.println((ArevaAnalysisResult)evaluator.retrieveResultsFor(pheno, criterion));
				assert((ArevaAnalysisResult)evaluator.retrieveResultsFor(pheno, criterion) != null);
//				System.out.println("ccc: " + criterion.getName());
//				if(evaluator.retrieveResultsFor(pheno, criterion) == null)
//					System.out.println("val: N/A");
//				else
//					System.out.println("val: " + evaluator.retrieveResultsFor(pheno, criterion).getValueFor(criterion));;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (AnalysisFailedException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Manually register dependencies for EMF.
     * 
     * Based on work of Fabian Keller, https://github.com/DECLARE-Project/palladio-headless/
     */
    private static void initPackagesFactoriesPathMaps() {
        for (final EPackage ePackage : AbstractPCMWorkflowRunConfiguration.PCM_EPACKAGES) {
        	org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ePackage.getNsURI(), ePackage);    	
        }
        
        EPackage.Registry.INSTANCE.put(analysisNamespaceURI, analysisPackage);
        EPackage.Registry.INSTANCE.put("http:///QMLDeclarations.ecore", QMLDeclarationsPackage.eINSTANCE);
        
        // register factories: http://wiki.eclipse.org/EMF/FAQ#How_do_I_use_EMF_in_standalone_applications_.28such_as_an_ordinary_main.29.3F
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("pcm", new PcmResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("core", new CoreResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("entity", new EntityResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("composition", new CompositionResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("usagemodel", new UsagemodelResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("repository", new RepositoryResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("resourcetype", new ResourcetypeResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("protocol", new ProtocolResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("parameter", new ParameterResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("reliability", new ReliabilityResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("seff", new SeffResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("seffperformance", new SeffPerformanceResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("seffreliability", new SeffReliabilityResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qosannotations", new QosannotationsResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qosperformance", new QosPerformanceResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qosreliability", new QosReliabilityResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("system", new SystemResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("resourceenvironment", new ResourceenvironmentResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("allocation", new AllocationResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("subsystem", new SubsystemResourceFactoryImpl());
       
        //For DSE:
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("qmlcontracttype", new QMLContractResourceFactoryImpl());
        org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(analysisModelFileExtension, analysisFactory);
        
    	URIConverter.URI_MAP.put(
    			URI.createURI("pathmap://PCM_MODELS/"),
    			URI.createURI("file://"+PCMDefaultModelsPath, false)); //Pure Java variant of createPlatformPluginURI
    }
	
	private static PCMResourceSetPartition createResourceSetPartition(){
		initPackagesFactoriesPathMaps(); //Init. default resource factories;

		PCMResourceSetPartition resourceSetPartition = new PCMResourceSetPartition(); // Create a resource set
		resourceSetPartition.initialiseResourceSetEPackages(AbstractPCMWorkflowRunConfiguration.PCM_EPACKAGES);
		resourceSetPartition.loadModel(URI.createURI("file://"+usaggeModel));
		resourceSetPartition.loadModel(URI.createURI("file://"+allocationModel));
		resourceSetPartition.resolveAllProxies();
		
		return resourceSetPartition;
//		Alternative impl.: If proper a config is needed:
//		Properties config = new Properties();
//		config.setProperty("Storage_Path", path);
//		config.setProperty("Filename_UsageModel", usaggeModel);
//		config.setProperty("Filename_AllocationModel", allocationModel);
//
//		//Headless operation of Palladio is adapted from https://github.com/DECLARE-Project/palladio-headless/ (Fabian Keller)
//		PCMResourceSetPartition resourceSetPartition = new PCMResourceSetPartition(); // Create a resource set
//        ArrayList<String> fileList = new ArrayList<String>();
//        fileList.add(config.getProperty("Filename_UsageModel"));
//        fileList.add(config.getProperty("Filename_AllocationModel"));
//        resourceSetPartition.initialiseResourceSetEPackages(AbstractPCMWorkflowRunConfiguration.PCM_EPACKAGES);
//        for (String modelFile : fileList) {
//            resourceSetPartition.loadModel(modelFile);
//        }
//        resourceSetPartition.resolveAllProxies();
	}
	
	private static DSEWorkflowConfiguration initDSEWorkflowConfiguration(){
		DSEWorkflowConfiguration conf = new DSEWorkflowConfiguration();
		Map<String, Object> attr = new HashMap<String, Object>();
		attr.put(analysisModelFileType, resourceRelationsModel);
		attr.put("qmlDefinitionFile", qmlDeclarationFile);
		ILaunchConfiguration rawconf = new CustomLaunchConfiguration(attr);
		conf.setRawConfig(rawconf);
		return conf;
	}
	
	/**
     * Custom Launch Configuration for the Palladio Bench.
     * 
     * Based on work of Fabian Keller, https://github.com/DECLARE-Project/palladio-headless/
     */
	private static class CustomLaunchConfiguration implements ILaunchConfiguration {

		private Map<String, Object> attr = new HashMap<String, Object>();
		private Logger log = Logger.getLogger(CustomLaunchConfiguration.class.getName());

		public CustomLaunchConfiguration(Map<String, Object> attr) {
			this.attr = attr;
			log.info("Using ILaunchConfiguration from " + this.getClass().getName() + " with attributes:");
			log.info(this.toString());
		}

		@Override
		public String toString() {
			return this.attr.entrySet().toString();
		}

		@Override
		public boolean contentsEqual(ILaunchConfiguration iLaunchConfiguration) {
			return iLaunchConfiguration.contentsEqual(this);
		}

		@Override
		public ILaunchConfigurationWorkingCopy copy(String s) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public void delete() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean exists() {
			return false;
		}

		private <T> T get(String key, T defaultValue) {
			Object val = this.attr.get(key);
			if (null == val) {
				return defaultValue;
			} else {
				return (T) val;
			}
		}

		@Override
		public boolean getAttribute(String s, boolean b) throws CoreException {
			return this.get(s, b);
		}

		@Override
		public int getAttribute(String s, int i) throws CoreException {
			return this.get(s, i);
		}

		@Override
		public List<String> getAttribute(String s, List<String> list) throws CoreException {
			return this.get(s, list);
		}

		@Override
		public Set<String> getAttribute(String s, Set<String> set) throws CoreException {
			return this.get(s, set);
		}

		@Override
		public Map<String, String> getAttribute(String s, Map<String, String> map) throws CoreException {
			return this.get(s, map);
		}

		@Override
		public String getAttribute(String s, String s1) throws CoreException {
			return this.get(s, s1);
		}

		@Override
		public Map<String, Object> getAttributes() throws CoreException {
			return this.attr;
		}

		@Override
		public String getCategory() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public IFile getFile() {
			throw new RuntimeException("NYI");
		}

		@Override
		public IPath getLocation() {
			throw new RuntimeException("NYI");
		}

		@Override
		public IResource[] getMappedResources() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public String getMemento() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public String getName() {
			throw new RuntimeException("NYI");
		}

		@Override
		public Set<String> getModes() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunchDelegate getPreferredDelegate(Set<String> set) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunchConfigurationType getType() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunchConfigurationWorkingCopy getWorkingCopy() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean hasAttribute(String s) throws CoreException {
			return this.attr.containsKey(s);
		}

		@Override
		public boolean isLocal() {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean isMigrationCandidate() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean isWorkingCopy() {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunch launch(String s, IProgressMonitor iProgressMonitor) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunch launch(String s, IProgressMonitor iProgressMonitor, boolean b) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public ILaunch launch(String s, IProgressMonitor iProgressMonitor, boolean b, boolean b1) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public void migrate() throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean supportsMode(String s) throws CoreException {
			throw new RuntimeException("NYI");
		}

		@Override
		public boolean isReadOnly() {
			throw new RuntimeException("NYI");
		}

		@Override
		public Object getAdapter(Class aClass) {
			throw new RuntimeException("NYI");
		}
	}

}
