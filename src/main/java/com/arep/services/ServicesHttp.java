package com.arep.services;

import com.arep.modelo.Animal;

public class ServicesHttp {
	
	private DataBase db;
	
	public ServicesHttp() {
		db = new DataBase();
	}
	
	public void addAnimal(Animal animal) {
		db.addAnimal(animal);
	}
	
	public String getAnimals() {
		return db.getAnimals();
	}	
	
	
}
