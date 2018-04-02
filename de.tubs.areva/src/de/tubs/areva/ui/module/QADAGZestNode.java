package de.tubs.areva.ui.module;

import java.util.Map;

import org.eclipse.gef4.graph.Node;

import de.tubs.areva.emf.model.darg.Architecture;

public class QADAGZestNode extends Node {
	private String name = "";
	private double quality = 0d;
	private double weight = 0d;
	
	
	public QADAGZestNode(String name, double quality, double weight, Map<String, Object> attrs) {
		super(attrs);
		this.name = name;
		this.quality = quality;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public double getQuality() {
		return quality;
	}
	
	public double getWeight() {
		return weight;
	}
}
