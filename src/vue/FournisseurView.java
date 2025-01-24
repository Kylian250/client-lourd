package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FournisseurView extends JFrame {
    private Utilisateur utilisateur;

    public FournisseurView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
   
        setTitle("Gestion des Fournisseurs");
        setLayout(new FlowLayout());
        setSize(400, 200);

        JButton ajouterFournisseurButton = new JButton("Ajouter Fournisseur");
        JButton modifierFournisseurButton = new JButton("Modifier Fournisseur");
        JButton supprimerFournisseurButton = new JButton("Supprimer Fournisseur");

        ajouterFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour ajouter un produit
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
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                } else {
                    // Code pour supprimer un produit
                }
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerFournisseurButton.setEnabled(false); // Grise le bouton
        }

        // Ajouter les boutons à la fenêtre
        add(ajouterFournisseurButton);
        add(modifierFournisseurButton);
        add(supprimerFournisseurButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation avec un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur("manager"); // Remplacer par un utilisateur réel
        new FournisseurView(utilisateur);
    }
}