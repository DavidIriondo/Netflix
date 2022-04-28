package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.services.SeasonService;
import com.everis.d4i.tutorial.services.impl.SeasonServiceImpl;

class SeasonControllerImplTest {
	
	@Mock
	private SeasonService seasonService;
	
	@InjectMocks
	private SeasonControllerImpl seasonControllerImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetSeasonsByTvShow() {
		
		List<SeasonRest>  ssList = new ArrayList<>();
		
		SeasonRest ss1 = new SeasonRest();
		SeasonRest ss2 = new SeasonRest();
		SeasonRest ss3 = new SeasonRest();
		SeasonRest ss4 = new SeasonRest();
		
		ss1.setId(1L);
		ss2.setId(2L);
		ss3.setId(3L);
		ss4.setId(4L);
		
		
		ssList.add(ss1);
		ssList.add(ss2);
		ssList.add(ss3);
		ssList.add(ss4);
		
		
		try {
			
			
			when(seasonService.getSeasonsByTvShow(anyLong())).thenReturn(ssList);
			
			List<SeasonRest> ssResult =  seasonControllerImpl.getSeasonsByTvShow(anyLong()).getData();
			
			//VERIFY
			verify(seasonService, atLeast(1)).getSeasonsByTvShow(anyLong());
			
			//SEASSON
			assertNotNull(ssResult);
			for (SeasonRest ssr : ssResult) {
				assertTrue(ssList.contains(ssr));
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testGetSeasonByTvShowIdAndSeasonNumber() {
		
		
		SeasonRest ss = new SeasonRest();
		ss.setId(1L);
		ss.setName("Season 1");
		ss.setNumber((short)1);
		
		
		try {
			
			when(seasonService.getSeasonByTvShowIdAndSeasonNumber(anyLong(), anyShort())).thenReturn(ss);
			
			SeasonRest ssResult =  seasonControllerImpl.getSeasonByTvShowIdAndSeasonNumber(anyLong(), anyShort()).getData();
			
			//VERIFY
			verify(seasonService, atLeast(1)).getSeasonByTvShowIdAndSeasonNumber(anyLong(), anyShort());
			
			//SEASSON
			assertNotNull(ssResult);
			assertTrue(ss.getId() == ssResult.getId());
			assertTrue(ss.getName().equals(ssResult.getName()));
			assertTrue(ss.getNumber() == ssResult.getNumber());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
	}

}
