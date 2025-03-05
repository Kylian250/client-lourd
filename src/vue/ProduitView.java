package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

public class ProduitView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtNom, txtPrix, txtQuantiter;
    private JButton btnAjouterProduit;


    public ProduitView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        setTitle("Gestion des produits");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Utilisation de GridBagLayout pour un alignement propre
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marge entre les éléments
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNom = new JLabel("Nom du produit:");
        txtNom = new JTextField(20);
        JLabel labelPrix = new JLabel("Prix:");
        txtPrix = new JTextField(20);
        JLabel labelQuantiter = new JLabel("Quantité:");
        txtQuantiter = new JTextField(20);
        btnAjouterProduit = new JButton("Ajouter Produit");


        


        // Ajout des éléments avec positionnement précis
        gbc.gridx = 0; gbc.gridy = 0;
        add(labelNom, gbc);
        gbc.gridx = 1; 
        add(txtNom, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(labelPrix, gbc);
        gbc.gridx = 1; 
        add(txtPrix, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(labelQuantiter, gbc);
        gbc.gridx = 1; 
        add(txtQuantiter, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; // Le bouton prend 2 colonnes
        add(btnAjouterProduit, gbc);

        setVisible(true);
    }

    public String getNomProduit() {
        return txtNom.getText();
    }

    public double getPrixProduit() {
        return Double.parseDouble(txtPrix.getText());
    }

    public int getQuantiterProduit() {
        return Integer.parseInt(txtQuantiter.getText());
    }

    public void setAjouterProduitListener(ActionListener listener) {
        btnAjouterProduit.addActionListener(listener);
    }

}