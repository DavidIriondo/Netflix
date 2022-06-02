package com.netflix.project.controllers.impl.integrates;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.netflix.project.controllers.impl.SeasonControllerImpl;
import com.netflix.project.repositories.SeasonRepository;
import com.netflix.project.services.impl.SeasonServiceImpl;
import com.netflix.project.utils.constants.RestConstants;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class SeasonControllerImplTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private SeasonControllerImpl seasonControllerImpl;
	
	@Autowired
	private SeasonServiceImpl seasonServiceImpl;
	
	@Autowired
	private SeasonRepository seasonRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetSeasonsByTvShow() {
		try {
			
			String TV_SHOW_ID = "3";
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_SEASON,
							TV_SHOW_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].id").value(4))
			.andExpect(jsonPath("$.data.[0].name").value("One"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetSeasonByTvShowIdAndSeasonNumber() {
		try {
			
			String TV_SHOW_ID = "1";
			String SEASON_ID = "1";
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_SEASON + RestConstants.RESOURCE_NUMBER,
							TV_SHOW_ID, SEASON_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(1))
			.andExpect(jsonPath("$.data.name").value("One"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
