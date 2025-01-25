package modele.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.Utilisateur;

public class Connexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_de_stock";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    

    private Connexion() {

    }

    public static Connection getConnection() {
        
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
                return null;
            }
       
    }

     public static Utilisateur verifierConnexion(String nom, String motDePasse) {
        Utilisateur utilisateur = null; // Initialiser l'objet Utilisateur à null
        String sql = "SELECT * FROM utilisateurs WHERE nom = ? AND mot_de_passe = ?";

        try (Connection conn = Connexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Paramétrer les valeurs dans la requête
            statement.setString(1, nom);
            statement.setString(2, motDePasse);

            // Exécuter la requête et analyser les résultats
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Récupérer les données de l'utilisateur
                int id = resultSet.getInt("id");
                String role = resultSet.getString("role");
                utilisateur = new Utilisateur(id, nom, motDePasse, role);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de la connexion : " + e.getMessage());
        }

        return utilisateur; // Retourner l'utilisateur ou null si non trouvé
    }
}
