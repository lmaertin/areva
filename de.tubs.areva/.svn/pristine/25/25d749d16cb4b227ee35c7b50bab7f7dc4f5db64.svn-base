package de.tubs.areva.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.tubs.areva.ui.dialog.ResourceFileSelectionDialog;

public class SelectResourceThresholdPage extends WizardPage {

	private Composite container;
	
	String input = "";
	
	public SelectResourceThresholdPage() {
		super("Select Resource Difference Threshold");
		setTitle("Select Resource Difference Threshold");
		setDescription("Select maximum number of changed resources");
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
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true,
		        false);
	    
	    Text textBox = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP);
	    
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
	    
	    setControl(container);
	    
	    setPageComplete(true);
	}

}
