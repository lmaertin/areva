package de.tubs.areva.ui.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.tubs.areva.ui.dialog.ResourceFileSelectionDialog;


public class CreateARGWizard extends Dialog {
	
	URI allocationFile = null;
	URI qadagFile = null;
	URI candidatesFile = null;
	URI newickFile = null;
	String outputDirectory = "";
	
	public URI getAllocationFile() {
		return allocationFile;
	}
	
	public URI getQadagFile() {
		return qadagFile;
	}
	
	public URI getCandidatesFile() {
		return candidatesFile;
	}
	
	public URI getNewickFile() {
		return newickFile;
	}
	
	public String getOutputDirectory() {
		return outputDirectory;
	}
	
	public CreateARGWizard(Shell parentShell) {
		super(parentShell);
		
		
		
	}
	
	@Override
	  protected Control createDialogArea(Composite parent) {
		
	    Composite container = (Composite) super.createDialogArea(parent);
	    
	    Label label = new Label(container, SWT.NORMAL);
	    label.setText("Allocation File:");
	    
	    Label labelResource = new Label(container, SWT.BORDER);
	    labelResource.setText("Nothing");
	    
	    Button button = new Button(container, SWT.PUSH);
	    button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	    button.setText("Browse workspace...");
	    button.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Allocation Model", "Choose .allocation model:", new String[] {"allocation"}, container.getShell());
	        resourceDialog.open();
	        
	        allocationFile = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true);
	        
	        System.out.println("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        labelResource.setText("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        container.layout();
	      }
	    });
	    
	    Label label3 = new Label(container, SWT.NORMAL);
	    label3.setText("Candidates File:");
	    
	    Label labelCSV = new Label(container, SWT.BORDER);
	    labelCSV.setText("Nothing");
	    
	    Button button3 = new Button(container, SWT.PUSH);
	    button3.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	    button3.setText("Browse workspace...");
	    button3.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Candidates File", "Choose .csv candidates file:", new String[] {"csv"}, container.getShell());
	        resourceDialog.open();
	        
	        candidatesFile = URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString());
	        
	        System.out.println("" + URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString()));
	        labelCSV.setText("" + URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString()));
	        container.layout();
	      }
	    });
	    
	    Label labelNewickLabel = new Label(container, SWT.NORMAL);
	    labelNewickLabel.setText("Newick File:");
	    
	    Label labelNewick = new Label(container, SWT.BORDER);
	    labelNewick.setText("Nothing");
	    
	    Button buttonNewick = new Button(container, SWT.PUSH);
	    buttonNewick.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	    buttonNewick.setText("Browse workspace...");
	    buttonNewick.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Newick File", "Choose .newick file:", new String[] {"newick"}, container.getShell());
	        resourceDialog.open();
	        
	        newickFile = URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString());
	        
	        System.out.println("" + URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString()));
	        labelNewick.setText("" + URI.createFileURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString()));
	        container.layout();
	      }
	    });
	    
	    Label label2 = new Label(container, SWT.NORMAL);
	    label2.setText("Qadag File:");
	    
	    Label labelQadag = new Label(container, SWT.BORDER);
	    labelQadag.setText("Nothing");
	    
	    Button button2 = new Button(container, SWT.PUSH);
	    button2.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	    button2.setText("Browse workspace...");
	    button2.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        ResourceFileSelectionDialog resourceDialog = new ResourceFileSelectionDialog("Qadag Model", "Choose .qadag model:", new String[] {"qadag"}, container.getShell());
	        resourceDialog.open();
	        
	        qadagFile = URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true);
	        
	        System.out.println("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        labelQadag.setText("" + URI.createPlatformResourceURI(((IFile)resourceDialog.getFirstResult()).getFullPath().toString(), true));
	        container.layout();
	      }
	    });
	    
	    Label label4 = new Label(container, SWT.NORMAL);
	    label4.setText("Output Directory:");
	    
	    Label labelDirectory = new Label(container, SWT.BORDER);
	    labelDirectory.setText("Nothing");
	    
	    Button buttonDirectory = new Button(container, SWT.PUSH);
	    buttonDirectory.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
	        false));
	    buttonDirectory.setText("Browse workspace...");
	    buttonDirectory.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        DirectoryDialog dialog = new DirectoryDialog(container.getShell());
	        dialog.setMessage("Choose output directory:");
	        dialog.setFilterPath(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile().getPath().toString());
	        
	        outputDirectory = dialog.open();
	        
	        labelDirectory.setText("" + outputDirectory);
	        container.layout();
	      }
	    });
	    
	    return container;
	  }

	  // overriding this methods allows you to set the
	  // title of the custom dialog
	  @Override
	  protected void configureShell(Shell newShell) {
	    super.configureShell(newShell);
	    newShell.setText("Create new ARG");
	  }

	  @Override
	  protected Point getInitialSize() {
	    return new Point(450, 800);
	  }
}
