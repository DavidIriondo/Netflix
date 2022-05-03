package com.everis.d4i.tutorial.controllers.impl.integrates;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;

import com.everis.d4i.tutorial.NetflixMain;
import com.everis.d4i.tutorial.constants.ConstantsUtil;
import com.everis.d4i.tutorial.controllers.impl.ActorControllerImpl;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.security.entities.Role;
import com.everis.d4i.tutorial.security.entities.User;
import com.everis.d4i.tutorial.security.repositories.UserRepository;
import com.everis.d4i.tutorial.security.services.UserService;
import com.everis.d4i.tutorial.security.services.Impl.RoleServiceImpl;
import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ActorControllerImplTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private ActorServiceImpl actorServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl userServiceImpl;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	 
	@Before
	public void setUp() throws Exception {
	}

	@Test
	void testGetActors() {

		try {
			
			System.out.println("CANTIDAD DE USUARIOS: "+userRepository.count());
			System.out.println("CANTIDAD DE ACTORES: "+actorRepository.count());
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR)
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
	void testGetActorById() {
		String ACTOR_ID = "/1";
		
		try {
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR + ACTOR_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(1))
			.andExpect(jsonPath("$.data.name").value("Robert"))
			.andExpect(jsonPath("$.data.surname").value("Downey Jr."))
			.andExpect(jsonPath("$.data.age").value(40));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testPostActor() {
		
		ActorRest actor = new ActorRest();
		actor.setName("Sofia");
		actor.setSurname("Vergara");
		actor.setAge(50);
		
		try {
			String actorJson = ConstantsUtil.toJSON(actor);
			
			//CALLING API
			mvc.perform(
					post(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.content(actorJson)
					.contentType(MediaType.APPLICATION_JSON))
			
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(5))
			.andExpect(jsonPath("$.data.name").value("Sofia"))
			.andExpect(jsonPath("$.data.surname").value("Vergara"))
			.andExpect(jsonPath("$.data.age").value(50));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdateActor() {;
		
		try {
			String ACTOR_ID = "/3";
			
			Actor actor = new Actor();
			actor.setName("Michael");
			actor.setSurname("Jackson");
			
			String actorJson = ConstantsUtil.toJSON(actor);
			
			//CALLING API
			mvc.perform(
					patch(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR + ACTOR_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(actorJson))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(3))
			.andExpect(jsonPath("$.data.name").value("Michael"))
			.andExpect(jsonPath("$.data.surname").value("Jackson"))
			.andExpect(jsonPath("$.data.age").value(45));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	//TO FIX
	@Test
	void testDeleteActor() {
		String ACTOR_ID = "/2";
		
		try {
			/*
			//CALLING API
			mvc.perform(
					delete(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR + ACTOR_ID)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.ADMIN_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.id").value(2))
			.andExpect(jsonPath("$.data.name").value("Scarlet"))
			.andExpect(jsonPath("$.data.surname").value("Johansson"))
			.andExpect(jsonPath("$.data.age").value(35));
			
			//Try to find the deleted user
		    ActorRest deletedUser = actorServiceImpl.getActorbyId(2L);
		    assertNull(deletedUser);*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetActorTvShows() {
		String ACTOR_ID = "/2";
		
		try {
			
			//CALLING API
			mvc.perform(
					get(RestConstants.APPLICATION_NAME+RestConstants.API_VERSION_1+RestConstants.RESOURCE_ACTOR + ACTOR_ID + RestConstants.RESOURCE_TV_SHOW)
					.header(HttpHeaders.AUTHORIZATION,ConstantsUtil.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.data.[0].tvShow").value("Game of thrones"))
			.andExpect(jsonPath("$.data.[0].season").value(1))
			.andExpect(jsonPath("$.data.[0].chapter").value(1))
			.andExpect(jsonPath("$.data.[1].tvShow").value("Breaking bad"))
			.andExpect(jsonPath("$.data.[1].season").value(3))
			.andExpect(jsonPath("$.data.[1].chapter").value(5));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


