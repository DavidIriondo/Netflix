package com.everis.d4i.tutorial.controllers.impl;

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

import com.everis.d4i.tutorial.controllers.TvShowController;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.security.repositories.UserRepository;
import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;

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
	public NetflixResponse<List<TvShowRest>> getTvShowsByCategory(@RequestParam Long categoryId)
			throws NetflixException {
		
		System.out.println("PERMISO CONSEDIDO");
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowsByCategory(categoryId));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> getTvShowById(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowService.getTvShowById(id));
	}

	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
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
	public NetflixResponse<TvShowRest> deleteTvShow(@PathVariable Long id) throws NetflixException {
		TvShowRest tv;
		
		tv =  tvShowServiceImpl.deleteTvShow(id);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tv);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_CATEGORY, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<TvShowRest> addCategories(@PathVariable Long id, @RequestBody List<Long> list) throws NetflixException {
		TvShowRest tv;
		
		tv =  tvShowServiceImpl.addCategories(id, list);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tv);
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_AWARD, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<AwardRest>> tvShowAwards(@PathVariable Long id) throws NetflixException {
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				tvShowServiceImpl.tvShowAwards(id));
		
	}

}
