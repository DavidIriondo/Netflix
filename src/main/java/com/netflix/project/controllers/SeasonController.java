package com.netflix.project.controllers;

import java.util.List;

import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.SeasonRest;
import com.netflix.project.responses.NetflixResponse;

public interface SeasonController {

	NetflixResponse<List<SeasonRest>> getSeasonsByTvShow(Long tvShowId) throws NetflixException;

	NetflixResponse<SeasonRest> getSeasonByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException;

}
