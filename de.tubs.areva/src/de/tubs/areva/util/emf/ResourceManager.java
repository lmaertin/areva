package de.tubs.areva.util.emf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

public class ResourceManager {
	
	public static Resource createAndAddResource(String outputFile, String[] fileextensions, ResourceSet rs) {
	     for (String fileext : fileextensions) {
	        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileext, new XMLResourceFactoryImpl());
	     }		
	     URI uri = URI.createFileURI(outputFile);
	     Resource resource = rs.createResource(uri);
	     ((ResourceImpl)resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
	     
	     return resource;
	}
	
	public static void saveResource(Resource resource) {
		
	     Map<Object, Object> saveOptions = ((XMLResource)resource).getDefaultSaveOptions();
	     saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
	     saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<Object>());
	     try {
	        resource.save(saveOptions);
	     } catch (IOException e) {
	        throw new RuntimeException(e);
	     }
	}
}
