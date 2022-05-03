package com.everis.d4i.tutorial.controllers.impl.integrates;

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

import com.everis.d4i.tutorial.constants.ConstantsUtil;
import com.everis.d4i.tutorial.controllers.impl.AwardControllerImpl;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.services.impl.AwardServiceImpl;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.google.common.net.HttpHeaders;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class AwardControllerImplTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AwardControllerImpl awardControllerImpl;
	
	@Autowired
	private AwardServiceImpl awardServiceImpl;
	
	@Autowired
	private AwardRepository awardRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetAwardById() {
		String ACTOR_ID = "/1";
			
		try {
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_AWARD + ACTOR_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(1))
			.andExpect(jsonPath("$.data.name").value("OSCARS"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
