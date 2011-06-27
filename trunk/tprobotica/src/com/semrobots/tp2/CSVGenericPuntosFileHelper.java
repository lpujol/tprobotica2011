package com.semrobots.tp2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.semrobots.math.Matrix;
import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class CSVGenericPuntosFileHelper {
	
	public static void cargarArchivo(String archivo, List<Matrix> lista, int numDimensiones) throws IOException, ArchivoCSVInvalido {
		List<String> lineas = readAll(archivo);
		
		for (int i = 0; i < lineas.size(); i++){
			cargarPunto(lineas.get(i), lista, i, numDimensiones);
		}
	}
	
	private static void cargarPunto(String lineaPunto,
			List<Matrix> lista, int nrolinea, int numDimensiones) throws ArchivoCSVInvalido {
		validarLinea(lineaPunto, nrolinea, numDimensiones);
		try{
			Matrix punto = armarPuntoDeLinea(lineaPunto);
			lista.add(punto);
		} catch (NumberFormatException e){
			throw new ArchivoCSVInvalido("La linea de datos de punto \"" + lineaPunto + "\" (linea " + nrolinea + ") tiene algun valor no numerico.", e);
		}
	}

	private static Matrix armarPuntoDeLinea(String lineaPunto) {
		String[] datos = lineaPunto.split(",");
		double[] datosMatriz = new double[datos.length];
		for (int i = 0; i < datos.length; i++){
			datosMatriz[i] = new Double(datos[i]);
		}
		Matrix punto = new Matrix(new double[][]{datosMatriz});
		return punto;
	}

	private static void validarLinea(String lineaPunto, int nrolinea, int numDimensiones)
			throws ArchivoCSVInvalido {
		String[] datos = lineaPunto.split(",");
		if (datos.length != numDimensiones){
			throw new ArchivoCSVInvalido("La linea de datos de punto \"" + lineaPunto + "\" (linea " + nrolinea + ").");
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
