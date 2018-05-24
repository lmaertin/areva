/**
 * 
 */
package de.uka.ipd.sdq.dsexplore.analysis.areva;

import java.util.ArrayList;
import java.util.List;

import de.uka.ipd.sdq.dsexplore.analysis.IAnalysisQualityAttributeDeclaration;
import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer.QualityAttribute;
import de.uka.ipd.sdq.dsexplore.qml.contract.QMLContract.EvaluationAspect;
import de.uka.ipd.sdq.dsexplore.qml.contract.QMLContract.Value;
import de.uka.ipd.sdq.dsexplore.qml.contracttype.QMLContractType.Dimension;
import de.uka.ipd.sdq.dsexplore.qml.handling.QMLConstantsContainer;
import de.uka.ipd.sdq.dsexplore.qml.reader.QMLDimensionReader;

/**
 * This class declares, which {@code Dimension} and {@code EvaluationAspect} can 
 * be evaluated by this extension.
 * 
 * @author maertin
 *
 */
public class ArevaSolverQualityAttributeDeclaration implements IAnalysisQualityAttributeDeclaration {

	public static final String qualityAttributeAccuracy = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_ACCURACY_DEFINITION_PATH;
	public static final String qualityAttributeLifetime = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_LIFETIME_DEFINITION_PATH;
	public static final String qualityAttributeNoise = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_NOISE_DEFINITION_PATH;
	public static final String qualityAttributePointingtime = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_POINTINGTIME_DEFINITION_PATH;
	public static final String qualityAttributePowerconsumption = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_POWERCONSUMPTION_DEFINITION_PATH;
	public static final String qualityAttributeTorque = QMLConstantsContainer.QUALITY_ATTRIBUTE_DIMENSION_TORQUE_DEFINITION_PATH;
			
	private Dimension accuracy;
	private Dimension lifetime;
	private Dimension noise;
	private Dimension pointingtime;
	private Dimension powerconsumption;
	private Dimension torque;
	
	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.dsexplore.analysis.IQualityAttribute#getDimension()
	 */
	@Override
	public List<Dimension> getDimensions() {
		accuracy = new QMLDimensionReader().getDimension(qualityAttributeAccuracy);
		lifetime = new QMLDimensionReader().getDimension(qualityAttributeLifetime);
		noise = new QMLDimensionReader().getDimension(qualityAttributeNoise);
		pointingtime = new QMLDimensionReader().getDimension(qualityAttributePointingtime);
		powerconsumption = new QMLDimensionReader().getDimension(qualityAttributePowerconsumption);
		torque = new QMLDimensionReader().getDimension(qualityAttributeTorque);
		
		List<Dimension> result = new ArrayList<Dimension>(1);
		result.add(accuracy);
		result.add(lifetime);
		result.add(noise);
		result.add(pointingtime);
		result.add(powerconsumption);
		result.add(torque);
		
		return result;
	}
	
	protected Dimension getAccuracyDimension() {
		return accuracy;
	}

	protected Dimension getLifetimeDimension() {
		return lifetime;
	}

	protected Dimension getNoiseDimension() {
		return noise;
	}

	protected Dimension getPointingtimeDimension() {
		return pointingtime;
	}

	protected Dimension getPowerconsumptionDimension() {
		return powerconsumption;
	}

	protected Dimension getTorqueDimension() {
		return torque;
	}

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.dsexplore.analysis.IAnalysisQualityAttribute#getDimension()
	 */
	@Override
	public boolean canEvaluateAspectForDimension(EvaluationAspect aspect, Dimension dimension) {
		if(aspect instanceof Value) {
			return true;
		}
		return false;
	}

	@Override
	public QualityAttribute getQualityAttribute() {
		return QualityAttribute.AREVA_QUALITY;
	}

}
