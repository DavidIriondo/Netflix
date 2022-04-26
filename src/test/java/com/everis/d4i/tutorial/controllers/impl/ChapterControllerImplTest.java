package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyShort;
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
			
			when(chapterServiceImpl.getChaptersByTvShowIdAndSeasonNumber(anyLong(), anyShort())).thenReturn(chList);
			
			List<ChapterRest> chResult = chapterControllerImpl.getChaptersByTvShowIdAndSeasonNumber(anyLong(), anyShort()).getData();
			
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
		
		try {
			
			when(chapterServiceImpl.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(anyLong(), anyShort(), anyShort()))
			.thenReturn(ch);
			
			ChapterRest chResult = chapterControllerImpl.getChapterByTvShowIdAndSeasonNumberAndChapterNumber(
					anyLong(), anyShort(), anyShort()).getData();

			assertTrue(ch.getId() == chResult.getId());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	@Test
	void testUpdateTvShow() {
		
		ChapterRest ch = new ChapterRest();
		ch.setId(1L);
		ch.setName("Cap 1");
		
		try {
			
			when(chapterService.updateChapter(1L,new Chapter())).thenReturn(ch);
			
			ChapterRest  chResult =  chapterControllerImpl.updateTvShow(1L, new Chapter()).getData();
			
			assertTrue(ch.getId() == chResult.getId());
			assertTrue(ch.getName() == chResult.getName());
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		

}
