package com.netflix.project.controllers;

import java.util.List;

import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	NetflixResponse<TvShowRest> updateTvShow(Long id, TvShow tvShow) throws NetflixException;
	
	NetflixResponse<TvShowRest> deleteTvShow(Long id) throws NetflixException;
	
	NetflixResponse<TvShowRest> addCategories(Long id, List<Long>list) throws NetflixException;
	
	NetflixResponse<List<AwardRest>> tvShowAwards(Long id) throws NetflixException;
}
