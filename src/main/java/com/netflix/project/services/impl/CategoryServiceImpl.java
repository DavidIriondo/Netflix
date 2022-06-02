package com.netflix.project.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.entities.Category;
import com.netflix.project.exceptions.InternalServerErrorException;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.CategoryRest;
import com.netflix.project.repositories.CategoryRepository;
import com.netflix.project.services.CategoryService;
import com.netflix.project.utils.constants.ExceptionConstants;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<CategoryRest> getCategories() throws NetflixException {

		return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryRest.class))
				.collect(Collectors.toList());

	}

	public CategoryRest createCategories(final CategoryRest categoryRest) throws NetflixException {
		Category category = new Category();
		category.setName(categoryRest.getName());
		try {
			category = categoryRepository.save(category);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(category, CategoryRest.class);
	}

	
	
}
