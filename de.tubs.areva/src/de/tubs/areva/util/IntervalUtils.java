package de.tubs.areva.util;

public class IntervalUtils {

	public static boolean isInRange(String interval, float value) {
		
		boolean openingParenthesis = (interval.charAt(0) == '(');
		boolean closingParenthesis = (interval.charAt(interval.length()-1) == ')');
		
		int semicolonPosition = interval.indexOf(';');
		
		float leftValue = Float.parseFloat(interval.substring(1, semicolonPosition));
		float rightValue = Float.parseFloat(interval.substring(semicolonPosition+1, interval.length()-1));
		
		if(openingParenthesis) {
			if(value <= leftValue) {
				return false;
			}
		} else {
			if(value < leftValue) {
				return false;
			}
		}
		
		if(closingParenthesis) {
			if(value >= rightValue) {
				return false;
			}
		} else {
			if(value > rightValue) {
				return false;
			}
		}
		
		
		
		return true;
		
	}
}
