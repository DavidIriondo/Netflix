package com.netflix.project.controllers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.project.controllers.ChapterController;
import com.netflix.project.entities.Chapter;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.ChapterRest;
import com.netflix.project.responses.NetflixResponse;
import com.netflix.project.services.ChapterService;
import com.netflix.project.services.impl.ChapterServiceImpl;
import com.netflix.project.utils.constants.CommonConstants;
import com.netflix.project.utils.constants.RestConstants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_CHAPTER)
public class ChapterControllerImpl implements ChapterController {

	@Autowired
	private ChapterService chapterService;

	@Autowired
	private ChapterServiceImpl chapterServiceImpl;
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a list of chapters", notes = "Return a list of all the availables chapters on data base. This list is probably to long.")
	public NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChaptersByTvShowIdAndSeasonNumber(tvShowId, seasonNumber));
	} 

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a single chapter", notes = "Return a single chapter. It depends of Tvshow ID, Season ID and Chapter ID")
	public NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(@PathVariable Long tvShowId,
			@PathVariable short seasonNumber, @PathVariable short number) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(tvShowId, seasonNumber, number));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a chapter resource", notes = "Return the updated respurce")
	public NetflixResponse<ChapterRest> updateTvShow(@PathVariable Long id,@RequestBody Chapter chapter) throws NetflixException {
		ChapterRest ch;
		
		//lets update the information
		ch =  chapterServiceImpl.updateChapter(id, chapter);
		
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				ch);
	}
}
