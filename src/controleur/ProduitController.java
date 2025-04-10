package controleur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import modele.Produit;
import modele.Vente;
import modele.DAO.Connexion;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import vue.ProduitView;

public class ProduitController {
    private ProduitView vue;
    private ProduitDAO produitDAO;
    private VenteDAO venteDAO;

    public ProduitController(ProduitView vue, ProduitDAO produitDAO, VenteDAO venteDAO) {
        this.vue = vue;
        this.produitDAO = produitDAO;
        this.venteDAO = venteDAO;

        // Listener unifié pour l'ajout et la modification
        this.vue.setActionListener(e -> {
            try {
                String nom = vue.getNomProduit();
                double prix = vue.getPrixProduit();
                int quantiter = vue.getQuantiterProduit();
                int qteMax = vue.getQteMax();
                int qteAlert = vue.getQteAlert();
                int idFournisseur = vue.getIdFournisseur();

                if (vue.isModificationMode()) {
                    // Mode modification
                    Produit produitModifie = new Produit(
                        nom, quantiter, prix, qteMax, qteAlert, idFournisseur
                    );
                    produitDAO.modifierProduit(produitModifie);
                    JOptionPane.showMessageDialog(null, "Produit modifié !");
                } else {
                    // Mode ajout
                    Produit produit = new Produit(
                        nom, quantiter, prix, qteMax, qteAlert, idFournisseur
                    );
                    produitDAO.ajouterProduit(produit);
                    JOptionPane.showMessageDialog(null, "Produit ajouté !");
                }
                vue.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer des valeurs numériques valides.");
            }
        });
    }

    

    public Produit getProduitByNom(String nom) {
        String query = "SELECT * FROM produit WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id_produit");
                double prix = resultSet.getDouble("prixUnitaire");
                int quantiter = resultSet.getInt("quantiter"); // Utilisation de 'quantiter' pour correspondre à la BDD
                return new Produit(id, nom, quantiter, prix); // Correction constructeur
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
        }
        return null;
    }

    // Méthode pour récupérer les produits depuis la base de données
    public ArrayList<String> getProduitsFromBDD() {
        ArrayList<String> produits = new ArrayList<>();
        String query = "SELECT nom, prixUnitaire, quantiter FROM produit"; // Adapté selon la structure de ta BDD
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                double prix = resultSet.getDouble("prixUnitaire");
                int quantite = resultSet.getInt("quantiter");
                produits.add("Nom: " + nom + ", Prix: " + prix + ", Quantité: " + quantite);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
        }
        return produits;
    }
}