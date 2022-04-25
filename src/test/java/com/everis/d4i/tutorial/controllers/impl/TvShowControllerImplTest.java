package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.TvShowServiceImpl;

@ExtendWith(MockitoExtension.class)
class TvShowControllerImplTest {
		
	@Mock
	private TvShowServiceImpl tvShowServiceImpl;
	
	@InjectMocks
	private TvShowControllerImpl tvShowControllerImpl; 

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testUpdateTvShow() {
		//Create the test resource
		TvShow tv = new TvShow();
		tv.setId(2L);
		tv.setName("Los simpson");
		//Resource fined
		TvShowRest tvUpdated = new TvShowRest();
		tv.setId(1L);
		tv.setName("Friends");
		
		//Create the test return resource
		NetflixResponse<TvShowRest>tvReturned = new NetflixResponse<TvShowRest>();
		TvShowRest showRest = new TvShowRest();
		showRest.setName("Los simpson");
		tvReturned.setData(showRest);
		
		//Update tv show
		try {
			when(tvShowServiceImpl.updateTvShow(1L, tv))
			.thenReturn(tvUpdated);
			
			TvShowRest tvResult = tvShowControllerImpl.updateTvShow(1L, tv).getData();
			
			//
			assertEquals(tvUpdated.getName(), tvResult.getName());
			
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testDeleteTvShow() {
		fail("Not yet implemented");
	}

	@Test
	void testAddCategories() {
		fail("Not yet implemented");
	}

}
