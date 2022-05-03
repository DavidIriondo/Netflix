package com.everis.d4i.tutorial.controllers.impl.integrates;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.everis.d4i.tutorial.controllers.impl.CategoryControllerImpl;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.services.impl.CategoryServiceImpl;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.google.common.net.HttpHeaders;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CategoryControllerImplTest {
	
	@Autowired
	private MockMvc mvc;

	@Autowired
	private CategoryControllerImpl categoryControllerImpl;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetCategories() {
		try {
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_CATEGORY)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].id").value(1))
			.andExpect(jsonPath("$.data.[1].id").value(2))
			.andExpect(jsonPath("$.data.[2].id").value(3));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCreateCategory() {		
		try {
			Category category = new Category();
			category.setName("THILLER");
			
			String categoryJson = ConstantsUtil.toJSON(category);
			
			//CALLING API
			mvc.perform(
					post(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_CATEGORY)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(categoryJson))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(4))
			.andExpect(jsonPath("$.data.name").value("THILLER"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
