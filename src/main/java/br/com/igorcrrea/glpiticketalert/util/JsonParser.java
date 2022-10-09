package br.com.igorcrrea.glpiticketalert.util;

import br.com.igorcrrea.glpiticketalert.model.Configurations;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.model.Tickets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JsonParser {

	public Optional<List<Data>> parseData() throws IOException, InterruptedException {
		Gson gson = (new GsonBuilder()).create();
		Tickets tickets = gson.fromJson(ConnectionAPI.getJson(), Tickets.class);

		if (tickets.getData() == null){
			return Optional.empty();
		}
		return Optional.of(tickets.getData());
	}

	public String creteConfigJson(Configurations infos){
		Gson gson = (new GsonBuilder()).create();
		return gson.toJson(infos, Configurations.class);
	}
	public Configurations readConfig(String json) {
		Gson gson = (new GsonBuilder()).create();
		return gson.fromJson(json, Configurations.class);
	}
}
