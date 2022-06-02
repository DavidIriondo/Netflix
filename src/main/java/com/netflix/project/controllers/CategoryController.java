package com.netflix.project.controllers;

import java.util.List;

import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.CategoryRest;
import com.netflix.project.responses.NetflixResponse;

public interface CategoryController {

	NetflixResponse<List<CategoryRest>> getCategories() throws NetflixException;

	NetflixResponse<CategoryRest> createCategory(CategoryRest categoryRest) throws NetflixException;

}
