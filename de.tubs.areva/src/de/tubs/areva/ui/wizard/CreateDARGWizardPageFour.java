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

public class CreateDARGWizardPageFour extends WizardPage {

	private Composite container;
	
	public URI qadagFile = null;
	public boolean generateQadag = true;
	
	public CreateDARGWizardPageFour() {
		super("Generate default QADAG");
		setTitle("Generate default QADAG");
		setDescription("Generate a default QADAG for use in Operation Mode Selection");
	}
	
	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		
		Button button = new Button(container, SWT.CHECK);
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true,
		        false);
		
	    gridData = new GridData(SWT.BEGINNING, SWT.CENTER, false,
		        false);
	    gridData.horizontalSpan = 2;
	    
	    button.setLayoutData(gridData);
	    button.setText("Use custom QADAG");
	    button.setSelection(true);
	    button.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        	
	        generateQadag = button.getSelection();
	        container.layout();
	      }
	    });
	    
	    setControl(container);
	    setPageComplete(true);
	}

	
}
