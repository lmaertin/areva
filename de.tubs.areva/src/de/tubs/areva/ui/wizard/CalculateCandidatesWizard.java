package de.tubs.areva.ui.wizard;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.Wizard;

public class CalculateCandidatesWizard extends Wizard {
	
	public CreateDARGWizardPageFastSetup fastSetupPage = null;
	public CreateDARGWizardPageOneThree systemPage = null;
	public CreateDARGWizardPageOneTwo designDescisionsPage = null;
	public SelectResourceRelationsPage resourceRelationsPage = null;
	public SelectUsageScenarioNamePage usageModelPage = null;
	public CreateDARGWizardPageThree outputPage = null;
	
	public boolean finished = false;
	
	@Override
	public boolean performFinish() {
		
		if(!fastSetupPage.getInput().isEmpty()) {
			System.out.println(fastSetupPage.getInput());
			
			String[] inputs = fastSetupPage.getInput().split("\\r?\\n");
			
			System.out.println(inputs.length);
			for(String input: inputs) {
				System.out.println(input);
			}
			
			systemPage.systemFile = URI.createPlatformResourceURI(inputs[0], true);
			designDescisionsPage.designdecisionsFile = URI.createPlatformResourceURI(inputs[1], true);
			resourceRelationsPage.resourceRelationsFile = URI.createPlatformResourceURI(inputs[2], true);
			usageModelPage.usageScenarioName = inputs[3];
			outputPage.outputDirectory = inputs[4];
		}
		
		finished = true;
		return true;
	}
	
	public CalculateCandidatesWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public boolean canFinish() {
		
		if(!fastSetupPage.input.isEmpty()) {
			return true;
		}
		
		return systemPage.isPageComplete() 
				&& systemPage.isPageComplete()
				&& designDescisionsPage.isPageComplete()
				&& resourceRelationsPage.isPageComplete()
				&& usageModelPage.isPageComplete();
	}
	
	@Override 
	public void addPages() {
		
		fastSetupPage = new CreateDARGWizardPageFastSetup();
		addPage(fastSetupPage);
		
		systemPage = new CreateDARGWizardPageOneThree();
		addPage(systemPage);
		
		designDescisionsPage = new CreateDARGWizardPageOneTwo();
		addPage(designDescisionsPage);
		
		resourceRelationsPage = new SelectResourceRelationsPage();
		addPage(resourceRelationsPage);
		
		usageModelPage = new SelectUsageScenarioNamePage();
		addPage(usageModelPage);
		
		outputPage = new CreateDARGWizardPageThree();
		addPage(outputPage);
	}
	
	@Override
	public String getWindowTitle() {
		return "Calculate Candidates";
	}

}
