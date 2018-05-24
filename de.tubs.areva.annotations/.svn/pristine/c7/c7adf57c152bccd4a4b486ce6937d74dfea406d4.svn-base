package de.tubs.areva.annotations.processors;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;

import de.tubs.areva.annotations.ExportMethod;

public class ExportMethodProcessor extends AbstractProcessor {
	
	private Filer filer;
	private int counter = 0;
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		
		super.init(processingEnv);
		
		filer = processingEnv.getFiler();
		processingEnv.getMessager();
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		
		System.out.println("New Round: " + counter);
		
		System.out.println("END");
		
		Map<String, Writer> fileHandlers = new HashMap<>();
		
		for (Element elem: roundEnv.getElementsAnnotatedWith(ExportMethod.class)) {
			
			
			
			ExecutableElement method = (ExecutableElement) elem;
			
			String className = "" + ((TypeElement) method.getEnclosingElement()).toString();
			String methodName = method.getSimpleName().toString();
			
			System.out.println("Element found: " + className + " " + methodName);
			
			if(fileHandlers.containsKey(className)) {
				
				try {
					fileHandlers.get(className).append("" + className + "." + methodName + ";");
					
					System.out.println("Existing File");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				
				try {
					fileHandlers.put(className, filer.createResource(StandardLocation.SOURCE_OUTPUT, "", "" + className + ".arevainf").openWriter());
					fileHandlers.get(className).append("" + className + "." + methodName + ";");
					System.out.println("New File");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		for(Entry<String, Writer> entry : fileHandlers.entrySet()) {
			
		    entry.getKey();
		    Writer value = entry.getValue();
		    
		    try {
				value.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		counter++;
		
		return true;
	}
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
	  Set<String> annotataions = new LinkedHashSet<String>();
	  annotataions.add(ExportMethod.class.getCanonicalName());
	  return annotataions;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
	  return SourceVersion.latestSupported();
	}

}
