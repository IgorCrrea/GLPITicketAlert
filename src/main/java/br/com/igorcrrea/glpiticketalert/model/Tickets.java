package br.com.igorcrrea.glpiticketalert.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Tickets implements Serializable {
	

	@Serial
	private static final long serialVersionUID = -7445894399030122914L;
	
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	@SuppressWarnings("unused")
	public void setData(List<Data> data) {
		this.data = data;
	}


}
