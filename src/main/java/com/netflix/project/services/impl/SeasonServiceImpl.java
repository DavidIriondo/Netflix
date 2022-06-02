package com.netflix.project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.entities.Season;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.exceptions.NotFoundException;
import com.netflix.project.json.SeasonRest;
import com.netflix.project.repositories.SeasonRepository;
import com.netflix.project.services.SeasonService;
import com.netflix.project.utils.constants.ExceptionConstants;

@Service
public class SeasonServiceImpl implements SeasonService {

	@Autowired
	private SeasonRepository seasonRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<SeasonRest> getSeasonsByTvShow(Long tvShowId) throws NetflixException {
		return seasonRepository.findByTvShowId(tvShowId).stream()
				.map(season -> modelMapper.map(season, SeasonRest.class)).collect(Collectors.toList());
	}

	@Override
	public SeasonRest getSeasonByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber) throws NetflixException {
		Season season = seasonRepository.findByTvShowIdAndNumber(tvShowId, seasonNumber)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_SEASON));
		return modelMapper.map(season, SeasonRest.class);
	}

}
