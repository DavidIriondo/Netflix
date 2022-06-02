package com.netflix.project.controllers.impl.integrates;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.net.HttpHeaders;
import com.netflix.project.constants.ConstantsUtil;
import com.netflix.project.controllers.impl.ChapterControllerImpl;
import com.netflix.project.entities.Chapter;
import com.netflix.project.repositories.ChapterRepository;
import com.netflix.project.services.impl.ChapterServiceImpl;
import com.netflix.project.utils.constants.RestConstants;


@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ChapterControllerImplTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ChapterControllerImpl chapterControllerImpl;
	
	@Autowired
	private ChapterServiceImpl chapterServiceImpl;
	
	@Autowired
	private ChapterRepository chapterRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetChaptersByTvShowIdAndSeasonNumber() {
		try {
			
			String TV_SHOW_ID = "1";
			String SEASON_ID = "1";
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_CHAPTER,
							TV_SHOW_ID, SEASON_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].id").value(1))
			.andExpect(jsonPath("$.data.[0].name").value("Chapter 1"))
			.andExpect(jsonPath("$.data.[1].id").value(2))
			.andExpect(jsonPath("$.data.[1].name").value("Chapter 2"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetChapterByTvShowIdAndSeasonNumberAndChapterNumber() {
		try {
			
			String TV_SHOW_ID = "1";
			String SEASON_ID = "1";
			String CHAPTER_ID = "1";
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_CHAPTER + RestConstants.RESOURCE_ID,
							TV_SHOW_ID, SEASON_ID, CHAPTER_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(1))
			.andExpect(jsonPath("$.data.name").value("Chapter 1"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdateTvShow() {
		try {
			
			String TV_SHOW_ID = "1";
			String SEASON_ID = "1";
			String CHAPTER_ID = "2";
			
			Chapter chapter = new Chapter();
			chapter.setName("CAPITULO 2");
			
			String chapterJSon = ConstantsUtil.toJSON(chapter);
			
			//CALLING API
			mvc.perform(
					patch(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_CHAPTER + RestConstants.RESOURCE_ID,
							TV_SHOW_ID, SEASON_ID, CHAPTER_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(chapterJSon))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(2))
			.andExpect(jsonPath("$.data.name").value("CAPITULO 2"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
