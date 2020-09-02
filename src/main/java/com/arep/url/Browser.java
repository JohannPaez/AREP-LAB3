package com.arep.url;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * 
 * @author SebastianPaez
 *
 */
public class Browser {
	
	public static void main(String[] args) {
		
		System.out.println("Ingrese una URL válida para escribir su contenido en resultado.html");
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		String url = scanner.next();
		try {
			
			URL site = new URL(url);
			try {
				FileWriter resultHtml = new FileWriter("src/main/resources/static/resultado.html");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(site.openStream()));
				String line = null;
				while (bufferedReader.readLine() != null) {
					line = bufferedReader.readLine();
					resultHtml.write(line);
				}
				resultHtml.close();
				flag = true;
			} catch (IOException e) {
				System.err.println("Ha ocurrido un problema con su solicitud: " + e.getMessage());
			}
			
			
		} catch (MalformedURLException e) {
			System.err.println("La URL ingresada no es valida, intente nuevamente.");
		}
		
		if (flag) {
			System.out.println("Su solicitud fue procesada con exito, revise el archivo src/main/resources/static/resultado.html");			
		}
		System.out.println("\n-------Encabezados------");
		UrlReader.readURL(url);
	}
}
