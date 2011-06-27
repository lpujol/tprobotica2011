package com.semrobots.tp2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.semrobots.math.Matrix;
import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class CSVCamaraYRotacionFileHelper {
	
	/**
	 * Estructura del archivo:
	 * Xs,Ys,Zs,p
	 * &lt;Xs&gt;,&lt;Ys&gt;,&lt;Zs&gt;,&lt;p&gt;
	 * Matriz de rotacion
	 * &lt;r11&gt;,&lt;r12&gt;,&lt;r13&gt;
	 * &lt;r21&gt;,&lt;r22&gt;,&lt;r23&gt;
	 * &lt;r31&gt;,&lt;r32&gt;,&lt;r33&gt;
	 *         ...
	 * @param archivo
	 * @param parametros 
	 * @throws IOException
	 * @throws ArchivoCSVInvalido 
	 */
	public static void cargarArchivo(String archivo, ParametrosDeCamaraYPuntos parametros) throws IOException, ArchivoCSVInvalido {
		List<String> lineas = readAll(archivo);
		
		if (lineas.size() < 6){
			throw new ArchivoCSVInvalido("Cantidad insuficiente de lineas.");
		}
		
		//descarto la primera linea que es el primer header
		String lineaPosicionDeCamara = lineas.get(1);
		cargarParametrosDePosicionDeCamara(lineaPosicionDeCamara, parametros);
		//descarto la tercera linea que es el segundo header
		String lineaR1 = lineas.get(3);
		String lineaR2 = lineas.get(4);
		String lineaR3 = lineas.get(5);
		cargarMatrizDeRotacion(lineaR1, lineaR2, lineaR3, parametros);
	}
	
	private static void cargarMatrizDeRotacion(String lineaR1, String lineaR2,
			String lineaR3, ParametrosDeCamaraYPuntos params) throws ArchivoCSVInvalido {
		String[] datos1 = lineaR1.split(",");
		if (datos1.length < 3){
			throw new ArchivoCSVInvalido("La linea de datos de rotacion de camara \"" + lineaR1 + "\" tiene menos de 3 datos.");
		}
		String[] datos2 = lineaR2.split(",");
		if (datos2.length < 3){
			throw new ArchivoCSVInvalido("La linea de datos de rotacion de camara \"" + lineaR2 + "\" tiene menos de 3 datos.");
		}
		String[] datos3 = lineaR3.split(",");
		if (datos3.length < 3){
			throw new ArchivoCSVInvalido("La linea de datos de rotacion de camara \"" + lineaR3 + "\" tiene menos de 3 datos.");
		}

		try{
			params.setR(new Matrix(new double[][]{
					{new Double(datos1[0]),new Double(datos2[0]),new Double(datos3[0])},
					{new Double(datos1[1]),new Double(datos2[1]),new Double(datos3[1])},
					{new Double(datos1[2]),new Double(datos2[2]),new Double(datos3[2])},
					}));
		} catch (NumberFormatException e){
			throw new ArchivoCSVInvalido("La linea de datos de rotacion tienen algun valor no numerico.", e);
		}
	}

	private static void cargarParametrosDePosicionDeCamara(
			String lineaPosicionDeCamara, ParametrosDeCamaraYPuntos params) throws ArchivoCSVInvalido {
		//Xs,Ys,Zs,p
		String[] datos = lineaPosicionDeCamara.split(",");
		if (datos.length < 4){
			throw new ArchivoCSVInvalido("La linea de datos de posicion de camara \"" + lineaPosicionDeCamara + "\" tiene menos de 4 datos. No respeta la estructura \"Xs,Ys,Zs,p\"");
		}
		try{
			params.setS(new Matrix(new double[][]{{
				new Double(datos[0]),
				new Double(datos[1]),
				new Double(datos[2])
			}}));
			params.setP(new Double(datos[3]));
		} catch (NumberFormatException e){
			throw new ArchivoCSVInvalido("La linea de datos de posicion de camara \"" + lineaPosicionDeCamara + "\" tiene algun valor no numerico.", e);
		}
	}

	private static List<String> readAll(String archivo) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
		List<String> lineas = new ArrayList<String>();
		
		String linea = reader.readLine();
		while (linea != null){
			lineas.add(linea);
			linea = reader.readLine();
		}
		return lineas;
	}
}
