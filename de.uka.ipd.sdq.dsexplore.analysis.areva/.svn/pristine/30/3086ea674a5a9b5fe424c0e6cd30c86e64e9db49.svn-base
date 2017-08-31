package de.uka.ipd.sdq.dsexplore.analysis.areva;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.analyzer.workflow.runconfig.FileNamesInputTab;

import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer;
import de.uka.ipd.sdq.workflow.launchconfig.LaunchConfigPlugin;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;

public class ArevaAnalysisTab extends FileNamesInputTab implements
ILaunchConfigurationTab {

    private Text textArevaModel;

    @Override
    public void createControl(final Composite parent) {

        // Create a listener for GUI modification events:
        final ModifyListener modifyListener = new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                ArevaAnalysisTab.this.setDirty(true);
                ArevaAnalysisTab.this.updateLaunchConfigurationDialog();
            }
        };


        // Create a new Composite to hold the page's controls:
        final Composite container = new Composite(parent, SWT.NONE);
        setControl(container);
        container.setLayout(new GridLayout());

        /**
         * Add cost model input section
         */
        this.textArevaModel = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, "Areva Model File", DSEConstantsContainer.AREVA_MODEL_EXTENSION, textArevaModel, getShell(), DSEConstantsContainer.DEFAULT_AREVA_MODEL_FILE);
    }

    @Override
    public String getName() {
        return "Areva Analysis";
    }

    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            this.textArevaModel.setText(configuration.getAttribute(
                    DSEConstantsContainer.AREVA_FILE, ""));
        } catch (final CoreException e) {
            LaunchConfigPlugin.errorLogger(getName(),DSEConstantsContainer.AREVA_FILE, e.getMessage());
        }
    }

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(
                DSEConstantsContainer.AREVA_FILE,
                this.textArevaModel.getText());
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {

    }

    @Override
    public void activated(final ILaunchConfigurationWorkingCopy workingCopy) {
        // Leave this method empty to prevent unnecessary invocation of
        // initializeFrom() and multiple resulting invocations of
        // performApply().
    }

    @Override
    public void deactivated(final ILaunchConfigurationWorkingCopy workingCopy) {

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        final String extension = DSEConstantsContainer.AREVA_MODEL_EXTENSION[0].replace("*", "");
        if (this.textArevaModel.getText().equals("") || !this.textArevaModel.getText().contains(extension)){
            setErrorMessage("Areva model is missing!");
            return false;
        }
        return true;
    }



}
