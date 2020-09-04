package com.arep.services;

import java.util.HashMap;

import com.arep.model.Animal;

public class ServicesHttp {
	
	private DataBase db;
	
	public ServicesHttp() {
		db = new DataBase();
	}
	
	public String getAnimals() {
		return db.getAnimals();
	}
	
	public void addAnimal(String json) {
		Animal animal = convertJsonToAnimal(json);
		db.addAnimal(animal);
	}
	
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
