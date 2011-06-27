package com.semrobots.tp1.rotmat;

import com.semrobots.math.Matrix;

public class BiAxisRotationMatrix extends RotationMatrix {

	protected BiAxisRotationMatrix(double[][] m) {
		super(m);
	}

	protected BiAxisRotationMatrix(Matrix m) {
		super(m);
	}

	
	public static BiAxisRotationMatrix rotate(int axis1, int axis2, double a1, double a2){
		int n = axis2 * 3 + axis1;
		switch(n){
		case Y_AXIS*3+X_AXIS:
			return new BiAxisRotationMatrix(new double[][] { { c(a1), 0.0, s(a1) },
					{ s(a1) * s(a2), c(a2), -c(a1) * s(a2) },
					{ -s(a1) * c(a2), s(a2), c(a1) * c(a2) } });
		case Y_AXIS*3+Z_AXIS:
			return new BiAxisRotationMatrix(
					new double[][] { { c(a1) * c(a2), -s(a2), s(a1) * c(a2) },
							{ c(a1) * s(a2), c(a2), s(a1) * s(a2) },
							{ -s(a1), 0.0, c(a1) } });
		case Z_AXIS*3+X_AXIS:
			return new BiAxisRotationMatrix(new double[][] { { c(a1), -s(a1), 0.0 },
					{ s(a1) * c(a2), c(a1) * c(a2), -s(a2) },
					{ s(a1) * s(a2), c(a1) * s(a2), c(a2) } });
		case Z_AXIS*3+Y_AXIS:
			return new BiAxisRotationMatrix(
					new double[][] { { c(a1) * c(a2), -s(a1) * c(a2), s(a2) },
							{ s(a1), c(a1), 0.0 },
							{ -c(a1) * s(a2), s(a1) * s(a2), c(a2) } });
		case X_AXIS*3+Z_AXIS:
			return new BiAxisRotationMatrix(
					new double[][] { { c(a2), -c(a1) * s(a2), s(a1) * s(a2) },
							{ s(a2), c(a1) * c(a2), -s(a1) * c(a2) },
							{ 0.0, s(a1), c(a1) } });
		case X_AXIS*3+Y_AXIS:
			return new BiAxisRotationMatrix(new double[][] {
					{ c(a2), s(a1) * s(a2), c(a1) * s(a2) },
					{ 0.0, c(a1), -s(a1) },
					{ -s(a2), s(a1) * c(a2), c(a1) * c(a2) } });
		default:
			throw new AssertionError("Invalid pair of axis.");
		}
	}
	
	
	public static BiAxisRotationMatrix rotateYX(double a1, double a2) {
		return rotate(Y_AXIS, X_AXIS, a1, a2);
	}

	public static BiAxisRotationMatrix rotateYZ(double a1, double a2) {
		return rotate(Y_AXIS, Z_AXIS, a1, a2);
	}

	public static BiAxisRotationMatrix rotateZX(double a1, double a2) {
		return rotate(Z_AXIS, X_AXIS, a1, a2);
	}

	public static BiAxisRotationMatrix rotateZY(double a1, double a2) {
		return rotate(Z_AXIS, Y_AXIS, a1, a2);
	}

	public static BiAxisRotationMatrix rotateXZ(double a1, double a2) {
		return rotate(X_AXIS, Z_AXIS, a1, a2);
	}

	public static BiAxisRotationMatrix rotateXY(double a1, double a2) {
		return rotate(X_AXIS, Y_AXIS, a1, a2);
	}
}
