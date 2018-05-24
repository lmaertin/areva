package de.tubs.areva.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class CreateDARGWizardPageFastSetup extends WizardPage {

	private Composite container;
	
	String input = "";
	
	public CreateDARGWizardPageFastSetup() {
		super("Quick Setup");
		setTitle("Quick Setup");
		setDescription("Insert all required paths or skip this page.");
	}
	
	public String getInput() {	
		return input;
	}
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    
	    Text textBox = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    
	    textBox.setLayoutData(gridData);
	    
	    textBox.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					
					if(!textBox.getText().isEmpty()) {
						input = textBox.getText();
						setPageComplete(true);
					} else {						
						input = "";
						setPageComplete(true);
					}
				}
		    });
	    

	    //add default values
	    textBox.setText("/validation/acs/pcm/aocs.system\n" +
	    				"/validation/acs/pcm/aocs.designdecision\n" +
	    				"/validation/acs/pcm/aocs.resourcerelations\n" +
	    				"aocs\n" +
	    				"C:\\Users\\maertin\\workspaces\\areva-runtime\\validation\\acs\\pcm\\AREva_results\\CandGen");
	    
	    setControl(container);
	    
	    setPageComplete(true);
	}

}