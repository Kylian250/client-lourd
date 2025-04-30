package vue;

import javax.swing.*;
import modele.Utilisateur;
import utils.WindowManager;

import java.awt.*;
import java.awt.event.ActionListener;
import components.RetourButton;

public class MenuPrincipal extends JFrame {
    private Utilisateur utilisateur;

    public MenuPrincipal(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        setTitle("Menu Principal");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 40; // Augmente la largeur des boutons
        gbc.ipady = 10; // Augmente la hauteur des boutons

        // Création des boutons avec style
        JButton gestionProduitButton = createStyledButton("Gestion des produits");
        JButton gestionVenteButton = createStyledButton("Gestion des ventes");
        JButton gestionFournisseurButton = createStyledButton("Gestion des fournisseurs");
        JButton gestionCommandeButton = createStyledButton("Gestion des commandes");

        // Ajout des boutons avec espacement
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(gestionProduitButton, gbc);
        gbc.gridy++;
        mainPanel.add(gestionVenteButton, gbc);
        gbc.gridy++;
        mainPanel.add(gestionFournisseurButton, gbc);
        gbc.gridy++;
        mainPanel.add(gestionCommandeButton, gbc);

        // Listeners pour ouvrir les autres vues
        gestionProduitButton.addActionListener(e -> new GestionProduitView(utilisateur));
        gestionVenteButton.addActionListener(e -> new GestionVenteView(utilisateur));
        gestionFournisseurButton.addActionListener(e -> new GestionFournisseurView(utilisateur));
        gestionCommandeButton.addActionListener(e -> new GestionCommandeView(utilisateur));

        add(mainPanel);
        
        WindowManager.switchWindow(null, this);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }
}