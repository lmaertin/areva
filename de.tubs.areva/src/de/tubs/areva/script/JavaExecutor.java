package de.tubs.areva.script;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.w3c.dom.Document;

public class JavaExecutor implements IScriptExecutor {

	static JavaExecutor instance = null;
	private URLClassLoader currentClassLoader = null;
	private Collection<Object> currentMethods = new ArrayList<Object>();
	private Map<String, Object> currentObjects = null; 
	
	private JavaExecutor() {
	}
	
	public static IScriptExecutor getInstance() {
		if(instance == null) {
			instance = new JavaExecutor();
		}
		return instance;
	}
	
	@Override
	public Object executeMethod(String methodName, EObject target) {
		
		int separator = methodName.lastIndexOf('.');
		
		if(separator < 0) {
			return null;
		}
		
		String objectClassName = methodName.substring(0, separator);
		String objectMethodName = methodName.substring(separator + 1, methodName.length());
		
		if(target != null) {
			System.out.println("<Qadag> Target not null: ");
		}
		
		Class<?> clazz = null;
		
		Object instance = null;
		
		if(!currentObjects.containsKey(objectClassName)) {
			
			try {
				clazz = Class.forName(objectClassName, true, currentClassLoader);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				instance = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			currentObjects.put(objectClassName, instance);
			
		} else {
			
			instance = currentObjects.get(objectClassName);
			clazz = instance.getClass();
		}
		
		// Load models into Document list for script methods
		List<Document> models = new ArrayList<Document>();
		/*
		File modelsFolder = new File(modelsFolderPath);
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		
		try {
		    builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		    e.printStackTrace();  
		}
		
		for(File model: modelsFolder.listFiles()) {
			
			try {
				models.add(builder.parse(model));
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
		
		
		// Check for appropriate method and execute it (TODO: Exact check for name)
		for (Method scriptMethod: clazz.getMethods()) {
			
			System.out.println("<Qadag> Found method");
			String foundMethodName = scriptMethod.getName();
			System.out.println("<Qadag> Looking for method: " + objectMethodName);
			
			if(foundMethodName.startsWith(objectMethodName) && scriptMethod.getGenericReturnType() == float.class) {
				
				System.out.println("<Qadag> Method found: " + methodName);
				scriptMethod.setAccessible(true);
				
				Object returnObject = null;
				try {
					if(scriptMethod.getParameterCount() == 2) {
						returnObject = scriptMethod.invoke(instance, target, models);
					} else if(scriptMethod.getParameterCount() == 1) {
						returnObject = scriptMethod.invoke(instance, target);
					} else if(scriptMethod.getParameterCount() == 0) {
						returnObject = scriptMethod.invoke(instance);
					}
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("<Qadag> Invoked");
				
				return (Float)returnObject;
			}
		}
		
		return 4f;
	}

	@Override
	public Collection<?> getAvailableMethodNames(EObject node) {
		
		return currentMethods;
	}

	@Override
	public void init(EObject root) {
		
		// First: set up the new class loader 
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(root.eResource().getURI().toPlatformString(true)));
		
		String binFolderPath = "";
		
		try {
			binFolderPath = file.getProject().getLocationURI().toURL().getPath() + "/bin/";
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("<Qadag> Looking at path: " + binFolderPath);
		
		try {
			currentClassLoader = URLClassLoader.newInstance(new URL[] {(new File(binFolderPath)).toURI().toURL()}, this.getClass().getClassLoader());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		System.out.println("<Qadag> Classes loaded");
		
		
		
		// Second: load all available methods
		String exportedMethodsFilePath = null;
		
		try {
			
			exportedMethodsFilePath = "" + file.getProject().getLocationURI().toURL().getPath() + "/.apt_generated";
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		currentMethods.clear();
		
		currentMethods.add("");
		
		File folder = new File(exportedMethodsFilePath);
		
		for(File folderFile: folder.listFiles()) {
			
			if(!folderFile.isDirectory()) {
			
				BufferedReader reader = null;

				try {
					
				    reader = new BufferedReader(new FileReader(folderFile));

				    String line = reader.readLine();
				    
				    String[] exportedMethods = line.split(";");
				    
				    for(int i = 0; i < exportedMethods.length; i++) {
				    	currentMethods.add(exportedMethods[i]);
				    }
				    

				} catch (IOException e) {
				    e.printStackTrace();
				} finally {
				    try {
				        reader.close();
				    } catch (IOException e) {
				        e.printStackTrace();
				    }
				}
			}
		}
		
		currentObjects = new HashMap<>();
		
	}

}
