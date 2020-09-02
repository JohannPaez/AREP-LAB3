package com.arep.url;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlScanner {
	
	public static void main(String[] args) {
		try {
			URL site = new URL("https://web.microsoftstream.com:443/video/search?q=ref+of+url&&asd=asdasd#redas=asd4r");
			System.out.println("Protocol: " + site.getProtocol());
			System.out.println("Authority: " + site.getAuthority());
			System.out.println("Host: " + site.getHost());
			System.out.println("Port: " + site.getPort());
			System.out.println("Path: " + site.getPath());
			System.out.println("Query: " + site.getQuery());
			System.out.println("File: " + site.getFile());
			System.out.println("Ref: " + site.getRef());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
