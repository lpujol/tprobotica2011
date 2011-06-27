package com.semrobots.math.util;

public class DoubleUtil {
	private static final double ALMOST_EQUAL_DIST = 1E-16;	//FIXME absolutamente arbitrario :P
	private static final double DEFAULT_HOLGADEZ = 5.0;	//FIXME absolutamente arbitrario :P
	
	public static boolean almostEquals(double d1, double d2, double holgadez){
		return Math.abs(d1 - d2) <= ALMOST_EQUAL_DIST * holgadez;
	}
	
	public static boolean almostEquals(double d1, double d2){
		return almostEquals(d1, d2, DEFAULT_HOLGADEZ);
	}
}
