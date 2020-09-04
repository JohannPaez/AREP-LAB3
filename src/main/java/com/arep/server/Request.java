package com.arep.server;

import java.util.HashMap;
import java.util.Map;

public class Request {
	
	private String tipoPeticion;
	private String path;
	private String body;
	private HashMap<String, String> headers;
	
	// Diccionario llave valor para la query
	private Map<String, String> parametros;
	
	
	/**
	 * Lee la request y toma los valores importantes, el path, query y tipo de peticion
	 * @param request Es la solicitud recibida
	 */
	public Request(String request) {	
		parametros = new HashMap<>();
		String[] line = request.split("\n")[0].split(" ");	
		tipoPeticion = line[0]; 
		String[] pathQuery = line[1].replace("?", " ").split(" ");
		path =  pathQuery[0];
		//System.out.println("PATH " + path);
		//System.out.println("Tipo peticion " + tipoPeticion);		
		if (pathQuery.length > 1) {
			llenarParametros(pathQuery[1]);
		}
		
	}
	
	public Request() {
		
	}
	
	/**
	 * Llena los parametros en caso de que se hayan enviado por peticion
	 * @param query Es el string con los parametros enviados
	 */
	private void llenarParametros(String query) {
		String[] line = query.split("&&");
		
		System.out.println();
		for (String s: line) {
			String[] dic = s.split("="); 
			String llave = dic[0];
			String valor = dic[1];
			parametros.put(llave, valor);
		}
		
	}

	
	/**
	 * Retorna el tipo de peticion 
	 * @return Tipo de peticion realizada
	 */
	public String getTipoPeticion() {
		return tipoPeticion;
	}

	/**
	 * Da el path de la solicitud
	 * @return El path de toda la solicitud
	 */
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}


	
	/**
	 * Da los parametros de la solicitud en formato llave-valor
	 * @return Los parametros de la solicitud
	 */
	public Map<String, String> getParametros() {
		return parametros;
	}

	/**
	 * Retorna el contenido de la solicitud
	 * @return body Que es el contenido de la solicitud
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Cambia el contenido de la solicitud
	 * @param body Es el nuevo contenido a cambiar
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Retorna los encabezados de la solicitud
	 * @return headers Que son los encabezados de la solicitud
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	/**
	 * Cambia los encabezados de la solicitud
	 * @param headers Son los encabezados de la solicitud
	 */
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
}

