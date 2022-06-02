package com.netflix.project.services;

import java.util.HashMap;
import java.util.List;

import com.netflix.project.entities.Actor;
import com.netflix.project.entities.Chapter;
import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ActorChapterRest;
import com.netflix.project.json.ActorRest;
import com.netflix.project.projections.ActorChapterProjection;


public interface ActorService{

	//Get list of actors
	List<ActorRest> getListOfActors() throws NetflixException;
	
	//Get by id
	ActorRest getActorbyId(Long id) throws NetflixException;
	
	//Post new actor resource
	ActorRest postActor(Actor actor) throws NetflixException;
	
	//Update actor
	ActorRest updateActor(Long id, Actor actor) throws NetflixException;
	
	//Delete actor
	ActorRest deleteActor(Long id) throws NetflixException; 
	
	//Show actor´s tv shows and chapters
	List<ActorChapterProjection> actorTvshows(Long id) throws NetflixException;

}
