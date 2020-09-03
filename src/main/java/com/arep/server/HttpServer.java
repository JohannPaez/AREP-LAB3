package com.arep.server;

import java.util.function.Function;

public class HttpServer {
	
	private SocketServer server; 
	private int port;
	
	public void port(int port) {
		//this.server = new SocketServer(port);
	}
	
	
	
	
	public static void get(String path, Function<Request, String> f) {
		
	}
	
	
	
}