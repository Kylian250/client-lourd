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
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Connexion.getConnection();
            connection.setAutoCommit(false); // Début de la transaction

            // 1. Mettre à jour le stock du produit
            String updateProduit = "UPDATE produit SET quantiter = quantiter - ? WHERE id_produit = ?";
            statement = connection.prepareStatement(updateProduit);
            statement.setInt(1, vente.getQuantite());
            statement.setInt(2, vente.getId_produit());
            statement.executeUpdate();

            // 2. Ajouter la vente
            String insertVente = "INSERT INTO vente (date_vente, id_produit, quantite) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(insertVente);
            statement.setDate(1, vente.getDate());
            statement.setInt(2, vente.getId_produit());
            statement.setInt(3, vente.getQuantite());
            statement.executeUpdate();

            connection.commit(); // Valider la transaction
        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback(); // Annuler en cas d'erreur
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Erreur lors de l'ajout de la vente: " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = Connexion.getConnection();
            connection.setAutoCommit(false); // Début de la transaction

            // 1. Récupérer l'ancienne vente pour ajuster le stock
            Vente ancienneVente = getVenteById(vente.getId_vente());
            if (ancienneVente != null) {
                // Restaurer l'ancien stock
                String updateAncienStock = "UPDATE produit SET quantiter = quantiter + ? WHERE id_produit = ?";
                statement = connection.prepareStatement(updateAncienStock);
                statement.setInt(1, ancienneVente.getQuantite());
                statement.setInt(2, ancienneVente.getId_produit());
                statement.executeUpdate();
            }

            // 2. Mettre à jour le nouveau stock
            String updateNouveauStock = "UPDATE produit SET quantiter = quantiter - ? WHERE id_produit = ?";
            statement = connection.prepareStatement(updateNouveauStock);
            statement.setInt(1, vente.getQuantite());
            statement.setInt(2, vente.getId_produit());
            statement.executeUpdate();

            // 3. Mettre à jour la vente
            String updateVente = "UPDATE vente SET date_vente = ?, id_produit = ?, quantite = ? WHERE id_vente = ?";
            statement = connection.prepareStatement(updateVente);
            statement.setDate(1, vente.getDate());
            statement.setInt(2, vente.getId_produit());
            statement.setInt(3, vente.getQuantite());
            statement.setInt(4, vente.getId_vente());
            statement.executeUpdate();

            connection.commit(); // Valider la transaction
            System.out.println("Vente modifiée avec succès.");
            
        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback(); // Annuler en cas d'erreur
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Erreur lors de la modification de la vente : " + e.getMessage());
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                int quantite = resultSet.getInt("quantite"); // Ajout de la quantité
                Produit produit = new ProduitDAO().getProduitById(id_produit);
                return new Vente(id, id_produit, date_vente, produit, quantite); // Ajout de la quantité
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la vente : " + e.getMessage());
        }
        return null;
    }

    public List<Vente> getAllVentes() {
        List<Vente> ventes = new ArrayList<>();
        String query = "SELECT v.*, p.* FROM vente v JOIN produit p ON v.id_produit = p.id_produit";
        
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                Produit produit = new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("nom"),
                    rs.getInt("quantiter"),
                    rs.getDouble("prixUnitaire")
                );
                
                Vente vente = new Vente(
                    rs.getInt("id_vente"),
                    rs.getInt("id_produit"),
                    rs.getDate("date_vente"),
                    produit,
                    rs.getInt("quantite")
                );
                
                ventes.add(vente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des ventes: " + e.getMessage());
        }
        
        return ventes;
    }
}
