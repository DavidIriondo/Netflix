package com.netflix.project.services;

import java.util.List;

import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.SeasonRest;

public interface SeasonService {

	List<SeasonRest> getSeasonsByTvShow(Long tvShowId) throws NetflixException;

	SeasonRest getSeasonByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber) throws NetflixException;

}
