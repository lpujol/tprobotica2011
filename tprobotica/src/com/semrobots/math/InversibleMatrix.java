package com.semrobots.math;

import com.semrobots.math.exceptions.InvalidMatrixOpException;

public class InversibleMatrix extends Matrix{
	public InversibleMatrix(int x) {
		super(x, x);
	}
	
	public InversibleMatrix(double[][] m) throws InvalidMatrixOpException {
		super(m);
		if (this.getXLen() != this.getYLen()){
			throw new InvalidMatrixOpException("Las matrices deben ser cuadradas.");
		}
	}
	
	public InversibleMatrix(Matrix m) throws InvalidMatrixOpException {
		super(m);
		if (this.getXLen() != this.getYLen()){
			throw new InvalidMatrixOpException("Las matrices deben ser cuadradas.");
		}
	}

	public InversibleMatrix invert(){
		Jama.Matrix m = new Jama.Matrix(this.getArray());
		if (m.det() == 0.0){
			throw new InvalidMatrixOpException("La matriz es singular.");
		}
		return new InversibleMatrix(m.inverse().getArray());
	}
}
