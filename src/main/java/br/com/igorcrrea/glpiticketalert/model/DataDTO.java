package br.com.igorcrrea.glpiticketalert.model;

import com.google.gson.annotations.SerializedName;

public class DataDTO {

	public DataDTO(Integer id, String title, String data) {
		this.id = id;
		this.title = title;
		this.data = data;
	}

	@SerializedName("2")
	private final Integer id;
	@SerializedName("1")
	private final String title;
	@SerializedName("19")
	private final String data;

	public String getTitle() {
		return title;
	}


}
