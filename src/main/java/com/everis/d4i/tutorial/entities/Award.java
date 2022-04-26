package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "AWARDS")
public class Award implements Serializable {

	private static final long serialVersionUID = 4916713904971428569L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Column(name = "NAME")
	@ApiModelProperty(value = "name", example = "Oscars")
	private String name;
	
	@Column(name = "DATE")
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TV_SHOW_ID", nullable = false)
	@ApiModelProperty(example = "[]")
	private TvShow tvShowAward;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TvShow getTvShows() {
		return tvShowAward;
	}

	public void setTvShows(TvShow tvShowAward) {
		this.tvShowAward = tvShowAward;
	}

	}
