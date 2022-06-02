package com.netflix.project.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netflix.project.entities.Chapter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {

	private static final long serialVersionUID = 180802329613616158L;
	
	private Long id;
	
	private String name;
	
	private String surname;
	
	private Integer age;
	
	//private List<Chapter> chapters;

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	/*
	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}*/
	
	
	
}