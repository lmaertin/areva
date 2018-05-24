package de.uka.ipd.sdq.dsexplore.analysis;

import org.opt4j.core.Criterion;

import de.uka.ipd.sdq.statistics.estimation.ConfidenceInterval;

public interface IStatisticAnalysisResult extends IAnalysisResult {

	public double getMedianValue();
	
	public double getMeanValue();
	
	public double getStandardDeviation();
	
	public double getCoefficientOfVariance();

	public ConfidenceInterval getConfidenceInterval(Criterion criterion);
	
	public long getNumberOfObservations();
}
