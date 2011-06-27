package com.semrobots.tp1.rotmat;

import com.semrobots.math.InversibleMatrix;
import com.semrobots.math.Matrix;

public class RotationMatrix extends InversibleMatrix {

	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	public static final int Z_AXIS = 2;
	
	protected RotationMatrix(double[][] m) {
		super(m);
	}

	protected RotationMatrix(Matrix m) {
		super(m);
	}

	@Override
	public RotationMatrix invert() {
		return new RotationMatrix(this.transpose());
	}

	public static RotationMatrix rotateX(double ang) {
		return rotate(X_AXIS, ang);
	}

	public static RotationMatrix rotateY(double ang) {
		return rotate(Y_AXIS, ang);
	}

	public static RotationMatrix rotateZ(double ang) {
		return rotate(Z_AXIS, ang);
	}

	
	public static RotationMatrix rotate(int axis, double ang) {
		switch (axis){
		case X_AXIS:
			return new RotationMatrix(new double[][] { { 1.0, 0.0, 0.0 },
				{ 0.0, c(ang), -s(ang) }, { 0.0, s(ang), c(ang) } });
		case Y_AXIS:
			return new RotationMatrix(new double[][] { { c(ang), 0.0, s(ang) },
				{ 0.0, 1.0, 0.0 }, { -s(ang), 0.0, c(ang) } });
		case Z_AXIS:
			return new RotationMatrix(new double[][] { { c(ang), -s(ang), 0.0 },
				{ s(ang), c(ang), 0.0 }, { 0.0, 0.0, 1.0 } });
		default:
			throw new AssertionError("Bad axis");
		}
	}

	
	protected static double s(double ang) {
		return Math.sin(ang);
	}

	protected static double c(double ang) {
		return Math.cos(ang);
	}
}
