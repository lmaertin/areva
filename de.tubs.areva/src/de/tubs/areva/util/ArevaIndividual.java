package de.tubs.areva.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.tubs.areva.resourcerelations.Resource;
import de.tubs.areva.resourcerelations.ResourceOptions;
import de.tubs.areva.resourcerelations.Specification;

public class ArevaIndividual {
	private long id;
	private Set<DoFBasicComponent> usedDoFBasicComponents; //BasicComponents used by current system variant
	private Set<ResourceOptions> affectedResourceGroups;
	private Map<String,Double> dimensionToValue;
	private Set<ArevaIndividual> dominatedBy;

	public ArevaIndividual(long id) {
		this.id = id;
		this.usedDoFBasicComponents = new HashSet<DoFBasicComponent>();
		this.affectedResourceGroups = new HashSet<ResourceOptions>();
		this.dimensionToValue = new HashMap<String,Double>();
		this.dominatedBy = new HashSet<ArevaIndividual>();
	}

	public void addUsedBasicComponents(DoFBasicComponent dofbc){
		usedDoFBasicComponents.add(dofbc);
	}

	public void addAffectedResourceOptions(Set<ResourceOptions> rgSet){
		affectedResourceGroups.addAll(rgSet);
	}

	public long getId() {
		return id;
	}

	public Set<DoFBasicComponent> getUsedBasicComponents() {
		return usedDoFBasicComponents;
	}

	public Set<ResourceOptions> getAffectedResourceGroups() {
		return affectedResourceGroups;
	}

	public Map<String, Double> getDimensionToValue() {
		return dimensionToValue;
	}
	
	public Set<ArevaIndividual> getDominatingIndividuals(){
		return dominatedBy;
	}

	/**
	 * Read and update values for each dimension wrt. a matching property from the given specification.
	 * @param spec
	 */
	public void addValueToDimension(Specification spec){
		String propName = spec.getAssignedProp().getName().toLowerCase();
		double newValue = Math.round(spec.getValue() * 10000.0) / 10000.0; //round value to 4 digets
//		if(!propName.startsWith("-"))
//			newValue = -newValue;  //invert values for max. dimensions
		Double oldValue = dimensionToValue.get(propName);
		if(oldValue == null) {
			dimensionToValue.put(propName, newValue); //init. dimension
		}
		else{
			double sum = Math.round((newValue+oldValue.doubleValue()) * 10000.0) / 10000.0; //round sum to 4 digets
			dimensionToValue.replace(propName, sum); //update dimension
		}
	}
	
	/**
	 * Checks Pareto domination of individual over all dimensions of an opponent individual.
	 * @param opponent
	 * @return
	 */
	public boolean dominates(ArevaIndividual opponent){
		boolean equal = true;
		for(String dim : dimensionToValue.keySet()){
			double value = dimensionToValue.get(dim).doubleValue();
			double opponentValue = opponent.getDimensionToValue().get(dim).doubleValue();
			
			//for maximization dimensions flip signs -> convert to minimalization problem
			if(!dim.startsWith("-")){
				value = -value;
				opponentValue = -opponentValue;
			}
			
			if (value > opponentValue) { //bigger values are bad -> aim for minimalization -> return false for "non-domination"
//				System.out.println(dim + ": " + value + " (" + id + ") > " + opponentValue + " (" + opponent.getId() + ")");
				return false;
			} else if (value < opponentValue) { //domination, continue with other dimensions
//				System.out.println(dim + ": " + value + " (" + id + ") < " + opponentValue + " (" + opponent.getId() + ")");
				equal = false;
			}
		}
		return !equal;
	}
	
//	/**
//	 * Checks weak Pareto domination of individual over at least one dimension of an opponent individual.
//	 * @param opponent
//	 * @return
//	 */
//	public boolean weaklyDominates(ArevaIndividual opponent){
//		for(String dim : dimensionToValue.keySet()){
//			double value = dimensionToValue.get(dim).doubleValue();
//			double opponentValue = opponent.getDimensionToValue().get(dim).doubleValue();
//			if(!dim.startsWith("-")){//for maximization dimensions flip signs -> convert to minimalization problem
//				value = -value;
//				opponentValue = -opponentValue;
//				//System.out.println("invert values for " + dim);
//			}
//			if (opponentValue < value) {
//				return false;
//			} 
//		}
//		return true;
//	}

//	public void check(){
//		double matchCount = 0;
//		String mfs = "MFS";
//		String asc = "ASC High Res.";
//		String css = "CSS High Res.";
//		String ons = "ONS";
//		String rws = "RW 4 Wheels";
//		String mcs = "MCS 6 Coils";		
//		for (ResourceOptions resOp : affectedResourceGroups){
//			String groupName = resOp.getName();
//			System.out.println(groupName);
//			if(groupName.equals(mfs))
//				matchCount++;
//			if(groupName.equals(asc))
//				matchCount++;
//			if(groupName.equals(css))
//				matchCount++;
//			if(groupName.equals(ons))
//				matchCount++;
//			if(groupName.equals(rws))
//				matchCount++;
//			if(groupName.equals(mcs))
//				matchCount++;
//		}
//			
////		double torque = 0.087;
////		double lifetime = 184;
////		double accuracy = 310;
////		double noise = 150;
////		double pointingtime = 0.5822;
////		double powerconsumption = 36.41;
////		for(String dim : dimensionToValue.keySet()){
////			double value = dimensionToValue.get(dim);
////			if(dim.equals("torque") && value == torque)
////				matchCount++;
////			if(dim.equals("lifetime") && value == lifetime)
////				matchCount++;
////			if(dim.equals("accuracy") && value == accuracy)
////				matchCount++;
////			if(dim.equals("-noise") && value == noise)
////				matchCount++;
////			if(dim.equals("-pointingtime") && value == pointingtime)
////				matchCount++;
////			if(dim.equals("-powerconsumption") && value == powerconsumption)
////				matchCount++;
////		}
//		
//		if(matchCount == 6){
//			System.out.println(id + " full match");
//			for(String dim : dimensionToValue.keySet()){
//				double value = dimensionToValue.get(dim);
//				System.out.println("\t" + dim + "=" + value);
//			}
//			for (DoFBasicComponent dofComp : usedDoFBasicComponents){
//				System.out.println("\t comp: " + dofComp.getComp().getEntityName());
//			}
//			for (ResourceOptions resOp : affectedResourceGroups){
//				System.out.println("\t resOp: " + resOp.getName());
//				for(Resource res : resOp.getResources()){
//					System.out.println("\t\t res: " + res.getName());
//				}
//			}
//		}
//	}
	
	@Override
	public String toString() {
		String output = "Candidate " + id + ":";
		output += "\n\tBCs: ";
		for(DoFBasicComponent adofbc : usedDoFBasicComponents){		
			output += adofbc.getComp().getEntityName() + ", ";
		}	
		output += "\n\tRes Opt: ";
		for(ResourceOptions rg : affectedResourceGroups){
			output += rg.getName() + ", ";
		}	
		output += "\n\tDimensions: ";
		for(String dim : dimensionToValue.keySet()){
			output += dim + "(" + dimensionToValue.get(dim) + "), ";
		}
		return output;
	}
}