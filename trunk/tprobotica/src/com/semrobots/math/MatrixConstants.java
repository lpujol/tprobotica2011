package com.semrobots.math;

public class MatrixConstants {
	public static final Matrix ZERO_3_3 = new Matrix(3,3);
	public static final Matrix ID_3_3 = new Matrix(new double[][]{{1.0,0.0,0.0},{0.0,1.0,0.0},{0.0,0.0,1.0}});
	public static final Matrix X_VERSOR = new Matrix(new double[][]{{1.0, 0.0, 0.0}});
	public static final Matrix Y_VERSOR = new Matrix(new double[][]{{0.0, 1.0, 0.0}});
	public static final Matrix Z_VERSOR = new Matrix(new double[][]{{0.0, 0.0, 1.0}});
}
