package de.tubs.forjahn.bachelor.utils;

import java.util.ArrayList;
import java.util.List;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.forjahn.bachelor.calculations.IdealOpModeOrder;

public class ArchitectureGraph {
    private final List<Architecture> vertexes;
    private final List<Edge> edges;

    public ArchitectureGraph(List<Architecture> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Architecture> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
    
    public static ArchitectureGraph createGraphFromDomain(ARG domain) {
    	List<Architecture> vertexes = new ArrayList<>();
    	List<Edge> edges = new ArrayList<>();
    	for(Architecture source: domain.getArchitectures()) {
    		if(!Double.isNaN(source.getQuality())) {
    			vertexes.add(source);
    			for(Architecture target: domain.getArchitectures()) {
    				if(!Double.isNaN(target.getQuality())) {
    					edges.add(new Edge(source, target, ResourceHelper.GetArchitectureDifferences(source, target)));
    				}
    			}
    		}
    	}
		return new ArchitectureGraph(vertexes, edges);
    }



}
