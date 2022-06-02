package com.netflix.project.services;

import java.util.List;

import com.netflix.project.entities.Chapter;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ChapterRest;

public interface ChapterService {

	List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber) throws NetflixException;

	ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException;

	//Method used to change or update the TV show´s name
	ChapterRest updateChapter(Long id,Chapter newResource) throws NetflixException;
}
