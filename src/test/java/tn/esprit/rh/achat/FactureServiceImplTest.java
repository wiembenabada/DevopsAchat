package tn.esprit.rh.achat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;



import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entites.dto.FactureRequestModel;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import tn.esprit.rh.achat.services.ReglementServiceImpl;


@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private FactureServiceImpl factureService ;

    @Mock
    private OperateurRepository operateurRepository;
    @InjectMocks
    private OperateurServiceImpl operateurService;
    @Mock
    private FournisseurRepository fournisseurRepository;
    @InjectMocks
    private FournisseurServiceImpl fournisseurService;
    @Mock
    private ReglementRepository reglementRepository;
    @InjectMocks
    private ReglementServiceImpl reglementService;
    @Mock
    private DetailFactureRepository detailFactureRepository;
    @Mock
    private ProduitRepository  produitRepository;

    private Facture f1 ;
    private Facture f2 ;
    private Operateur o1;
    private DetailFacture d1 ;
    private DetailFacture d2 ;
    private Produit p1 ;
    private Fournisseur fournisseur;
    private Reglement reg;
    ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        this.p1 = new Produit();
        this.p1.setIdProduit(0L);
        this.p1.setPrix(100);
        this.p1.setLibelleProduit("Avatar");

        this.f1 = new Facture();
        this.f1.setIdFacture(0L);
        this.f1.setMontantRemise(20);
        this.f1.setMontantFacture(100);
        this.f1.setArchivee(false);
        this.f1.setDateCreationFacture(new Date(2022, 11, 12));

        this.f2 = new Facture();
        this.f2.setIdFacture(1L);
        this.f2.setMontantRemise(10);
        this.f2.setMontantFacture(200);
        this.f2.setArchivee(false);
        this.f2.setDateCreationFacture(new Date(2022, 11, 12));

        this.d1 = new DetailFacture();
        this.d1.setIdDetailFacture(1L);
        this.d1.setQteCommandee(10);
        this.d1.setPourcentageRemise(10);
        this.d1.setMontantRemise(10);
        this.d1.setProduit(p1);
        this.d1.setPrixTotalDetail(200);

        this.d2 = new DetailFacture();
        this.d2.setIdDetailFacture(2L);
        this.d2.setMontantRemise(10);
        this.d2.setPrixTotalDetail(200);

        this.o1 = new Operateur();
        this.o1.setIdOperateur(0L);
        this.o1.setNom("test");
        this.o1.setPrenom("test");
        this.o1.setPassword("aaaa");

        this.fournisseur = new Fournisseur();
        this.fournisseur.setIdFournisseur(0L);
        this.fournisseur.setCode("code");
        this.fournisseur.setLibelle("libelle");
        this.fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);

        this.reg = new Reglement();
        this.reg.setMontantPaye(5.0f);
        this.reg.setDateReglement(new Date(2022, 11, 12));

        this.modelMapper = new ModelMapper();
    }


  /*  @Test
    public void testAddFacture() {
        init();
        when(factureRepository.save(any(Facture.class))).thenReturn(f1);
        FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
        Facture fnew=factureService.addFacture(frm);
        assertNotNull(fnew);
        assertThat(fnew.getMontantFacture()).isEqualTo(100);
    }*/


    @Test
    public void testgetFactures() {
        init();
        List<Facture> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        when(factureRepository.findAll()).thenReturn(list);
        List<Facture> factures = factureService.retrieveAllFactures();
        assertEquals(2, factures.size());
        assertNotNull(factures);
    }
    @Test
    @DisplayName("Test Retrieve All Facture")
    public void testRetrieveAllFactures() {
        init();
        List<Facture> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        when(factureRepository.findAll()).thenReturn(list);
        List<Facture> factures = factureService.retrieveAllFactures();
        assertEquals(2, factures.size());
        assertNotNull(factures);
    }
    @Test
   @DisplayName("Test Get Facture by id")
    public void TestGetFacturetById() {
        init();
        when(factureRepository.save(any(Facture.class))).thenReturn(f1);
        FactureRequestModel frm=modelMapper.map(f1, FactureRequestModel.class);
        Facture fnew=factureService.addFacture(frm);
        when(factureRepository.findById(anyLong())).thenReturn(Optional.of(f1));
        Facture existingFacture = factureService.retrieveFacture(fnew.getIdFacture());
        assertNotNull(existingFacture);
        assertThat(existingFacture.getIdFacture()).isNotNull();
    }

    @Test
    @DisplayName("Test Cancel Facutre")
    public void testCancelFacture() {


        init();
        Long FactureId = 0L;
        when(factureRepository.findById(FactureId)).thenReturn(Optional.of(f1));
        assertThat(f1.getArchivee()).isFalse();
        factureService.cancelFacture(FactureId);
        assertThat(f1.getArchivee()).isTrue();
    }



    @Test
    @DisplayName("Test Get Facture By Fournisseur")
    public void testGetFacturesByFournisseur() {
        init();
        Set<Facture> list = new HashSet<>();
        list.add(f1);
        list.add(f2);
        fournisseur.setFactures(list);
        factureService.getFacturesByFournisseur(fournisseur.getIdFournisseur());
        assertThat(fournisseur.getFactures()).hasSize(2);
    }

    @Test
    @DisplayName("Test Assign Operateur To Facture")
    public void testAssignOperateurToFacture() {
        init();
        when(factureRepository.findById(anyLong())).thenReturn(Optional.of(f1));
        assertThat(f1.getIdFacture()).isZero();
        when(operateurRepository.findById(anyLong())).thenReturn(Optional.of(o1));
        Set<Facture> list = new HashSet<>();
        o1.setFactures(list);
        factureService.assignOperateurToFacture(o1.getIdOperateur(), f1.getIdFacture());
        assertThat(o1.getFactures()).hasSize(1);
    }

    @Test
    @DisplayName("Test Pourcentage Recouverment")
    public void testPourcentageRecouvrement() {
        init();
        when(factureRepository.getTotalFacturesEntreDeuxDates(new Date(2022, 11, 11),new Date(2022, 11, 24))).thenReturn(8.0f);
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(new Date(2022, 11, 11),new Date(2022, 11, 24))).thenReturn(2.0f);
        assertThat(factureService.pourcentageRecouvrement(new Date(2022, 11, 11),new Date(2022, 11, 24))).isEqualTo(25.0f);
    }

    @Test
    public void testAddDetailsFacture() {
        init();
        Set<DetailFacture> detailsFacture= new HashSet<>();;
        detailsFacture.add(d1);

        when(produitRepository.findById(d1.getProduit().getIdProduit())).thenReturn(Optional.of(d1.getProduit()));
        when(factureService.addDetailsFacture(f1, detailsFacture)).thenReturn(f1);
        System.out.println("test " + f1.getMontantRemise());
        System.out.println("test " + f1.getMontantFacture());
        assertEquals(100L, f1.getMontantRemise());
        assertEquals(900f, f1.getMontantFacture());





    }
}