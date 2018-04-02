package de.tubs.areva.jobs;

import org.eclipse.debug.core.ILaunch;
import org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration;
import org.palladiosimulator.analyzer.workflow.jobs.PCMWorkflowJobBuilder;
import org.palladiosimulator.solver.runconfig.PCMSolverWorkflowRunConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IJob;

/**
 * This class creates a workflow for a PCM solver analysis.
 * 
 * The top-level job of this workflow is the PCMSolverJob.
 * 
 * @author brosch
 * 
 */
public class PCMSolverWorkflowJobBuilder extends PCMWorkflowJobBuilder {

	/**
	 * Eclipse launch object which is passed to the top-level job.
	 */
	private ILaunch launch;

	/**
	 * The constructor.
	 * @param launch the launch object
	 */
	public PCMSolverWorkflowJobBuilder(ILaunch launch) {
		this.launch = launch;
	}

	/*
	 * (non-Javadoc)
	 * @see org.palladiosimulator.analyzer.workflow.jobs.PCMWorkflowJobBuilder#buildJob(org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration)
	 */
	public IJob buildJob(AbstractPCMWorkflowRunConfiguration config) {
		return new PCMSolverJob(
				(PCMSolverWorkflowRunConfiguration) config, launch);
	}

}