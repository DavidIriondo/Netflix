package com.netflix.project.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.SeasonController;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.SeasonRest;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.services.SeasonService;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_SEASON)
public class SeasonControllerImpl implements SeasonController {

	@Autowired
	private SeasonService seasonService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list of seasons", notes = "Returns all seasons of a tvshow")
	public NetflixResponse<List<SeasonRest>> getSeasonsByTvShow(@PathVariable Long tvShowId) throws NetflixException {
		System.out.println("ESTOY SQUI");
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				seasonService.getSeasonsByTvShow(tvShowId));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a single season resource", notes = "Returns a season resource, depends of TVshow ID and Season ID")
	public NetflixResponse<SeasonRest> getSeasonByTvShowIdAndSeasonNumber(@PathVariable Long tvShowId,
			@PathVariable short number) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				seasonService.getSeasonByTvShowIdAndSeasonNumber(tvShowId, number));
	}

}
