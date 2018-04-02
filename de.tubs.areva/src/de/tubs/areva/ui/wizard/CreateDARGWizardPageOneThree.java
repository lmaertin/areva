package de.tubs.areva.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.tubs.areva.ui.dialog.ResourceFileSelectionDialog;

public class CreateDARGWizardPageOneThree extends WizardPage {

	private Composite container;
	
	public URI systemFile = null;
	
	public CreateDARGWizardPageOneThree() {
		super("Choose system model");
		setTitle("Choose system model");
		setDescription("Choose the Palladio system model (.system).");
	}
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		
		Label label = new Label(container, SWT.NORMAL);
	    label.setText("System File:");
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
		        false);
	    
	    Label labelResource = new Label(container, SWT.BORDER);
	    labelResource.setText("");
	    labelResource.setLayoutData(gridData);
	    
	    gridData = new GridData(SWT.END, SWT.CENTER, false,
		        false);
	    gridData.horizontalSpan = 2;
	    
	    Button button = new Button(container, SWT.PUSH);
	    button.setLayoutData(gridData);
	    /*
	    button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	        */
	    button.setText("Browse workspace...");
	    button.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("System Model", "Choose .system model:", new String[] {"system"}, container.getShell());
	        resourceDialog.open();
	        if(resourceDialog.getFirstResult() != null) {
	        	systemFile = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true);
	        
	        	System.out.println("" + ((IFile)resourceDialog.getFirstResult()).getFullPath().toString());
	        	labelResource.setText("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        	
	        	setPageComplete(true);
	        } else {
	        	setPageComplete(false);
	        }
	        container.layout();
	      }
	    });
	    
	    setControl(container);
	    if(systemFile != null) {
	    	setPageComplete(true);
	    } else {
	    	setPageComplete(false);
	    }
	}

}