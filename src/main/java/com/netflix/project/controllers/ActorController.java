package com.netflix.project.controllers;

import java.util.List;

import com.netflix.project.entities.Actor;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ActorChapterRest;
import com.netflix.project.json.ActorRest;
import com.netflix.project.projections.ActorChapterProjection;
import com.netflix.project.responses.NetflixResponse;

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
