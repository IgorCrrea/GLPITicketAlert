package br.com.igorcrrea.glpiticketalert.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.igorcrrea.glpiticketalert.model.Data;
import br.com.igorcrrea.glpiticketalert.model.Tickets;

public class JsonParser {

	public static List<Data> run() throws IOException, InterruptedException {
		String json = ConnectionAPI.getJson();

		Gson gson = (new GsonBuilder()).create();

		Tickets objetos = gson.fromJson(json, Tickets.class);

		if(objetos.getData()==null) {
			return new ArrayList<Data>();
		}else {
			return objetos.getData();
		}

	}

}
