package de.tubs.areva.ui.module;

import java.util.Map;

import org.eclipse.gef4.graph.Edge;
import org.eclipse.gef4.graph.Node;

import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.darg.DARGNode;

public class DARGZestEdge extends Edge {
	public Architecture source = null;
	public Architecture target = null;
	
	public DARGZestEdge(Map<String, Object> attrs, Node source, Node target) {
		super(attrs, source, target);
	}
	
	public void setSourceArchitecture(Architecture architecture) {
		this.source = architecture;
	}
	
	public void setTargetArchitecture(Architecture architecture) {
		this.target = architecture;
	}
	
	public Architecture getSourceArchitecture() {
		return source;
	}
	
	public Architecture getTargetArchitecture() {
		return target;
	}

}
