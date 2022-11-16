import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.slf4j.Logger;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;
import tn.esprit.rh.achat.services.ICategorieProduitService;


@SpringBootTest(classes = AchatApplication.class)
@ExtendWith(MockitoExtension.class)
class CategorieProduitServicesImplTest {


	
	@MockBean
	private CategorieProduitRepository CategorieRepository;
	
	
	@InjectMocks
	CategorieProduitServiceImpl CategorieServiceImp;
	
	
	
     
	@Test
	void testAddCategorieProduit() {
		CategorieProduit CatProd = new CategorieProduit();
		CatProd.setCodeCategorie("testAdd");
		CatProd.setLibelleCategorie("test add categorie produit");
		Mockito.when(CategorieRepository.save(CatProd)).thenReturn(CatProd);
		assertEquals(CatProd, CategorieServiceImp.addCategorieProduit(CatProd));
	}


	
	@Test
	void testRetrieveAllCategorieProduits() {
		CategorieProduit CatProd1 = new CategorieProduit();
		CatProd1.setCodeCategorie("Cat Prod1");
		CatProd1.setLibelleCategorie("categorie produit 1 ");
		CategorieProduit CatProd2 = new CategorieProduit();
		CatProd2.setCodeCategorie("Cat Prod2");
		CatProd2.setLibelleCategorie("categorie produit 2");
		List<CategorieProduit> list = new ArrayList<CategorieProduit>();
		list.add(CatProd1);
		list.add(CatProd2);

		Mockito.when(CategorieRepository.findAll()).thenReturn(list);
		assertEquals(2, CategorieServiceImp.retrieveAllCategorieProduits().size());
		
	}



	@Test
	void testDeleteCategorieProduit() {
		CategorieServiceImp.deleteCategorieProduit(1l);
		Mockito.verify(CategorieRepository,Mockito.times(1)).deleteById(Mockito.anyLong());
	}

	@Test
	void testUpdateCategorieProduit() {
		CategorieProduit CatProd = new CategorieProduit();
		CatProd.setCodeCategorie("Cat ProdUP");
		CatProd.setLibelleCategorie("categorie produit update ");
		Mockito.when(CategorieRepository.save(CatProd)).thenReturn(CatProd);
		CategorieProduit CatProdUP=CategorieServiceImp.updateCategorieProduit(CatProd);
		Assertions.assertNotNull(CatProdUP);
		
	}

	@Test
	void testRetrieveCategorieProduit() {
		   CategorieProduit CP=CategorieProduit.builder().codeCategorie("Cat1").idCategorieProduit(1l).libelleCategorie("categorie prod1").build();
		   Mockito.when(CategorieRepository.findById(1l)).thenReturn(Optional.of(CP));
		   CategorieProduit CP1=CategorieServiceImp.retrieveCategorieProduit(CP.getIdCategorieProduit());
		   Assertions.assertNotNull(CP1);
	}

}
