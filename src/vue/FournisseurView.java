package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;

public class FournisseurView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtNom, txtAdresse, txtTelephone;
    private JButton btnAjouterFournisseur;

    public FournisseurView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        setTitle("Gestion des Fournisseurs");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajoute des marges
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel labelNom = new JLabel("Nom du fournisseur :");
        txtNom = new JTextField(20);
        JLabel labelAdresse = new JLabel("Adresse du fournisseur :");
        txtAdresse = new JTextField(20);
        JLabel labelTelephone = new JLabel("Téléphone du fournisseur :");
        txtTelephone = new JTextField(20);
        btnAjouterFournisseur = new JButton("Ajouter Fournisseur");

        // Ajout des éléments avec positionnement structuré
        add(labelNom, gbc);
        gbc.gridy++;
        add(txtNom, gbc);
        gbc.gridy++;
        add(labelAdresse, gbc);
        gbc.gridy++;
        add(txtAdresse, gbc);
        gbc.gridy++;
        add(labelTelephone, gbc);
        gbc.gridy++;
        add(txtTelephone, gbc);
        gbc.gridy++;
        add(btnAjouterFournisseur, gbc);

        setVisible(true);
    }

    public String getNom() {
        return txtNom.getText();
    }

    public String getAdresse() {
        return txtAdresse.getText();
    }

    public String getTelephone() {
        return txtTelephone.getText();
    }

    public void setAjouterFournisseurListener(ActionListener listener) {
        btnAjouterFournisseur.addActionListener(listener);
    }
}