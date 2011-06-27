package com.semrobots.tp2;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.semrobots.math.Matrix;
import com.semrobots.rotmat.GenericRotationMatrix;
import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class MainCalibracion {

	public static void main(String[] args) {
		if (invalidParams(args)){
			params();
			return;
		}
		
		String archivoCamYRot = args[0];
		String archivoPtos = args[1];
		String archivoMediciones = args[2];
		
		try {
			ParametrosDeCamaraYPuntos parametros = new ParametrosDeCamaraYPuntos();
			CSVCamaraYRotacionFileHelper.cargarArchivo(archivoCamYRot, parametros);
			CSVPuntosVistaFileHelper.cargarArchivo(archivoPtos, parametros);
			CSVMedicionesFileHelper.cargarArchivo(archivoMediciones, parametros);
			
			CorrectorDePosicionResolver resolver = new CorrectorDePosicionResolver();
			if (parametros.getPuntosVista().size() < 4){
				throw new ArchivoCSVInvalido("Deben ingresarse por lo menos 4 puntos.");
			}
			if (parametros.getPuntosVista().size() != parametros.getMediciones().size()){
				throw new ArchivoCSVInvalido("Las mediciones difieren de los calculos de puntos de vista.");
			}
			Matrix solucion = resolver.resolverProblema(parametros);
			
			System.out.println(String.format("alfa: %f\nbeta: %f\ngama: %f\ndsx: %f\ndsy: %f\ndsz: %f", toarray(solucion.getArray()[0])));
			Matrix S = parametros.getS().add(new Matrix(new double[][]{{solucion.get(0, 3), solucion.get(0, 4), solucion.get(0, 5)}}));
//			System.out.println(String.format("S corregido: %f, %f, %f", toarray(S.getArray()[0])));
			System.out.print("S corregido: " + S.transpose());
			Matrix R = getRCorregido(parametros, solucion);
			System.out.println("R corregido:\n" + R);
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + archivoCamYRot + " no existe o no tiene permisos.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio un error leyendo el archivo " + archivoCamYRot + ".");
			e.printStackTrace();
		} catch (ArchivoCSVInvalido e) {
			e.printStackTrace();
		}
	}

	private static Matrix getRCorregido(ParametrosDeCamaraYPuntos parametros,
			Matrix solucion) {
		double alfa = solucion.get(0, 0);
		double beta = solucion.get(0, 1);
		double gama = solucion.get(0, 2);
		Matrix axis = new Matrix(new double[][]{{alfa, beta, gama}});
		double ang = Math.sqrt(axis.quadSum());
		axis = axis.scale(1/ang);
		Matrix R = GenericRotationMatrix.rotate(axis, -ang).multiply(parametros.getR());
		return R;
	}
	
	private static Object[] toarray(double[] ds) {
		Object[] ar = new Object[ds.length];
		for (int i = 0; i < ds.length; i++){
			ar[i] = new Double(ds[i]);
		}
		return ar;
	}

	public static boolean invalidParams(String[] args) {
		return args.length != 3;
	}
	
	private static void params() {
		System.out.println("params: <archivo de camara y rotacion> <archivo puntos> <archivo salida punto de vista>");
		System.out.println("El archivo de camara y rotacion tiene los datos de R (matriz de rotacion de la camara) y S (vector posicion de la camara desde un eje de coordenadas universal)");
		System.out.println("El archivo es un csv que debe respetar la estructura:");
		System.out.println("Xs,Ys,Zs,p");
		System.out.println("<Xs>,<Ys>,<Zs>,<p>");
		System.out.println("Matriz de rotacion");
		System.out.println("<r11>,<r12>,<r13>");
		System.out.println("<r21>,<r22>,<r23>");
		System.out.println("<r31>,<r32>,<r33>");
		System.out.println("Xs,Ys,Zs son las coordenadas aproximadas de S, mientras que alfa,beta,gama son los valores del versor que sirve de eje de rotacion a la camara.");
		System.out.println("Ambos archivos de puntos posee la posicion de varios puntos desde el eje de coordenadas universal.");
		System.out.println("Debe respetar la estructura:");
		System.out.println("<X1>,<Y1>");
		System.out.println("<X2>,<Y2>");
		System.out.println("        ...");
		System.out.println("X,Y son las coordenadas del punto de vista.");
	}
}
