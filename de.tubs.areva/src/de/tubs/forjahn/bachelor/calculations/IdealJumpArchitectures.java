package de.tubs.forjahn.bachelor.calculations;

import java.util.ArrayList;
import java.util.List;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.util.ResourceHelper;
import de.tubs.forjahn.bachelor.utils.ArchitectureGraph;
import de.tubs.forjahn.bachelor.utils.DijkstraAlgorithm;

public class IdealJumpArchitectures {

	public static List<Architecture> getJumpArchitectures(ARG domain1, ARG domain2) {
		List<Architecture> domain1Architectures = new ArrayList<>();
		List<Architecture> domain2Architectures = new ArrayList<>();
		List<Architecture> intersectedArchitectures = new ArrayList<>();
		
		for(Architecture architecture: domain1.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				domain1Architectures.add(architecture);
			}
		}
		for(Architecture architecture: domain2.getArchitectures()) {
			if(!Double.isNaN(architecture.getQuality())) {
				domain2Architectures.add(architecture);
			}
		}
		
		for(Architecture architecture1: domain1Architectures) {
			for(Architecture architecture2: domain2Architectures) {
				if(architecture1.getId().equals(architecture2.getId())) {
					intersectedArchitectures.add(architecture2);
				}
			}
		}
		
		return intersectedArchitectures;
	}
	
	public static double getArchitectureJumpValue(ARG domain, Architecture source, Architecture target, double distanceWeight, double qualityWeight, double centralityWeight, double resourceWeight, Centrality centralityMethod) {		
		ArchitectureGraph graph = ArchitectureGraph.createGraphFromDomain(domain);
		DijkstraAlgorithm shortestPathAlg = new DijkstraAlgorithm(graph);
		shortestPathAlg.execute(source);
		double distanceValue = distanceWeight * shortestPathAlg.getPath(target).size();
		double staticValue = getStaticArchitectureJumpValue(domain, target, qualityWeight, centralityWeight, resourceWeight, centralityMethod);
		return distanceValue + staticValue;
	}
	
	public static double getStaticArchitectureJumpValue(ARG domain, Architecture architecture, double qualityWeight, double centralityWeight, double resourceWeight, Centrality centralityMethod) {		
		double qualityValue = qualityWeight * architecture.getQuality();
		
		double centralityValue;
		
		if(centralityMethod == Centrality.DEGREE) {
			centralityValue = centralityWeight * Numbers.getDegreeCentrality(architecture);
		} else if(centralityMethod == Centrality.BETWEEN) {
			centralityValue = centralityWeight * Numbers.getBetweenCentrality(domain, architecture);
		} else {
			centralityValue = centralityWeight * Numbers.getClosenessCentrality(domain, architecture);
		}
		
		double resourceValue = resourceWeight * architecture.getBoundResources().size();
		
		return qualityValue + centralityValue + resourceValue;
	}
}
