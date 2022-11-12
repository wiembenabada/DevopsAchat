package tn.esprit.spring.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RunAs;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.StockRepository;
import tn.esprit.spring.service.IStockService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceImplTest {
	
	@Autowired
	IStockService stockService;
	@Autowired
	StockRepository rStock;
	
	//@Test
/*	public void testAddStock() {
		List<Stock> stocks = stockService.retrieveAllStock();
		int expected = stocks.size();
		Stock s = new Stock();
		s.setLibellestock("stock test");
		s.setQtestock(10);
		s.setQtemin(100);
		Stock savedStock= stockService.addStock(s);
		
		assertEquals(expected+1, stockService.retrieveAllStock().size());
		assertNotNull(savedStock.getLibellestock());
		//stockService.deleteStock(savedStock.getIdstock());
		
		 //List<Stock> stocks2 = (List<Stock>) stockService.retrieveAllStock();
		   // assertThat(stocks2).size().isGreaterThan(0);
		
		
		
	
	}
	
	//@Test
	//public void testDeleteStock(){
		/*List<Stock> stocks = stockService.retrieveAllStock();
		int expected = stocks.size();
		
		//Stock savedStock= stockService.addStock(s);
				stockService.deleteStock(savedStock.getIdstock());
				//Stock deletedStock = rStock.getById(savedStock.getIdstock());
				assertEquals(expected-1, stockService.retrieveAllStock().size());*/
				
	//}

	
	
}
