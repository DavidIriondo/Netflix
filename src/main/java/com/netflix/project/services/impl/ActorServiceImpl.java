package com.netflix.project.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.entities.Actor;
import com.netflix.project.entities.Chapter;
import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.InternalServerErrorException;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ActorChapterRest;
import com.netflix.project.json.ActorRest;
import com.netflix.project.json.CategoryRest;
import com.netflix.project.projections.ActorChapterProjection;
import com.netflix.project.repositories.ActorRepository;
import com.netflix.project.services.ActorService;
import com.netflix.project.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private ActorRepository actorRepository;


	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<ActorRest> getListOfActors() {
		
		//Return a full list of of actors
		return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public ActorRest getActorbyId(Long id) {
		//Get just one resource
		Actor actor = actorRepository.getOne(id);
		
		return modelMapper.map(actor, ActorRest.class);
	}

	@Override
	public ActorRest postActor(Actor actor) {
		//Ave new actor in database
		actorRepository.saveAndFlush(actor);

		return modelMapper.map(actor, ActorRest.class);

	}

	@Override
	public ActorRest updateActor(Long id, Actor actor) throws NetflixException{
		try {
			Actor ac = actorRepository.getOne(id);
			
			//Files to update
			//Name
			if(actor.getName() != null) {
				ac.setName(actor.getName());
			}
			
			//Surname
			if(actor.getSurname() != null) {
				ac.setSurname(actor.getSurname());
			}
			
			//Age
			if(actor.getAge() != null) {
				ac.setAge(actor.getAge());
			}
			
			//List of chapters
			if(actor.getChapters() != null) {
				ac.setChapters(actor.getChapters());
			}
			
			//Save information
			actorRepository.save(ac);
			
			return modelMapper.map(ac, ActorRest.class);
			
		}catch(Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ActorRest deleteActor(Long id) throws NetflixException{
		
		try {
			//Find the actor
			Actor ac =actorRepository.getOne(id);
			//Delete actor
			actorRepository.delete(ac);
			actorRepository.flush();
			//return
			
			return modelMapper.map(ac, ActorRest.class);
		}catch (Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@Override
	public List<ActorChapterProjection> actorTvshows(Long id) throws NetflixException {
		
		//Return a full list of of actors
		return actorRepository.getActorChapters(id);
			}


}
