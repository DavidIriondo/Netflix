package com.netflix.project.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.ActorController;
import com.netflix.project.entities.Actor;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ActorChapterRest;
import com.netflix.project.json.ActorRest;
import com.netflix.project.projections.ActorChapterProjection;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.services.impl.ActorServiceImpl;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ExampleProperty;


@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController{
 
	@Autowired
	private ActorServiceImpl actorServiceImpl;
	
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return a list of Actors", notes = "Return an Array of Actor type with all his properties")
	public NetflixResponse<List<ActorRest>> getActors() throws NetflixException {
		 
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.getListOfActors());
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return a single actor", notes = "Return a single Actor resource with all his properties")
	public NetflixResponse<ActorRest> getActorById(@PathVariable Long id) throws NetflixException {
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.getActorbyId(id));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ActorRest> postActor(@RequestBody Actor actor) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.postActor(actor));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a single Actor information", notes = "Return the updated Actor resource")
	public NetflixResponse<ActorRest> updateActor(@PathVariable Long id,@RequestBody Actor actor) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.updateActor(id, actor));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a single Actor resource", notes = "Return the deleted Actor resource")
	public NetflixResponse<ActorRest> deleteActor(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.deleteActor(id));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID + RestConstants.RESOURCE_TV_SHOW, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list of episodes and tvshows where an actor has participated ",
	notes = "Return a list of tvshows and each episode of the tvshow where a single Actor has participated")
	public NetflixResponse<List<ActorChapterProjection>> getActorTvShows(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.actorTvshows(id));
	}

}
