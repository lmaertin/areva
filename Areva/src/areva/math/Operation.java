package areva.math;

/**
 * An operation calculates a part of a formula. A typical operation is "+"
 * 
 * @author Benjamin Sigg
 * @version 2.0 https://github.com/DanielGronau/bdparser
 *          http://www.java-forum.org
 *          /allgemeines/12306-parser-fuer-mathematische-formeln.html
 */
public interface Operation {
	/**
	 * The higher the prority the earlier the operation will be calculated.
	 * 
	 * @return the priority
	 */
	public int getPriority();

	/**
	 * calculates the operation
	 * 
	 * @param a
	 *            first argument
	 * @param b
	 *            second argument
	 * @return result
	 */
	public double calculate(double a, double b);
}