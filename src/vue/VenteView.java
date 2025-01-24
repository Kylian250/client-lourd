package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VenteView extends JFrame {
    private Utilisateur utilisateur;

    public VenteView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
   
        setTitle("Gestion des Ventes");
        setLayout(new FlowLayout());
        setSize(400, 200);

        JButton ajouterVenteButton = new JButton("Ajouter Vente");
        JButton modifierVenteButton = new JButton("Modifier Vente");
        JButton supprimerVenteButton = new JButton("Supprimer Vente");

        ajouterVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour ajouter un produit
            }
        });

        modifierVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un produit
            }
        });

        supprimerVenteButton.addActionListener(new ActionListener() {
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
            supprimerVenteButton.setEnabled(false); // Grise le bouton
        }

        // Ajouter les boutons à la fenêtre
        add(ajouterVenteButton);
        add(modifierVenteButton);
        add(supprimerVenteButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation avec un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur("manager"); // Remplacer par un utilisateur réel
        new VenteView(utilisateur);
    }
}