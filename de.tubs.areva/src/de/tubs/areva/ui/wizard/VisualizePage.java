package de.tubs.areva.ui.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.ui.dialog.ResourceFileSelectionDialog;

public class VisualizePage extends WizardPage {

	private Composite container;
	private List<String> architectureOptions = null;
	private List<String> allResources = null;
	private List<String> defaultPath = null;
	private List<Combo> pathCombos = new ArrayList<Combo>();
	private Text pathSelectionText = null;
	
	public List<String> pathSelection = null;
	public Combo architectureCombo = null;
	public int selectedMode = 0;
	public String architectureSelection = "";
	
	
	public int selection = 1;
	
	public VisualizePage(List<String> allResources, List<String> defaultPath, List<String> architectureOptions) {
		super("Visualize Results");
		setTitle("Visualize Results");
		setDescription("Select different options to visualize results.");
		this.allResources = allResources;
		this.defaultPath = defaultPath;
		this.architectureOptions = architectureOptions;
	}
	
	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.NONE);
		
		container.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Group optionsGroup = new Group(container, SWT.NONE);
		optionsGroup.setLayout(new RowLayout(SWT.VERTICAL));
		optionsGroup.setText("Visualization Options");
		
		Button singleArchitecture = new Button(optionsGroup, SWT.RADIO);
		singleArchitecture.setText("Achitecture");
		singleArchitecture.setData(1);
		
		Button tailoredPath = new Button(optionsGroup, SWT.RADIO);
		tailoredPath.setText("Path (tailored)");
		tailoredPath.setData(2);
		
		Button fullPath = new Button(optionsGroup, SWT.RADIO);
		fullPath.setText("Path (full)");
		fullPath.setData(3);
		
		setControl(container);
		
		final Group architectureGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
		architectureGroup.setText("Architecture Options");
		architectureGroup.setEnabled(false);
		architectureGroup.setLayout(new RowLayout(SWT.VERTICAL));
		
		Combo combo = new Combo(architectureGroup, SWT.READ_ONLY);
		combo.setItems();
		combo.setSize(250, 65);
		combo.setItems(architectureOptions.toArray(new String[architectureOptions.size()]));
		architectureCombo = combo;
		combo.addModifyListener(new ModifyListener(){

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				architectureSelection = architectureCombo.getText();
			}});
		combo.select(0);
		
		final Group pathGroup = new Group(container, SWT.SHADOW_ETCHED_IN);
		pathGroup.setText("Path Options");
		pathGroup.setEnabled(false);
		pathGroup.setLayout(new GridLayout(1, true));
		
		SelectionListener selectionListener = new SelectionAdapter () {
	         public void widgetSelected(SelectionEvent event) {
	            selection = (int) ((Button) event.getSource()).getData();
	            selectedMode = selection;
	            if(selection >= 2) {
	            	pathGroup.setEnabled(true);
	            	architectureGroup.setEnabled(false);
	            } else {
	            	pathGroup.setEnabled(false);
	            	architectureGroup.setEnabled(true);
	            }
	         };
	    };
	    singleArchitecture.addSelectionListener(selectionListener);
		tailoredPath.addSelectionListener(selectionListener);
		fullPath.addSelectionListener(selectionListener);
		
		setPageComplete(true);
		
		pathSelection = new ArrayList<String>(defaultPath);
		
		List<String> allResourcesLocal = new ArrayList<String>(allResources);
		int i = 0;
		for(String pathResource: defaultPath) {
			combo = new Combo(pathGroup, SWT.READ_ONLY);
			pathCombos.add(combo);
			combo.setItems(getOptions(allResources, defaultPath, i));
			combo.setSize(250, 65);
			combo.setText(pathResource);
			combo.addModifyListener(new ModifyListener(){

				@Override
				public void modifyText(ModifyEvent e) {
					// TODO Auto-generated method stub
					pathSelection = new ArrayList<String>();
					String pathSelectionString = "";
					for(Combo combo: pathCombos) {
						pathSelection.add(combo.getText());
						pathSelectionString += combo.getText() + ";";
					}
					pathSelectionString = pathSelectionString.substring(0, pathSelectionString.length()-1);
					pathSelectionText.setText(pathSelectionString);
				}});
			
			allResourcesLocal.remove(pathResource);
			combo.requestLayout();
			i++;
		}
		pathSelectionText = new Text(pathGroup, SWT.BORDER | SWT.H_SCROLL);
		pathSelectionText.addModifyListener(new ModifyListener(){
			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				pathSelection = new ArrayList<String>();
				String[] resources = pathSelectionText.getText().split(";");
				for(String resource: resources) {
					pathSelection.add(resource);
				}
			}});
		pathSelectionText.setSize(250, 65);
		String pathSelectionString = "";
		for(Combo pathCombo: pathCombos) {
			pathSelectionString += pathCombo.getText() + ";";
		}
		pathSelectionString = pathSelectionString.substring(0, pathSelectionString.length()-1);

		GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
		data.widthHint = 250;
		pathSelectionText.setLayoutData(data);
		pathSelectionText.setText(pathSelectionString);
	}
	
	private String[] getOptions(List<String> allResources, List<String> defaultPath, int index) {
		
		List<String> options = new ArrayList<String>(allResources);
		for(int i = 0; i < index; i++) {
			options.remove(defaultPath.get(i));
		}
		
		return options.toArray(new String[options.size()]);
	}

}
