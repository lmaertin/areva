package de.tubs.areva.jobs;

import org.eclipse.debug.core.ILaunch;
import org.palladiosimulator.analyzer.accuracy.jobs.TransformPCMForAccuracyInfluenceAnalysisJob;
import org.palladiosimulator.analyzer.workflow.jobs.EventsTransformationJob;
import org.palladiosimulator.analyzer.workflow.jobs.LoadMiddlewareConfigurationIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.StoreAllPCMModelsJob;
import org.palladiosimulator.analyzer.workflow.jobs.ValidatePCMModelsJob;
import org.palladiosimulator.solver.RunPCMAnalysisJob;
import org.palladiosimulator.solver.runconfig.PCMSolverWorkflowRunConfiguration;

import de.uka.ipd.sdq.workflow.jobs.ICompositeJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class PCMSolverJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> implements ICompositeJob {

	public PCMSolverJob(PCMSolverWorkflowRunConfiguration config, ILaunch launch) {
		super(false);

		// 0. NILS
		this.addJob(new TestJob(config));
		
		// 1. Load PCM Models into memory
		// This now also creates a plug-in in the workspace and stores temporary
		// data there:
		this.addJob(new LoadPCMModelsIntoBlackboardJob(config));
		this.addJob(new LoadMiddlewareConfigurationIntoBlackboardJob(config));

		// 2. Validate PCM Models
		this.addJob(new ValidatePCMModelsJob(config));

		// -- Stage Model modification
		// 3. Modification for AccuracyInfluenceAnalysis
		if (config.isAccuracyInfluenceAnalysisEnabled()) {
			this.add(new TransformPCMForAccuracyInfluenceAnalysisJob(config));
		}

		// 4. Transform Event Model Elements
		this.add(new EventsTransformationJob(config));

		// -- Stage analysis
		// 8. Store resulting model(s)
		this.add(new StoreAllPCMModelsJob(config));

		// 3. Run Analysis on Loaded Models
		this.add(new RunPCMAnalysisJob(config));
		
		this.add(new PCMTestJob(config));
	}

}