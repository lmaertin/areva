package de.tubs.forjahn.bachelor.utils;

import de.tubs.areva.emf.model.darg.Architecture;

public class Edge  {
    private final Architecture source;
    private final Architecture destination;
    private final int weight;

    public Edge(Architecture source, Architecture destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    
    public Architecture getDestination() {
        return destination;
    }

    public Architecture getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}
