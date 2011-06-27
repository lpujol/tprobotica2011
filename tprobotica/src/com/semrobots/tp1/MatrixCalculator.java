package com.semrobots.tp1;

import com.semrobots.math.Matrix;
import com.semrobots.tp1.rotmat.RotationMatrix;
import com.semrobots.tp1.rotmat.UVWParametersBiAxisRotationMatrix;

public class MatrixCalculator {
	
	public static Matrix calculateMatrix(double u, double v, double w, String eje, String plano){
		String ordenDeRotacion = getOrdenDeRotacion(eje, plano);
		return buildMatrix(u, v, w, ordenDeRotacion);
	}
	
	public static double calculateRot1(double u, double v, double w, String eje, String plano){
		double a1;
		double a2;
		double r1;
		
		String ordenDeRotacion = getOrdenDeRotacion(eje, plano);
		
		char segundoEjeDeRot = ordenDeRotacion.charAt(0);
		a1 = getCoeficienteDeComponenteDeEje(u, v, w, segundoEjeDeRot);	//proyeccion en el 2do eje de rotacion
		char primerEjeDeRot = ordenDeRotacion.charAt(1);
		a2 = getCoeficienteDeComponenteDeEje(u, v, w, primerEjeDeRot);	//proyeccion en el 1er eje de rotacion
		
		r1 = Math.acos(Math.sqrt(1-a1*a1-a2*a2)/Math.sqrt(1-a1*a1));	//angulo de 2da rotacion
		
		return r1;
	}
	
	public static double calculateRot2(double u, double v, double w, String eje, String plano){
		double a1;
		double r2;
		
		String ordenDeRotacion = getOrdenDeRotacion(eje, plano);
		
		char segundoEjeDeRot = ordenDeRotacion.charAt(0);
		a1 = getCoeficienteDeComponenteDeEje(u, v, w, segundoEjeDeRot);	//proyeccion en el 2do eje de rotacion
		r2 = Math.acos(a1);	//angulo de primera rotacion
		
		return r2;
	}

	private static double getCoeficienteDeComponenteDeEje(
			double u, double v, double w, char eje) {
		switch (eje){
		case 'x':
			return u;
		case 'y':
			return v;
		case 'z':
			return w;
		default:
			throw new AssertionError("Otro eje");	
		}
	}

	public static String getOrdenDeRotacion(String eje, String plano){
		String primeraRotacion;
		String segundaRotacion;
		
		if (eje.equals("x") && plano.equals("xy")){
			primeraRotacion = "z";
			segundaRotacion = "x";
		} else if (eje.equals("x") && plano.equals("xz")){
			primeraRotacion = "y";
			segundaRotacion = "x";
		} else if (eje.equals("y") && plano.equals("xy")){
			primeraRotacion = "z";
			segundaRotacion = "y";
		} else if (eje.equals("y") && plano.equals("yz")){
			primeraRotacion = "x";
			segundaRotacion = "y";
		} else if (eje.equals("z") && plano.equals("yz")){
			primeraRotacion = "x";
			segundaRotacion = "z";
		} else if (eje.equals("z") && plano.equals("xz")){
			primeraRotacion = "y";
			segundaRotacion = "z";
		} else {
			throw new AssertionError("Nunca deberia ocurrir!!");
		}
		return primeraRotacion + segundaRotacion;
	}
	
	private static int axisToInt(char axis){
		if (axis == 'x'){
			return RotationMatrix.X_AXIS;
		}else if (axis == 'y'){
			return RotationMatrix.Y_AXIS;
		}else if (axis == 'z'){
			return RotationMatrix.Z_AXIS;
		}else{
			throw new AssertionError("Nunca deberia ocurrir!!");
		}
	}
	
	private static Matrix buildMatrix(double u, double v, double w,
			String ordenDeRotacion) {
		int axis1 = axisToInt(ordenDeRotacion.charAt(0));
		int axis2 = axisToInt(ordenDeRotacion.charAt(1));
		return UVWParametersBiAxisRotationMatrix.rotate(axis1, axis2, u,v,w);
	}
}
