package com.subash.api.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.subash.api.model.Jewellery;


@SpringBootTest
class JewelleryServiceImplTest {

	
	@Autowired
	private JewelleryServiceImpl jewelleryService;
	
	@Test
	void testAddJewellery() {
		Jewellery jewellery = new Jewellery(0, "Ring", "Comfortable", 10000, 2);
		assertEquals("Add Success", jewelleryService.addJewellery(jewellery));
	}

	@Test
	void testAddJewelleryFalse() {
		Jewellery jewellery = null;
		assertEquals("Add Failure", jewelleryService.addJewellery(jewellery));
	}

	@Test
	void testGetJewellery() {
	    
	    Jewellery jewellery = jewelleryService.getJewellery(13);
	    assertNotNull(jewellery);
	    assertEquals(13, jewellery.getId()); 
	}


	@Test
	void testGetAllJewellery() {
	    List<Jewellery> jewelleryList = jewelleryService.getAllJewellery();
	    assertNotNull(jewelleryList);
	    assertTrue(jewelleryList.size() > 0);
	}


	@Test
	void testUpdateJewellery() {
	    
	    Jewellery existingJewellery = jewelleryService.getJewellery(13);
	    assertNotNull(existingJewellery);

	   
	    existingJewellery.setName("Updated Ring");
	    existingJewellery.setDescription("Updated description");
	    existingJewellery.setPrice(12000); 
	    existingJewellery.setStock(3); 

	
	    jewelleryService.updateJewellery(existingJewellery);

	  
	    Jewellery updatedJewellery = jewelleryService.getJewellery(13);
	    assertEquals("Updated Ring", updatedJewellery.getName());
	    assertEquals("Updated description", updatedJewellery.getDescription());
	    assertEquals(12000, updatedJewellery.getPrice());
	    assertEquals(3, updatedJewellery.getStock());
	}

}
