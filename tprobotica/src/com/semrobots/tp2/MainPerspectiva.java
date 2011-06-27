package com.semrobots.tp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.semrobots.math.Matrix;
import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class MainPerspectiva {

	public static void main(String[] args) {
		if (invalidParams(args)){
			params();
			return;
		}
		
		String archivoCamaraYRotacion = args[0];
		String archivo = args[1];
		String archivosalida = args[2];
		
		try {
			ParametrosDeCamaraYPuntos parametros = new ParametrosDeCamaraYPuntos();
			CSVCamaraYRotacionFileHelper.cargarArchivo(archivoCamaraYRotacion, parametros);
			CSVPuntosFileHelper.cargarArchivo(archivo, parametros);
			
			if (parametros.getPuntos().size() < 4){
				throw new ArchivoCSVInvalido("Deben ingresarse por lo menos 4 puntos.");
			}

			PerspectivaResolver resolver = new PerspectivaResolver();
			List<Matrix> vectores = resolver.calcularPerspectiva(parametros);
			FileWriter writer = new FileWriter(new File(archivosalida));
			
			for (Matrix vector : vectores){
				writer.write(lineToString(vector));
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + archivo + " no existe o no tiene permisos.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio un error leyendo el archivo " + archivo + ".");
			e.printStackTrace();
		} catch (ArchivoCSVInvalido e) {
			e.printStackTrace();
		}
	}

	private static String lineToString(Matrix vector) {
		return String.format("%s,%s,%s\n", 
				String.format("%1.16f", vector.get(0, 0)).replaceAll(",", "."),
				String.format("%1.16f", vector.get(0, 1)).replaceAll(",", "."),
				String.format("%1.16f", vector.get(0, 2)).replaceAll(",", "."));
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
		System.out.println("El archivo de puntos posee la posicion de varios puntos desde el eje de coordenadas universal.");
		System.out.println("Debe respetar la estructura:");
		System.out.println("X,Y,Z,x,y");
		System.out.println("<X1>,<Y1>,<Z1>");
		System.out.println("<X2>,<Y2>,<Z2>");
		System.out.println("        ...");
		System.out.println("X,Y,Z son las coordenadas del punto en el eje de coordenadas universal.");
	}
}
