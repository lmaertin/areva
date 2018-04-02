package de.tubs.areva.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.tubs.areva.ui.dialog.ResourceFileSelectionDialog;

public class CreateDARGWizardPageFastSetupOutput extends WizardPage {

	private Composite container;
	CreateDARGWizard wizard;
	public Text textBox;
	
	String output = "";
	
	public CreateDARGWizardPageFastSetupOutput() {
		super("Quick Setup Save String");
		setTitle("Quick Setup Save String");
		setDescription("Copy this string into the Quick Setup wizard to quickly repeat the setup process.");
	}
	
	public String getOutput() {
		return output;
	}
	
	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
	    
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true,
		        true);
	    
	    textBox = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    textBox.setLayoutData(gridData);
	    
	    setControl(container);
	    setPageComplete(true);
	}
}
