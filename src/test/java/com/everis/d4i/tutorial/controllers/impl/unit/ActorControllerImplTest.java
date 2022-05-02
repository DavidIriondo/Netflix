package com.everis.d4i.tutorial.controllers.impl.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyVararg;
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

import com.everis.d4i.tutorial.controllers.impl.ActorControllerImpl;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.projections.ActorChapterProjection;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;

class ActorControllerImplTest {
	
	@Mock
	private ActorServiceImpl actorServiceImpl;

	@InjectMocks
	private ActorControllerImpl actorControllerImpl;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetActors() {
		List<ActorRest> actors = new ArrayList<>();
		
		ActorRest ac1 = new ActorRest();
		ActorRest ac2 = new ActorRest();
		ActorRest ac3 = new ActorRest();
		ActorRest ac4 = new ActorRest();
		
		ac1.setId(1L);
		ac2.setId(2L);
		ac3.setId(3L);
		ac4.setId(4L);
		
		ac1.setName("Antonio");
		ac2.setName("Robbert");
		ac3.setName("Scarlet");
		ac4.setName("Dwayne");
		
		actors.add(ac1);
		actors.add(ac2);
		actors.add(ac3);
		actors.add(ac4);
		
		try {
			when(actorServiceImpl.getListOfActors()).thenReturn(actors);
			
			List<ActorRest> actorsResult = actorControllerImpl.getActors().getData();
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).getListOfActors();
			
			//ASSERT
			assertNotNull(actorsResult);
			assertTrue(actorsResult.size() == 4);
			assertTrue(actorsResult.contains(ac1));
			assertTrue(actorsResult.contains(ac2));
			assertTrue(actorsResult.contains(ac3));
			assertTrue(actorsResult.contains(ac4));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testGetActorById() {
		ActorRest ac1 = new ActorRest();
		ac1.setId(1L);
		ac1.setName("Dwayne");
		ac1.setSurname("Johnson");
		ac1.setAge(30);
		
		
		try {
			
			when(actorServiceImpl.getActorbyId(1L)).thenReturn(ac1);
			
			ActorRest acResult = actorControllerImpl.getActorById(1L).getData();
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).getActorbyId(1L);
			
			//ASSERT
			assertNotNull(acResult);
			assertTrue(ac1.getId() == acResult.getId());
			assertTrue(ac1.getName().equals(acResult.getName()));
			assertTrue(ac1.getSurname().equals(acResult.getSurname()));
			assertTrue(ac1.getAge().equals(acResult.getAge()));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testPostActor() {
		
		ActorRest ac = new ActorRest();
		ac.setId(1L);
		ac.setName("Dwayne");
		ac.setAge(50);
		ac.setSurname("Johnson");
		
		Actor acPost = new Actor();
		acPost.setId(1L);
		acPost.setName("Dwayne");
		acPost.setAge(50);
		acPost.setSurname("Johnson");
		
		try {
			
			when(actorServiceImpl.postActor(acPost)).thenReturn(ac);
			
			ActorRest  acResult =  actorControllerImpl.postActor(acPost).getData();
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).postActor(acPost);
			
			//ASSERT
			assertNotNull(acResult);
			assertTrue(ac.getId() == acResult.getId());
			assertTrue(ac.getName() == acResult.getName());
			assertTrue(ac.getSurname() == acResult.getSurname());
			assertTrue(ac.getAge() == acResult.getAge());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	void testUpdateActor() {
		
		Actor ac = new Actor();
		ac.setId(1L);
		ac.setName("Dwayne the rock");
		ac.setAge(50);
		ac.setSurname("Johnson");
		
		
		ActorRest acUpdated = new ActorRest();
		acUpdated.setId(1L);
		acUpdated.setName("Dwayne the rock");
		acUpdated.setAge(50);
		acUpdated.setSurname("Johnson"); 
		
		try {
			
			
			when(actorServiceImpl.updateActor(1L,ac)).thenReturn(acUpdated);
			
			ActorRest  acResult =  actorControllerImpl.updateActor(1L, ac).getData();
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).updateActor(1L, ac);
			
			//ASSERT
			assertNotNull(acResult);
			assertTrue(ac.getName() == acResult.getName());
			assertTrue(ac.getSurname() == acResult.getSurname());
			assertTrue(ac.getAge() == acResult.getAge());
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testDeleteActor() {
		
		ActorRest ac = new ActorRest();
		ac.setId(1L);
		ac.setName("Dwayne the rock");
		ac.setAge(50);
		ac.setSurname("Johnson");
		
		try {
			
			when(actorServiceImpl.deleteActor(1L)).thenReturn(ac);
			
			ActorRest  acResult =  actorControllerImpl.deleteActor(1L).getData();
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).deleteActor(1L);
			
			//ASSERT
			assertNotNull(acResult);
			assertTrue(ac.getName() == acResult.getName());
			assertTrue(ac.getSurname() == acResult.getSurname());
			assertTrue(ac.getAge() == acResult.getAge());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	void testGetActorTvShows() {
		ActorRest ac = new ActorRest();
		ac.setId(1L);
		
		ActorChapterTest ac1 = new ActorChapterTest();
		ActorChapterTest ac2 = new ActorChapterTest();
		ActorChapterTest ac3 = new ActorChapterTest();
		ActorChapterTest ac4 = new ActorChapterTest();

		ac1.setTvShowId(1L);
		ac1.setSeason(1L);
		ac1.setChapter(2L);
		ac1.setTvShow("Los simpson");
		
		ac2.setTvShowId(1L);
		ac2.setSeason(1L);
		ac2.setChapter(3L);
		ac2.setTvShow("Los simpson");
		
		ac3.setTvShowId(1L);
		ac3.setSeason(1L);
		ac3.setChapter(3L);
		ac3.setTvShow("Los simpson");
		
		ac4.setTvShowId(1L);
		ac4.setSeason(1L);
		ac4.setChapter(4L);
		ac4.setTvShow("Los simpson");
		
		
		List<ActorChapterProjection> acList = new ArrayList<ActorChapterProjection>();
	
		acList.add(ac1);
		acList.add(ac2);
		acList.add(ac3);
		acList.add(ac4);
		
		try {
			when(actorServiceImpl.actorTvshows(1L)).thenReturn(acList);
			
			List<ActorChapterProjection> acResult =  actorControllerImpl.getActorTvShows(1L).getData();
			
			assertEquals(acResult.size(), acList.size());
			
			//VERIFY
			verify(actorServiceImpl, atLeast(1)).actorTvshows(1L);
			
			//ASSERT
			assertNotNull(acResult);
			//Two list contains same objects
			for (ActorChapterProjection aci : acResult) {
				
				assertTrue(acList.contains(aci));
			}
			
		} catch (NetflixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//This class implements the projection interface in order to test the API
	class ActorChapterTest implements ActorChapterProjection{
		
		Long TvShowId;
		String TvShow;
		Long season;
		Long chapter;
		

		@Override
		public Long getTvShowId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getTvShow() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getSeason() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getChapter() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setTvShowId(Long tvShowId) {
			TvShowId = tvShowId;
		}

		public void setTvShow(String tvShow) {
			TvShow = tvShow;
		}

		public void setSeason(Long season) {
			this.season = season;
		}

		public void setChapter(Long chapter) {
			this.chapter = chapter;
		}
		
		
		
		
	}

}
