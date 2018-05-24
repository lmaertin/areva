package de.uka.ipd.sdq.dsexplore.launch;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.analyzer.workflow.jobs.PreparePCMBlackboardPartitionJob;
import org.palladiosimulator.analyzer.workflow.jobs.ValidatePCMModelsJob;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.ICompositeJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class PerOpteryxJob extends
SequentialBlackboardInteractingJob<MDSDBlackboard> implements
ICompositeJob {

    //DSECandidateConfiguration candidateConfig;

    public  PerOpteryxJob(final DSEWorkflowConfiguration config, final DSELaunch launch) throws CoreException {
        super(false);

        // 1. Load PCM Models into memory
        this.addJob(new LoadPCMModelsIntoBlackboardJob(config));

        // 2. Validate PCM Models
        this.addJob(new ValidatePCMModelsJob(config));

        // 3. Copy initial instance to separate blackboard partition
        this.add(new MoveInitialPCMModelPartitionJob());

        // 4 Create the PCM partition anew but empty
        this.add(new PreparePCMBlackboardPartitionJob());

        // 5. Run Optimisation on Loaded Models
        this.add(new OptimisationJob(config, launch));

    }

    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
        super.cleanup(monitor);
        monitor.worked(1);
    }


}
