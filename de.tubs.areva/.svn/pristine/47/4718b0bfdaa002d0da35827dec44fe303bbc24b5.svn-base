package de.tubs.forjahn.bachelor.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;

import de.tubs.areva.command.ProcessResourceFailure;
import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.resourcerelations.Platform;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.forjahn.bachelor.utils.ArchitectureGraph;
import de.tubs.forjahn.bachelor.utils.DijkstraAlgorithm;

public class Numbers {
	
	// 3.1 ist die Veränderung des Wizards mit mehreren QADAGs und dadurch mehreren erzeugten DARGs
	
	// 3.2.1
	
	public static int getDomainSize(ARG domain) {
		return domain.getArchitectures().size();
	}
	
	public static int getDomainInterconnections(ARG domain) {
		
		int interconnections = 0;
		
		for(Architecture architecture: domain.getArchitectures()) {
			interconnections += architecture.getRelatedArchitectures().size();
		}
		
		return interconnections;
	}

	public static double getClosenessCentrality(ARG domain, Architecture architecture) {
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(domain);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		shortestPathAlg.execute(architecture);
		
		int shortestPathLengthSum = 0;
		for(Architecture target: graph.getVertexes()) {
			if(architecture != target) {
				List<Architecture> shortestPath = shortestPathAlg.getPath(target);
				shortestPathLengthSum += shortestPath.size();
			}
		}
		
		return shortestPathLengthSum;
	}

	public static double getBetweenCentrality(ARG domain, Architecture architecture) {
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(domain);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		
		int shortestPathCount = 0;
		for(Architecture source: graph.getVertexes()) {
			if(source != architecture) {
				shortestPathAlg.execute(source);
				for(Architecture target: graph.getVertexes()) {
					if(target != architecture) {
						if(source != target) {
							List<Architecture> shortestPath = shortestPathAlg.getPath(target);
							if(shortestPath.contains(architecture)) {
								shortestPathCount++;
							}
						}
					}
				}
			}
		}
		
		return shortestPathCount;
	}

	public static double getDegreeCentrality(Architecture architecture) {
		
		int result = 0;
		for(Architecture connectedArchitecture: architecture.getRelatedArchitectures()) {
			if(!Double.isNaN(connectedArchitecture.getQuality())) {
				result++;
			}
		}
		return result;
	}
}
