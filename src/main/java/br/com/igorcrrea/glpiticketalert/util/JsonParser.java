package br.com.igorcrrea.glpiticketalert.util;

import br.com.igorcrrea.glpiticketalert.model.Configurations;
import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.model.Tickets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

	public List<Data> parseData() throws IOException, InterruptedException {

		String json = ConnectionAPI.getJson();
		
		Gson gson = (new GsonBuilder()).create();

		Tickets objects = gson.fromJson(json, Tickets.class);
		
		if (objects.getData() == null) {
			return new ArrayList<>();
		} else {
			return objects.getData();
		}

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
