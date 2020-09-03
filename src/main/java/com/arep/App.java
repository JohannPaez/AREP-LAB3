package com.arep;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.arep.server.SocketServer;

/**
 * 
 * @author SebastianPaez
 *
 */
public class App {



	public static void main(String[] args) {
		

		/*socketServer.get("/hola", (request) -> {					
			return "HOLA MUNDO";
		});*/
	}
	
	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 System.out.println("PUERTO --------- " + System.getenv("PORT"));
			 return Integer.parseInt(System.getenv("PORT"));
		 }
		 	return 80;
	}
}
