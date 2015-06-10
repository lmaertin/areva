package areva.math;

/**
 * This class calculates points and point-series used for drawing graph
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class PointsCalculator {

	public final static int NUMBER_OF_POINTS_PER_INTERVAL = 1000;

	/**
	 * @param interval
	 * @return array of x-values in the interval
	 */
	public static double[] calcXSeries(Interval interval) {
		double a = interval.getA();
		double b = interval.getB();
		if (interval.getAClosed() && interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL + 2];
			for (int i = 0; i <= NUMBER_OF_POINTS_PER_INTERVAL + 1; i++) {
				d[i] = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
			}
			return d;
		} else if (!interval.getAClosed() && interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL];
			for (int i = 1; i <= NUMBER_OF_POINTS_PER_INTERVAL; i++) {
				d[i - 1] = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
			}
			return d;
		} else if (interval.getAClosed() && !interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL];
			for (int i = 0; i < NUMBER_OF_POINTS_PER_INTERVAL; i++) {
				d[i] = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
			}
			return d;
		} else {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL - 1];
			for (int i = 1; i <= NUMBER_OF_POINTS_PER_INTERVAL - 1; i++) {
				d[i - 1] = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
			}
			return d;
		}
	}

	/**
	 * This method calculates y-series for a given interval and function
	 * compatibly to the x-series
	 * 
	 * @param interval
	 * @param function
	 * @return array of y-values
	 */
	public static double[] calcYSeries(Interval interval, String function) {
		Parser parser = new Parser();
		double a = interval.getA();
		double b = interval.getB();
		String f = function;

		if (interval.getAClosed() && interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL + 2];
			for (int i = 0; i <= NUMBER_OF_POINTS_PER_INTERVAL + 1; i++) {
				double xValue = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
				f = function.replaceAll("x", "" + xValue);
				double yValue = parser.parse(f);
				d[i] = yValue;
			}
			return d;
		} else if (!interval.getAClosed() && interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL];
			for (int i = 1; i <= NUMBER_OF_POINTS_PER_INTERVAL; i++) {
				double xValue = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
				f = function.replaceAll("x", "" + xValue);
				double yValue = parser.parse(f);
				d[i - 1] = yValue;
			}
			return d;
		} else if (interval.getAClosed() && !interval.getBClosed()) {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL];
			for (int i = 0; i < NUMBER_OF_POINTS_PER_INTERVAL; i++) {
				double xValue = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
				f = function.replaceAll("x", "" + xValue);
				double yValue = parser.parse(f);
				d[i] = yValue;
			}
			return d;
		} else {
			double[] d = new double[NUMBER_OF_POINTS_PER_INTERVAL - 1];
			for (int i = 1; i <= NUMBER_OF_POINTS_PER_INTERVAL - 1; i++) {
				double xValue = interval.getA() + i * (b - a)
						/ NUMBER_OF_POINTS_PER_INTERVAL;
				f = function.replaceAll("x", "" + xValue);
				double yValue = parser.parse(f);
				d[i - 1] = yValue;
			}
			return d;
		}
	}

	/**
	 * tests whether the parser is able to parse with x-Value d
	 * @param s
	 * @param d
	 * @return true if function is correct
	 */
	public static boolean testFunction(String s, double d) {
		s = s.replaceAll("x", "" + d);
		Parser p = new Parser();

		try {
			p.parse(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * calculates a single point
	 * @param d x-value
	 * @param term
	 * @return y-value
	 */
	public static Double calcSinglePoint(Double d, String term) {
		term = term.replaceAll("x", "" + d);
		Parser p = new Parser();
		return p.parse(term);
	}

}
