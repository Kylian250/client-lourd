package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Fournisseur> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String query = "SELECT * FROM fournisseur";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("nom");
                String address = resultSet.getString("adress");
                String phone = resultSet.getString("telephone");
                fournisseurs.add(new Fournisseur(name, address, phone));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des fournisseurs : " + e.getMessage());
        }
        return fournisseurs;
    }

    public void modifierFournisseur(Fournisseur fournisseur) {
        String query = "UPDATE fournisseur SET adress = ?, telephone = ? WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fournisseur.getAddress());
            statement.setString(2, fournisseur.getPhone());
            statement.setString(3, fournisseur.getName());
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fournisseur modifié avec succès.");
            } else {
                System.out.println("Aucun fournisseur trouvé avec ce nom.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du fournisseur : " + e.getMessage());
        }
    }

    public Fournisseur getFournisseurByNom(String nom) {
        String query = "SELECT * FROM fournisseur WHERE nom = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String address = resultSet.getString("adress");
                String telephone = resultSet.getString("telephone");
                return new Fournisseur(nom, address, telephone);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du fournisseur : " + e.getMessage());
        }
        return null;
    }
}

