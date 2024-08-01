package com.subash.api.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.subash.api.model.Jewellery;
import com.subash.api.model.Sales;

@SpringBootTest
class SalesServiceImplTest {

	@Autowired
	SalesServiceImpl salesService;

	@Test
	void testAddSales() {
		Sales sales = new Sales();
		sales.setSaleDate("2023-07-18");
		sales.setQuantitySold(10);
		
		List<Jewellery> jewelleryList = new ArrayList<>();
		Jewellery jewellery1 = new Jewellery(0, "Ring", "Comfortable", 10000, 2);
		jewelleryList.add(jewellery1);

		sales.setJewellery(jewelleryList);

		assertEquals("Add Success", salesService.addSales(sales));
	}
	@Test
	void testAddJewelleryFalse() {
		Sales sales = null;
		assertEquals("Add Failure", salesService.addSales(sales));
	}

	@Test
	void testGetSales() {
		Sales sales = salesService.getSales(41);
		assertNotNull(sales);
		assertEquals(41, sales.getSaleId());
	}

	@Test
	void testGetAllSales() {
		List<Sales> salesList = salesService.getAllSales();
		assertNotNull(salesList);
		assertTrue(salesList.size() > 0);
	}

	@Test
	void testUpdateSales() {
		Sales existingSales = salesService.getSales(41);
		assertNotNull(existingSales);

		existingSales.setSaleDate("13-12-2023");
		existingSales.setQuantitySold(5);

		salesService.updateSales(existingSales);

		Sales updatedSales = salesService.getSales(41);
		assertNotNull(updatedSales);
		assertEquals("13-12-2023", updatedSales.getSaleDate());
		assertEquals(5, updatedSales.getQuantitySold());

	}

}
