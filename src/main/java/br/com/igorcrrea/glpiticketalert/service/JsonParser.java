package br.com.igorcrrea.glpiticketalert.service;

import br.com.igorcrrea.glpiticketalert.model.DataDTO;
import br.com.igorcrrea.glpiticketalert.model.TicketsDTO;
import br.com.igorcrrea.glpiticketalert.util.LoginInfosDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonParser {

	public static List<DataDTO> parseData() throws IOException, InterruptedException {

		String json = ConnectionAPI.getJson();
		
		Gson gson = (new GsonBuilder()).create();

		TicketsDTO objects = gson.fromJson(json, TicketsDTO.class);
		
		if (objects.getData() == null) {
			return new ArrayList<>();
		} else {
			return objects.getData();
		}

	}

	public static String creteConfigJson(LoginInfosDTO infos){
		Gson gson = (new GsonBuilder()).create();
		return gson.toJson(infos, LoginInfosDTO.class);
	}
	public static LoginInfosDTO readConfig(String json) {
		Gson gson = (new GsonBuilder()).create();
		return gson.fromJson(json, LoginInfosDTO.class);
	}
}
