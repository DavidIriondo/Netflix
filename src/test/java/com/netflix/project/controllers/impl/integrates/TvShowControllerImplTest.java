package com.netflix.project.controllers.impl.integrates;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Patch;

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
import com.netflix.project.controllers.impl.TvShowControllerImpl;
import com.netflix.project.entities.TvShow;
import com.netflix.project.repositories.TvShowRepository;
import com.netflix.project.services.impl.TvShowServiceImpl;
import com.netflix.project.utils.constants.RestConstants;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class TvShowControllerImplTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TvShowControllerImpl tvShowControllerImpl;
	
	@Autowired
	private TvShowServiceImpl tvShowServiceImpl;
	
	@Autowired
	private TvShowRepository tvShowRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetTvShowsByCategory() {
		
		String categoryKey = "categoryId";
		String categoryValue = "1";
		
		try {
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW)
					.param(categoryKey, categoryValue)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].id").value(1))
			.andExpect(jsonPath("$.data.[1].id").value(2));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetTvShowById() {
		String TV_SHOW_ID = "/3";
		try {
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW + TV_SHOW_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(3))
			.andExpect(jsonPath("$.data.name").value("The simpsons"))
			.andExpect(jsonPath("$.data.recommendedAge").value(12));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdateTvShow() {
		
		try {
			String TV_SHOW_ID = "/2";
			
			TvShow tvShow = new TvShow();
			tvShow.setName("Friends");
			tvShow.setRecommendedAge((byte)6);
			
			String tvshowJson = ConstantsUtil.toJSON(tvShow);
			
			
			//CALLING API
			mvc.perform(
					patch(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW + TV_SHOW_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(tvshowJson))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(2))
			.andExpect(jsonPath("$.data.name").value("Friends"))
			.andExpect(jsonPath("$.data.recommendedAge").value(6));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//FIX
	@Test
	void testDeleteTvShow() {
		/*try {
			String TV_SHOW_ID = "/4";

			
			
			//CALLING API
			mvc.perform(
					delete(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW + TV_SHOW_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE))

			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(4))
			.andExpect(jsonPath("$.data.name").value("Famili guy"))
			.andExpect(jsonPath("$.data.recommendedAge").value(18));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Test
	void testAddCategories() {
		try {
			String TV_SHOW_ID = "/3";
			
			Integer[] categoriesId = {1, 2, 3};
			String categoriesJSon = ConstantsUtil.toJSON(categoriesId);
			
			
			//CALLING API
			mvc.perform(
					post(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW + TV_SHOW_ID + RestConstants.RESOURCE_CATEGORY)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(categoriesJSon))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(3))
			.andExpect(jsonPath("$.data.category[0].name").value("FAMILY"))
			.andExpect(jsonPath("$.data.category[1].name").value("COMEDY"))
			.andExpect(jsonPath("$.data.category[2].name").value("DRAMA"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testTvShowAwards() {
		try {
			String TV_SHOW_ID = "/1";
		
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_TV_SHOW + TV_SHOW_ID + RestConstants.RESOURCE_AWARD)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].id").value(1))
			.andExpect(jsonPath("$.data.[0].name").value("OSCARS"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
