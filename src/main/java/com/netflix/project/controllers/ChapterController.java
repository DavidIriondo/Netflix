package com.netflix.project.controllers;

import java.util.List;

import com.netflix.project.entities.Chapter;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ChapterRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.responses.NetflixResponse;

public interface ChapterController {

	NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException;

	NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException;
	
	//Update resource 
	NetflixResponse<ChapterRest> updateTvShow(Long id, Chapter chapter) throws NetflixException;

}
