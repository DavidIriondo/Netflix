package com.netflix.project.services;

import java.util.List;

import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.TvShowRest;

public interface TvShowService {

	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	//Method used to change or update the TV show´s name
	TvShowRest updateTvShow(Long id,TvShow newResource) throws NetflixException;
	
	TvShowRest deleteTvShow(Long id) throws NetflixException;
	
	TvShowRest addCategories(Long id, List<Long> categories_id) throws NetflixException;

	List<AwardRest> tvShowAwards(Long id) throws NetflixException;

}
