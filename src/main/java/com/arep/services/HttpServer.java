package com.arep.services;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.arep.server.Request;
import com.arep.server.SocketServer;

/**
 * 
 * @author SebastianPaez
 *
 */
public class HttpServer {
	
	private static SocketServer server; 
	
	/**
	 * Crea un instancia del servidor en el puerto deseado
	 * @param port Es el puerto dado
	 */
	public static void port(int port) {
		server = SocketServer.getSocketServer(port);
		
	}
		
	/**
	 * Metodo get para realizar solicitudes al servidor
	 * @param path Es el path al cual se atendera
	 * @param f Es la funcion que se ejecutara
	 */
	public static void get(String path, BiFunction<Request, String, String> f) {
		server.get(path, f);
	}
	
	/**
	 * Metodo post para realizar solicitudes al servidor
	 * @param path Es el path al cual se atendera
	 * @param f Es la funcion que se ejecutara
	 */
	public static void post(String path, BiFunction<Request, String, String> f) {
		server.post(path, f);
	}
	
	/**
	 * Detiene la ejecución del servidor
	 */
	public static void stopServer() {
		server.stopServer();
	}
	
}