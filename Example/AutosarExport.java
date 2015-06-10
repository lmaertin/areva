

import java.util.List;

import org.jdom.Element;

public class AutosarExport{

	private Element root;
	private Element systemArchitecture;
	private List<Element> ecuList;
	
	public AutosarExport(Element root, Element systemArchitecture, List<Element> ecuList) {
		this.root = root;
		this.systemArchitecture = systemArchitecture;
		this.ecuList = ecuList;
	}

	public Element getRoot() {
		return root;
	}

	public Element getSystemArchitecture() {
		return systemArchitecture;
	}

	public List<Element> getEcuList() {
		return ecuList;
	}
	
}