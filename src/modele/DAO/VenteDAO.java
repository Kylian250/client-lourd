package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import modele.Produit;
import modele.Vente;

public class VenteDAO {
    public void ajouterVente(Vente vente) {
        String query = "INSERT INTO vente (date_vente, id_produit) VALUES (?, ?)";
        try (Connection connection = Connexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
        
            statement.setDate(1, vente.getDate());
            statement.setInt(2, vente.getId_produit());
    
            int rowsAffected = statement.executeUpdate();
            
            // Si aucune ligne n'a été affectée, cela signifie que l'insertion a échoué
            if (rowsAffected > 0) {
                System.out.println("Vente ajoutée avec succès.");
            } else {
                System.out.println("Erreur : La vente n'a pas été ajoutée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
            e.printStackTrace(); // Pour obtenir un détail plus précis de l'erreur
        }
    }
 
    public void supprimerVente(int id){
        String query = "DELETE FROM vente WHERE id_vente = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
    
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vente supprimé avec succès.");
            } else {
                System.out.println("Aucune vente trouvé avec cette id.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la vente : " + e.getMessage());
        }
    }

    public void modifierVente(Vente vente) {
        String query = "UPDATE vente SET date_vente = ?, id_produit = ? WHERE id_vente = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, vente.getDate());
            statement.setInt(2, vente.getId_produit());
            statement.setInt(3, vente.getId_vente());
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Vente modifiée avec succès.");
            } else {
                System.out.println("Aucune vente trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la vente : " + e.getMessage());
        }
    }

    public Vente getVenteById(int id) {
        String query = "SELECT * FROM vente WHERE id_vente = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id_produit = resultSet.getInt("id_produit");
                Date date_vente = resultSet.getDate("date_vente");
                Produit produit = new ProduitDAO().getProduitById(id_produit);
                return new Vente(id, id_produit, date_vente, produit);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la vente : " + e.getMessage());
        }
        return null;
    }

    public List<Vente> getAllVentes() {
        List<Vente> ventes = new ArrayList<>();
        String query = "SELECT * FROM vente"; // Requête SQL pour récupérer toutes les ventes
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
    
            while (resultSet.next()) {
                int id_vente = resultSet.getInt("id_vente");
                int id_produit = resultSet.getInt("id_produit");
                Date date_vente = resultSet.getDate("date_vente");
    
                // Crée l'objet Produit (tu peux récupérer plus de détails sur le produit si nécessaire)
                Produit produit = new Produit(id_produit, "", 0); // Produit simplifié avec seulement l'id
                ventes.add(new Vente(id_vente, id_produit, date_vente, produit));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes : " + e.getMessage());
        }
        return ventes;
    }
}
