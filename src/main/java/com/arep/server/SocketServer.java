package  com.arep.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * @author SebastianPaez
 *
 */
public class SocketServer extends ServerSocket implements Runnable {

	private Thread thread;
	private ReadWriteRequest readerWriter;
	private HashMap<String, BiFunction<Request, String, String>> solicitudes = new HashMap<>();
	private static HashMap<String, Function<Request, String>> solicitudesTest = new HashMap<>();
	private static SocketServer socketServer;

	/**
	 * Crea un nuevo socketServer
	 * 
	 * @param port El puerto por donde correra el aplicativo
	 * @throws IOException En cazo de no estar el puerto disponible
	 */
	public SocketServer(int port) throws IOException {
		super(port);
		System.out.println("Corriendo en puerto " + port);
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Da una instancia del servidor, creandola si no existe
	 * @param port El puerto por el cual el servidor funcionara
	 * @return Una instancia del servidor
	 */
	public static SocketServer getSocketServer(int port) {
		if (socketServer == null)
			try {
				socketServer = new SocketServer(port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Ocurrio un problema al intentar iniciar el servidor \n");
				e.printStackTrace();
			}
		return socketServer;
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
					readerWriter.badResponse();
					continue;
				}
				Request request = new Request(readerString);
				request.setBody(readerWriter.getBody());
				String body = request.getBody();
				if (body != null) request.setHeaders(readerWriter.getHeaders());			
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
					String data = solicitudes.get(path).apply(request, body);
					System.out.println("------------------------------------ DATA -----------------------------------------");
					System.out.println(data);
					System.out.println("------------------------------------ DATA -----------------------------------------");
					readerWriter.write("plain", data);
				}
				else readerWriter.badResponse();
				client.close();
			} catch (Exception e) {
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
	
	/**
	 * Metodo get para realizar solicitudes al servidor
	 * @param path Es el path al cual se atendera
	 * @param f Es la funcion que se ejecutara
	 */
	public void get(String path, BiFunction<Request, String, String> f) {	
		solicitudes.put(path, f);
	}
	
	/*
	 * Metodo get para realizar solicitudes al servidor
	 * @param path Es el path al cual se atendera
	 * @param f Es la funcion que se ejecutara
	 */
	public static void getStatic(String path, Function<Request, String> f) {	
		solicitudesTest = new HashMap<>();
		solicitudesTest.put(path, f);
	}
	
	/**
	 * Retorna una lista de las solicitudes get estaticas para pruebas
	 * @return solicitudesTest
	 */
	public static HashMap<String, Function<Request, String>> getSolicitudesTest() {
		return solicitudesTest;
	}
	
	
	/**
	 * Metodo post para realizar solicitudes al servidor
	 * @param path Es el path al cual se atendera
	 * @param f Es la funcion que se ejecutara
	 */
	public void post(String path, BiFunction<Request, String, String> f) {	
		solicitudes.put(path, f);
	}
	
	/**
	 * Detiene la ejecución del servidor
	 */
	public void stopServer() {
		thread.stop();
		System.out.println("Cerro el servidor!");
	}
}
