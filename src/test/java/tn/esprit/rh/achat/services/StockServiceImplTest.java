package com.esprit.examen.services;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.services.IStockService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	
	@Autowired
	IStockService stockService;
	
	
	@Test
	public void testAddStock(){
		List<Stock> stocks = stockService.retrieveAllStocks();
		int expected = stocks.size();
		Stock s = new Stock();
		s.setLibelleStock("test");
		s.setQte(25);
		s.setQte(300);
		Stock savedStock= stockService.addStock(s);
		assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
		
	}
	
	
	@Test
	public void testRetrieveStock() {
		Stock s = new Stock();
		s.setLibelleStock("test");
		s.setQte(600);
		s.setQteMin(30);
		Stock savedStock= stockService.addStock(s);
		Stock getStock= stockService.retrieveStock(savedStock.getIdStock());
		assertNotNull(savedStock.getLibelleStock());
		assertNotNull(savedStock.getQte());
		assertNotNull(savedStock.getQteMin());
		assertEquals(savedStock.getIdStock(),getStock.getIdStock());
		
		stockService.deleteStock(savedStock.getIdStock());
		}
	
	@Test
	public void testUpdateStock() {
		Stock s = new Stock();
		s.setLibelleStock("test");
		s.setQte(2055);
		s.setQteMin(20);
		Stock savedStock= stockService.addStock(s);
		savedStock.setLibelleStock("rania");;
		stockService.updateStock(savedStock);
		assertEquals(s.getLibelleStock(),savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
		}
	
	@Test
	public void testDeleteStock() {
		Stock s = new Stock();
		s.setLibelleStock("test");
		s.setQte(20668);
		s.setQteMin(30);
		Stock savedService= stockService.addStock(s);
		stockService.deleteStock(savedService.getIdStock());
		assertNotNull(savedService.getIdStock());
		
	}

}