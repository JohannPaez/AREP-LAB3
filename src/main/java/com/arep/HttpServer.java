package com.arep;

import java.util.function.Function;

import com.arep.server.Request;
import com.arep.server.SocketServer;

public class HttpServer {
	
	private static SocketServer server; 
	
	public static void port(int port) {
		server = SocketServer.getSocketServer(port);
		
	}
		
	public static void get(String path, Function<Request, String> f) {
		server.get(path, f);
	}
	
	
}