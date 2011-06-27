package com.semrobots.tp2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.semrobots.math.Matrix;

public class CorrectorDePosicionResolver {

	public Matrix resolverProblema(ParametrosDeCamaraYPuntos parametros) {
		Matrix matrizDeEcuaciones = calcularMatrizDeEcuaciones(parametros);
		Matrix matrizSolucion = calcularVectorSolucion(parametros);
		return NormalizationHelper.normalizarTerminos(matrizDeEcuaciones).invert().multiply(NormalizationHelper.normalizarSolucion(matrizDeEcuaciones, matrizSolucion));
	}

	private List<Matrix> calcularErrorDeVista(
			ParametrosDeCamaraYPuntos parametros) {
		List<Matrix> err = new ArrayList<Matrix>();
		Iterator<Matrix> iVista = parametros.getPuntosVista().iterator();
		Iterator<Matrix> iMed = parametros.getMediciones().iterator();
		while (iVista.hasNext()){
			Matrix pVista = discardLambda(iVista.next());
			Matrix pMed = iMed.next();
			err.add(pVista.substract(pMed));
		}
		return err;
	}

	private Matrix discardLambda(Matrix next) {
		return new Matrix(new double[][]{{next.get(0, 0), next.get(0, 1)}});
	}

	private Matrix calcularVectorSolucion(ParametrosDeCamaraYPuntos parametros) {
		List<Matrix> errorDePuntosVista = calcularErrorDeVista(parametros);
		int nroPuntos = errorDePuntosVista.size();
		int nroEcuaciones = nroPuntos * 2;	//por cada punto hay 2 ecuaciones
		Matrix matrizSolucion = new Matrix(nroEcuaciones);
		for (int i = 0; i < nroPuntos; i++){
			double[] sol = getTerminoSolucion(errorDePuntosVista, i);
			matrizSolucion.set(0, i * 2, sol[0]);
			matrizSolucion.set(0, i * 2 + 1, sol[1]);
		}
		return matrizSolucion;
	}

	private Matrix calcularMatrizDeEcuaciones(
			ParametrosDeCamaraYPuntos parametros) {
		int nroPuntos = parametros.getPuntosVista().size();
		Matrix matrizDeEcuaciones = new Matrix(6, nroPuntos * 2);	//por cada punto hay 2 ecuaciones
		for (int j = 0; j < nroPuntos; j++){
			double u0 = parametros.getMediciones().get(j).get(0, 0);
			double v0 = parametros.getMediciones().get(j).get(0, 1);
			double lambda0 =-parametros.getPuntosVista().get(j).get(0, 2);
			double[][] terminos = getTerminos(parametros, u0, v0, lambda0);
			for (int i = 0; i < 6; i++){
				matrizDeEcuaciones.set(i, j*2, terminos[i][0]);
				matrizDeEcuaciones.set(i, j*2+1, terminos[i][1]);
			}
		}
		return matrizDeEcuaciones;
	}

		
	private double[] getTerminoSolucion(List<Matrix> errorDePuntosVista,
			int i) {
		Matrix error = errorDePuntosVista.get(i);
		return new double[]{error.get(0, 0), error.get(0, 1)};
	}

	//el orden en que se van a calcular los terminos es:
	//alfa, beta, gama, dsx, dsy, dsz
	private double[][] getTerminos(ParametrosDeCamaraYPuntos parametros, double u0, double v0, double lambda0) {
		Matrix Rt = parametros.getR().transpose();
		
		double p = parametros.getP();
		
		Matrix RtSc = Rt.scale(lambda0);
		Matrix terminosDif = new Matrix(new double[][]{
				{0, p, v0, -RtSc.get(0, 0), -RtSc.get(1, 0), -RtSc.get(2, 0)},
				{-p, 0, -u0, -RtSc.get(0, 1), -RtSc.get(1, 1), -RtSc.get(2, 1)},
				{-v0, u0, 0, -RtSc.get(0, 2), -RtSc.get(1, 2), -RtSc.get(2, 2)}
		}).transpose();
		Matrix vCam = new Matrix(new double[][]{{1.0, 0.0}, {0.0, 1.0}, {u0/p, v0/p}});
		return vCam.multiply(terminosDif).getArray();
	}

}
