package com.arep;

import static com.arep.server.SocketServer.getStatic;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.arep.server.Request;
import com.arep.server.SocketServer;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	/**
	 * Prueba que se retorne la informacion de los archivos
	 */
	@Test
	public void shouldDoGetFiles() {
		Request req = new Request();
		req.setPath("/files");
		getStatic("/files", (request) -> {
			return "returnfiles";
		});
		String data = SocketServer.getSolicitudesTest().get(req.getPath()).apply(req);
		assertEquals(data, "returnfiles");		
	}
	
	/**
	 * Prueba que se retorne la informacion del html
	 */
	@Test
	public void shouldDoGetHtmls() {
		Request req = new Request();
		req.setPath("/html");
		getStatic("/html", (request) -> {
			return "index.html";
		});
		String data = SocketServer.getSolicitudesTest().get(req.getPath()).apply(req);
		assertEquals(data, "index.html");		
	}
	
	/**
	 * Prueba que se retorne la informacion del css
	 */
	@Test
	public void shouldDoGetCss() {
		Request req = new Request();
		req.setPath("/css");
		getStatic("/css", (request) -> {
			return "style.css";
		});
		String data = SocketServer.getSolicitudesTest().get(req.getPath()).apply(req);
		assertEquals(data, "style.css");		
	}
}
