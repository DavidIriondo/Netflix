package com.netflix.project.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorChapterRest implements Serializable {

	private static final long serialVersionUID = 180802329782216158L;
	
	private Long tvShowId;
	private String tvShow;
	private Long season;
	private Long chapter;
	
	public ActorChapterRest() {
	}
	
	public ActorChapterRest(Long tvShowId, String tvShow, Long season, Long chapter) {
		this.tvShowId = tvShowId;
		this.tvShow = tvShow;
		this.season = season;
		this.chapter = chapter;
	}
	
	public Long getTvShowId() {
		return tvShowId;
	}
	public void setTvShowId(Long tvShowId) {
		this.tvShowId = tvShowId;
	}
	public String getTvShow() {
		return tvShow;
	}
	public void setTvShow(String tvShow) {
		this.tvShow = tvShow;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public Long getChapter() {
		return chapter;
	}
	public void setChapter(Long chapter) {
		this.chapter = chapter;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
