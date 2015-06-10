
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.JDOMParseException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;


public class ARXMLLoader {

	private File file;
	private Document doc;
	
	public ARXMLLoader(){
		
		//this.file = file;
	}
	
	/**
	 * Startet den Ladevorgang
	 * @return das geladene Project
	 * @throws JDOMException
	 * @throws IOException
	 */
	public AutosarExport loadArchitecture(String path) {
		AutosarExport arch = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			file = new File(path); //Test-Datei
			doc = builder.build(file);
			if(doc != null){
				doc = removeNamespaces(doc);
				arch = load(doc);
			}
		} catch (JDOMParseException e) {
			System.out.println("Fehler");
		} catch (FileNotFoundException e) {
			System.out.println("Fehler");
		} catch (JDOMException e) {
			System.out.println("Fehler");
		} catch (IOException e) {
			System.out.println("Fehler");
		}
		return arch;
	}

	private AutosarExport load(Document doc){
		Element root = doc.getRootElement();
		Element sysAr = loadSystem();
		List<Element> ecuList = loadEcus();
		return new AutosarExport(root, sysAr, ecuList);
	}
	
	public static void main(String[] args) {
		System.out.println("beginnt");
		//ARXMLLoader neu = new ARXMLLoader();
		//neu.loadArchitecture();
		System.out.println("klappt");
	}

	/**
	 * Entfernt die Namespaces aus dem JDom-Dokument um die Navigation über das Dokument zu vereinfachen
	 * @param doc
	 */
	private Document removeNamespaces(Document doc){
		Element root = doc.getRootElement();
		root.setNamespace(null);
		for ( Iterator i = root.getDescendants(new ElementFilter()); i.hasNext();){
			Element el = (Element)i.next();
			if (el.getNamespace() != null) el.setNamespace(null);
		}
		return doc;
	}
	
	/**
	 * Ermittelt den Knoten mit der System-Arch.
	 * @param root
	 * @param doc
	 * @return
	 */
	private Element loadSystem(){
		Element system = null;
		List nodes = getXNodes("/AUTOSAR/TOP-LEVEL-PACKAGES/AR-PACKAGE/ELEMENTS/SYSTEM");
		//Folgende Bedingung muss stimmen, da nur 1 System pro ARXML moeglich
		if(nodes.size() == 1)
			system = (Element)nodes.get(0);
		return system;
	}
	
	private List<Element> loadEcus(){
		List<Element> ecuList = getXNodes("/AUTOSAR/TOP-LEVEL-PACKAGES/AR-PACKAGE/ELEMENTS/SYSTEM-TOPOLOGY-TYPE/TOPOLOGY-ELEMENTS/ECU-INSTANCE");
		if(ecuList.isEmpty())
			ecuList = getXNodes("/AUTOSAR/TOP-LEVEL-PACKAGES/AR-PACKAGE/SUB-PACKAGES/AR-PACKAGE/ELEMENTS/ECU-INSTANCE"); //dieser Fall trat schonmal auf
		return ecuList;
	}
	
	/**
	 * Ermittelt die Knoten im JDom-Dokument die ueber den uebergebenen Pfad errecihbar sind.
	 * @param path
	 * @return
	 */
	public List getXNodes(String path){
		List nodes = null;
		if(doc != null){
			try {
				nodes = XPath.selectNodes(doc, path);
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	
	/**
	 * Speichert in eine XML-Datei ab dem übergebenen Knoten
	 */
	private void outputTestFile(Element newRoot, String filePath) {
		try {
			XMLOutputter out = new XMLOutputter( Format.getPrettyFormat() ); 
			FileOutputStream output = new FileOutputStream(filePath);
			newRoot.detach();
			out.output(new Document(newRoot),output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
