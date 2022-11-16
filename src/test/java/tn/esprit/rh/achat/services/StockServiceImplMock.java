package tn.esprit.rh.achat.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplMock {
	
	
	 @Mock
    StockRepository sto;
	@InjectMocks
	StockServiceImpl stockService;
	Stock s = new Stock("stock test",10,100);

	@Test
	public void testAddStock() {

		Stock s = new Stock("stock test",10,100);
        Mockito.when(sto.save(ArgumentMatchers.any(Stock.class))).thenReturn(s);
		Stock savedStock= stockService.addStock(s);

		assertNotNull(savedStock.getLibelleStock());
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);

		stockService.deleteStock(savedStock.getIdStock());

	}

	@Test
	public void testDeleteStock() {
		Stock s = new Stock("stock test",30,60);
		s.setIdStock(Long.valueOf(100));
		stockService.addStock(s);
		stockService.deleteStock(s.getIdStock());
		assertNull(stockService.retrieveStock(s.getIdStock()));
	}

	@Test
	public void testRetrieveStock() {
		Mockito.when(sto.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
		Stock s1 = stockService.retrieveStock(s.getIdStock());
		Assertions.assertNotNull(s1);
	}


	@Test
	public void testAddstock() {
		Mockito.when(sto.save(s)).thenReturn(s);
		Stock s1 = stockService.addStock(s);
		Assertions.assertNotNull(s1);

	}

	@Test
	public void testUpdatestock() {
		s.setQteMin(5);
		Mockito.when(sto.save(s)).thenReturn(s);
		Stock s1 = stockService.updateStock(s);
		Assertions.assertEquals(s,s1);

	}

	@Test
	public void testDeletestock() {
		stockService.deleteStock(s.getIdStock());
		Mockito.verify(sto, Mockito.times(1)).deleteById(s.getIdStock());
	}

}