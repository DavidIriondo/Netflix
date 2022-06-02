package com.netflix.project.controllers.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.CategoryController;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.CategoryRest;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.services.CategoryService;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CATEGORY)
public class CategoryControllerImpl implements CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list categories", notes = "Return a list of all the available categories")
	public NetflixResponse<List<CategoryRest>> getCategories() throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				categoryService.getCategories());
	}

	@Override 
	@ResponseStatus(HttpStatus.OK) 
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a new category resource", notes = "Return the posted category")
	public NetflixResponse<CategoryRest> createCategory(
			@ApiParam(value = RestConstants.PARAMETER_CATEGORY, required = true) @RequestBody @Valid final CategoryRest categoryRest)
			throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				categoryService.createCategories(categoryRest));
	}

}
