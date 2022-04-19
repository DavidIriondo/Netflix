package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException {
		return chapterRepository.findBySeasonTvShowIdAndSeasonNumber(tvShowId, seasonNumber).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRest.class)).collect(Collectors.toList());
	}

	@Override
	public ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException {
		Chapter chapter = chapterRepository
				.findBySeasonTvShowIdAndSeasonNumberAndNumber(tvShowId, seasonNumber, chapterNumber)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CHAPTER));
		return modelMapper.map(chapter, ChapterRest.class);
	}

	@Override
	public ChapterRest updateChapter(Long id, Chapter newResource) throws NetflixException {
		Chapter ch;
		
		try {
			//Find the resource we want to update
			ch = chapterRepository.findById(id).get();
			
			//Changing information
			//Name
			if(newResource.getName() != null) {
				ch.setName(newResource.getName());
			}
			
			//TODO : Solve the update data with primitive data types
			//Number
			/*if(newResource.getNumber() != 0) {
				ch.setName(newResource.getName());
			}
			
			if(newResource.getDuration() != null) {
				ch.setName(newResource.getName());
			}*/

			//Season
			if(newResource.getSeason() != null) {
				ch.setSeason(newResource.getSeason());
			}
			
			
			
			//Save new tvShow resource 
			chapterRepository.saveAndFlush(ch);
			
			//Return the updated object
			return modelMapper.map(ch, ChapterRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

}
