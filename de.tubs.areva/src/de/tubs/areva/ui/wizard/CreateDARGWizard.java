package de.tubs.areva.ui.wizard;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.Wizard;

public class CreateDARGWizard extends Wizard {

	public CreateDARGWizardPageFastSetup pageFastSetup = null;
	public CreateDARGWizardPageOneOne pageOneOne = null;
	public CreateDARGWizardPageOneTwo pageOneTwo = null;
	public CreateDARGWizardPageOneThree pageOneThree = null;
	public CreateDARGWizardPageTwo pageTwo = null;
	public CreateDARGWizardPageTwoTwo pageTwoTwo = null;
	public CreateDARGWizardPageThree pageThree = null;
	public CreateDARGWizardPageFour pageFour = null;
	public CreateDARGWizardPageFastSetupOutput pageFive = null;
	public SelectResourceThresholdPage thresholdPage = null;
	
	public boolean finished = false;
	
	public CreateDARGWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public String getWindowTitle() {
		return "Create DARG";
		
	}
	
	@Override
	public boolean canFinish() {
		
		if(!pageFastSetup.input.isEmpty()) {
			return true;
		}
		
		return pageOneOne.isPageComplete() 
				&& pageOneTwo.isPageComplete() 
				&& pageOneThree.isPageComplete() 
				&& pageTwo.isPageComplete()
				&& pageTwoTwo.isPageComplete()
				&& pageThree.isPageComplete() 
				&& pageFour.isPageComplete();
	}
	
	@Override public void addPages() {
		
		pageFastSetup = new CreateDARGWizardPageFastSetup();
		addPage(pageFastSetup);
		
		pageOneThree = new CreateDARGWizardPageOneThree();
		addPage(pageOneThree);
		
		pageOneTwo = new CreateDARGWizardPageOneTwo();
		addPage(pageOneTwo);
		
		pageOneOne = new CreateDARGWizardPageOneOne();
		addPage(pageOneOne);
		
		
		
		pageTwo = new CreateDARGWizardPageTwo();
		addPage(pageTwo);
		pageTwoTwo = new CreateDARGWizardPageTwoTwo();
		addPage(pageTwoTwo);
		
		pageThree = new CreateDARGWizardPageThree();
		addPage(pageThree);
		
		pageFour = new CreateDARGWizardPageFour();
		addPage(pageFour);
		
		pageFive = new CreateDARGWizardPageFastSetupOutput();
		addPage(pageFive);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		if(!pageFastSetup.getInput().isEmpty()) {
			System.out.println(pageFastSetup.getInput());
			
			String[] inputs = pageFastSetup.getInput().split("\\r?\\n");
			
			System.out.println(inputs.length);
			for(String input: inputs) {
				System.out.println(input);
			}
			
			pageOneOne.resourceRelationsFile = URI.createPlatformResourceURI(inputs[2], true);
			pageOneTwo.designdecisionsFile = URI.createPlatformResourceURI(inputs[1], true);
			pageOneThree.systemFile = URI.createPlatformResourceURI(inputs[0], true);
			pageTwo.candidatesFile = URI.createPlatformResourceURI(inputs[3], true);
			pageTwoTwo.candidatesFile = URI.createPlatformResourceURI(inputs[4], true);
			pageThree.outputDirectory = inputs[5];
			//pageFour.qadagFile = URI.createPlatformResourceURI(inputs[5]);
		}
		
		finished = true;
		return true;
	}

}
