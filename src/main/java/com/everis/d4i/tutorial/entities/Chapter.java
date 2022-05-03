package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private Long id;

	@Column(name = "NUMBER")
	@ApiModelProperty(value = "number", example = "2")
	private short number;

	@Column(name = "NAME")
	@ApiModelProperty(value = "name", example = "cap 2")
	private String name;

	@Column(name = "DURATION")
	@ApiModelProperty(value = "duration", example = "30")
	private short duration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEASON_ID", nullable = false)
	@ApiModelProperty(value = "season", example = "{}")
	private Season season;
	
	//@ManyToMany(mappedBy = "chapters")
	@ManyToMany(mappedBy = "chapters")
	@ApiModelProperty(value = "actors", example = "[]")
	private List<Actor> actors;
	
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getDuration() {
		return duration;
	}

	public void setDuration(short duration) {
		this.duration = duration;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	

}
