package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Produit;

public class ProduitDAO {
    public void ajouterProduit(Produit produit) {
        String query = "INSERT INTO produit (nom, quantiter, prixUnitaire) VALUES (?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produit.getNom());
            statement.setInt(2, produit.getQuantite());
            statement.setDouble(3, produit.getPrixUnitaire());
            statement.executeUpdate();
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
}