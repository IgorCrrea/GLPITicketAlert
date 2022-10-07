package br.com.igorcrrea.glpiticketalert.model;

import com.google.gson.annotations.SerializedName;

public class DataDTO {

	public DataDTO(Integer id, String title, String data) {
		this.id = id;
		this.title = title;
		this.data = data;
	}

	@SerializedName("2")
	private Integer id;
	@SerializedName("1")
	private String title;
	@SerializedName("19")
	private String data;

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getData() {
		return data;
	}
	
	
	
}
