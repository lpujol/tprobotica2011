package com.semrobots.tp1.rotmat;


public class UVWParametersBiAxisRotationMatrix extends BiAxisRotationMatrix{

	protected UVWParametersBiAxisRotationMatrix(double[][] m) {
		super(m);
	}

	public static UVWParametersBiAxisRotationMatrix rotateYX(double u, double v, double w) {
		return rotate(Y_AXIS, X_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotateYZ(double u, double v, double w) {
		return rotate(Y_AXIS, Z_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotateZX(double u, double v, double w) {
		return rotate(Z_AXIS, X_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotateZY(double u, double v, double w) {
		return rotate(Z_AXIS, Y_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotateXZ(double u, double v, double w) {
		return rotate(X_AXIS, Z_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotateXY(double u, double v, double w) {
		return rotate(X_AXIS, Y_AXIS, u, v, w);
	}

	public static UVWParametersBiAxisRotationMatrix rotate(int axis1, int axis2, double u, double v,
			double w) {
		int n = axis1 * 3 + axis2;
		double pproy;
		switch(n){
		case Y_AXIS*3+X_AXIS:
			pproy = Math.sqrt(u*u+w*w);
			return new UVWParametersBiAxisRotationMatrix(new double[][] { { w/pproy, u, v*u/pproy },
					{ 0.0, v, -pproy },
					{ -u/pproy, w, v*w/pproy } });
		case Y_AXIS*3+Z_AXIS:
			pproy = Math.sqrt(u*u+w*w);
			return new UVWParametersBiAxisRotationMatrix(new double[][] { { v*u/pproy, u, -w/pproy },
					{ -pproy, v, 0.0 },
					{ v*w/pproy, w, u/pproy } });
		case Z_AXIS*3+X_AXIS:
			pproy = Math.sqrt(u*u+v*v);
			return new UVWParametersBiAxisRotationMatrix(new double[][] { { v/pproy, w*u/pproy, u },
					{ -u/pproy, w*v/pproy, v },
					{ 0.0, -pproy, w } });
		case Z_AXIS*3+Y_AXIS:
			pproy = Math.sqrt(u*u+v*v);
			return new UVWParametersBiAxisRotationMatrix(
					new double[][] { { w*u/pproy, -v/pproy, u },
							{ w*v/pproy, u/pproy, v },
							{ -pproy, 0.0, w } });
		case X_AXIS*3+Z_AXIS:
			pproy = Math.sqrt(v*v+w*w);
			return new UVWParametersBiAxisRotationMatrix(
					new double[][] { { u, -pproy, 0.0 },
							{ v, u*v/pproy, -w/pproy },
							{ w, u*w/pproy, v/pproy } });
		case X_AXIS*3+Y_AXIS:
			pproy = Math.sqrt(v*v+w*w);
			return new UVWParametersBiAxisRotationMatrix(
					new double[][] { { u, 0.0, -pproy },
							{ v, w/pproy, u*v/pproy },
							{ w, -v/pproy, u*w/pproy } });
		default:
			throw new AssertionError("Combinacion invalida");
		}
	}


}
