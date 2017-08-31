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

public class CreateDARGWizardPageTwoTwo extends WizardPage {

private Composite container;
	
	public URI candidatesFile = null;
	
	public CreateDARGWizardPageTwoTwo() {
		super("Choose optimal candidates file");
		setTitle("Choose optimal candidates file");
		setDescription("Choose DSE's optimal candidates file (.csv) corresponding to the chosen allocation model.");
	}
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		
		Label label = new Label(container, SWT.NORMAL);
	    label.setText("Candidates File:");
	    
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
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Optimal Candidates File", "Choose .csv optimal candidates file:", new String[] {"csv"}, container.getShell());
	        resourceDialog.open();
	        
	        if(resourceDialog.getFirstResult() != null) {
	        
		        candidatesFile = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString());
		        
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
	    if(candidatesFile != null) {
	    	setPageComplete(true);
	    } else {
	    	setPageComplete(false);
	    }
	}

}
