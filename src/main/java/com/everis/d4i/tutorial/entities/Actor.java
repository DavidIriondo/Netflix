package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ACTORS")
@ApiModel(value = "Car", description = "The model for car")
public class Actor implements Serializable {

	private static final long serialVersionUID = 4916713907852425156L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Column(name = "NAME")
	@ApiModelProperty(value = "name", example = "Lucian")
	private String name;
	
	@Column(name = "SURNAME")
	@ApiModelProperty(value = "name", example = "Smith")
	private String surname;
	
	@Column(name = "AGE")
	@ApiModelProperty(value = "age", example = "22")
	private Integer age;
	
	//N:M relationship with Chapters model
	@ApiModelProperty(value = "chapters", example = "[]")
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "PARTICIPATES",
			joinColumns = @JoinColumn(name = "ACTOR_ID"),
			inverseJoinColumns = @JoinColumn(name = "CHAPTERS_ID"))
	private List<Chapter> chapters;
	
	//Getters and Setters
	
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

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
}
