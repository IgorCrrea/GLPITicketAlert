package br.com.igorcrrea.glpiticketalert.service;

import br.com.igorcrrea.glpiticketalert.Properties;
import br.com.igorcrrea.glpiticketalert.util.LoginUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConnectionAPI {


	private static final java.util.Properties PROP = Properties.getProp();

	private static  String URL ;
	private static String USER_TOKEN;
	private static String APP_TOKEN;
	private static String SESSION_TOKEN = OpenSession();

	private static String OpenSession() {

		if (LoginUtils.readFile().getUrl() == null || LoginUtils.readFile().getUrl().length() < 1){
			URL = "http://localhost/";
		}else {URL = LoginUtils.readFile().getUrl();}

		if (LoginUtils.readFile().getUserToken() == null || LoginUtils.readFile().getUserToken().length() < 1){
			USER_TOKEN = "NoToken";
		}else {USER_TOKEN = LoginUtils.readFile().getUserToken();}

		if (LoginUtils.readFile().getAppToken() == null || LoginUtils.readFile().getAppToken().length() < 1){
			APP_TOKEN = "NoToken";
		}else {APP_TOKEN = LoginUtils.readFile().getAppToken();}

		HttpClient client = HttpClient.newBuilder().build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL + "/apirest.php/initSession?"))
				.headers("App-Token", APP_TOKEN, "Authorization", "user_token " + USER_TOKEN).GET().build();

		HttpResponse<String> response;
		try {
			response = client.send(request, BodyHandlers.ofString());
			String responseBody = response.body();

			String[] parsedResponse = responseBody.split("\":\"");

			return parsedResponse[1].substring(0, parsedResponse[1].length() - 2);

		} catch (IOException | InterruptedException | ArrayIndexOutOfBoundsException e) {
			return "error";
		}

	}

	
	public static String getJson() throws IOException, InterruptedException{
		
		HttpClient client = HttpClient.newBuilder()
			      .build();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL+ "/apirest.php/search/Ticket?is_deleted=0&itemtype=Ticket&sort=158&start=0&criteria[0][field]=12&criteria[0][searchtype]=equals&criteria[0][value]=1&criteria[0][link]=AND"))
	            .headers("Content-Type", "application/json","Session-Token",SESSION_TOKEN,"App-Token",APP_TOKEN)
	            .GET()
	            .build();
		
		HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			return response.body();
		
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
			System.exit(0);
		}
	}

}
