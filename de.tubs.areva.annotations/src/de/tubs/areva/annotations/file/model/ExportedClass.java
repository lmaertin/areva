package de.tubs.areva.annotations.file.model;

import java.util.ArrayList;
import java.util.List;

public class ExportedClass {
	
	private String name;
	
	private List<String> exportedMethods = new ArrayList<String>();
	
	public String getName() {
		return name;
	}
	
	public List<String> getExportedMethods() {
		return exportedMethods;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setExportedMethods(List<String> exportedMethods) {
		this.exportedMethods = exportedMethods;
	}
	
}
