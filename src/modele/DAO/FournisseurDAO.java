package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Fournisseur;

public class FournisseurDAO {
    public void ajouterFournisseur(Fournisseur fournisseur) {
        String query = "INSERT INTO fournisseur (nom, adress, telephone) VALUES (?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fournisseur.getName());
            statement.setString(2, fournisseur.getAddress());
            statement.setString(3, fournisseur.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur : " + e.getMessage());
        }
    }

    public void supprimerFournisseur(String nom){
        String query = "DELETE FROM fournisseur WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
           statement.setString(1, nom);

       int rowsAffected = statement.executeUpdate();
       if (rowsAffected > 0) {
           System.out.println("Fournisseur supprimé avec succès.");
       } else {
           System.out.println("Aucun fournisseur trouvé avec ce nom.");
       }
   } catch (SQLException e) {
       System.out.println("Erreur lors de la suppression du fournisseur : " + e.getMessage());
   }
    }
}

