package de.tubs.areva.ui.wizard;

import java.util.ArrayList;
import java.util.List;

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

public class SelectQadagPage extends WizardPage {

	private Composite container;
	
	//URI qadagFile = null;
	List<URI> qadagFiles = new ArrayList<URI>();
	
	public SelectQadagPage() {
		super("Choose Operating Mode Model");
		setTitle("Choose Operating Mode Model");
		setDescription("Choose the ArEva Operating Mode Model (.qadag).");
	}
	/*
	public URI getQadagFile() {
		return qadagFile;
	}
	*/
	
	
	public List<URI> getQadagFiles() {
		return qadagFiles;
	}
	
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label = new Label(container, SWT.NORMAL);
	    label.setText("Qadag Files:");
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
		        false);
	    
	    //Label labelResource = new Label(container, SWT.BORDER);
	    //labelResource.setText("");
	    //labelResource.setLayoutData(gridData);
	    

		org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(container, SWT.NORMAL);
		list.setLayoutData(gridData);
	    
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
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Qadag Model", "Choose .qadag model:", new String[] {"qadag"}, container.getShell());
	        resourceDialog.open();
	        if(resourceDialog.getFirstResult() != null) {
	        	URI qadagURI = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true);
	        	//qadagFile = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true);
	        
	        	System.out.println("" + ((IFile)resourceDialog.getFirstResult()).getFullPath().toString());
	        	//labelResource.setText("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        	list.add("" + qadagURI);
	        	qadagFiles.add(qadagURI);
	        	setPageComplete(true);
	        } else {
	        	//setPageComplete(false);
	        }
	        container.layout();
	      }
	    });
	    
	    setControl(container);
	    if(qadagFiles.size() != 0) {
	    	setPageComplete(true);
	    } else {
	    	setPageComplete(false);
	    }
	}

}
