package com.arep;

import static com.arep.services.HttpServer.port;

import com.arep.modelo.Animal;
import com.arep.services.ServicesHttp;

import static com.arep.services.HttpServer.get;

/**
 * 
 * @author SebastianPaez
 *
 */
public class App {
	
	

	public static void main(String[] args) {
		ServicesHttp servicesHttp = new ServicesHttp();
		port(getPort());
		
		get("/hola", (request) -> {
			return "HOLA JOSELIN";
		});
		get("/hello", (request) -> {
			return servicesHttp.getAnimals();
		});
		
		get("/add", (request) -> {
			Animal animal = new Animal("Pez", "Doroti", 2);
			servicesHttp.addAnimal(animal);
			return "";
		});
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * 
	 * @return El número de puerto del servicio.
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 36000;
	}
}
