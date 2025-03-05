package vue;

import java.awt.event.ActionListener;

import modele.Produit;
import modele.Utilisateur;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controleur.ProduitController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

public class GestionProduitView {
    private Utilisateur utilisateur;

    private JFrame frame;

    GestionProduitView(Utilisateur utilisateur) {

        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des produits");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JButton ajouterProduitButton = new JButton("Ajouter Produit");
        JButton modifierProduitButton = new JButton("Modifier Produit");
        JButton supprimerProduitButton = new JButton("Supprimer Produit");
        JButton afficherProduitsButton = new JButton("Afficher Liste Produits");

        ajouterProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProduitController(new ProduitView(utilisateur),new ProduitDAO(),new VenteDAO());
            }
        });

        modifierProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un produit
            }
        });

        supprimerProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateur.getRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                } else {
                    // Demande du nom du produit à l'utilisateur
                    String nom = JOptionPane.showInputDialog("Entrez le nom du produit à supprimer :");
        
                    // Vérification que le champ n'est pas vide ou null
                    if (nom != null && !nom.trim().isEmpty()) {
                        ProduitDAO produitDAO = new ProduitDAO();
                        produitDAO.supprimerProduit(nom);
        
                        JOptionPane.showMessageDialog(null, "Produit supprimé");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le nom du produit ne peut pas être vide.");
                    }
                }
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerProduitButton.setEnabled(false); // Grise le bouton
        }

        // Action pour afficher la liste des produits
        afficherProduitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer la liste des produits depuis la base de données
                ProduitDAO produitDAO = new ProduitDAO();
                List<Produit> produits = produitDAO.getAllProduits();
                
                // Afficher la liste dans une fenêtre pop-up ou autre
                StringBuilder listeProduits = new StringBuilder();
                for (Produit produit : produits) {
                    listeProduits.append("Nom: " + produit.getNom() + ", Prix: " + produit.getPrixUnitaire() + ", Quantité: " + produit.getQuantite() + "\n");
                }
                
                // Afficher la liste dans un JOptionPane
                JOptionPane.showMessageDialog(null, listeProduits.toString(), "Liste des Produits", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ajouter les boutons à la fenêtre
        frame.add(ajouterProduitButton);
        frame.add(modifierProduitButton);
        frame.add(supprimerProduitButton);
        frame.add(afficherProduitsButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}