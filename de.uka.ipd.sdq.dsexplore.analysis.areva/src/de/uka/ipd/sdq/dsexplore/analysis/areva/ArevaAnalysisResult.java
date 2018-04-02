package de.uka.ipd.sdq.dsexplore.analysis.areva;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opt4j.core.Criterion;
import org.palladiosimulator.solver.models.PCMInstance;

import de.uka.ipd.sdq.dsexplore.analysis.IAnalysisResult;
import de.uka.ipd.sdq.dsexplore.qml.pcm.datastructures.EvaluationAspectWithContext;

public class ArevaAnalysisResult implements IAnalysisResult {
	
	/** Logger for log4j. */
	private static Logger logger = 
		Logger.getLogger("de.uka.ipd.sdq.dsexplore.analysis.areva");

	private boolean normalized = false;
	
	private double accuracy;
	private double lifetime;
	private double noise;
	private double pointingtime;
	private double powerconsumption;
	private double torque;
	
	private Map<Criterion, EvaluationAspectWithContext> criterionToAspectMap;
	private ArevaSolverQualityAttributeDeclaration arevaQualityDimensionDeclaration;
	
	public ArevaAnalysisResult(Map<String,Double> dimensionToValue, PCMInstance pcmInstance2, Map<Criterion, EvaluationAspectWithContext> criterionToAspect, ArevaSolverQualityAttributeDeclaration arevaQualityAttribute) {		
		this.criterionToAspectMap = criterionToAspect;
		this.arevaQualityDimensionDeclaration = arevaQualityAttribute;
		
		this.accuracy = dimensionToValue.get(arevaQualityDimensionDeclaration.getAccuracyDimension().getEntityName().toLowerCase());
		this.lifetime = dimensionToValue.get(arevaQualityDimensionDeclaration.getLifetimeDimension().getEntityName().toLowerCase());
		this.noise = dimensionToValue.get(arevaQualityDimensionDeclaration.getNoiseDimension().getEntityName().toLowerCase());
		this.pointingtime = dimensionToValue.get(arevaQualityDimensionDeclaration.getPointingtimeDimension().getEntityName().toLowerCase());
		this.powerconsumption = dimensionToValue.get(arevaQualityDimensionDeclaration.getPowerconsumptionDimension().getEntityName().toLowerCase());
		this.torque = dimensionToValue.get(arevaQualityDimensionDeclaration.getTorqueDimension().getEntityName().toLowerCase());
	}

	@Override
	public double getValueFor(Criterion criterion)  {
		EvaluationAspectWithContext aspect = this.criterionToAspectMap.get(criterion);
		
		if (aspect != null){
			if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getAccuracyDimension())){
				return this.getAccuracy();
			} 
			else if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getLifetimeDimension())){
				return this.getLifetime();
			}
			else if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getNoiseDimension())){
				return this.getNoise();
			}
			else if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getPointingtimeDimension())){
				return this.getPointingtime();
			}
			else if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getPowerconsumptionDimension())){
				return this.getPowerconsumption();
			}
			else if (EcoreUtil.equals(aspect.getDimension(), this.arevaQualityDimensionDeclaration.getTorqueDimension())){
				return this.getTorque();
			}
		} 
		
		logger.warn("Unknown aspect for Areva result, adding NaN.");
		return Double.NaN;
	}
	
	public double getAccuracy() {
		return accuracy;
	}

	public double getLifetime() {
		return lifetime;
	}

	public double getNoise() {
		return noise;
	}

	public double getPointingtime() {
		return pointingtime;
	}

	public double getPowerconsumption() {
		return powerconsumption;
	}

	public double getTorque() {
		return torque;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	public void setNoise(double noise) {
		this.noise = noise;
	}

	public void setPointingtime(double pointingtime) {
		this.pointingtime = pointingtime;
	}

	public void setPowerconsumption(double powerconsumption) {
		this.powerconsumption = powerconsumption;
	}

	public void setTorque(double torque) {
		this.torque = torque;
	}
	
	public boolean isNormalized() {
		return normalized;
	}

	public void setNormalized(boolean normalized) {
		this.normalized = normalized;
	}

}
