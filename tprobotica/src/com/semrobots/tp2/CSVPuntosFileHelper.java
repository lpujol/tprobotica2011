package com.semrobots.tp2;

import java.io.IOException;

import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class CSVPuntosFileHelper {
	
	public static void cargarArchivo(String archivo, ParametrosDeCamaraYPuntos parametros) throws IOException, ArchivoCSVInvalido {
		CSVGenericPuntosFileHelper.cargarArchivo(archivo, parametros.getPuntos(), 3);
	}
}
