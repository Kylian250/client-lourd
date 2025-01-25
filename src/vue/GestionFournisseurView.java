package vue;

import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.FournisseurDAO;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controleur.FournisseurController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

public class GestionFournisseurView {
    private Utilisateur utilisateur;

    private JFrame frame;

    GestionFournisseurView(Utilisateur utilisateur){
        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des Fournisseurs");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        JButton ajouterFournisseurButton = new JButton("Ajouter Fournisseur");
        JButton modifierFournisseurButton = new JButton("Modifier Fournisseur");
        JButton supprimerFournisseurButton = new JButton("Supprimer Fournisseur");

        ajouterFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FournisseurController(new FournisseurView(utilisateur), new FournisseurDAO());
            }
        });

        modifierFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un produit
            }
        });

        supprimerFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateur.getRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des fournisseurs.");
                } else {
                    // Demande du nom du produit à l'utilisateur
                    String nom = JOptionPane.showInputDialog("Entrez le nom du fournisseur à supprimer :");
        
                    // Vérification que le champ n'est pas vide ou null
                    if (nom != null && !nom.trim().isEmpty()) {
                        FournisseurDAO fournisseurDAO = new FournisseurDAO();
                        fournisseurDAO.supprimerFournisseur(nom);
        
                        JOptionPane.showMessageDialog(null, "Fournisseur supprimé");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne peut pas être vide.");
                    }
                }
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerFournisseurButton.setEnabled(false); // Grise le bouton
        }

        frame.add(ajouterFournisseurButton);
        frame.add(modifierFournisseurButton);
        frame.add(supprimerFournisseurButton);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    

    }
    
}