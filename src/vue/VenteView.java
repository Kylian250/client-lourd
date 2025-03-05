package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class VenteView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtQuantiter, txtDate_vente, txtId_produit;
    private JButton btnAjouterVente;

    public VenteView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        setTitle("Gestion des Ventes");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrer la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Utilisation de GridBagLayout pour une meilleure organisation
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Ajout d'une marge entre les éléments
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelDate_vente = new JLabel("Date de vente (YYYY-MM-DD):");
        txtDate_vente = new JTextField(20);
        JLabel labelId_produit = new JLabel("ID du produit:");
        txtId_produit = new JTextField(20);
        btnAjouterVente = new JButton("Ajouter Vente");

        // Ajout des composants avec positionnement précis

        gbc.gridx = 0; gbc.gridy = 1;
        add(labelDate_vente, gbc);
        gbc.gridx = 1;
        add(txtDate_vente, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(labelId_produit, gbc);
        gbc.gridx = 1;
        add(txtId_produit, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnAjouterVente, gbc);

        setVisible(true);
    }


    public Date getDate_vente() {
        return Date.valueOf(txtDate_vente.getText());
    }

    public int getId_produit() {
        return Integer.parseInt(txtId_produit.getText());
    }

    public void setAjouterVenteListener(ActionListener listener) {
        btnAjouterVente.addActionListener(listener);
    }
}