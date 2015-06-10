package areva.defaultPerspective;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * In this view a user can select the architecture, parse and arch.-type, which is used for the
 * evaluation
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class XMLSelection extends ViewPart {
	FileDialog fd;
	private Document doc;
	private final String STORAGE_NAME = "architectureSettings.xml";
	private File storageFile;
	private File file;
	private String parserName;
	private String parserFolder;
	private String architectureName;
	private String architectureType;
	// together with archtiecturePath -> full path of the architecture
	private String architectureFolder;
	private Element rootElement;

	public XMLSelection() {
		super();
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void createPartControl(Composite parent) {
		loadSettings();
		// View Layout
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 8;
		parent.setLayout(gridLayout);

		// "type" label
		Label label = new Label(parent, SWT.NULL);
		label.setText("type: ");
		fd = new FileDialog(parent.getShell(), SWT.OPEN);

		// "type" combo box
		final Combo type = new Combo(parent, SWT.SINGLE | SWT.READ_ONLY);
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.grabExcessHorizontalSpace = true;
		type.setLayoutData(gridData2);
		type.add("Palladio system");
		type.add("AUTOSAR");
		type.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				architectureType = type.getText();
				saveSettings();
			}
		});

		// "selected architecture" label
		label = new Label(parent, SWT.NULL);
		label.setText("architecture: ");

		// "selected architecture" textfield
		final Label xmlName = new Label(parent, SWT.SINGLE | SWT.BORDER);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 1;
		gridData.widthHint = 100;
		xmlName.setLayoutData(gridData);

		// "selected parser" label
		label = new Label(parent, SWT.NULL);
		label.setText("parser: ");

		// "selected parser" label
		final Label xmlNameParser = new Label(parent, SWT.SINGLE | SWT.BORDER);
		xmlNameParser.setLayoutData(gridData);

		// "select" button
		Button b1 = new Button(parent, SWT.NULL);
		b1.setText("select architecture");
		b1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				fd.open();
				architectureName = fd.getFileName();
				architectureFolder = fd.getFilterPath();
				xmlName.setText(architectureName);
				saveSettings();
			}
		});

		// "select parser button" button
		Button b2 = new Button(parent, SWT.NULL);
		b2.setText("select parser");
		b2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				fd.open();
				parserName = fd.getFileName();
				parserFolder = fd.getFilterPath();
				xmlNameParser.setText(parserName);
				saveSettings();
			}
		});

		//if there is a architecture type set the box to it, else ""
		if (architectureType == null)
			type.setText("");
		else
			type.setText(architectureType);
		
		//same for the architecture name
		if (architectureName == null)
			xmlName.setText("");
		else
			xmlName.setText(architectureName);
		//and the name of the parser
		if (parserName == null)
			xmlNameParser.setText("");
		else
			xmlNameParser.setText(parserName);
		parent.pack();
		saveSettings();

	}

	/**
	 * loads the settings out of a xml file, if there is none, creates one
	 */
	private void loadSettings() {
		try {
			//getWorkspace
			IWorkspace workspace = ResourcesPlugin.getWorkspace();

			//try the load the file
			file = workspace.getRoot().getLocation().toFile();
			storageFile = new File(file.getPath() + "/" + STORAGE_NAME);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(storageFile);

			doc.getDocumentElement().normalize();

			//scans the xml-file...
			
			Element e = (Element) doc.getElementsByTagName("project").item(0);
			rootElement = e;

			e = (Element) rootElement.getElementsByTagName("parserName")
					.item(0);
			parserName = e.getAttribute("path");
			e = (Element) rootElement.getElementsByTagName("parserFolder")
					.item(0);
			parserFolder = e.getAttribute("path");
			e = (Element) rootElement.getElementsByTagName("architectureName")
					.item(0);
			architectureName = e.getAttribute("path");
			e = (Element) rootElement.getElementsByTagName("architectureType")
					.item(0);
			architectureType = e.getAttribute("type");
			e = (Element) rootElement
					.getElementsByTagName("architectureFolder").item(0);
			architectureFolder = e.getAttribute("path");

		} catch (Exception e) {
			// e.printStackTrace();
			//If an error occurs, try to create a new file
			initializeXml();
		}

	}

	/**
	 *saves the settings, if there is an error, tries to write a new settings file
	 */
	private void saveSettings() {
		try {
			Element e = null;
			e = (Element) rootElement.getElementsByTagName("parserName")
					.item(0);
			e.setAttribute("path", parserName);

			e = (Element) rootElement.getElementsByTagName("parserFolder")
					.item(0);
			e.setAttribute("path", parserFolder);

			e = (Element) rootElement.getElementsByTagName("architectureName")
					.item(0);
			e.setAttribute("path", architectureName);

			e = (Element) rootElement.getElementsByTagName("architectureType")
					.item(0);
			e.setAttribute("type", architectureType);

			e = (Element) rootElement
					.getElementsByTagName("architectureFolder").item(0);
			e.setAttribute("path", architectureFolder);

			// if there is an error, create new settings file
		} catch (Exception e) {
			e.printStackTrace();
			initializeXml();
			loadSettings();
		}

		saveIntoFile();

	}

	/**
	 * creates a xml file
	 */
	private void initializeXml() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			doc = docBuilder.newDocument();

			rootElement = doc.createElement("project");
			doc.appendChild(rootElement);

			Element e = null;
			e = doc.createElement("parserName");

			rootElement.appendChild(e);
			e = doc.createElement("parserFolder");
			rootElement.appendChild(e);
			e = doc.createElement("architectureName");
			rootElement.appendChild(e);
			e = doc.createElement("architectureFolder");
			rootElement.appendChild(e);
			e = doc.createElement("architectureType");
			rootElement.appendChild(e);

			saveIntoFile();

		} catch (Exception e) {
			System.out.println("initializesArevaXML in Storage");
			e.printStackTrace();
		}
	}

	/**
	 * saves the changes into the file
	 */
	public void saveIntoFile() {
		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(doc),
					new StreamResult(new File(file.getPath() + "/"
							+ STORAGE_NAME)));

		} catch (Exception e) {
			System.out.println("save in Storage");
			e.printStackTrace();
		}
	}
	
	public String getArchitecturePathFull() {
		return architectureFolder + "\\" + architectureName;
	}
	
	public String getParserPathFull() {
		return parserFolder + "\\" + parserName;
	}
	
	public String getArchitectureType() {
		return architectureType;
	}
	
	public String getParserName() {
		return parserName;
	}
	
	public String getParserFolder() {
		return parserFolder;
	}
}
