package com.semrobots.tp2;

import java.util.ArrayList;
import java.util.List;

import com.semrobots.math.Matrix;

public class PerspectivaResolver {

	public List<Matrix> calcularPerspectiva(ParametrosDeCamaraYPuntos parametros) {
		int nroPuntos = parametros.getPuntos().size();
		List<Matrix> list = new ArrayList<Matrix>();
		for (int j = 0; j < nroPuntos; j++){
			Matrix punto = parametros.getPuntos().get(j);
			
			//Ms = matriz Mq expresado en sistema de coordenadas S0
			Matrix Ms = parametros.getR().transpose().multiply((punto.substract(parametros.getS())));
			
			double lambda0 = calcularLambdaAproximado(parametros, Ms);
			Matrix ms = Ms.scale(lambda0);
			ms.set(0, 2, lambda0);
			
			//ms es (u,v,lambda)
			list.add(ms);
		}
		return list;
	}

		
	private double calcularLambdaAproximado(ParametrosDeCamaraYPuntos parametros, Matrix Ms) {
		double Mzs = Ms.get(0, 2);
		return -parametros.getP() / Mzs;
	}
}
