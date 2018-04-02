package de.tubs.areva.util;

public class ZestViewCounter {
	static int i = 0;
	
	static public String get() {
		i++;
		return String.valueOf(i);
	}
}
