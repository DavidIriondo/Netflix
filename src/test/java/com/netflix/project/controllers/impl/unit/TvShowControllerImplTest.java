package com.netflix.project.controllers.impl.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.netflix.project.controllers.impl.TvShowControllerImpl;
import com.netflix.project.entities.Category;
import com.netflix.project.entities.TvShow;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.json.CategoryRest;
import com.netflix.project.json.TvShowRest;
import com.netflix.project.services.TvShowService;
import com.netflix.project.services.impl.TvShowServiceImpl;

@ExtendWith(MockitoExtension.class)
class TvShowControllerImplTest {
	 
	@Mock
	private TvShowService tvShowService;
		
	@Mock
	private TvShowServiceImpl tvShowServiceImpl;
	
	@InjectMocks
	private TvShowControllerImpl tvShowControllerImpl; 
	
	private ModelMapper modelMapper = new ModelMapper();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGetTvShowsByCategory() {
		
		TvShowRest tv1 = new TvShowRest();
		TvShowRest tv2 = new TvShowRest();
		TvShowRest tv3 = new TvShowRest();
		
		tv1.setId(1L);
		tv2.setId(2L);
		tv3.setId(3L);
		
		tv1.setName("Los simpson");
		tv2.setName("Big Ban Theory");
		tv3.setName("Friends");

		
		CategoryRest comedy = new CategoryRest();
		comedy.setId(1L);
		comedy.setName("COMEDY");
		
		
		List<CategoryRest> categories = new ArrayList<>();
		categories.add(comedy);
		
		tv1.setCategory(categories);
		tv2.setCategory(categories);
		tv3.setCategory(categories);
		
		List<TvShowRest> tvShowList = new ArrayList<>();
		
		tvShowList.add(tv1);
		tvShowList.add(tv2);
		tvShowList.add(tv3);
		
		
		try {
			
			when(tvShowService.getTvShowsByCategory(anyLong())).thenReturn(tvShowList);
			
			List<TvShowRest> tvResult =  tvShowControllerImpl.getTvShowsByCategory(anyLong()).getData();
			
			
			//VERIFY
			verify(tvShowService, atLeast(1)).getTvShowsByCategory(anyLong());
			
			verify(tvShowControllerImpl, atLeast(1)).getTvShowsByCategory(anyLong());
			
			
			//ASSERTS
			assertNotNull(tvResult);//Not null
			assertEquals(tvShowList.size(), tvResult.size());//same size
			for (TvShowRest tvr : tvResult) {
				assertTrue(tvShowList.contains(tvr));
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception 
		}
		
	};
	
	@Test
	void testGetTvShowById() {
		
		TvShowRest tv = new TvShowRest();
		tv.setId(1L);
		tv.setName("Los simpson");
		tv.setShortDescription("American Tvshow");
		tv.setLongDescription("The series is a satire towards American society that narrates the life and daily life of a middle-class family in that country...");
		
		try {
			when(tvShowService.getTvShowById(1L)).thenReturn(tv);
			
		 	TvShowRest tvResult =  tvShowControllerImpl.getTvShowById(1L).getData();
		 	
		 	//VERIFY
		 	verify(tvShowService, atLeast(1)).getTvShowById(anyLong());
		 	
		 	//ASSERTS
		 	assertNotNull(tvResult);
		 	assertEquals(tv.getId(), tvResult.getId());
		 	assertEquals(tv.getName(), tvResult.getName());
		 	assertEquals(tv.getShortDescription(), tvResult.getShortDescription());
		 	assertEquals(tv.getLongDescription(), tvResult.getLongDescription());
		 	
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};

	@Test
	void testUpdateTvShow() {
		//Create the test resource
		TvShow tv = new TvShow();
		tv.setId(1L);
		tv.setName("Los simpson");
		//Resource fined
		TvShowRest tvUpdated = new TvShowRest();
		tv.setId(1L);
		tv.setName("Friends");
		
		//Update tv show
		try {
			when(tvShowServiceImpl.updateTvShow(1L, tv)).thenReturn(tvUpdated);
			
			TvShowRest tvResult = tvShowControllerImpl.updateTvShow(1L, tv).getData();
			
		 	//VERIFY
		 	verify(tvShowServiceImpl, atLeast(1)).updateTvShow(anyLong(), any());
		 	
		 	//ASSERTS
		 	assertNotNull(tvResult);
		 	assertEquals(tvUpdated.getId(), tvResult.getId());
			assertEquals(tvUpdated.getName(), tvResult.getName());
			assertNotEquals(tv.getName(), tvResult.getName());
			
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testDeleteTvShow() {
		//Creating a new object
		TvShowRest tv = new TvShowRest();
		tv.setId(1L);
		tv.setName("Como conoci a vuestra madre");
		
		try {
			when(tvShowServiceImpl.deleteTvShow(1L)).thenReturn(tv);
			
			TvShowRest tvResult = tvShowControllerImpl.deleteTvShow(1L).getData();
			
		 	//VERIFY
		 	verify(tvShowServiceImpl, atLeast(1)).deleteTvShow(1L);
		 	
		 	//ASSERTS
		 	assertNotNull(tvResult);
			assertEquals(tvResult.getId(), tv.getId());
			assertEquals(tvResult.getName(), tv.getName());
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testAddCategories() {
		//Creating TVSHOW
		TvShow tvResult = new TvShow();
		tvResult.setId(1L);
		tvResult.setName("Breaking Bad");
		
		//Creating CATEGORIES
		Category c1 = new Category(); 
		Category c2 = new Category(); 
		Category c3 = new Category(); 
		Category c4 = new Category(); 
		
		c1.setId(1L);
		c2.setId(2L);
		c3.setId(3L);
		c4.setId(4L);
		
	    c1.setName("SCARY");
	    c2.setName("DRAMA");
	    c3.setName("THILLER");
	    c4.setName("COMEDY");
	    
	    
		//LIST OF CATEGORIES ID
		List<Long>categoriesID = new ArrayList<Long>();
		categoriesID.add(1L);
		categoriesID.add(2L);
		categoriesID.add(3L);
		categoriesID.add(4L);
		
		//LIST OF CATEGORIES
		List<Category>categories = new ArrayList<Category>();
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		categories.add(c4);
		
		//ADD CATEGORIES TO OUR TVSHOW
		tvResult.setCategory(categories);
		
		
		try {
			
			 
			when(tvShowServiceImpl.addCategories(1L, categoriesID)).thenReturn(modelMapper.map(tvResult, TvShowRest.class));
			
			TvShowRest tvshow =  tvShowControllerImpl.addCategories(1L, categoriesID).getData();
			
		 	//VERIFY
		 	verify(tvShowServiceImpl, atLeast(1)).addCategories(1L, categoriesID);
		 	
		 	//ASSERTS
			//If tv show´category list is greater than 0
		 	assertNotNull(tvshow);
			assertTrue(tvshow.getCategory().size() == 4);
			
			for (Category ct : categories) {
				assertFalse(tvshow.getCategory().contains(ct));
			}
				
			
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	void testTvShowAwards() {
		
		List<AwardRest> awardRests = new ArrayList<AwardRest>();
		//AWARDS
		AwardRest aw1 = new AwardRest();
		AwardRest aw2 = new AwardRest();
		AwardRest aw3 = new AwardRest();
		AwardRest aw4 = new AwardRest();
		
		aw1.setId(1L);
		aw2.setId(2L);
		aw3.setId(3L);
		aw4.setId(4L);
		
		aw1.setName("Premio 1");
		aw2.setName("Premio 2");
		aw3.setName("Premio 3");
		aw4.setName("Premio 4");
		
		awardRests.add(aw1);
		awardRests.add(aw2);
		awardRests.add(aw3);
		awardRests.add(aw4);
		
		try {
			
			when(tvShowServiceImpl.tvShowAwards(1L)).thenReturn(awardRests);
			
			List<AwardRest> awList  = tvShowControllerImpl.tvShowAwards(1L).getData();
			
		 	//VERIFY
		 	verify(tvShowServiceImpl, atLeast(1)).tvShowAwards(anyLong());
		 	
		 	//ASSERTS
		 	assertNotNull(awList);
			assertTrue(awList.size() == 4);
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
