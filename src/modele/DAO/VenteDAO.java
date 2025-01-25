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
        String query = "INSERT INTO vente (quantiter, date_vente, id_produit) VALUES (?, ?, ?)";
        try (Connection connection = Connexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vente.getQuantiter());
            statement.setDate(2, vente.getDate());
            statement.setInt(3, vente.getId_produit());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
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


    public List<Vente> getVentesByProduitAndDateRange(int produitId, Date startDate, Date endDate) {
        List<Vente> ventes = new ArrayList<>();
        String query = "SELECT * FROM ventes WHERE produit_id = ? AND date_vente BETWEEN ? AND ?";
        try (Connection connection = Connexion.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, produitId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date_vente");
                int quantite = resultSet.getInt("quantite");
                Produit produit = new Produit(produitId, "", 0); // Produits déjà en base
                ventes.add(new Vente(id, date, quantite, produit));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes : " + e.getMessage());
        }
        return ventes;
    }
}
