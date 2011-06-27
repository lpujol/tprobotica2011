package com.semrobots.tp2;

import com.semrobots.math.InversibleMatrix;
import com.semrobots.math.Matrix;

public class NormalizationHelper {
	public static InversibleMatrix normalizarTerminos(Matrix terminosEcuaciones) {
		return new InversibleMatrix(terminosEcuaciones.transpose().multiply(terminosEcuaciones));
	}
	
	public static Matrix normalizarSolucion(Matrix terminosEcuaciones, Matrix solucion) {
		return terminosEcuaciones.transpose().multiply(solucion);
	}
}
