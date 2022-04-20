package com.everis.d4i.tutorial.services;

import java.util.HashMap;
import java.util.List;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorChapterRest;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.projections.ActorChapterProjection;


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
