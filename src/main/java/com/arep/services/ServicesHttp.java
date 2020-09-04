package com.arep.services;

import java.util.HashMap;

import com.arep.model.Animal;

/**
 * Clase encargada de ofrecer los servicios http 
 * @author SebastianPaez
 *
 */
public class ServicesHttp {
	
	private DataBase db;
	
	/**
	 * Crea una nueva instancia de la base de datos
	 */
	public ServicesHttp() {
		db = new DataBase();
	}
	
	/**
	 * Da una lista de los animales de la base de datos en formato JSON
	 * @return La lista de animales
	 */
	public String getAnimals() {
		return db.getAnimals();
	}
	
	/**
	 * Añade un animal dado su cadena en formato JSON
	 * @param json Es la cadena
	 */
	public void addAnimal(String json) {
		Animal animal = convertJsonToAnimal(json);
		db.addAnimal(animal);
	}
	
	/**
	 * Retorna un animal dadas sus caracteristicas en formato json
	 * @param json Es el json a trata
	 * @return Un animal con las caracteristicas dadas
	 */
	private Animal convertJsonToAnimal(String json) {
		System.out.println("JSON \n" + json + "\n");
		String sinComillas = json.replace("\"", "");
		sinComillas = sinComillas.substring(1, sinComillas.length() - 1);
		String[] jsonAnimal = sinComillas.split(",");
		HashMap<String, String> valores = new HashMap<>();
		for (String value: jsonAnimal) {
			String[] dic = value.split(":");
			valores.put(dic[0].trim(), dic[1].trim());
		}
		Animal animal = new Animal(valores.get("animal"), valores.get("nombre"), Integer.parseInt(valores.get("edad")));
		return animal;
	}
	
	
}
