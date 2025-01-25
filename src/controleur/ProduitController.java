package controleur;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.vue.setAjouterProduitListener(e -> {
            String nom = vue.getNomProduit();
            double prix = vue.getPrixProduit();
            int quantiter = vue.getQuantiterProduit(); // Utilisation de 'quantiter' pour correspondre à la BDD
            Produit produit = new Produit(nom, quantiter, prix); // L'id sera généré par la DB
            produitDAO.ajouterProduit(produit);
            JOptionPane.showMessageDialog(null, "Produit ajouté !");
        });
    }

    public void genererRapport(String produitNom, Date startDate, Date endDate) {
        Produit produit = produitDAO.getProduitByNom(produitNom);
        if (produit != null) {
            // Les dates sont déjà au bon format
            Date start = startDate;
            Date end = endDate;

            // Récupérer les ventes pour le produit et la période
            List<Vente> ventes = venteDAO.getVentesByProduitAndDateRange(produit.getId_produit(), start, end);

            // Calculer les ventes totales
            int ventesTotales = 0;
            for (Vente vente : ventes) {
                ventesTotales += vente.getQuantiter(); // Utilisation de 'quantiter'
            }

            // Afficher le rapport
            vue.afficherRapport(produitNom, produit.getPrixUnitaire(), ventesTotales);
        } else {
            JOptionPane.showMessageDialog(null, "Produit non trouvé !");
        }
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
}