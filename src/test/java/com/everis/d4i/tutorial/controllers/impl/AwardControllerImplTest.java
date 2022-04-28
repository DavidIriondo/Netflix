package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
		aw.setDate(new Date(2020, 2, 20));
		
		try {
			
			when(awardServiceImpl.getAwardById(anyLong())).thenReturn(aw);
			
			
			AwardRest awResult = awardControllerImpl.getAwardById(1L).getData();
			
			//VERIFY
			verify(awardServiceImpl, atLeast(1)).getAwardById(anyLong());
			
			//ASSERT
			assertNotNull(awResult);
			assertEquals(aw.getId(), awResult.getId());
			assertEquals(aw.getName(), awResult.getName());
			assertEquals(aw.getDate(), awResult.getDate());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

}
