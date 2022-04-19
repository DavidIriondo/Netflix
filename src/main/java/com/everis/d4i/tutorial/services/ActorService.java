package com.everis.d4i.tutorial.services;

import java.util.HashMap;
import java.util.List;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.json.ActorRest;


public interface ActorService{

	//Get list of actors
	List<ActorRest> getListOfActors();
	
	//Get by id
	ActorRest getActorbyId(Long id);
	
	//Post new actor resource
	ActorRest postActor(Actor actor);
	
	//Update actor
	ActorRest updateActor(Long id, Actor actor);
	
	//Delete actor
	ActorRest deleteActor(Long id);
	
	//Show actor´s tv shows and chapters
	HashMap<TvShow, List<Chapter>> actorTvshows(Long id);

}
