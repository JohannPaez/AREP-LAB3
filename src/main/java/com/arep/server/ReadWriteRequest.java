package com.arep.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.imageio.ImageIO;

/**
 * Clase encargada de leer y escribir la solicitud del cliente socket
 * @author SebastianPaez
 *
 */
public class ReadWriteRequest {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Constructor donde se asigna el socket correspondiente
	 * @param socket Es el socket a ser guardado
	 */
	public ReadWriteRequest (Socket socket) {
		//this.setSocket(socket);
		this.socket = socket; 
		try {
			//System.out.println("LLEGO SOCKET");
			out = new PrintWriter(socket.getOutputStream(), true);
			//System.out.println("OUT FINAL ");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//System.out.println("SALIO IN ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error \n" + e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Lee el encabezado enviado al momento de conectarse
	 * @return Un string con la información recibida
	 */
	public String read() {
		try {
			String line;
			String res = "";
			//System.out.println("INLINE " + in.readLine());
			while ((line = in.readLine()).length() != 0) {				
				res = res + line + "\n";
				if (!in.ready()) break;
			}
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al leer la peticion \n" );
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Escribe la información en el socket para mostrar el recurso solicitado
	 * @param extension Es la extencion del recurso solicitado por el cliente
	 * @param data Es la información que se renderizara en el sitio web
	 */
	public void write(String extension, String data) {
		try {
			String encabezado = "HTTP/1.1 200 OK\r\n"
			        + "Content-Type: text/" + extension + "\r\n\r\n";
			out.println(encabezado + data);
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Ocurrio un error al intentar leer el archivo HTML solicitado \n" + e);
			badResponse();
		}
	}
	
	public void badResponse() {		
		
		System.out.println("ENTRO BADRESPONSE");
		try {
			String response = "HTTP/1.0 404 Not Found \r\n" 
					+ "Content-type: text/html" + "\r\n\r\n"
					+ "<h1> 404 File not found <h1>";
			out.println(response);
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("El archivo solicitado no existe. \n" + e);
		}
	}
		
	
	/**
	 * Escribe la información en el socket para mostrar la imagen solicitada
	 * @param img Es la imagen a mostrar
	 */ 
	public void writeImage(String img) {
			
		out.println("HTTP/1.1 200 OK");
		out.println("Content-Type: image/png\r\n");
        try {
        	System.out.println(" PARTE 1");
        	BufferedImage image= ImageIO.read(new File(System.getProperty("user.dir"),"src/main/resources/"+ img));
        	System.out.println(" PARTE 2");
			ImageIO.write(image, "JPG", socket.getOutputStream());
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Ocurrio un error al intentar enviar la imagen. \n" + e);
			badResponse();
		}
	}
	
	/**
	 * Da el socket con el que se esta realizando las peticiones
	 * @return El socket utilizado
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Cambia el socket para que se realicen las peticiones
	 * @param socket Es el nuevo socket para poder trabajar las peticiones
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}	
