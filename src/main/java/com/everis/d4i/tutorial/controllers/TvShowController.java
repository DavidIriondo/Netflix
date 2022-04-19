package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	//Update resource 
	NetflixResponse<TvShowRest> updateTvShow(Long id, TvShow tvShow) throws NetflixException;
	
	NetflixResponse<TvShowRest> deleteTvShow(Long id) throws NetflixException;
}
