package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modele.Produit;

public class ProduitDAO {
    public void ajouterProduit(Produit produit) {
        String query = "INSERT INTO produit (nom, quantite, prix_unitaire) VALUES (?, ?, ?)";
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
}