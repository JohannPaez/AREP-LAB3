package com.arep;

import static com.arep.services.HttpServer.port;

import com.arep.model.Animal;
import com.arep.services.ServicesHttp;

import static com.arep.services.HttpServer.get;
import static com.arep.services.HttpServer.post;

/**
 * 
 * @author SebastianPaez
 *
 */
public class App {
	
	
	/**
	 * Enciende el servidor para poder utilizar peticiones get y post
	 * @param args Son los parametros recibidos al momento de ejecutar
	 */
	public static void main(String[] args) {
		ServicesHttp servicesHttp = new ServicesHttp();
		port(getPort());
		
		get("/animals", (request, response) -> {
			return servicesHttp.getAnimals();
		});
		
		post("/addanimal", (request, response) -> {
			String body = request.getBody();
			String res = "Para poder a�adir un animal, envielo desde el formulario en la p�gina principal :D";
			System.out.println("REQUEST POST ----------- \n" + body);
			if (body != null) {
				servicesHttp.addAnimal(body);
				res = "El animal con las caracter�sticas \n" + body + "\nHa sido a�adido correctamente"; 
			} 			
			return res;
		});
	}

	/**
	 * Funcion que retorna el n�mero del puerto por el cual se correr� el servicio.
	 * @return El n�mero de puerto del servicio.
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 36000;
	}
}
