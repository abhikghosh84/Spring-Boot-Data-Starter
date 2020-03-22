package com.spring.jdbc.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.jdbc.model.Sale;

class SalesDaoTest {
	
	private SalesDao dao;

	@BeforeEach
	void setUp() throws Exception {
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl("jdbc:mysql://192.168.99.100:3306/testdb");
		datasource.setUsername("root");
		datasource.setPassword("root");
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		dao = new SalesDao(new JdbcTemplate(datasource));
		
	}

	@Test
	void testList() {
		
		Sale sale = new Sale("TEST ITEM", 1, 499.90f);
		dao.save(sale);
		List<Sale> sales = dao.list();
		
		assertTrue(sales.size()>0);
	}

	@Test
	void testSave() {
		Sale sale = new Sale("Samsung Galaxy", 1, 499.90f);
		dao.save(sale);
	}

	@Test
	void testGet() {
		assertNotNull(dao.get(1));
	}

	@Test
	void testUpdate() {
		Sale sale = new Sale();
		sale.setId(1);
		sale.setQuantity(2);
		sale.setAmount(1099.49f);
		sale.setItem("iPhone X");
		
		dao.update(sale);
	}

	@Test
	void testDelete() {
		Sale sale = new Sale("TEST ITEM", 1, 499.90f);
		dao.save(sale);
		Sale newSale = dao.list().stream().filter(s -> s.getItem().equals("TEST ITEM")).findFirst().get();
		assertNotNull(newSale);
		dao.delete(newSale.getId());
		Optional<Sale> deletedSale = dao.list().stream().filter(s -> s.getItem().equals("TEST ITEM")).findFirst();
		assertTrue(!deletedSale.isPresent());
	}

}
