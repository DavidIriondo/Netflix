package com.netflix.project.controllers.impl;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.AwardController;
import com.netflix.project.entities.Award;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.exceptions.NotFoundException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.services.impl.AwardServiceImpl;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_AWARD)
public class AwardControllerImpl implements AwardController{
	
	@Autowired
	private AwardServiceImpl awardServiceImpl;

	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Override
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return a single Award resource", notes = "Return the Award requested by id")
	public NetflixResponse<AwardRest> getAwardById(@PathVariable Long id) throws NetflixException {

		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				awardServiceImpl.getAwardById(id));
	}

}
