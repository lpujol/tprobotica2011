package com.semrobots.tp2;

import java.io.IOException;

import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class CSVMedicionesFileHelper {
	
	public static void cargarArchivo(String archivo, ParametrosDeCamaraYPuntos params) throws IOException, ArchivoCSVInvalido {
		CSVGenericPuntosFileHelper.cargarArchivo(archivo, params.getMediciones(), 2);
	}
}
