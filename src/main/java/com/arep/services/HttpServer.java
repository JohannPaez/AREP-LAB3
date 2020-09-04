package com.arep.services;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.arep.server.Request;
import com.arep.server.SocketServer;

public class HttpServer {
	
	private static SocketServer server; 
	
	public static void port(int port) {
		server = SocketServer.getSocketServer(port);
		
	}
		
	public static void get(String path, BiFunction<Request, String, String> f) {
		server.get(path, f);
	}
	
	public static void post(String path, BiFunction<Request, String, String> f) {
		server.post(path, f);
	}
	
	public static void stopServer() {
		server.stopServer();
	}
	
}