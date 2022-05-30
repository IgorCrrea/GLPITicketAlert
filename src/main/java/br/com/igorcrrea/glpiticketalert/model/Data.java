package br.com.igorcrrea.glpiticketalert.model;

import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("2")
	private Integer id;
	@SerializedName("1")
	private String titulo;
	@SerializedName("19")
	private String data;
	
	@Override
	public String toString() {
		return "ID: "+id+" |Titulo: "+titulo+" |Data: "+data;
	}

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getData() {
		return data;
	}
	
	
	
}
