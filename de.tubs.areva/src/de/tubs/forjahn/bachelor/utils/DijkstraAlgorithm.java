package de.tubs.forjahn.bachelor.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.tubs.areva.emf.model.darg.Architecture;

public class DijkstraAlgorithm {

    private final List<Architecture> nodes;
    private final List<Edge> edges;
    private Set<Architecture> settledNodes;
    private Set<Architecture> unSettledNodes;
    private Map<Architecture, Architecture> predecessors;
    private Map<Architecture, Integer> distance;

    public DijkstraAlgorithm(ArchitectureGraph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Architecture>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Architecture source) {
        settledNodes = new HashSet<Architecture>();
        unSettledNodes = new HashSet<Architecture>();
        distance = new HashMap<Architecture, Integer>();
        predecessors = new HashMap<Architecture, Architecture>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Architecture node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Architecture node) {
        List<Architecture> adjacentNodes = getNeighbors(node);
        for (Architecture target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Architecture node, Architecture target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Architecture> getNeighbors(Architecture node) {
        List<Architecture> neighbors = new ArrayList<Architecture>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Architecture getMinimum(Set<Architecture> vertexes) {
    	Architecture minimum = null;
        for (Architecture vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Architecture vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Architecture destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Architecture> getPath(Architecture target) {
        LinkedList<Architecture> path = new LinkedList<Architecture>();
        Architecture step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}