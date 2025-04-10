package modele.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Commande;

public class CommandeDAO {
    public void ajouterCommande(int id_produit, int quantite) {
        Connection connection = null;
        try {
            connection = Connexion.getConnection();
            connection.setAutoCommit(false); // Début de la transaction

            // 1. Ajouter la commande
            String queryCommande = "INSERT INTO commande (id_produit, quantite) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(queryCommande)) {
                statement.setInt(1, id_produit);
                statement.setInt(2, quantite);
                statement.executeUpdate();
            }

            // 2. Mettre à jour la quantité du produit
            String queryUpdateProduit = "UPDATE produit SET quantiter = quantiter + ? WHERE id_produit = ?";
            try (PreparedStatement statement = connection.prepareStatement(queryUpdateProduit)) {
                statement.setInt(1, quantite);
                statement.setInt(2, id_produit);
                statement.executeUpdate();
            }

            connection.commit(); // Valider la transaction
            System.out.println("Commande ajoutée et stock mis à jour avec succès.");

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Annuler la transaction en cas d'erreur
                }
            } catch (SQLException ex) {
                System.out.println("Erreur lors du rollback : " + ex.getMessage());
            }
            System.out.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    public void supprimerCommande(int id_commande) {
        String query = "DELETE FROM commande WHERE id_commande = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, id_commande);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Commande supprimée avec succès.");
            } else {
                System.out.println("Aucune commande trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la commande : " + e.getMessage());
        }
    }

    public List<Commande> getAllCommandes() {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM commande";
        
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                int id_commande = resultSet.getInt("id_commande");
                int id_produit = resultSet.getInt("id_produit");
                int quantite = resultSet.getInt("quantite");
                
                commandes.add(new Commande(id_commande, id_produit, quantite));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des commandes : " + e.getMessage());
        }
        
        return commandes;
    }

    public Commande getCommandeById(int id_commande) {
        String query = "SELECT * FROM commande WHERE id_commande = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, id_commande);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id_produit = resultSet.getInt("id_produit");
                int quantite = resultSet.getInt("quantite");
                return new Commande(id_commande, id_produit, quantite);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la commande : " + e.getMessage());
        }
        return null;
    }
}
