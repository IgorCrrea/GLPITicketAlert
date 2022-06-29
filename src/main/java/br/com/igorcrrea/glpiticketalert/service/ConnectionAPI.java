package br.com.igorcrrea.glpiticketalert.service;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

import br.com.igorcrrea.glpiticketalert.Propriedades;

public class ConnectionAPI {

	private static final Properties PROP = Propriedades.getProp();

	private static final String URL = PROP.getProperty("server.urlAPI");

	private static final String USER_TOKEN = PROP.getProperty("server.userToken");
	private static final String APP_TOKEN = PROP.getProperty("server.appToken");
	private static final String SESSION_TOKEN = OpenSession();

	private static String OpenSession() {

		HttpClient client = HttpClient.newBuilder().build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL + "/apirest.php/initSession?"))
				.headers("App-Token", APP_TOKEN, "Authorization", "user_token " + USER_TOKEN).GET().build();

		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			String corpoResposta = response.body();

			String[] resposta = corpoResposta.split("\":\"");

			return resposta[1].substring(0, resposta[1].length() - 2);

		} catch (IOException | InterruptedException | ArrayIndexOutOfBoundsException e) {
			return "erro";
		}

	}

	
	public static String getJson() throws ConnectException{
		
		HttpClient client = HttpClient.newBuilder()
			      .build();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL+ "/apirest.php/search/Ticket?is_deleted=0&itemtype=Ticket&sort=158&start=0&criteria[0][field]=12&criteria[0][searchtype]=equals&criteria[0][value]=1&criteria[0][link]=AND"))
	            .headers("Content-Type", "application/json","Session-Token",SESSION_TOKEN,"App-Token",APP_TOKEN)
	            .GET()
	            .build();
		
		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			throw new ConnectException();
		}
		
	}

	public static void Kill() {
		HttpClient client = HttpClient.newBuilder()
				.build();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL + "/apirest.php/killSession?"))
				.headers("App-Token", APP_TOKEN,"Session-Token", SESSION_TOKEN)
				.GET()
				.build();
		try {
			client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException();
		}
	}

}
