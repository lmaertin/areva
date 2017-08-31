package de.tubs.areva.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;

public class ResourceHelper {

	public static void GetUsedResourcesWithoutDuplicates(ResourceOptions resourceOption, List<Resource> resourceList) {
		
		for(Resource resource: resourceOption.getResources()) {
			
			boolean contains = false;
			
			for(Resource existingResource: resourceList) {
				if(resource.getName().equals(existingResource.getName())) {
					contains = true;
					break;
				}
			}
			
			if(!contains) {
				resourceList.add(resource);
			}
		}
	}
	
	public static int GetArchitectureDifferences(Architecture architecture1, Architecture architecture2) {
		
		int resourceComplement = complement(architecture1.getBoundResources(), architecture2.getBoundResources()).size();
		
		int basicComponentComplement = complement(architecture1.getBoundBasicComponents(), architecture2.getBoundBasicComponents()).size();
		
		return resourceComplement + basicComponentComplement;
	}
	
	public static List<Resource> GetArchitectureDifferencesResourcesList(Architecture architecture1, Architecture architecture2) {
		
		return complement(architecture1.getBoundResources(), architecture2.getBoundResources());
	}
	
	public static <T> List<T> complement(List<T> setA,
			List<T> setB) {
        return minus(union(setA, setB), intersect(setA, setB));
    }
 
	public static <T> List<T> intersect(List<T> setA,
    		List<T> setB) {
    	List<T> set = new ArrayList<T>(setA);
        set.retainAll(setB);
        return set;
    }
 
	public static <T> List<T> minus(List<T> setA,
    		List<T> setB) {
    	List<T> set = new ArrayList<T>(setA);
        set.removeAll(setB);
        return set;
    }
 
	public static <T> List<T> union(List<T> setA,
    		List<T> setB) {
    	List<T> set = new ArrayList<T>(setA);
        set.addAll(setB);
        return set;
    }
}
