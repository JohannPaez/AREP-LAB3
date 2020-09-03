package com.arep.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.function.Function;

public class SocketServer extends ServerSocket implements Runnable {

	private Thread thread;
	private ReadWriteRequest readerWriter;
	private HashMap<String, Function<Request, String>> solicitudes = new HashMap<>();

	/**
	 * Crea un nuevo socketServer
	 * 
	 * @param port El puerto por donde correra el aplicativo
	 * @throws IOException En cazo de no estar el puerto disponible
	 */
	public SocketServer(int port) throws IOException {
		super(port);
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Método que se ejecuta en paralelo para poder utilizar las demás peticiones de
	 * la web
	 */
	public void run() {
		while (true) {
			try {
				System.out.println("\nCliente");
				Socket client = accept();
				System.out.println("Conexion");
				readerWriter = new ReadWriteRequest(client);
				System.out.println("Read Request");
				String readerString = readerWriter.read();
				if (readerString.equals("")) {
					System.out.println("------------------------- readerString NULO ---------------------------------" );
					readerWriter.badResponse();
					continue;
				}
				Request request = new Request(readerString);
				System.out.println("Construye Request");
				String path = request.getPath();
				System.out.println("Request " + path);
				
				String[] pathData = request.getPath().split("/");
				if (pathData.length > 1) {
					viewImage(pathData);
					viewCss(pathData);
					viewJavaScript(pathData);
				}
				if (path.equals("/index.html") || path.equals("/")) {
					viewHtml();				 
				} else if (solicitudes.get(path) != null) {
					String data = solicitudes.get(path).apply(request);
					readerWriter.write("plain", data);
				}
				else readerWriter.badResponse();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Ocurrio un problema al intentar realizar esas peticiones.");
				e.printStackTrace();
			}

		}
	}
	
	
	/**
	 * Muestra el archivo html correspondiente
	 */
	public void viewHtml() {
		String pathIndex = "/index.html";
		String file = ReadFiles.readFiles("static" + pathIndex);
		if (ReadFiles.exist) readerWriter.write("html", file);
	}

	/**
	 * Muestra el Css correspondiente dado su path
	 * @param pathCss Es el path de la imagen solicitada
	 */
	public void viewCss(String[] pathCss) {
		if (pathCss[1].equals("css")) {
			String file = ReadFiles.readFiles("static/css/" + pathCss[2]);
			if (ReadFiles.exist) readerWriter.write("css", file);
		}
	}
	
	/**
	 * Muestra el JS correspondiente dado su path
	 * @param pathJs Es el path de la imagen solicitada
	 */
	public void viewJavaScript(String[] pathJs) {
		if (pathJs[1].equals("js")) {
			String file = ReadFiles.readFiles("static/js/" + pathJs[2]);
			if (ReadFiles.exist) readerWriter.write("js", file);
		}
	}
	
	/**
	 * Muestra la imagen correspondiente dado su path
	 * @param pathImg Es el path de la imagen solicitada
	 */
	public void viewImage(String[] pathImg) {
		if (pathImg[1].equals("img")) {
			readerWriter.writeImage("static/img/" + pathImg[2]);
		}
	}
	
	public void get(String path, Function<Request, String> f) {	
		solicitudes.put(path, f);
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 System.out.println("PUERTO --------- " + System.getenv("PORT"));
			 return Integer.parseInt(System.getenv("PORT"));
		 }
		 	return 36000;
	}

	public static void main(String[] args) {
		
		try {
			System.out.println("Corriendo sobre el puerto 80");
			SocketServer socketServer = new SocketServer(getPort());
			socketServer.get("/hola", (request) -> {
				
				
				return "HOLA MUNDO";
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Algo ha ocurrido, intente nuevamente!");
			e.printStackTrace();
		}
	}

}
