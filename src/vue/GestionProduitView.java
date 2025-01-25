package vue;

import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controleur.ProduitController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class GestionProduitView {
    private Utilisateur utilisateur;

    private JFrame frame;

    GestionProduitView(Utilisateur utilisateur) {

        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des produits");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        JButton ajouterProduitButton = new JButton("Ajouter Produit");
        JButton modifierProduitButton = new JButton("Modifier Produit");
        JButton supprimerProduitButton = new JButton("Supprimer Produit");

        ajouterProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProduitController(new ProduitView(utilisateur),new ProduitDAO(),new VenteDAO());
            }
        });

        modifierProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour ajouter un produit
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
        frame.add(ajouterProduitButton);
        frame.add(modifierProduitButton);
        frame.add(supprimerProduitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
