package de.tubs.areva.ui.wizard;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.Wizard;

public class VisualizeWizard extends Wizard {
	
	private List<String> allResources = null;
	private List<String> defaultPath = null;
	private List<String> architectureOptions = null;
	
	public VisualizePage visualizePage = null;
	
	public boolean finished = false;
	
	public VisualizeWizard(List<String> allResources, List<String> defaultPath, List<String> architectureOptions) {
		super();
		setNeedsProgressMonitor(true);
		this.allResources = allResources;
		this.defaultPath = defaultPath;
		this.architectureOptions = architectureOptions;
	}
	
	@Override
	public String getWindowTitle() {
		return "Create DARG";
		
	}
	
	@Override
	public boolean canFinish() {
		
		return true;
	}
	
	@Override public void addPages() {
		
		visualizePage = new VisualizePage(allResources, defaultPath, architectureOptions);
		addPage(visualizePage);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		
		finished = true;
		return true;
	}

}
