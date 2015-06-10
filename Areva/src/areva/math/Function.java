package areva.math;

/**
 * A function gets a list of arguments and calculates something of it.
 * 
 * @author Benjamin Sigg
 * @version 2.0 https://github.com/DanielGronau/bdparser
 *          http://www.java-forum.org
 *          /allgemeines/12306-parser-fuer-mathematische-formeln.html
 */
public interface Function {
	/**
	 * proofes if the number of arguments is valid
	 * 
	 * @param count
	 *            number of arguments
	 * @return <code>true</code> falls <code>count</code> trivial
	 */
	public boolean validNrOfArguments(int count);

	/**
	 * calculates the function
	 * 
	 * @param values
	 *            the arguments, which number has been tested
	 * @return result
	 */
	public double calculate(double[] values);
}
