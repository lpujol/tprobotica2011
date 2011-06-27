package com.semrobots.tp2011;

import com.semrobots.math.Matrix;

public class TP2011Resolver {

	public Matrix resolver(ParametrosTp2011 parametros,
			ParametrosTp2011 parametros2) {
		Matrix B1=parametros.getS();
		Matrix B2=parametros2.getS();
		Matrix A1=parametros.getR().multiply(parametros.getUvp());
		Matrix A2=parametros2.getR().multiply(parametros2.getUvp());
		
		
		double A2B2=A2.get(0, 0)*B2.get(0, 0)+A2.get(0, 1)*B2.get(0, 1)+A2.get(0, 2)*B2.get(0, 2);
		double A1B1=A1.get(0, 0)*B1.get(0, 0)+A1.get(0, 1)*B1.get(0, 1)+A1.get(0, 2)*B1.get(0, 2);
		
		double moduloA2=Math.sqrt(A2.get(0, 0)*A2.get(0, 0)+A2.get(0, 1)*A2.get(0, 1)+A2.get(0, 2)*A2.get(0, 2));
		double moduloA1=Math.sqrt(A1.get(0, 0)*A1.get(0, 0)+A1.get(0, 1)*A1.get(0, 1)+A1.get(0, 2)*A1.get(0, 2));
		
		double moduloB2=Math.sqrt(B2.get(0, 0)*B2.get(0, 0)+B2.get(0, 1)*B2.get(0, 1)+B2.get(0, 2)*B2.get(0, 2));
		double moduloB1=Math.sqrt(B1.get(0, 0)*B1.get(0, 0)+B1.get(0, 1)*B1.get(0, 1)+B1.get(0, 2)*B1.get(0, 2));
		
		double moduloA1cuadrado=moduloA1*moduloA1;
		double moduloA2cuadrado=moduloA2*moduloA2;
		
		double A1porB1sobreModuloA1=(A1B1)/moduloA1;
		double A2porB2sobreModuloA2=(A2B2)/moduloA2;
		
		double A1porB1sobreModuloA1cuadrado=(A1B1)/(moduloA1cuadrado);
		double A2porB2sobreModuloA2cuadrado=(A2B2)/(moduloA2cuadrado);
		
		double escalarGrande=Math.sqrt(Math.abs((3*A1porB1sobreModuloA1*A1porB1sobreModuloA1+moduloB1*moduloB1-(3*A2porB2sobreModuloA2*A2porB2sobreModuloA2+moduloB2*moduloB2))))/2;
		
		Matrix res=A2.scale(A2porB2sobreModuloA2cuadrado).add(B2).substract(A1.scale(A1porB1sobreModuloA1cuadrado).add(B1)).scale(escalarGrande).add(A1.scale(A1porB1sobreModuloA1cuadrado)).add(B1);
		return res;
	}

}
