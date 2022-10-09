package br.com.igorcrrea.glpiticketalert.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConnectionAPI {

	private static  String url;
	private static String userToken;
	private static String appToken;
	private static String SESSION_TOKEN;

	private static String OpenSession() {
		setCredentials();

		HttpClient client = HttpClient.newBuilder().build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url + "/apirest.php/initSession?"))
				.headers("App-Token", appToken, "Authorization", "user_token " + userToken).GET().build();

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

	private static void setCredentials() {
		url = LoginUtils.readFile().getUrl();
		userToken = LoginUtils.readFile().getUserToken();
		appToken = LoginUtils.readFile().getAppToken();
		if(url.isBlank()){
			url = "http://localhost/";
		}
	}

	public static String getJson() throws IOException, InterruptedException{
		HttpClient client = HttpClient.newBuilder()
			      .build();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url + "/apirest.php/search/Ticket?is_deleted=0&itemtype=Ticket&sort=158&start=0&criteria[0][field]=12&criteria[0][searchtype]=equals&criteria[0][value]=1&criteria[0][link]=AND"))
	            .headers("Content-Type", "application/json","Session-Token",SESSION_TOKEN,"App-Token", appToken)
	            .GET()
	            .build();
		
		HttpResponse<String> response;
			response = client.send(request, BodyHandlers.ofString());
			return response.body();
		
	}

	public static void Kill() {
		try {
		HttpClient client = HttpClient.newBuilder()
				.build();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url + "/apirest.php/killSession?"))
				.headers("App-Token", appToken,"Session-Token", SESSION_TOKEN)
				.GET()
				.build();

			client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.exit(0);
		}
	}

	public static String updateSession(){
		return SESSION_TOKEN = OpenSession();
	}

}
