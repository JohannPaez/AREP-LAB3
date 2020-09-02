package com.arep.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.nio.file.Paths;

/**
 * Clase encargada de leer archivos en el resources
 * @author SebastianPaez
 *
 */
public class ReadFiles {
	
	public static boolean exist = false;
	/**
	 * Devuelve el archivo solicitado
	 * @param path Es donde se encuentra el archivo solicitado
	 * @return Un String con la interpretación de ese archivo
	 */
	public static String readFiles(String path) {	
		exist = false;
		URI uri;
		try {
			uri = ReadFiles.class.getClassLoader().getResource(path).toURI();
			System.out.println("URI " + uri.toString());
			File file = Paths.get(uri).toFile();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String resultHtml = "";
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {				
				resultHtml += line + "\n";
			}
			exist = true;
			return resultHtml;
		} catch (Exception e) {
			System.err.println("Error al buscar el archivo en la ruta solicitada.");
			e.printStackTrace();
			return null;
		}		
	}
	
}
