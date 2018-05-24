package de.tubs.areva.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.solver.models.PCMInstance;
import org.palladiosimulator.solver.runconfig.PCMSolverWorkflowRunConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class PCMTestJob implements IBlackboardInteractingJob<MDSDBlackboard> {

	private MDSDBlackboard blackboard;
	
	public PCMTestJob(final PCMSolverWorkflowRunConfiguration configuration) {
		
	}
	
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

		PCMInstance currentModel = new PCMInstance(
				(PCMResourceSetPartition) this.blackboard
						.getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID));
		
		
			
		for(AssemblyContext assembly: currentModel.getAllocation().getSystem_Allocation().getAssemblyContexts__ComposedStructure())
		{
			BasicComponent component = (BasicComponent) assembly.getEncapsulatedComponent__AssemblyContext();
			System.out.println("Component found: " + component.getEntityName());
			
			EList<String> strings = StereotypeAPI.getTaggedValue(component, "keyValues", "BasicComponentTag");
			
			for(String string: strings) {
				System.out.println("" + string);
			}
		}
		
		
	}

	@Override
	public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
		
	}

	@Override
	public String getName() {
		return "Run ArEva PCM Test Job";
	}

	@Override
	public void setBlackboard(MDSDBlackboard blackboard) {
		this.blackboard = blackboard;
	}

}
