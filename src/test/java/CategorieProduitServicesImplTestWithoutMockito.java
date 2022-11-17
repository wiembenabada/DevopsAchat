import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.services.ICategorieProduitService;

@ActiveProfiles(profiles = "tester")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = AchatApplication.class)
class CategorieProduitServicesImplTestWithoutMockito {

	@Autowired
	private ICategorieProduitService CategorieServiceImp;
	
	
	@Test
	@Order(1)
	void testAddCategorieProduit() {
		CategorieProduit CP = CategorieServiceImp.addCategorieProduit(CategorieProduit.builder().codeCategorie("Cat1").libelleCategorie("categorie prod1").build());
		Assertions.assertNotNull(CP);
	}

	@Test
	@Order(2)
	void testRetrieveAllCategorieProduits() {
		int ListCatProds = CategorieServiceImp.retrieveAllCategorieProduits().size();
		CategorieServiceImp.addCategorieProduit(CategorieProduit.builder().codeCategorie("Cat1").libelleCategorie("categorie prod1 : test add").build());
		Assertions.assertEquals(ListCatProds+1,CategorieServiceImp.retrieveAllCategorieProduits().size());
	}

/*
	@Test
	@Order(3)
	void testDeleteCategorieProduit() {
		
		CategorieServiceImp.deleteCategorieProduit(CategorieServiceImp.retrieveCategorieProduit(1l).getIdCategorieProduit());
		CategorieProduit CP =  CategorieServiceImp.retrieveCategorieProduit(1l); 
		Assertions.assertNull(CP);
	}*/

	@Test
	@Order(4)
	void testUpdateCategorieProduit() {
		CategorieProduit CP = CategorieServiceImp.updateCategorieProduit(CategorieProduit.builder().codeCategorie("Cat1 updated").build());
		Assertions.assertNotNull(CP);
	}

	@Test
	@Order(5)
	void testRetrieveCategorieProduit() {
		CategorieProduit CP =CategorieProduit.builder().codeCategorie("Cat test").libelleCategorie("categorie prod test retrieve one ").build();
		CategorieServiceImp.addCategorieProduit(CP);
		CategorieProduit CPTest = CategorieServiceImp.retrieveCategorieProduit(CP.getIdCategorieProduit());
		Assertions.assertNotNull(CPTest);
	}

}
