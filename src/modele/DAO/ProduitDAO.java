package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Produit;

public class ProduitDAO {
    public void ajouterProduit(Produit produit) {
        String query = "INSERT INTO produit (nom, quantiter, prixUnitaire, qte_max, qte_alert, id_fournisseur) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, produit.getNom());
            statement.setInt(2, produit.getQuantite());
            statement.setDouble(3, produit.getPrixUnitaire());
            statement.setInt(4, produit.getQteMax());
            statement.setInt(5, produit.getQteAlert());
            statement.setInt(6, produit.getIdFournisseur());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produit ajouté avec succès.");
            } else {
                System.out.println("Erreur : Le produit n'a pas été ajouté.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }

    public void supprimerProduit(String nom) {
        String query = "DELETE FROM produit WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nom);
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produit supprimé avec succès.");
            } else {
                System.out.println("Aucun produit trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du produit : " + e.getMessage());
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
                int quantiter = resultSet.getInt("quantiter");
                double prixUnitaire = resultSet.getDouble("prixUnitaire");

                // Retourner un objet Produit basé sur les données récupérées
                return new Produit(id, nom, quantiter, prixUnitaire);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
        }

        // Retourner null si le produit n'a pas été trouvé
        return null;
    }

    public Produit getProduitById(int id) {
        String query = "SELECT * FROM produit WHERE id_produit = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                int quantiter = resultSet.getInt("quantiter");
                double prixUnitaire = resultSet.getDouble("prixUnitaire");

                // Retourner un objet Produit basé sur les données récupérées
                return new Produit(id, nom, quantiter, prixUnitaire);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du produit : " + e.getMessage());
        }

        // Retourner null si le produit n'a pas été trouvé
        return null;
    }

    public List<Produit> getAllProduits() {
    List<Produit> produits = new ArrayList<>();
    String query = "SELECT * FROM produit";
    try (Connection connection = Connexion.getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id_produit");
            String nom = resultSet.getString("nom");
            int quantite = resultSet.getInt("quantiter");
            double prix = resultSet.getDouble("prixUnitaire");
            int qteMax = resultSet.getInt("qte_max");
            int qteAlert = resultSet.getInt("qte_alert");
            int idFournisseur = resultSet.getInt("id_fournisseur");
            
            // Log pour déboguer
            System.out.println("Lecture produit: " + nom + ", Quantité: " + quantite + ", Alerte: " + qteAlert);
            
            produits.add(new Produit(id, nom, quantite, prix, qteMax, qteAlert, idFournisseur));
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des produits : " + e.getMessage());
    }
    return produits;
}

public void modifierProduit(Produit produit) {
    String query = "UPDATE produit SET quantiter = ?, prixUnitaire = ? WHERE nom = ?";
    try (Connection connection = Connexion.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, produit.getQuantite());
        statement.setDouble(2, produit.getPrixUnitaire());
        statement.setString(3, produit.getNom());
        
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Produit modifié avec succès.");
        } else {
            System.out.println("Aucun produit trouvé avec ce nom.");
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification du produit : " + e.getMessage());
    }
}
}