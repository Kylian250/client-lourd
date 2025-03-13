package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Fournisseur;
import java.awt.*;
import java.awt.event.ActionListener;

public class FournisseurView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtNom, txtAdresse, txtTelephone;
    private JButton btnAction;
    private boolean isModificationMode = false;
    private Fournisseur fournisseurAModifier;

    public FournisseurView(Utilisateur utilisateur) {
        this(utilisateur, null);
    }

    public FournisseurView(Utilisateur utilisateur, Fournisseur fournisseur) {
        this.utilisateur = utilisateur;
        this.fournisseurAModifier = fournisseur;
        this.isModificationMode = (fournisseur != null);

        setTitle(isModificationMode ? "Modifier un fournisseur" : "Ajouter un fournisseur");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0;

        JLabel labelNom = new JLabel("Nom du fournisseur :");
        txtNom = new JTextField(20);
        JLabel labelAdresse = new JLabel("Adresse du fournisseur :");
        txtAdresse = new JTextField(20);
        JLabel labelTelephone = new JLabel("Téléphone du fournisseur :");
        txtTelephone = new JTextField(20);
        btnAction = new JButton(isModificationMode ? "Modifier le fournisseur" : "Ajouter le fournisseur");

        // Pré-remplir les champs si en mode modification
        if (isModificationMode) {
            txtNom.setText(fournisseur.getName());
            txtAdresse.setText(fournisseur.getAddress());
            txtTelephone.setText(fournisseur.getPhone());
            txtNom.setEnabled(false); // On ne permet pas de modifier le nom
        }

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
        add(btnAction, gbc);

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

    public void setActionListener(ActionListener listener) {
        btnAction.addActionListener(listener);
    }

    public boolean isModificationMode() {
        return isModificationMode;
    }
}