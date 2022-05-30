package br.com.igorcrrea.glpiticketalert.model;

import java.io.Serializable;
import java.util.List;

public class RequestObject implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer totalcount;
	
	private List<Data> data;

	public Integer getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
	
	
}
