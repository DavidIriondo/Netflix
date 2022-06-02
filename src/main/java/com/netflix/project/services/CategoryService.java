package com.netflix.project.services;

import java.util.List;

import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.CategoryRest;

public interface CategoryService {

	List<CategoryRest> getCategories() throws NetflixException;

	CategoryRest createCategories(CategoryRest categoryRest) throws NetflixException;
}
