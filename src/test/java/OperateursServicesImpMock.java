  
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AchatApplication.class)
public class OperateursServicesImpMock {

	@Mock
	OperateurRepository OperateurRepository;

	@InjectMocks
	OperateurServiceImpl OperateurServiceImp;
	
	Operateur op =Operateur.builder().nom("chokri").prenom("toumi").build();
	
	List<Operateur> listOperateurs = new ArrayList<Operateur>() {
	{
	add(Operateur.builder().nom("basma").prenom("zenati").build());
	add(Operateur.builder().nom("abc12").prenom("azertm").build());
	}
	};
	
	
	
	@Test
	@Order(1)
	void testAddOperateur() {
		Operateur op = OperateurServiceImp.addOperateur(Operateur.builder().nom("Name").prenom("prenom").password("aedf1223").build());
		Assertions.assertNotNull(op);
	}


	@Test
	void testRetrieveOperateur() {
		Operateur op =Operateur.builder().nom("Operateur").prenom("prenom ").build();
		OperateurServiceImp.addOperateur(op);
		Operateur oper = OperateurServiceImp.retrieveOperateur(op.getIdOperateur());
		Assertions.assertNotNull(oper);
	}
	
	@Test
	void testUpdateOperateur() {
		Operateur op = OperateurServiceImp.updateOperateur(Operateur.builder().nom("Nom mod").build());
		Assertions.assertNotNull(op);
	}

	@Test
	void testRetrieveAllOperateur() {
		int ListOp = OperateurServiceImp.retrieveAllOperateurs().size();
		OperateurServiceImp.addOperateur(Operateur.builder().nom("devops").build());
		Assertions.assertEquals(ListOp+1,OperateurServiceImp.retrieveAllOperateurs().size());
	}



	

}
