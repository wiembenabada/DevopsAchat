import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.services.IOperateurService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = AchatApplication.class)
class OperateursServicesImplTest {

		@Autowired
	    private IOperateurService OperateurServiceImp;
		/*
		 @Test 
		@Order(1)
		public void testAddOperateur() {
			Operateur op = OperateurServiceImp.addOperateur(Operateur.builder().nom("emna16").prenom("toumi").build());
			Assertions.assertNotNull(op);
		}
		
		@Test
		@Order(2)
		public void testRetrieveAllOperateur() {
			int listOperateur = OperateurServiceImp.retrieveAllOperateurs().size();
			OperateurServiceImp.addOperateur(Operateur.builder().nom("emna16").prenom("toumi").build());
			Assertions.assertEquals(listOperateur+1,OperateurServiceImp.retrieveAllOperateurs().size());
		}
		
		 @Test 
		@Order(3)
		public void testUpdateOperateur() {
			Operateur p = OperateurServiceImp.updateOperateur(Operateur.builder().nom("zied").build());
			Assertions.assertNotNull(p);
		}
			
		 @Test 
		 @Order(4)
		public void testDeleteOperateur() {
		  Operateur p =  OperateurServiceImp.retrieveOperateur((long) 5);
			 OperateurServiceImp.deleteOperateur(p.getIdOperateur());
			 Assertions.assertNull(p);
			}
		 
		 @Test 
		 @Order(4)
		public void testRetrieveOperateur() {
				Operateur op =Operateur.builder().nom("test").prenom("qsdfg").build();
				OperateurServiceImp.addOperateur(op);
				Operateur p = OperateurServiceImp.retrieveOperateur(op.getIdOperateur());
				Assertions.assertNotNull(p);
				
}
*/
	}

