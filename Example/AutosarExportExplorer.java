
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;


public class AutosarExportExplorer {

	private String linksizeTag = "@linksize"; //Kennzeichner fuer benutzerdefierte Linkbreiten in Type-Description
	
	private AutosarExport autosarExport;
	
	public AutosarExportExplorer(AutosarExport autosarExport) {
		this.autosarExport = autosarExport;
	}
	
	/*
	 * Die folgenden Methoden werden in die Autovervollständigungsliste
	 * eingetragen und werden somit später dem Benutzer angeboten.
	 * Voraussetzung fuer die Integration in die Liste ist, dass die Methode
	 * protected oder public ist, nicht private!
	 */
	protected Element getRootElement() {
		return autosarExport.getRoot();
	}

	protected Element getSystemArchitectureElement() {
		return autosarExport.getSystemArchitecture();
	}

	protected List<Element> getEcuElementList() {
		return autosarExport.getEcuList();
	}
	
	/**
	 * Ermittelt den Unterknoten des Knotens element, der über den übergebenen Pfad erreichbar ist.
	 */
	protected Element getElementFromElementAndPath(Element element, String path){
		Element subElement = null;
		if(element != null){
			try {
				subElement = (Element)XPath.selectSingleNode(element, path);
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		}
		return subElement;
	}
	
	/**
	 * Ermittelt alle Unterknoten des Knotens element, die über den übergebenen Pfad erreichbar sind.
	 */
	protected List<Element> getElementListFromElementAndPath(Element element, String path){
		List<Element>  nodes = null;
		if(element != null){
			try {
				nodes = XPath.selectNodes(element, path);
			} catch (JDOMException e) {
				e.printStackTrace();
			}
		}
		return nodes; //gibt immer mindestens eine leere Liste zurück
	}
	
	/**
	 * Ermittelt dem Unterknoten des gesamten autosarExport-Dokuments der über den übergebenen Pfad erreichbar ist.
	 */
	protected Element getElementFromDocument(String path){
		return getElementFromElementAndPath(autosarExport.getRoot(), path);
	}
	
	/**
	 * Ermittelt alle Unterknoten des gesamten autosarExport-Dokuments die über den übergebenen Pfad erreichbar sind.
	 */
	protected List<Element> getElementListFromDocument(String path){
		return getElementListFromElementAndPath(autosarExport.getRoot(), path);
	}
	
	/**
	 * Ermittelt dem Unterknoten des globalen Elements-Knoten der über den übergebenen Pfad erreichbar ist.
	 */
	protected Element getElementFromElementsNode(String path){
		return getElementFromDocument("TOP-LEVEL-PACKAGES/AR-PACKAGE/ELEMENTS/"+path);
	}
	
	/**
	 * Ermittelt alle Unterknoten des globalen Elements-Knoten die über den übergebenen Pfad erreichbar sind.
	 */
	protected List<Element> getElementListFromElementsNode(String path){
		return getElementListFromDocument("TOP-LEVEL-PACKAGES/AR-PACKAGE/ELEMENTS/"+path);
	}
	
	/**
	 * Ermittelt dem Unterknoten der System-Architektur der über den übergebenen Pfad erreichbar ist.
	 */
	protected Element getElementFromSystemArchitecture(String path){
		return getElementFromElementAndPath(autosarExport.getSystemArchitecture(), path);
	}
	
	/**
	 * Ermittelt alle Unterknoten der System-Architektur die über den übergebenen Pfad erreichbar sind.
	 */
	protected List<Element> getElementListFromSystemArchitecture(String path){
		return getElementListFromElementAndPath(autosarExport.getSystemArchitecture(), path);
	}
	
	/**
	 * Liefert den Wert (vom Tag-Ende bis Zeilen-Ende) des übergebenen Schlüsselworts (ohne @) und Elements
	 * @param tagName
	 * @param element
	 * @return
	 */
	protected String getTagValueFormElement(String tagName, Element element){
		String tagValue = "";
		if(element.getChild("DESC") != null &&	element.getChild("DESC").getChild("L-2") != null){
			String desc = element.getChild("DESC").getChildText("L-2"); 
			int tagStart = desc.indexOf(tagName);
			if(tagStart > -1){
				String[] descLines = desc.substring(tagStart).split("\n");
				tagValue = descLines[0].substring(tagName.length()).trim();
			}
		}
		return tagValue;
	}
	
	/**
	 * Liefert den Wert (vom Tag-Ende bis Zeilen-Ende) des übergebenen Schlüsselworts (ohne @) und Elements als Double-Zahlenwert
	 * @param tagName
	 * @param element
	 * @return
	 */
	protected double getTagValueDoubleFormElement(String tagName, Element element){
		double result;
		try {
			result= Double.parseDouble(getTagValueFormElement(tagName, element));
		} catch (NumberFormatException e) {
			 result = 0;
		}
		return result;
	}
	
	/**
	 * Liefert den Wert (vom Tag-Ende bis Zeilen-Ende) des übergebenen Schlüsselworts (ohne @) und Elements als Integer-Zahlenwert
	 * @param tagName
	 * @param element
	 * @return
	 */
	protected int getTagValueIntegerFormElement(String tagName, Element element){
		return (int)getTagValueDoubleFormElement(tagName, element);
	}
	
	/**
	 * Liefert den Namen des übergebenen Elements
	 * @param element
	 * @return
	 */
	protected String getNameFromElement(Element element){
		return element.getChildTextTrim("SHORT-NAME");
	}
	
	/**
	 * Liefert eine Liste über die Komponenten-Mappings aller Ecus
	 * @return
	 */
	protected Element getComponentMappingElementForAllEcus(){
		return getElementFromSystemArchitecture("MAPPING/SW-MAPPINGS");
	}
	
	/**
	 * Liefert das Komponenten-Mapping einer Ecu
	 * @return
	 */
	protected Element getComponentMappingElementForEcuName(String ecuName){
		Element allElement = getComponentMappingElementForAllEcus();
		Element result = null;
		
		String ecuRefName;
		for(Element mapping : (List<Element>)allElement.getChildren()){
			ecuRefName = getElementFromElementAndPath(mapping,
					"ECU-INSTANCE-IREF/ECU-INSTANCE-REF").getTextTrim();
			if(ecuRefName.endsWith("/"+ecuName)){
				result = mapping;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Liefert das Component-IRefs Element eines Komponenten-Mappings
	 * @return
	 */
	protected Element getComponentIRefsElementFromComponentMappingElement(Element mappingElement){
		return getElementFromElementAndPath(mappingElement, "COMPONENT-IREFS");
	}
	
	/**
	 * Liefert eine Liste über alle Component-IRefs eines Komponenten-Mappings
	 * @return
	 */
	protected List<Element> getComponentIRefElementListFromComponentIRefsElement(Element iRefsElement){
		return getElementListFromElementAndPath(iRefsElement, "COMPONENT-IREF");
	}
	
	/**
	 * Liefert eine Liste der Referenz-Elemente aller Komponenten die aus einem Steuergerät liegen.
	 * @param ecuName
	 * @return
	 */
	protected List<Element> getComponentRefElementListForEcuName(String ecuName) {
		Element mappingElement = getComponentMappingElementForEcuName(ecuName);
		Element iRefsElement= getComponentIRefsElementFromComponentMappingElement(mappingElement);
		return getComponentIRefElementListFromComponentIRefsElement(iRefsElement);	
	}
	
	/**
	 * Liefert die Referenz zu dem Komposition-Element in dem die Komponente liegt
	 * @param compElement
	 * @return
	 */
	protected String getCompositionRefForComponentIRefElement(Element compElement) {
		return compElement.getChildTextTrim("SOFTWARE-COMPOSITION-REF");
	}
	
	/**
	 * Liefert die Referenz zu dem Component-Prototype-Element in dem die Komponente liegt
	 * @param compElement
	 * @return
	 */
	protected String getComponentRefForComponentIRefElement(Element compElement) {
		return compElement.getChildTextTrim("TARGET-COMPONENT-PROTOTYPE-REF");

	}
	
	/**
	 * Liefert eine Liste über alle Konnektoren
	 * @return
	 */
	protected List<Element> getConnectorElementList(){
		return getElementListFromElementsNode("COMPOSITION-TYPE/CONNECTORS");
	}
	
	/**
	 * Liefert eine Liste über alle Delegation-Konnektoren
	 * @return
	 */
	protected List<Element> getDelegationConnectorElementList(){
		return getElementListFromElementsNode("COMPOSITION-TYPE/CONNECTORS/" +
				"DELEGATION-CONNECTOR-PROTOTYPE");
	}
	
	/**
	 * Liefert eine Liste über alle Assembly-Konnektoren
	 * @return
	 */
	protected List<Element> getAssemblyConnectorElementList(){
		return getElementListFromElementsNode("COMPOSITION-TYPE/CONNECTORS/" +
				"ASSEMBLY-CONNECTOR-PROTOTYPE");
	} 
	
	/**
	 * Liefert eine Liste über alle Assembly-Konnektoren einer Komposition
	 * @return
	 */
	protected List<Element> getAssemblyConnectorsForCompositionTypeElement(Element compositionType) {
		return getElementListFromElementAndPath(compositionType, "CONNECTORS/ASSEMBLY-CONNECTOR-PROTOTYPE");		
	}
	
	/**
	 * Liefert eine Liste über alle Delegation-Konnektoren einer Komposition
	 * @return
	 */
	protected List<Element> getDelegationConnectorsForCompositionTypeElement(Element compositionType) {
		return getElementListFromElementAndPath(compositionType, "CONNECTORS/DELEGATION-CONNECTOR-PROTOTYPE");		
	}
	
	
	/**
	 * Liefert eine Liste über alle Assembly-Konnektoren einer Prototyp-Komponente
	 * @return
	 */
	protected List<Element> getAssemblyConnectorElementListForComponentRef(String componentRef){
		List<Element> list = getAssemblyConnectorElementList();
		List<Element> result = new ArrayList<Element>();
		String refNameProvider;
		String refNameRequester;
		for(Element connector : list){
			refNameProvider = getElementFromElementAndPath(connector,
					"PROVIDER-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();
			refNameRequester = getElementFromElementAndPath(connector,
					"REQUESTER-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();
		
			if(refNameProvider.equals(componentRef) || refNameRequester.equals(componentRef))
				result.add(connector);
		}
		return result;
	}
	
	/**
	 * Liefert eine Liste über die providing Assembly-Konnektoren einer Software-Komponente
	 * @return
	 */
	protected List<Element> getProvidingAssemblyConnectorElementListForComponentRef(String componentRef){
		List<Element> list = getAssemblyConnectorElementList();
		List<Element> result = new ArrayList<Element>();
		String refNameProvider;
		for(Element connector : list){
			refNameProvider = getProvidingComponentRefFromAssemblyConnector(connector);
			if(refNameProvider.equals(componentRef))
				result.add(connector);
		}
		return result;
	}
	
	/**
	 * Liefert eine Liste über die requesting Assembly-Konnektoren einer Software-Komponente
	 * @return
	 */
	protected List<Element> getRequestingAssemblyConnectorElementListForComponentRef(String componentRef){
		List<Element> list = getAssemblyConnectorElementList();
		List<Element> result = new ArrayList<Element>();
		String refNameRequester;
		for(Element connector : list){
			refNameRequester = getRequestingComponentRefFromAssemblyConnector(connector);
			if(refNameRequester.equals(componentRef))
				result.add(connector);
		}
		return result;
	}
	
	/**
	 * Liefert die providing Komponente eines Assembly-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getProvidingComponentRefFromAssemblyConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"PROVIDER-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert die requesting Komponente eines Assembly-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getRequestingComponentRefFromAssemblyConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"REQUESTER-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert den providing Port eines Assembly-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getProvidingPortRefFromAssemblyConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"PROVIDER-IREF/P-PORT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert den requesting Port eines Assembly-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getRequestingPortRefFromAssemblyConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"REQUESTER-IREF/R-PORT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert eine Liste über alle Komponenten-Mappings einer Ecus
	 * @return
	 */
	protected List<Element> getDelegationConnectorElementListForComponentRef(String componentRef){
		List<Element> list = getDelegationConnectorElementList();
		List<Element> result = new ArrayList<Element>();
		String refNameInner;
		for(Element connector : list){
			refNameInner = getElementFromElementAndPath(connector,
					"INNER-PORT-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();
		
			if(refNameInner.equals(componentRef))
				result.add(connector);
		}
		return result;
	}
	
	/**
	 * Liefert die innere Komponente eines Delegation-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getInnerComponentRefFromDelegationConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"INNER-PORT-IREF/COMPONENT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert den inneren Port eines Delegation-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getInnerPortRefFromDelegationConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"INNER-PORT-IREF/PORT-PROTOTYPE-REF").getTextTrim();	
	}
	
	/**
	 * Liefert den äußeren Port eines Delegation-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getOuterPortRefFromDelegationConnector(Element connector){
		return getElementFromElementAndPath(connector,
				"OUTER-PORT-REF").getTextTrim();	
	}
	
	/**
	 * Liefert die Komponente zu dem den äußeren Port eines Delegation-Konnektors
	 * @param connector
	 * @return
	 */
	protected String getOuterComponentRefFromDelegationConnector(Element connector){
		String outerPortRef = getOuterPortRefFromDelegationConnector(connector);
		return outerPortRef.substring(0,outerPortRef.lastIndexOf("/"));
	}
	
	/**
	 * Liefert das Interface eines Ports.
	 * @param interFaceTRef
	 * @return
	 */
	protected Element getInterfaceForPort(Element port){
		Element resultIf = null;
		if(port != null){
			//1. Versuch: R-Interface
			Element refChild = port.getChild("REQUIRED-INTERFACE-TREF");
			//2. Versuch: P-Interface 
			if(refChild == null)
				refChild = port.getChild("PROVIDED-INTERFACE-TREF");
			
			String elementName = refChild.getAttributeValue("DEST").trim();
			String interfaceRef = refChild.getTextTrim();

			for(Element interfac : getElementListFromElementsNode(elementName)){
				if(interfaceRef.endsWith("/"+getNameFromElement(interfac))){
					resultIf = interfac;
					break;
				}
			}
		}
		return  resultIf;
	}
	
	/**
	 * Liefert das Interface zu einer SWC- und Port-Referenz 
	 */
	protected Element getInterfaceForRefs(String compOrSWCRef, String portRef){
		Element interf;	
		Element resultPort = null;
		 
		 //1.Versuch: Composition finden (R-Port)
		 //2.Versuch: Composition finden (P-Port)
		 OUTER: for(Element composition : getElementListFromElementsNode("COMPOSITION-TYPE")){
			if(compOrSWCRef.endsWith("/"+getNameFromElement(composition))){
				for(Element port : getElementListFromElementAndPath(composition, "PORTS/R-PORT-PROTOTYPE")){
					if(portRef.endsWith("/"+getNameFromElement(port))){
						resultPort = port;
						break OUTER;
					}
				}
				for(Element port : getElementListFromElementAndPath(composition, "PORTS/P-PORT-PROTOTYPE")){
					if(portRef.endsWith("/"+getNameFromElement(port))){
						resultPort = port;
						break OUTER;
					}
				}
			}
		 }
		 
		 if(resultPort == null){
			 //3. Versuch: SWC finden (R-PORT)
			 //4. Versuch: SWC finden (P-PORT)
			 OUTER: for(Element swc : getElementListFromElementsNode("ATOMIC-SOFTWARE-COMPONENT-TYPE")){
				 if(compOrSWCRef.endsWith("/"+getNameFromElement(swc))){
					 for(Element port : getElementListFromElementAndPath(swc, "PORTS/R-PORT-PROTOTYPE")){
						 if(portRef.endsWith("/"+getNameFromElement(port))){
							 resultPort = port;
							 break OUTER;
						 }
					 }
					 for(Element port : getElementListFromElementAndPath(swc, "PORTS/P-PORT-PROTOTYPE")){
						 if(portRef.endsWith("/"+getNameFromElement(port))){
							 resultPort = port;
							 break OUTER;
						 }
					 }
				 }
			 }
		 }
		 
		 return getInterfaceForPort(resultPort);
	}
	
	/**
	 * Liefert das Interface eines Konnektors
	 */
	protected Element getInterfaceForConnector(Element connector){
		//nur fuer eine Seite (hier: requested) notwendig, da Kconnectoren immer synmetrisch
		String compRef = getRequestingComponentRefFromAssemblyConnector(connector);
		String portRef = getRequestingPortRefFromAssemblyConnector(connector);
//		String compRef = getProvidingComponentRefFromAssemblyConnector(connector);
//		String portRef = getProvidingPortRefFromAssemblyConnector(connector);
		return getInterfaceForRefs(compRef, portRef);
	}
	
	/**
	 * Liefert die Liste der Datenelemente eines Interfaces.
	 * @param interfac
	 * @return
	 */
	protected List<Element> getDataElementListForInterface(Element interfac) {
		return getElementListFromElementAndPath(interfac, "DATA-ELEMENTS/DATA-ELEMENT-PROTOTYPE");
	}
	
	/**
	 * Liefert die Linkbreite eines Interfaces
	 * @param interfac
	 * @return
	 */
	protected int determineLinksizeForInterface(Element interfac){
		int linksize = 0;
		for(Element dataElement : getDataElementListForInterface(interfac)){
			linksize += determineElementSizeForDataElement(dataElement);
		}
		return linksize;
	}
	
	/**
	 * Liefert die Linkbreite eines Konnektors
	 * @param connector
	 * @return
	 */
	protected int determineLinksizeForConnector(Element connector){
		Element interfac = getInterfaceForConnector(connector);
		return determineLinksizeForInterface(interfac);
	}

	/**
	 * Liefert die Datengröße eines Datenelements
	 * @param dataElement
	 * @return
	 */
	protected int determineElementSizeForDataElement(Element dataElement){
//		return getDataTypeSize(dataElement.getChildText("TYPE-TREF"));
		int linksize = getLinkSizeFromTag(dataElement);
		
		//Kontrolle ob benutzerdefinierte Linksize-Tags bereits vorhanden war
		if(linksize == 0){
			//sonst: Linksize aus Basis-Datentyp ermitteln
			Element refChild = dataElement.getChild("TYPE-TREF");
			String elementName = refChild.getAttributeValue("DEST").trim();
			String typeRef = refChild.getTextTrim();
			for(Element type : getElementListFromElementsNode(elementName)){
				if(typeRef.endsWith("/"+getNameFromElement(type))){
					Element swDataDefPros = type.getChild("SW-DATA-DEF-PROPS");
					if(swDataDefPros != null){
						Element baseTypeRef = swDataDefPros.getChild("BASE-TYPE-REF");
						if(baseTypeRef != null){
							linksize = getLinkSizeForBaseType(baseTypeRef.getTextTrim());						
							break;
						}
					}
					break;
				}
			}
		}
		return  linksize;
	}
	
//	/**
//	 * Liefert eine Liste mit allen atomaren Software-Komponenten, die auf einem Steuergerät liegen.
//	 * Hierbei werden die Kompositionen (mit verschatelten SWC) ebenfalls aufgelöst.
//	 * @param baseType
//	 * @return
//	 */
//	protected Element 
	
	/**
	 * Liefert eine Liste mit allen Kompositionen, die auf einem Steuergerät liegen.
	 * Hierbei werden die Kompositionen die verschachtelt sind ebenfalls aufgelöst.
	 * @param baseType
	 * @return
	 */
	protected List<Element> getCompositionsForEcuName(String ecuName){
		List<Element> compositionsList = new ArrayList<Element>();
		for(Element compIRef : getComponentRefElementListForEcuName(ecuName)){
			String compositionRef = getCompositionRefForComponentIRefElement(compIRef);
			String componentRef = getComponentRefForComponentIRefElement(compIRef);
			
			//1. Oberste Komposition aus Mapping holen:
			Element compositionTypeElement = getCompositionElementForRef(compositionRef);			
			Element component = getComponentProtoTypeElementForCompositionElementAndComponentRef(compositionTypeElement, componentRef);
			
			String type = component.getChild("TYPE-TREF").getAttributeValue("DEST");
			String tref = component.getChildTextTrim("TYPE-TREF");
			if(type.equals("COMPOSITION-TYPE")){
				//Es handelt sich um eine Komposition => Rekurssives Aufschlüsseln weiterer Kompositionen und diese in Liste eintragen
				compositionsList.addAll((extractCompositionsFromComposition(getCompositionElementForRef(tref))));
			}
		}
		return compositionsList;
	}
	
	
	/**
	 * Liefert eine Liste mit allen atomaren Software-Komponenten, die auf einem Steuergerät liegen.
	 * Hierbei werden die Kompositionen (mit verschatelten SWC) ebenfalls aufgelöst.
	 * @param baseType
	 * @return
	 */
	protected List<Element> getAtomicSWCForEcuName(String ecuName){
		List<Element> aSWCList = new ArrayList<Element>();
		for(Element compIRef : getComponentRefElementListForEcuName(ecuName)){
			String compositionRef = getCompositionRefForComponentIRefElement(compIRef);
			String componentRef = getComponentRefForComponentIRefElement(compIRef);
			
			//1. Oberste Komposition aus Mapping holen:
			Element compositionTypeElement = getCompositionElementForRef(compositionRef);
			Element component = getComponentProtoTypeElementForCompositionElementAndComponentRef(compositionTypeElement, componentRef);
			
			String type = component.getChild("TYPE-TREF").getAttributeValue("DEST");
			String tref = component.getChildTextTrim("TYPE-TREF");
			if(type.equals("ATOMIC-SOFTWARE-COMPONENT-TYPE")){
				//2. Ist bereits atomar => referenziertes Element in Liste eintragen
				aSWCList.add(getAtomicSWCElementForRef(tref));
			}
			else{
				//3. Es handelt sich um eine Komposition => Rekurssives Aufschlüsseln der aSWC
				//und diese in Liste eintragen
				aSWCList.addAll((extractAtomicSWCFromComposition(getCompositionElementForRef(tref))));
			}
		}
		return aSWCList;
	}

	//ATOMIC-SOFTWARE-COMPONENT-TYPE für aswc-ref
	protected Element getAtomicSWCElementForRef(String aswcRef){
		//ref=/Komfortsystem/SYS_Komfortsystem/SWA_Komfortsystem
		Element result = null;
		for(Element element : getElementListFromElementsNode("ATOMIC-SOFTWARE-COMPONENT-TYPE")){
			if(aswcRef.endsWith("/"+getNameFromElement(element))){
				result = element;
				break;
			}
		}
		return result;
	}
	
//	composition-type-element für composition-ref
	protected Element getCompositionElementForRef(String compositionRef){
		//ref=/Komfortsystem/SYS_Komfortsystem/SWA_Komfortsystem
		Element result = null;
		for(Element element : getElementListFromElementsNode("COMPOSITION-TYPE")){
			if(compositionRef.endsWith("/"+getNameFromElement(element))){
				result = element;
				break;
			}
		}
		return result;
	}
	
	protected Element getCompositionElementForName(String compositionName){
		//ref=/Komfortsystem/SYS_Komfortsystem/SWA_Komfortsystem
		Element result = null;
		for(Element element : getElementListFromElementsNode("COMPOSITION-TYPE")){
			if(compositionName.equals(getNameFromElement(element))){
				result = element;
				break;
			}
		}
		return result;
	}
	
	
	//alle component-prototypes-element einer composition
	protected Element getComponentsElementForCompositionTypeElement(Element compositionTypeElement){
		return getElementFromElementAndPath(compositionTypeElement, "COMPONENTS");
	}
	
	
	//component-prototype für composition-type-element und component-ref
	protected Element getComponentProtoTypeElementForCompositionElementAndComponentRef(Element compositionTypeElement, String componentRef){
		//ref=/Komfortsystem/SYS_Komfortsystem/SWA_Komfortsystem
		Element result = null;
		Element components = getComponentsElementForCompositionTypeElement(compositionTypeElement);
		for(Element element : getElementListFromElementAndPath(components,"COMPONENT-PROTOTYPE")){
			if(componentRef.endsWith("/"+getNameFromElement(element))){
				result = element;
				break;
			}
		}
		return result;
	}

	//private Methoden, die nicht dem Benutezr angeboten werden
	
	/**
	 * Ermittelt alle Folge-Kompositionen der übergebenen Komposition
	 * @param composition
	 * @return
	 */
	private List<Element> extractCompositionsFromComposition(Element compositionTypeElement){
		List<Element> compositionList = new ArrayList<Element>();
		compositionList.add(compositionTypeElement);
		Element components = getComponentsElementForCompositionTypeElement(compositionTypeElement);
		for(Element component : getElementListFromElementAndPath(components,"COMPONENT-PROTOTYPE")){
			String type = component.getChild("TYPE-TREF").getAttributeValue("DEST");
			String tref = component.getChildTextTrim("TYPE-TREF");
			if(type.equals("COMPOSITION-TYPE")){
				//Es Handelt sich um eine Komposition => Rekurssives Aufschlüsseln weiterer KOmpositionen und diese in Liste eintragen
				compositionList.addAll(extractCompositionsFromComposition(getCompositionElementForRef(tref)));
			}
		}		
		return compositionList;
	}
	
	/**
	 * Ermittelt alle atomaren Software-Komponenten der übergebenen Komposition
	 * @param composition
	 * @return
	 */
	private List<Element> extractAtomicSWCFromComposition(Element compositionTypeElement){
		List<Element> aSWCList = new ArrayList<Element>();
		Element components = getComponentsElementForCompositionTypeElement(compositionTypeElement);
		for(Element component : getElementListFromElementAndPath(components,"COMPONENT-PROTOTYPE")){
			String type = component.getChild("TYPE-TREF").getAttributeValue("DEST");
			String tref = component.getChildTextTrim("TYPE-TREF");
			if(type.equals("ATOMIC-SOFTWARE-COMPONENT-TYPE")){
				//Ist bereits atomar => referenziertes Element in Liste eintragen
				aSWCList.add(getAtomicSWCElementForRef(tref));
			}
			else{
				//Handelt sich um Komposition => Rekurssives Aufschlüsseln der aSWC und diese in Liste eintragen
				aSWCList.addAll(extractAtomicSWCFromComposition(getCompositionElementForRef(tref)));
			}
		}		
		return aSWCList;
	}
	
	/**
	 * Linkbreite für Basisdaten-Typen von Namen des Typen ableiten (experimentell)
	 * @param baseType
	 * @return
	 */
	private int getLinkSizeForBaseType(String baseType){
		int linkSize = 0;
		if (baseType.endsWith("Boolean"))
			linkSize = 1;
		else if (baseType.endsWith("8"))
			linkSize = 8;
		else if (baseType.endsWith("16"))
			linkSize =16;
		else if (baseType.endsWith("32"))
			linkSize = 32;
		else if (baseType.endsWith("64"))
			linkSize = 64;
		else if (baseType.endsWith("128"))
			linkSize = 128;
		else if (baseType.endsWith("256"))
			linkSize = 256;
		else {
			System.err.println("Datentyp unbekannt (" + baseType + ") -> Linkbreite = 0 (@linksize-Tag in Beschreibung setzen)");
		}	
		return linkSize;		
	}
	
	private int getLinkSizeFromTag(Element dataElement){
		int linkSize = 0;
		if(dataElement.getChild("DESC") != null &&
				dataElement.getChild("DESC").getChild("L-2") != null){
			String desc = dataElement.getChild("DESC").getChildText("L-2"); 
			int linksizeStart = desc.indexOf(linksizeTag);
			if(linksizeStart > -1){
				String[] descLines = desc.substring(linksizeStart).split("\n"); //am Zeilenende Splitten
				if(descLines.length > 0){
					String linksizeStr = descLines[0].substring(linksizeTag.length()).trim();
					try {
						linkSize = Integer.parseInt(linksizeStr);
					} catch (NumberFormatException e) {
						System.err.println("Unguelitge Linkbreite \"" + linksizeStr +
								"\" bei Daten-Element " + dataElement.getChildText("SHORT-NAME"));
					}
				}
			}
		}
		return linkSize;
	}
	
}
