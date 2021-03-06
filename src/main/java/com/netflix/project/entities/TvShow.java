package com.netflix.project.entities;

import java.io.Serializable;
import java.time.Year;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "TV_SHOWS")
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden = true)
	private Long id;

	@Column(name = "NAME")
	@ApiModelProperty(value = "name", example = "Los simpson")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	@ApiModelProperty(value = "shortDescription", example = "short description")
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	@ApiModelProperty(value = "longDescription", example = "long description")
	private String longDescription;

	@Column(name = "YEAR")
	@ApiModelProperty(value = "year", example = "1095")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	@ApiModelProperty(value = "recommendedAge", example = "7")
	private byte recommendedAge;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "TV_SHOW_CATEGORIES",
			joinColumns = @JoinColumn(name = "TVSHOW_ID"),
			inverseJoinColumns = @JoinColumn(name = "CATEGORIES_ID"))
	@ApiModelProperty(value = "category", example = "[]")
	private List<Category> category;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShowAward")
	@ApiModelProperty(value = "awards", example = "[]")
	private List<Award> awards;
	

	@Column(name = "ADVERTISING", nullable = true)
	@ApiModelProperty(value = "advertising", example = "advertesing")
	private String advertising;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	@ApiModelProperty(value = "seasons", example = "[]")
	private List<Season> seasons;

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

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}
	

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
	
	

}
