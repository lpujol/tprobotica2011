package com.semrobots.tp2;

import java.util.ArrayList;
import java.util.List;

import com.semrobots.math.Matrix;

public class ParametrosDeCamaraYPuntos {
	private Matrix s;
	private Matrix R;
	private Double p;
	private List<Matrix> puntos = new ArrayList<Matrix>();
	private List<Matrix> mediciones = new ArrayList<Matrix>();
	private List<Matrix> puntosVista = new ArrayList<Matrix>();;
	
	public Matrix getS() {
		return s;
	}
	public void setS(Matrix s) {
		this.s = s;
	}
	public Matrix getR() {
		return R;
	}
	public void setR(Matrix r) {
		R = r;
	}
	public Double getP() {
		return p;
	}
	public void setP(Double p) {
		this.p = p;
	}
	public List<Matrix> getPuntos() {
		return puntos;
	}
	public void setPuntos(List<Matrix> puntos) {
		this.puntos = puntos;
	}
	public void setMediciones(List<Matrix> mediciones) {
		this.mediciones = mediciones;
	}
	public List<Matrix> getMediciones() {
		return mediciones;
	}
	public List<Matrix> getPuntosVista() {
		return puntosVista;
	}
}
