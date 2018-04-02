package de.tubs.areva.ui.module;

import java.util.List;
import java.util.Map;

import org.eclipse.gef4.graph.Node;

import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.DARGNode;

public class DARGZestNode extends Node {
	private double quality = 0d;
	private List<String> boundResources = null;
	private String id = "";
	
	public DARGZestNode(Map<String, Object> attrs) {
		super(attrs);
	}
	
	public void setQuality(double quality) {
		this.quality = quality;
	}
	
	public void setBoundResources(List<String> boundResources) {
		this.boundResources = boundResources;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getQuality() {
		return quality;
	}
	
	public List<String> getBoundResources() {
		return boundResources;
	}
	
	public String getId() {
		return id;
	}

}
