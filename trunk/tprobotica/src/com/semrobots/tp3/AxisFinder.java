package com.semrobots.tp3;

import com.semrobots.math.Matrix;

public class AxisFinder {
	public static Matrix calculateRotationAxis(Matrix m){
		Matrix axiador = (m.substract(m.transpose())).scale(0.5);
		Matrix axisScaled = new Matrix(new double[][]{{-axiador.get(2, 1), axiador.get(2, 0), -axiador.get(1, 0)}});
		return axisScaled.scale(1.0/Math.sqrt(axisScaled.quadSum()));
	}
	
	public static double getAnguloDeRotacion(Matrix m){
		return Math.acos((m.traza() - 1.0)/2.0);
	}
}
