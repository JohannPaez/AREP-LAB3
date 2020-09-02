package com.arep.modelo;

public class Animal {
	private String animal;
	private String nombre;
	private int edad;
	
	
	public Animal (String animal, String nombre, int edad) {
		this.setanimal(animal);
		this.setNombre(nombre);
		this.setEdad(edad);
	}


	public String getanimal() {
		return animal;
	}


	public void setanimal(String animal) {
		this.animal = animal;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String toString() {
		return "{\"animal\":" + " \"" + animal +  " \"" + ", \"nombre\":" + " \"" + nombre +  " \"" + ", \"edad\":" + " \"" + edad +  " \"" + "}";
	}
}
