package de.tubs.areva.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.Wizard;

public class SelectOperatingModeWizard extends Wizard {

	public SelectArgPage argPage = null;
	public SelectQadagPage qadagPage = null;
	//public SelectNamePage namePage = null;
	public CreateDARGWizardPageThree outputPage = null;
	public SelectResourceThresholdPage thresholdPage = null;
	
	private IFile file;
	
	public boolean finished = false;
	
	public SelectOperatingModeWizard(IFile file) {
		super();
		setNeedsProgressMonitor(true);
		this.file = file;
	}
	
	@Override
	public String getWindowTitle() {
		return "Select Operating Mode";
		
	}
	
	@Override public void addPages() {
		
		argPage = new SelectArgPage(file);
		addPage(argPage);
		
		qadagPage = new SelectQadagPage(file);
		addPage(qadagPage);
		
		thresholdPage = new SelectResourceThresholdPage(file);
		addPage(thresholdPage);
		
		outputPage = new CreateDARGWizardPageThree(file);
		addPage(outputPage);
		
		
	}
	
	@Override
	public boolean performFinish() {
		
		finished = true;
		return true;
	}

}
