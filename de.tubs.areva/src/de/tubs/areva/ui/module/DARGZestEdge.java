package de.tubs.areva.ui.module;

import java.util.Map;

import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Node;

import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.DARGNode;

public class DARGZestEdge extends Edge {
	public DARGZestNode source = null;
	public DARGZestNode target = null;
	
	public DARGZestEdge(Map<String, Object> attrs, DARGZestNode source, DARGZestNode target) {
		super(attrs, source, target);
		this.source = source;
		this.target = target;
	}
	
	public DARGZestNode getSourceArchitecture() {
		return source;
	}
	
	public DARGZestNode getTargetArchitecture() {
		return target;
	}

}
