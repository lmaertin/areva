package areva.math;

/**
 * 
 * @author Sven von Höveling
 * @version 1.0
 * 
 */
public class Interval {
	//e.g [a,b)
	private String interval;
	private double a;
	private double b;
	//e.g. [a,b) -> a, true; b, false
	private boolean aClosed;
	private boolean bClosed;
	//boolean to see if the String has a right format
	private boolean intervalOK;
	
	static final Double EPS = 0.1E-8;

	/**
	 * @param s
	 *            string which represents the interval
	 */
	public void setInterval(String s) {
		this.interval = s;
		intervalOK = false;
		if (checkAndLoad()) {
			intervalOK = true;
		}
	}

	/**
	 * checks if the String is really representing an interval and if so it
	 * transfers the string into the interval class
	 * 
	 * @return true if string is interval and transferring was successful, else
	 *         false
	 */
	private boolean checkAndLoad() {
		char[] c = interval.toCharArray();
		try {
			if (c[0] == '[') {
				this.aClosed = true;
			} else if (c[0] == '(') {
				this.aClosed = false;
			} else {
				return false;
			}

			if (c[c.length - 1] == ']') {
				this.bClosed = true;
			} else if (c[c.length - 1] == ')') {
				this.bClosed = false;
			} else {
				return false;
			}

			boolean bool = false;
			int commaPos = 0;
			String leftDecimal = "";
			String rightDecimal = "";

			// Check if there is a comma
			for (int i = 0; i < c.length; i++) {
				if (c[i] == ',') {
					bool = true;
					commaPos = i;
				}
			}
			// return false, if there was no comma
			if (!bool) {
				return false;
			}
			// try to get the first decimal
			for (int i = 1; i < commaPos; i++) {
				leftDecimal += c[i];
			}
			a = Double.parseDouble(leftDecimal);

			// try to get the second decimal
			for (int i = commaPos + 1; i < c.length - 1; i++) {
				rightDecimal += c[i];
			}
			b = Double.parseDouble(rightDecimal);
			intervalOK = true;

		} catch (Exception e) {
			return false;

		}
		return true;
	}

	/**
	 * @return the left limit of interval
	 */
	public double getA() {
		return a;
	}

	/**
	 * @return the right limit of interval
	 */
	public double getB() {
		return b;
	}

	/**
	 * @return true if interval is closed on the left, else false
	 */
	public boolean getAClosed() {
		return aClosed;
	}

	/**
	 * @return true if interval is closed on the right, else false
	 */
	public boolean getBClosed() {
		return bClosed;
	}

	/**
	 * @return true if the interval if well defined, else false
	 */
	public boolean getIntervalOK() {
		return intervalOK;
	}

	/**
	 * @param d
	 *            decimal for which is checked if it is in the interval
	 * @return true if d is in the interval, else false
	 */
	public boolean decimalInInterval(double d) {
		if (d > this.getA() && d < this.getB()) {
			return true;
		} else if (this.getAClosed() && this.getBClosed()
				&& d - this.getA() < EPS) {
			return true;
		} else {
			return false;
		}
	}

}
