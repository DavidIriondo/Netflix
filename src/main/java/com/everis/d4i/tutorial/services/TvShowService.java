package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowService {

	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	//Method used to change or update the TV show´s name
	TvShowRest updateTvShow(Long id,TvShow newResource) throws NetflixException;
	
	public TvShowRest deleteTvShow(Long id) throws NetflixException;
	
}
