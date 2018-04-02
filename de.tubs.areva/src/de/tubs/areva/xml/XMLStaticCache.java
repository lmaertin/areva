package de.tubs.areva.xml;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XMLStaticCache {
	static String path = "";
	static String md5 = "";
	static String content = "";
	static Document doc = null;
	
	public static boolean load(String path) {
		
		// cache xml content if not cached already or file changed
		boolean reload = false;
		try {
			if(path.equals(XMLStaticCache.path)) {
				String currentMd5 = MD5Checksum.getMD5Checksum(path);
				
				if(!currentMd5.equals(XMLStaticCache.md5)) {
					reload = true;
				}
			} else {
				reload = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if(reload) {
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(path));
				String currentContent = new String(encoded, StandardCharsets.UTF_8);
				String currentMd5 = MD5Checksum.getMD5Checksum(path);
				
				XMLStaticCache.content = currentContent;
				XMLStaticCache.md5 = currentMd5;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(path);
		//XPathExpression expr = xpath.compile(<xpath_expression>);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static String getContent() {
		return XMLStaticCache.content;
	}
	
	public static Document getDocument() {
		return doc;
		
	}
}
