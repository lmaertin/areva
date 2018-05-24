/**
 * 
 */
package de.uka.ipd.sdq.dsexplore.analysis;

import java.util.ArrayList;
import java.util.List;

import de.uka.ipd.sdq.dsexplore.launch.DSEConstantsContainer.QualityAttribute;

/**
 * This class is supposed to know all possible {@code Dimension}s,i.e. quality attributes, that can be 
 * considered in PerOpteryx.
 * 
 * @author noorshams
 *
 */
public class AnalysisQualityAttributes {
	
	/**
	 * 
	 * @return a list of all considered {@code Dimension}s in PerOpteryx.
	 */
	public List<QualityAttribute> getAllQualityAttributes(){
//		
		List<QualityAttribute> attributes = new ArrayList<QualityAttribute>(5);
		attributes.add(QualityAttribute.PERFORMANCE_QUALITY);
		attributes.add(QualityAttribute.RELIABILITY_QUALITY);
		attributes.add(QualityAttribute.COST_QUALITY);
		attributes.add(QualityAttribute.SECURITY_QUALITY);
		attributes.add(QualityAttribute.NQR_QUALITY);
		attributes.add(QualityAttribute.AREVA_QUALITY);
		
		return attributes;
		
	}
}
