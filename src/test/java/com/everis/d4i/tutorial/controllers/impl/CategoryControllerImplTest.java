package com.everis.d4i.tutorial.controllers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.services.impl.CategoryServiceImpl;

class CategoryControllerImplTest {
	
	@Mock
	private CategoryServiceImpl categoryServiceImpl;
	
	@InjectMocks
	private CategoryControllerImpl categoryControllerImpl;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetCategories() {
		
		try {
			
			List<CategoryRest> caList = new ArrayList<>();
			
			CategoryRest ca1 = new CategoryRest();
			CategoryRest ca2 = new CategoryRest();
			CategoryRest ca3 = new CategoryRest();
			
			ca1.setId(1L);
			ca1.setName("THILLER");
			
			ca2.setId(2L);
			ca2.setName("DRAMA");
			
			ca3.setId(3L);
			ca3.setName("ROMANCE");
			
			caList.add(ca1);
			caList.add(ca2);
			caList.add(ca3);
			
			when(categoryServiceImpl.getCategories()).thenReturn(caList);
			
			List<CategoryRest> caResult =   categoryControllerImpl.getCategories().getData();
			
			for (CategoryRest car : caResult) {
				assertTrue(caList.contains(car));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Test
	void testCreateCategory() {
		
		CategoryRest ca1 = new CategoryRest();
		
		ca1.setId(1L);
		ca1.setName("THILLER");
		
		try {
			
			when(categoryServiceImpl.createCategories(ca1)).thenReturn(ca1);
			
			CategoryRest caResult =  categoryControllerImpl.createCategory(ca1).getData();
			
			assertEquals(ca1.getId(), caResult.getId());
			assertEquals(ca1.getName(), caResult.getName());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
