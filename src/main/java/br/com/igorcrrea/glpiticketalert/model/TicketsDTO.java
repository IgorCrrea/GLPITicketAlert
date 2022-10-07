package br.com.igorcrrea.glpiticketalert.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class TicketsDTO implements Serializable {
	

	@Serial
	private static final long serialVersionUID = -7445894399030122914L;
	
	private List<DataDTO> data;

	public List<DataDTO> getData() {
		return data;
	}

	@SuppressWarnings("unused")
	public void setData(List<DataDTO> data) {
		this.data = data;
	}


}
