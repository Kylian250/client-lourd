package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import modele.Produit;
import modele.Vente;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;

class TestUnitaireProduitVente {
    private Produit produit;
    private Vente vente;
    private ProduitDAO produitDAO;
    private VenteDAO venteDAO;

    @BeforeEach
    void setUp() {
        // Initialiser les DAO
        produitDAO = new ProduitDAO();
        venteDAO = new VenteDAO();

        // Nettoyer les données de test précédentes si elles existent
        try {
            produitDAO.supprimerProduit("Produit Test");
            produitDAO.supprimerProduit("Nouveau Produit");
        } catch (Exception e) {
            // Ignorer les erreurs si les produits n'existaient pas
        }

        // Créer un produit de test
        produit = new Produit("Produit Test", 10, 100.0, 20, 5, 1);
        
        // Créer une vente associée à ce produit
        vente = new Vente(new java.sql.Date(System.currentTimeMillis()), produit.getId_produit(), 5);
    }

    @Test
    @DisplayName("Test de la relation entre Produit et Vente")
    void testProduitVenteRelation() {
        // Vérifier que la vente est bien associée au produit
        assertNotNull(produit, "Le produit ne devrait pas être null");
        assertNotNull(vente, "La vente ne devrait pas être null");
        assertEquals(5, vente.getQuantite(), "La quantité de la vente devrait être 5");
    }

    @Test
    @DisplayName("Test d'ajout d'une vente à un produit")
    void testAjoutVenteProduit() {
        // Créer un nouveau produit et une nouvelle vente
        Produit nouveauProduit = new Produit("Nouveau Produit", 20, 200.0, 30, 10, 1);
        Vente nouvelleVente = new Vente(new java.sql.Date(System.currentTimeMillis()), nouveauProduit.getId_produit(), 10);

        // Vérifier les valeurs
        assertNotNull(nouveauProduit, "Le nouveau produit ne devrait pas être null");
        assertNotNull(nouvelleVente, "La nouvelle vente ne devrait pas être null");
        assertEquals(10, nouvelleVente.getQuantite(), "La quantité de la nouvelle vente devrait être 10");
    }

    @Test
    @DisplayName("Test de la mise à jour du stock après une vente")
    void testMiseAJourStock() {
        try {
            // D'abord, ajouter le produit dans la base de données
            produitDAO.ajouterProduit(produit);
            
            // Récupérer le produit depuis la base de données pour avoir son ID
            Produit produitEnBase = produitDAO.getProduitByNom("Produit Test");
            assertNotNull(produitEnBase, "Le produit devrait être en base de données");
            
            // Quantité initiale du produit
            int quantiteInitiale = produitEnBase.getQuantite();
            int quantiteVente = 5;

            // Créer et ajouter la vente avec l'ID du produit en base
            Vente nouvelleVente = new Vente(
                new java.sql.Date(System.currentTimeMillis()), 
                produitEnBase.getId_produit(), 
                quantiteVente
            );
            venteDAO.ajouterVente(nouvelleVente);

            // Recharger le produit depuis la base de données
            Produit produitMisAJour = produitDAO.getProduitById(produitEnBase.getId_produit());

            // Vérifier que le stock a été mis à jour
            assertEquals(quantiteInitiale - quantiteVente, produitMisAJour.getQuantite(), 
                "Le stock devrait être diminué de la quantité vendue");

        } finally {
            // Nettoyer la base de données
            try {
                // Supprimer d'abord les ventes associées
                venteDAO.supprimerVente(vente.getId_vente());
                // Puis supprimer le produit
                produitDAO.supprimerProduit("Produit Test");
            } catch (Exception e) {
                // Ignorer les erreurs de nettoyage
                System.out.println("Erreur lors du nettoyage : " + e.getMessage());
            }
        }
    }
}
