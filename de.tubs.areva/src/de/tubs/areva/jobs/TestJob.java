package de.tubs.areva.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.analyzer.workflow.configurations.AbstractPCMWorkflowRunConfiguration;

import de.uka.ipd.sdq.workflow.jobs.ICompositeJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class TestJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> implements ICompositeJob {

	AbstractPCMWorkflowRunConfiguration config;
	
	public TestJob(AbstractPCMWorkflowRunConfiguration config) {
		this.config = config;
	}
	
	@Override
	public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		System.out.println("ArEva jobs in progress...");
	}

	@Override
	public String getName() {
		return "Test Job";
	}
}
