package com.arep.model;

/**
 * Clase Animal que mostrara informacion de un animal
 * @author SebastianPaez
 *
 */
public class Animal {
	private String animal;
	private String nombre;
	private int edad;
	
	
	/**
	 * Construye un animal a partir de los parametros dados
	 * @param animal Es el tipo de animal
	 * @param nombre Es el nombre del animal
	 * @param edad Es la edad del animal
	 */
	public Animal (String animal, String nombre, int edad) {
		this.setanimal(animal);
		this.setNombre(nombre);
		this.setEdad(edad);
	}

	
	/**
	 * Retorna el tipo de animal
	 * @return animal 
	 */
	public String getanimal() {
		return animal;
	}

	/**
	 * Cambia el tipo de animal
	 * @param animal Es el nuevo tipo
	 */
	public void setanimal(String animal) {
		this.animal = animal;
	}

	/**
	 * Retorna el nombre del animal
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del animal
	 * @param nombre Es el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * Retorna la edad del animal
	 * @return edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Cambia la edad del animal
	 * @param edad Es la nueva edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * Retorna las caracteristicas del animal en formato JSON
	 */
	public String toString() {
		return "{\"animal\": " + "\"" + animal +  "\"" + ", \"nombre\": " + "\"" + nombre +  "\"" + ", \"edad\": " + edad + "}";
	}
}
