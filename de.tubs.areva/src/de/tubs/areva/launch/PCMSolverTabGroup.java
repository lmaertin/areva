package de.tubs.areva.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.palladiosimulator.analyzer.workflow.runconfig.ConfigurationTab;
import org.palladiosimulator.analyzer.workflow.runconfig.FileNamesInputTab;

import de.uka.ipd.sdq.workflow.launchconfig.tabs.DebugEnabledCommonTab;

/**
 * The configuration tabs shown in the run dialog or debug dialog for the PCM Solver launch.
 * 
 * The extension point org.eclipse.debug.ui.launchConfigurationTabGroups in the plugin.xml refers to
 * this class. The class inherits from the eclipse-internal tab group.
 * 
 * @author koziolek, brosch
 * 
 */
public class PCMSolverTabGroup extends AbstractLaunchConfigurationTabGroup {

    /**
     * The main routine that creates and sets the individual tab pages.
     * 
     * @param dialog
     *            the run or debug dialog
     * @param mode
     *            distinguishes between run and debug modes
     * @see org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
     *      java.lang.String)
     */
    @Override
    public void createTabs(final ILaunchConfigurationDialog dialog, final String mode) {

        // Assemble the tab pages:
        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { new FileNamesInputTab(), new MainConfigTab(),
                new ConfigurationTab(), new DebugEnabledCommonTab() };

        // Do the setup:
        setTabs(tabs);
    }
}
