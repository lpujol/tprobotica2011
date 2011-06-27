package com.semrobots.rotmat;

import com.semrobots.math.Matrix;
import com.semrobots.math.MatrixConstants;
import com.semrobots.tp1.rotmat.RotationMatrix;

public class GenericRotationMatrix extends RotationMatrix{

	protected GenericRotationMatrix(Matrix m) {
		super(m);
	}

	public static GenericRotationMatrix rotate(Matrix axis, double ang) {
		double alfa = axis.get(0, 0);
		double beta = axis.get(0, 1);
		double gamma = axis.get(0, 2);
		Matrix axiador = new Matrix(new double[][]{{0.0, gamma, -beta},{-gamma, 0.0, alfa},{beta, -alfa, 0.0}});
		return new GenericRotationMatrix(MatrixConstants.ID_3_3.add(axiador.scale(Math.sin(ang))).add(axiador.multiply(axiador).scale(1.0-Math.cos(ang))));
	}
}
