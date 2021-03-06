package com.netflix.project.json;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netflix.project.entities.TvShow;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AwardRest implements Serializable {

	private static final long serialVersionUID = 180802767613616158L;

	private Long id;
	
	private String name;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
