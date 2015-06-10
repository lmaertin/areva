package areva.persistence;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import areva.content.Function;
import areva.content.Metric;
import areva.content.ModelProvider;
import areva.content.QualityRating;

/**
 * This class controls the persistence using a xml file
 * 
 * @author Sven von Höveling
 * @version 1.0
 */
public class Storage {

	/**
	 * variables for persistence
	 * 
	 */
	private static Element metricsElement;
	private static Element qualityRatingsElement;
	private static Document doc;
	private static final String STORAGE_NAME = "areva.xml";
	private static File storageFile;
	private static File file;

	/**
	 * opens the xml-file which contains the data and creates objectes which are
	 * representing the data
	 */
	public static void loadXml() {
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			file = workspace.getRoot().getLocation().toFile();
			storageFile = new File(file.getPath() + "/" + STORAGE_NAME);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(storageFile);

			doc.getDocumentElement().normalize();
			NodeList n = doc.getElementsByTagName("metric");
			NodeList m;

			for (int i = 0; i < n.getLength(); i++) {
				Element e = (Element) n.item(i);
				String name = e.getAttribute("name");
				String code = e.getAttribute("code");
				if (code != null) {
					ModelProvider.getMetrics().add(new Metric(name, code));
				}

			}
			n = doc.getElementsByTagName("qualityRating");

			for (int i = 0; i < n.getLength(); i++) {
				Element e = (Element) n.item(i);
				String name = e.getAttribute("name");
				ModelProvider.getQualityRatings().add(new QualityRating(name));
				m = e.getElementsByTagName("function");
				for (int j = 0; j < m.getLength(); j++) {
					Element f = (Element) m.item(j);
					String interval = f.getAttribute("interval");
					String term = f.getAttribute("term");
					ModelProvider.getQualityRatings().get(i).getFunctions()
							.add(new Function(interval, term));
				}
			}
			metricsElement = (Element) doc.getElementsByTagName("metrics")
					.item(0);
			qualityRatingsElement = (Element) doc.getElementsByTagName(
					"qualityRatings").item(0);

		} catch (Exception e) {
			System.out.println("No areva.xml found, creating...");
			initializeArevaXml();
		}
	}

	/**
	 * saves the metrics into xml-structure
	 */
	public static void saveMetrics() {
		while (metricsElement.hasChildNodes()) {
			metricsElement.removeChild(metricsElement.getFirstChild());
		}
		for (int i = 0; i < ModelProvider.getMetrics().size(); i++) {
			Element tmp = doc.createElement("metric");
			tmp.setAttribute("name", ModelProvider.getMetrics().get(i)
					.getName());
			tmp.setAttribute("code", ModelProvider.getMetrics().get(i)
					.getCode());
			metricsElement.appendChild(tmp);
		}
		//save into file
		save();

	}

	/**
	 * saves the qr into the xml-structure
	 */
	public static void saveQualityRatings() {
		while (qualityRatingsElement.hasChildNodes()) {
			qualityRatingsElement.removeChild(qualityRatingsElement
					.getFirstChild());

		}
		for (int i = 0; i < ModelProvider.getQualityRatings().size(); i++) {
			Element tmp = doc.createElement("qualityRating");
			tmp.setAttribute("name", ModelProvider.getQualityRatings().get(i)
					.getName());
			ArrayList<Function> array = ModelProvider.getQualityRatings()
					.get(i).getFunctions();
			for (int j = 0; j < array.size(); j++) {
				Element tmp2 = doc.createElement("function");
				tmp2.setAttribute("interval", array.get(j).getInterval());
				tmp2.setAttribute("term", array.get(j).getTerm());
				tmp.appendChild(tmp2);
			}
			qualityRatingsElement.appendChild(tmp);
		}
		save();
	}

	/**
	 * saves metrics-code into the xml-file, which includes saving all metrics
	 */
	public static void saveMetricsCode() {
		saveMetrics();
	}

	/**
	 * saves the function-definitions into the xml-file, which includes saving
	 * all qr
	 */
	public static void saveFunctionDefintions() {
		saveQualityRatings();
	}

	/**
	 * save the changes into the file
	 */
	public static void save() {
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

	/**
	 * creates a xml file if there was none before
	 */
	private static void initializeArevaXml() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("project");
			doc.appendChild(rootElement);

			metricsElement = doc.createElement("metrics");
			rootElement.appendChild(metricsElement);

			qualityRatingsElement = doc.createElement("qualityRatings");
			rootElement.appendChild(qualityRatingsElement);

			save();

		} catch (Exception e) {
			System.out.println("initializesArevaXML in Storage");
			e.printStackTrace();
		}
	}
}
