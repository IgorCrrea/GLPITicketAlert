package br.com.igorcrrea.glpiticketalert.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import br.com.igorcrrea.glpiticketalert.Propriedades;

public class ConnectionAPI {
	
	private static final Properties PROP = Propriedades.getProp();
	
	private static final String URL= PROP.getProperty("server.urlAPI")+"/apirest.php/search/Ticket?is_deleted=0&itemtype=Ticket&sort=158&start=0&criteria[0][field]=12&criteria[0][searchtype]=equals&criteria[0][value]=1&criteria[0][link]=AND";
	private static final String SESSION_TOKEN = PROP.getProperty("server.sessionToken");
	private static final String APP_TOKEN = PROP.getProperty("server.appToken");
	
	public static String getJson()throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder()
			      .build();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL))
	            .headers("Content-Type", "application/json","Session-Token",SESSION_TOKEN,"App-Token",APP_TOKEN)
	            .GET()
	            .build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.body();
	}
	
}
