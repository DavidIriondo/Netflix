package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.services.impl.AwardServiceImpl;

class AwardControllerImplTest {
	
	@Mock
	private AwardServiceImpl awardServiceImpl;
	
	@InjectMocks
	private AwardControllerImpl awardControllerImpl;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAwardById() {
		
		AwardRest aw = new AwardRest();
		aw.setId(1L);
		aw.setName("Oscar");
		
		try {
			
			when(awardServiceImpl.getAwardById(anyLong())).thenReturn(aw);
			
			
			AwardRest awResult = awardControllerImpl.getAwardById(1L).getData();
			
			assertEquals(aw.getId(), awResult.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}

}
