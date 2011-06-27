package com.semrobots.tp2011;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.semrobots.math.Matrix;
import com.semrobots.tp2.ParametrosDeCamaraYPuntos;
import com.semrobots.tp2.exceptions.ArchivoCSVInvalido;

public class MainInterseccion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String archivoEntrada1 = args[0];
			String archivoEntrada2 = args[1];
			ParametrosTp2011 parametros = new ParametrosTp2011();
			ParametrosTp2011 parametros2 = new ParametrosTp2011();
			
			
			CSVTp2011.cargarArchivo(archivoEntrada1, parametros);		
			CSVTp2011.cargarArchivo(archivoEntrada2, parametros2);
			
			TP2011Resolver resolver=new TP2011Resolver();
			Matrix resultado=resolver.resolver(parametros,parametros2);
			
			System.out.println(resultado.get(0, 0)+","+resultado.get(0, 1) +","+resultado.get(0, 2));
			
			FileWriter writer = new FileWriter(new File("salidaInterseccion.csv"));			
				writer.write(lineToString(resultado));
			writer.close();		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	private static String lineToString(Matrix vector) {
		return String.format("%s,%s,%s\n", 
				String.format("%1.16f", vector.get(0, 0)).replaceAll(",", "."),
				String.format("%1.16f", vector.get(0, 1)).replaceAll(",", "."),
				String.format("%1.16f", vector.get(0, 2)).replaceAll(",", "."));
	}

	
}
