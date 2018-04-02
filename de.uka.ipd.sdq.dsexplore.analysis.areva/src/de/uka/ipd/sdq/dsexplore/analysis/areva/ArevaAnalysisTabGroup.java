package de.uka.ipd.sdq.dsexplore.analysis.areva;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

public class ArevaAnalysisTabGroup extends AbstractLaunchConfigurationTabGroup {

	public ArevaAnalysisTabGroup() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new ArevaAnalysisTab()
			};
			setTabs(tabs);
		
	}

	

}
