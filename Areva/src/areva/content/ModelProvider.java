package areva.content;

import java.util.ArrayList;
import areva.persistence.Storage;

/**
 * This class holds the metrics and qualityRatings and gives access to them
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class ModelProvider {

	private static ArrayList<Metric> metrics;
	private static ArrayList<QualityRating> qualityRatings;
	
	//boolean to see if the method load() was used already.
	private static boolean loaded = false;;

	/**
	 * initializes the ModelProvider, loads values from xml-file
	 */
	public static void load() {
		if (loaded != true) {
			metrics = new ArrayList<Metric>();
			qualityRatings = new ArrayList<QualityRating>();
			Storage.loadXml();
			loaded = true;
		}
	}

	/**
	 * @return metrics
	 */
	public static ArrayList<Metric> getMetrics() {
		return metrics;
	}

	/**
	 * @return qualityRatings
	 */
	public static ArrayList<QualityRating> getQualityRatings() {
		return qualityRatings;
	}

	/**
	 * This method returns the position of a metric which has the name of the
	 * parameter
	 * 
	 * @param name
	 *            of the metric which should be searched
	 * @return position, -1 if not found
	 */
	public static int findMetricPosition(String name) {
		for (int i = 0; i < metrics.size(); i++) {
			if (metrics.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method returns the position of a qualityRating which has the name of
	 * the parameter
	 * 
	 * @param name
	 *            of the qualityRating which should be searched
	 * @return the position, -1 if not found
	 */
	public static int findQualityRatingPosition(String name) {
		for (int i = 0; i < qualityRatings.size(); i++) {
			if (qualityRatings.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

}