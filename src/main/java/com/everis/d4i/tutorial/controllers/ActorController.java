package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorChapterRest;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.projections.ActorChapterProjection;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface ActorController {
	//Get all actors
	NetflixResponse<List<ActorRest>>getActors() throws NetflixException;
	
	//Get single actor
	NetflixResponse<ActorRest>getActorById(Long id) throws NetflixException;
	
	//Post new actor
	NetflixResponse<ActorRest>postActor(Actor actor) throws NetflixException;
	
	//Patch actor
	NetflixResponse<ActorRest>updateActor(Long id, Actor actor) throws NetflixException;

	//Delete actor 
	NetflixResponse<ActorRest>deleteActor(Long id) throws NetflixException;
	
	//List of actors´s TV shows
	NetflixResponse<List<ActorChapterProjection>>getActorTvShows(Long id) throws NetflixException;
	
}
