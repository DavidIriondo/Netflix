package com.netflix.project.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.TvShowController;
import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.security.repositories.UserRepository;
import com.netflix.project.security.services.Impl.UserServiceImpl;
import com.netflix.project.services.TvShowService;
import com.netflix.project.services.impl.TvShowServiceImpl;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_TV_SHOW)
public class TvShowControllerImpl implements TvShowController {

	@Autowired
	private TvShowService tvShowService;
	
	@Autowired
	private TvShowServiceImpl tvShowServiceImpl; 
	 
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list of tvShows", notes = "Returns all tvshows, it depends of the category(category ID)")
	public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
			throws NetflixException {
		
		System.out.println("PERMISO CONSEDIDO");
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowsByCategory(categoryId));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a single tvshow resource", notes = "Return a single tvshow given by the ID")
	public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowById(id));
	}

	
	@Override 
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update tvShows resource", notes = "Returns the updated tvshow information")
	public NetflixResponse<TvShowRest> updateTvShow(@PathVariable Long id, @RequestBody TvShow tvShow) throws NetflixException {
		TvShowRest tv;
		
		//using the service implementation we update our tvShow resource
		tv = tvShowServiceImpl.updateTvShow(id, tvShow);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tv);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a tvshow resource", notes = "Returns the deleted tvshow resource")
	public NetflixResponse<TvShowRest> deleteTvShow(@PathVariable Long id) throws NetflixException {
		TvShowRest tv;
		
		tv =  tvShowServiceImpl.deleteTvShow(id);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tv);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add a categories to a single tvshow", 
	notes = "Add one or many categories to a single tvShow. Return the updated tvshow resource. You must introduce an array of categories id. For example : [1, 3, 7, 10]")
	public NetflixResponse<TvShowRest> addCategories(@PathVariable Long id, @RequestBody List<Long> categoriesID) throws NetflixException {
		TvShowRest tv;
		
		tv =  tvShowServiceImpl.addCategories(id, categoriesID);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tv);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_AWARD, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list of awards", notes = "Returns a list of awards won by a single tvshow")
	public NetflixResponse<List<AwardRest>> tvShowAwards(@PathVariable Long id) throws NetflixException {
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowServiceImpl.tvShowAwards(id));
		
	}

}
