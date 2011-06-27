package com.semrobots.math;

import com.semrobots.math.exceptions.InvalidMatrixOpException;
import com.semrobots.math.util.DoubleUtil;

public class Matrix {
	private static final double DEFAULT_MATRIX_HOLGADEZ = 1.0;
	private double [][] a;
	
	public Matrix(int x, int y) {
		a = new double[x][y];
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				this.set(i, j, 0);
			}
		}
	}
	
	public Matrix(int y) {
		this(1, y);
	}
	
	public Matrix(double[][] m) {
		this(m.length, m[0].length);
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				this.set(i, j, this.get(i, j) + m[i][j]);
			}
		}
	}
	
	public Matrix(Matrix m) {
		this(m.getXLen(), m.getYLen());
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				this.set(i, j, this.get(i, j) + m.get(i,j));
			}
		}
	}
	
	public double get(int i, int j) {
		return a[i][j];
	}
	
	public void set(int i, int j, double value) {
		a[i][j] = value;
	}
	
	public double[][] getArray() {
		return a;
	}
	
	public int getXLen() {
		return a.length;
	}
	public int getYLen() {
		return a[0].length;
	}

	
	public double quadSum(){
		double res = 0.0;
		for (int i = 0; i < this.getXLen(); i++){
			for (int j = 0; j < this.getYLen(); j++){
				res += this.get(i, j) * this.get(i, j);
			}
		}
		return res;
	}
	
	public Matrix add(Matrix m) {
		if (m.getXLen() != this.getXLen() || m.getYLen() != this.getYLen()){
			throw new InvalidMatrixOpException("Suma entre matrices de distinto tamaño.");
		}
		Matrix r = new Matrix(getXLen(), getYLen());
		for (int i = 0; i < getXLen(); i++){
			for (int j = 0; j < getYLen(); j++){
				r.set(i, j, this.get(i, j) + m.get(i, j));
			}
		}
		return r;
	}
	
	public Matrix substract(Matrix m) {
		if (m.getXLen() != this.getXLen() || m.getYLen() != this.getYLen()){
			throw new InvalidMatrixOpException("Suma entre matrices de distinto tamaño.");
		}
		Matrix r = new Matrix(getXLen(), getYLen());
		for (int i = 0; i < getXLen(); i++){
			for (int j = 0; j < getYLen(); j++){
				r.set(i, j, this.get(i, j) - m.get(i, j));
			}
		}
		return r;
	}
	
	public Matrix scale(double k) {
		Matrix r = new Matrix(getXLen(), getYLen());
		for (int i = 0; i < getXLen(); i++){
			for (int j = 0; j < getYLen(); j++){
				r.set(i, j, this.get(i, j)*k);
			}
		}
		return r;
	}
	
	public Matrix multiply(Matrix m) {
		if (this.getXLen() != m.getYLen()){
			throw new InvalidMatrixOpException("Multiplicacion entre matrices que no coinciden en y del primero y x del segundo.");
		}
		Matrix r = new Matrix(m.getXLen(), this.getYLen());
		for (int i = 0; i < r.getXLen(); i++){
			for (int j = 0; j < r.getYLen(); j++){
				for (int k = 0; k < this.getXLen(); k++){
					r.set(i, j, r.get(i, j) + this.get(k, j) * m.get(i, k));
				}
			}
		}
		return r;
	}
	
	public Matrix transpose(){
		Matrix r = new Matrix(this.getYLen(), this.getXLen());
		for (int i = 0; i < getXLen(); i++){
			for (int j = 0; j < getYLen(); j++){
				r.set(j, i, this.get(i, j));
			}
		}
		return r;
	}
	
	
	public double traza(){
		if (getXLen() != getYLen()){
			throw new InvalidMatrixOpException("No puede calcularse la traza de una matriz no cuadrada.");
		}
		double res = 0.0;
		for (int i = 0; i < getXLen(); i++){
			res += this.get(i, i);
		}
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)){
			return true;
		}
		if (obj == null || !(obj instanceof Matrix)){
			return false;
		}
		Matrix mobj = (Matrix)obj;
		if (mobj.getXLen() != this.getXLen() || mobj.getYLen() != this.getYLen()){
			return false;
		}
		for (int i = 0; i < getXLen(); i++){
			for (int j = 0; j < getYLen(); j++){
				if (this.get(i, j) != mobj.get(i, j)){
					return false;
				}
			}
		}
		
		return true;
	}


	//metodo para descartar los efectos del redondeo (no sirve para caso de valores elevados)
	public boolean almostEqual(Matrix m){
		return almostEqual(m, DEFAULT_MATRIX_HOLGADEZ);
	}
	public boolean almostEqual(Matrix m, double holgadez){
		return DoubleUtil.almostEquals(this.substract(m).quadSum(), 0.0, holgadez * m.getXLen() * m.getYLen());
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < this.getYLen(); j++){
			for (int i = 0; i < this.getXLen(); i++){
				sb.append(String.format("%1.16f ", this.get(i, j)));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
