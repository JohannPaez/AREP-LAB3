package com.arep;

import static com.arep.HttpServer.port;
import static com.arep.HttpServer.get;

/**
 * 
 * @author SebastianPaez
 *
 */
public class App {
	

	public static void main(String[] args) {
		System.out.println("APP ");
		port(getPort());
		get("/hola", (request) -> {
			return "HOLA JOSELIN";
		});
	}

	/**
	 * Funcion que retorna el n�mero del puerto por el cual se correr� el servicio.
	 * 
	 * @return El n�mero de puerto del servicio.
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 36000;
	}
}
