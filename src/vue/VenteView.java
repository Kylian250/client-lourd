package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Vente;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class VenteView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtDate_vente, txtId_produit;
    private JButton btnAction;
    private boolean isModificationMode = false;
    private Vente venteAModifier;

    public VenteView(Utilisateur utilisateur) {
        this(utilisateur, null);
    }

    public VenteView(Utilisateur utilisateur, Vente vente) {
        this.utilisateur = utilisateur;
        this.venteAModifier = vente;
        this.isModificationMode = (vente != null);

        setTitle(isModificationMode ? "Modifier une vente" : "Ajouter une vente");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelDate_vente = new JLabel("Date de vente (YYYY-MM-DD):");
        txtDate_vente = new JTextField(20);
        JLabel labelId_produit = new JLabel("ID du produit:");
        txtId_produit = new JTextField(20);
        btnAction = new JButton(isModificationMode ? "Modifier la vente" : "Ajouter la vente");

        // Pré-remplir les champs si en mode modification
        if (isModificationMode) {
            txtDate_vente.setText(vente.getDate().toString());
            txtId_produit.setText(String.valueOf(vente.getId_produit()));
        }

        gbc.gridx = 0; gbc.gridy = 0;
        add(labelDate_vente, gbc);
        gbc.gridx = 1;
        add(txtDate_vente, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(labelId_produit, gbc);
        gbc.gridx = 1;
        add(txtId_produit, gbc);

        gbc.gridx = 0; gbc.gridy = 2; 
        gbc.gridwidth = 2;
        add(btnAction, gbc);

        setVisible(true);
    }

    public Date getDate_vente() {
        return Date.valueOf(txtDate_vente.getText());
    }

    public int getId_produit() {
        return Integer.parseInt(txtId_produit.getText());
    }

    public void setActionListener(ActionListener listener) {
        btnAction.addActionListener(listener);
    }

    public boolean isModificationMode() {
        return isModificationMode;
    }

    public Vente getVenteAModifier() {
        return venteAModifier;
    }
}