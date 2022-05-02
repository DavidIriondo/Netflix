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
import com.everis.d4i.tutorial.constants.ConstantsTest;
import com.everis.d4i.tutorial.controllers.impl.ActorControllerImpl;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.security.entities.Role;
import com.everis.d4i.tutorial.security.entities.User;
import com.everis.d4i.tutorial.security.repositories.UserRepository;
import com.everis.d4i.tutorial.security.services.UserService;
import com.everis.d4i.tutorial.security.services.Impl.RoleServiceImpl;
import com.everis.d4i.tutorial.security.services.Impl.UserServiceImpl;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
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
					.header(HttpHeaders.AUTHORIZATION,ConstantsTest.USER_ROLE))
			//TESTING RESPONSE
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$data.[0].id").value(1))
			.andExpect(jsonPath("$data.[1].id").value(2))
			.andExpect(jsonPath("$data.[2].id").value(3))
			.andExpect(jsonPath("$data.[3].id").value(4));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetActorById() {
		
	}

	@Test
	void testPostActor() {
		
	}

	@Test
	void testUpdateActor() {
		
	}

	@Test
	void testDeleteActor() {
		
	}

	@Test
	void testGetActorTvShows() {
		
	}
	
	
}


