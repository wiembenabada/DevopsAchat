import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.ICategorieProduitService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = AchatApplication.class)
class OperateursServicesImplTest {

	
	@Autowired
	private ICategorieProduitService CategorieServiceImp;
	
	@MockBean
	private CategorieProduitRepository CategorieRepository;
	
	/*
	@Test
	@Order(1)
	void testAddCategorieProduit() {
		CategorieProduit CP =new CategorieProduit();
		CP.setCodeCategorie("CAT1");
		CP.setLibelleCategorie("Categorie prod 1");
		CategorieServiceImp.addCategorieProduit(CP);
		Assertions.assertNotNull(CP);
	}
	
	
	@Test
	@Order(2)
	void testRetrieveAllCategorieProduits() {
		int listCatProd = CategorieServiceImp.retrieveAllCategorieProduits().size();
		CategorieProduit CP =new CategorieProduit();
		CP.setCodeCategorie("CAT2");
		CP.setLibelleCategorie("Categorie prod 2");
		CategorieServiceImp.addCategorieProduit(CP);
		Assertions.assertEquals(listCatProd+1,CategorieServiceImp.retrieveAllCategorieProduits().size());
	}



	@Test
	@Order(3)
	void testDeleteCategorieProduit() {
		CategorieProduit CP =  CategorieServiceImp.retrieveCategorieProduit((long) 1);
		CategorieServiceImp.deleteCategorieProduit(CP.getIdCategorieProduit());
		 Assertions.assertNull(CP);
	}

	@Test
	@Order(4)
	void testUpdateCategorieProduit() {
		fail("Not yet implemented");
	}

	@Test
	@Order(5)
	void testRetrieveCategorieProduit() {
		fail("Not yet implemented");
	}
*/
}
