package de.tubs.forjahn.bachelor.calculations;

import java.util.ArrayList;
import java.util.List;

import de.tubs.areva.emf.model.darg.ARG;
import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.util.ResourceHelper;

public class IdealOpModeOrder {

	public static int getDomainOrderDifference(List<ARG> domains, int threshold) {
		int differenceSum = 0;
		
		for(int i = 0; (i+1) < domains.size(); i++) {
			ARG source = domains.get(i);
			ARG target = domains.get(i+1);
			
			int difference = getDomainPairDifference(source, target);
			
			if(difference > threshold) {
				return Integer.MAX_VALUE;
			} else {
				differenceSum += difference;
			}
		}
		
		return differenceSum;
	}
	
	public static int getDomainPairDifference(ARG domain1, ARG domain2) {
		List<Architecture> domain1Architectures = new ArrayList<>();
		List<Architecture> domain2Architectures = new ArrayList<>();
		
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
		
		int difference = ResourceHelper.complement(domain1Architectures, domain2Architectures).size();
		
		return difference;
	}
}
