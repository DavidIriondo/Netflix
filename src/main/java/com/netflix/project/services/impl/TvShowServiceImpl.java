package com.netflix.project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.entities.Award;
import com.netflix.project.entities.Category;
import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.exceptions.NotFoundException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.repositories.AwardRepository;
import com.netflix.project.repositories.CategoryRepository;
import com.netflix.project.repositories.TvShowRepository;
import com.netflix.project.services.TvShowService;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AwardRepository awardRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	@Override
	public TvShowRest updateTvShow(Long id, TvShow newResource) throws NetflixException {
		TvShow tv;
		
		try {
			//Find the resource we want to update
			tv = tvShowRepository.findById(id).get();
			
			//Changing information
			//Name
			if(newResource.getName() != null) {
				tv.setName(newResource.getName());
			}
			
			//Short description
			if(newResource.getShortDescription() != null) {
				tv.setShortDescription(newResource.getShortDescription());
			}
			
			//Long description
			if(newResource.getLongDescription() != null) {
				tv.setLongDescription(newResource.getLongDescription());
			}
			
			//Year
			if(newResource.getYear() != null) {
				tv.setYear(newResource.getYear());
			}
			
			//Category
			if(newResource.getCategory() != null) {
				tv.setCategory(newResource.getCategory());
			}
			
			//Advertising
			if(tv.getAdvertising() != null) {
				tv.setAdvertising(newResource.getAdvertising());
			}
			
			
			//Seasons
			if(tv.getSeasons() != null) {
				tv.setSeasons(newResource.getSeasons());
			}
			
			//Recommended age
			if(tv.getRecommendedAge() != 0) {
				tv.setRecommendedAge(newResource.getRecommendedAge());
			}
			
			
			//Save new tvShow resource 
			tvShowRepository.saveAndFlush(tv);
			
			//Return the updated object
			return modelMapper.map(tv, TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
		
	}

	
	public TvShowRest deleteTvShow(Long id) throws NetflixException{
		TvShow tv;
		
		try {
			//Find tv show
			tv = tvShowRepository.findById(id).get();
			//deleting tv show
			tvShowRepository.delete(tv);
			//save changes
			tvShowRepository.flush();
			//return deleted resource
			return modelMapper.map(tv, TvShowRest.class);
			
		} catch (EntityNotFoundException entityNotFoundException) {
			
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public TvShowRest addCategories(Long id, List<Long> categories_id) throws NetflixException {
		
		try {
			//Find the tvShow
			TvShow tv =  tvShowRepository.findById(id).get();
			
			//For each categories listed, we will add it to tvShow??s categories list
			for (Long categoryId : categories_id) {
				tv.getCategory().add(categoryRepository.findById(categoryId).get());
			}
			
			//Save 
			tvShowRepository.saveAndFlush(tv);
			 
			//Return updated data
			return modelMapper.map(tv, TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public List<AwardRest> tvShowAwards(Long id) throws NetflixException {
		
		//Return a list of awards from a single tvshow
		return tvShowRepository.getOne(id).getAwards().stream()
				.map(award -> modelMapper.map(award, AwardRest.class)).collect(Collectors.toList());
	}
	
	
}
