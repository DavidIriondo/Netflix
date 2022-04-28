package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.services.impl.ChapterServiceImpl;

class ChapterControllerImplTest {
	
	@Mock
	private ChapterServiceImpl chapterServiceImpl;
	
	@Mock
	private ChapterService chapterService;

	@InjectMocks
	private ChapterControllerImpl chapterControllerImpl;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	} 

	@Test
	void testGetChaptersByTvShowIdAndSeasonNumber() {
		
		List<ChapterRest> chList = new ArrayList<>();
		
		ChapterRest ch1 = new ChapterRest();
		ch1.setId(1L);
		ch1.setName("Cap 1");
		
		ChapterRest ch2 = new ChapterRest();
		ch2.setId(2L);
		ch2.setName("Cap 2");
		
		ChapterRest ch3 = new ChapterRest();
		ch3.setName("Cap 3");
		ch3.setId(3L);
		
		chList.add(ch1);
		chList.add(ch2);
		chList.add(ch3);
		
		try {
			
			when(chapterService.getChaptersByTvShowIdAndSeasonNumber(anyLong(), anyShort())).thenReturn(chList);
			
			List<ChapterRest> chResult = chapterControllerImpl.getChaptersByTvShowIdAndSeasonNumber(anyLong(), anyShort()).getData();
			
			//VERIFY
			verify(chapterService, atLeast(1)).getChaptersByTvShowIdAndSeasonNumber(anyLong(), anyShort());
			
			//ASSERT
			assertNotNull(chResult);
			for (ChapterRest chr : chResult) {
				
				assertTrue(chList.contains(chr));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	void testGetChapterByTvShowIdAndSeasonNumberAndChapterNumber() {
		
		ChapterRest ch = new ChapterRest();
		ch.setId(1L);
		ch.setName("Cap 1");
		ch.setNumber((short) 2);
		ch.setDuration((short) 30);
		
		try { 
			 
			when(chapterService.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(anyLong(), anyShort(), anyShort()))
			.thenReturn(ch);
			
			ChapterRest chResult = chapterControllerImpl.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(
					anyLong(), anyShort(), anyShort()).getData();

			//VERIFY
			verify(chapterService, atLeast(1)).getChapterByTvShowIdAndSeasonNumberAndChapterNumber(anyLong(), anyShort(), anyShort());
			
			//ASSERT
			assertNotNull(chResult);
			assertTrue(ch.getId() == chResult.getId());
			assertTrue(ch.getName() == chResult.getName());
			assertTrue(ch.getDuration() == chResult.getDuration());
			assertTrue(ch.getNumber() == chResult.getNumber());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	@Test
	void testUpdateTvShow() {
		
		Chapter chp = new Chapter();
		chp.setName("Cap 1"); 
		chp.setNumber((short) 2);
		chp.setDuration((short) 30);
		
		ChapterRest ch = new ChapterRest();
		ch.setId(1L);
		ch.setName("Cap 1");
		ch.setNumber((short) 2);
		ch.setDuration((short) 30);
		
		 
		
		try {
			 
			when(chapterServiceImpl.updateChapter(1L,chp)).thenReturn(ch);
			
			ChapterRest  chResult =  chapterControllerImpl.updateTvShow(1L, chp).getData();
			
			//VERIFY
			verify(chapterServiceImpl, atLeast(1)).updateChapter(1L, chp);
			
			//ASSERT
			assertNotNull(chResult);
			assertTrue(ch.getId() == chResult.getId());
			assertTrue(ch.getName() == chResult.getName());
			assertTrue(ch.getDuration() == chResult.getDuration());
			assertTrue(ch.getNumber() == chResult.getNumber());
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		

}
