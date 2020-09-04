package com.arep.services;

import java.util.HashMap;

import org.bson.Document;

import com.arep.model.Animal;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * 
 * @author SebastianPaez
 *
 */
public class DataBase {
	
	private MongoCollection<org.bson.Document> columnas;
	
	
	/**
	 * Realiza la conexión con la base de datos
	 */
	public DataBase() {
		MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://USER_AREP_LAB3:Prueba123@cluster0arep.d7c1c.azure.mongodb.net/AREP-LAB3?retryWrites=true&w=majority");		
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("AREP-LAB3");		
		columnas = database.getCollection("ANIMALS");
	}
	
 	
	/**
	 * Añade un animal a la base de daots
	 * @param a Es el nuevo animal a añadir
	 */
	public void addAnimal(Animal a) {
		HashMap<String, Object> map = new HashMap<>();
		String animal = a.getanimal();
		String nombre = a.getNombre();
		int edad = a.getEdad();	
		map.put("animal", animal);
		map.put("nombre", nombre);
		map.put("edad", edad);
		Document registro = new Document(map);
        columnas.insertOne(registro);
	}
	
	
	/**
	 * Consulta todos los animales de la base de datos
	 * @return Una lista en formato JSON con los animales
	 */
	public String getAnimals() {
		String data ="[";
		Animal animal;
        for (Document d : columnas.find()) {
        	animal = new Animal(d.get("animal").toString(), d.get("nombre").toString(), Integer.parseInt(d.get("edad").toString()));
        	data += animal.toString() + ", ";
        }
        data = data.substring(0, data.length() - 2);
        data += "]";
        return data;
	}
	

}
