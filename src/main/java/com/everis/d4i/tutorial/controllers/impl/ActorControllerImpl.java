package com.everis.d4i.tutorial.controllers.impl;

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

import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;



@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController{

	@Autowired
	private ActorServiceImpl actorServiceImpl;
	
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<ActorRest>> getActors() throws NetflixException {
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.getListOfActors());
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
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
	public NetflixResponse<ActorRest> updateActor(@PathVariable Long id,@RequestBody Actor actor) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.updateActor(id, actor));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ActorRest> deleteActor(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorServiceImpl.deleteActor(id));
	}

	@Override
	public NetflixResponse<ActorRest> getActorTvShows(Long id) throws NetflixException {
		// TODO Auto-generated method stub
		return null;
	}

}
