package br.com.igorcrrea.glpiticketalert.service;

import br.com.igorcrrea.glpiticketalert.model.DataDTO;
import br.com.igorcrrea.glpiticketalert.model.TicketsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonParser {

	public static List<DataDTO> run() throws IOException, InterruptedException {

		String json = ConnectionAPI.getJson();
		
		Gson gson = (new GsonBuilder()).create();

		TicketsDTO objects = gson.fromJson(json, TicketsDTO.class);
		
		if (objects.getData() == null) {
			return new ArrayList<>();
		} else {
			return objects.getData();
		}

	}

}
