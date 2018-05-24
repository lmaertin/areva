package de.tubs.areva.util;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.repository.BasicComponent;

import de.tubs.areva.emf.model.darg.Architecture;
import de.tubs.areva.emf.model.qadag.Node;
import de.tubs.areva.emf.model.qadag.QualityAspect;

public class QADAGHelper {

	

	
	/*
	public static void setResoucesForArchitecture(Architecture arch, CSVReader csvFile) {
		
		List<String> column = csvFile.getTableAsMap().get("Candidate ID");
		
		int candidateRow = 0;
		
		for(; candidateRow < column.size(); candidateRow++) {
			
			if(column.get(candidateRow).equals(arch.getName().split(":")[1].trim())) {
				
				break;
			}
		}
		
		for(Entry<String, List<String>> entry : csvFile.getTableAsMap().entrySet()) {
			
			if(entry.getKey().contains("AllocationDegreeImpl:")) {
				
				if(!arch.getUsedResources().contains(entry.getValue().get(candidateRow))) {
					
					arch.getUsedResources().add(entry.getValue().get(candidateRow));
				}
			}
		}
	}
	*/
	
	/*
	public static List<List<String>> calculateResourceConnection(Architecture arch1, Architecture arch2) {
		
		List<String> additions = new ArrayList<>();
		List<String> removals = new ArrayList<>();
		
		for(int i = 0; i < arch2.getUsedResources().size(); i++) {
			
			additions.add(arch2.getUsedResources().get(i));
		}
		
		if(arch1 != arch2) {
			
			for(int i = 0; i < arch1.getUsedResources().size(); i++) {
				
				if(!arch2.getUsedResources().contains(arch1.getUsedResources().get(i))) {
					
					removals.add(arch1.getUsedResources().get(i));
					
				} else {
					additions.remove(arch1.getUsedResources().get(i));
				}
			}
		}
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		result.add(additions);
		result.add(removals);
		
		return result;
	}
	*/
	
	public static int getTaggedValue(BasicComponent c)
	{
		String stereotypeName = "BasicComponentWithAdditionalValue";
		
		if(!StereotypeAPI.isStereotypeApplied(c, stereotypeName))
			return -1;
		
		Integer nullableResult = StereotypeAPI.getTaggedValue(c, "AdditionalValue", stereotypeName);
		
		if(nullableResult != null){
			int result = (int) nullableResult;
			return result;
		}
		
		return -1;
	}
}
