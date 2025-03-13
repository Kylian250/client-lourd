package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private Utilisateur utilisateur;

    public MenuPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        setTitle("Menu Principal");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajoute des marges pour l'espacement
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        // Création des boutons
        JButton gestionProduitButton = new JButton("Gestion des produits");
        JButton gestionVenteButton = new JButton("Gestion des ventes");
        JButton gestionFournisseurButton = new JButton("Gestion des fournisseurs");

        // Ajout des boutons avec positionnement propre
        add(gestionProduitButton, gbc);
        gbc.gridy++;
        add(gestionVenteButton, gbc);
        gbc.gridy++;
        add(gestionFournisseurButton, gbc);
        
        

        // Listeners pour ouvrir les autres vues
        gestionProduitButton.addActionListener(e -> new GestionProduitView(utilisateur));
        gestionVenteButton.addActionListener(e -> new GestionVenteView(utilisateur));
        gestionFournisseurButton.addActionListener(e -> new GestionFournisseurView(utilisateur));

        // Désactivation des options en fonction du rôle

        setVisible(true); // Toujours à la fin pour éviter les bugs d'affichage
    }
}