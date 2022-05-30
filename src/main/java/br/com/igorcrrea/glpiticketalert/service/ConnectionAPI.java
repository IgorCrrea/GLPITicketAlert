package br.com.igorcrrea.glpiticketalert.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConnectionAPI {

	public static String getJson()throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder()
			      .build();
		
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("http://CAMINHO-GLPI/apirest.php/search/Ticket?is_deleted=0&itemtype=Ticket&sort=158&start=0&criteria[0][field]=12&criteria[0][searchtype]=equals&criteria[0][value]=1&criteria[0][link]=AND"))
	            .headers("Content-Type", "application/json","Session-Token","Sua-Session-Token","App-Token","Sua-App-Token")
	            .GET()
	            .build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		//System.out.println(response.body());
		return response.body();
	}
	
}
