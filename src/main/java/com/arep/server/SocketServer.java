package com.arep.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends ServerSocket implements Runnable {

	private Thread thread;
	private ReadWriteRequest readerWriter;

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
				System.out.println("Cliente");
				Socket client = accept();
				System.out.println("Conexion");
				readerWriter = new ReadWriteRequest(client);
				System.out.println("Read Request");
				Request request = new Request(readerWriter.read());
				System.out.println("Construye Request");
				String path = request.getPath();
				System.out.println("Request " + path);
				
				String[] pathData = request.getPath().split("/");
				if (pathData.length > 1) {
					viewImage(pathData);
					viewCss(pathData);
					viewJavaScript(pathData);
				}
				if (path.equals("/index.html") || path.equals("/")) viewHtml();				 
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
		readerWriter.write("html", file);
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

	/**
	 * Muestra el Css correspondiente dado su path
	 * @param pathCss Es el path de la imagen solicitada
	 */
	public void viewCss(String[] pathCss) {
		if (pathCss[1].equals("css")) {
			String file = ReadFiles.readFiles("static/css/" + pathCss[2]);
			readerWriter.write("css", file);
		}
	}
	
	/**
	 * Muestra el JS correspondiente dado su path
	 * @param pathJs Es el path de la imagen solicitada
	 */
	public void viewJavaScript(String[] pathJs) {
		if (pathJs[1].equals("js")) {
			String file = ReadFiles.readFiles("static/js/" + pathJs[2]);
			readerWriter.write("js", file);
		}
	}

	/**
	 * Funcion que retorna el número del puerto por el cual se correrá el servicio.
	 * @return El número de puerto del servicio.
	 */
	static int getPort() {
		 if (System.getenv("PORT") != null) {
			 return Integer.parseInt(System.getenv("PORT"));
		 }
		 	return 80;
	}

	public static void main(String[] args) {
		int port = getPort();
		try {
			System.out.println("Corriendo sobre el puerto 80");
			SocketServer socketServer = new SocketServer(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Algo ha ocurrido, intente nuevamente!");
			e.printStackTrace();
		}
	}

}
