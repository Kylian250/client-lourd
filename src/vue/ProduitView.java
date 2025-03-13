package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Produit;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProduitView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtNom, txtPrix, txtQuantiter;
    private JButton btnAction;
    private boolean isModificationMode = false;
    private Produit produitAModifier;

    public ProduitView(Utilisateur utilisateur) {
        this(utilisateur, null);
    }

    public ProduitView(Utilisateur utilisateur, Produit produit) {
        this.utilisateur = utilisateur;
        this.produitAModifier = produit;
        this.isModificationMode = (produit != null);

        setTitle(isModificationMode ? "Modifier un produit" : "Ajouter un produit");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNom = new JLabel("Nom du produit:");
        txtNom = new JTextField(20);
        JLabel labelPrix = new JLabel("Prix:");
        txtPrix = new JTextField(20);
        JLabel labelQuantiter = new JLabel("Quantité:");
        txtQuantiter = new JTextField(20);
        btnAction = new JButton(isModificationMode ? "Modifier le produit" : "Ajouter le produit");

        // Pré-remplir les champs si en mode modification
        if (isModificationMode) {
            txtNom.setText(produit.getNom());
            txtPrix.setText(String.valueOf(produit.getPrixUnitaire()));
            txtQuantiter.setText(String.valueOf(produit.getQuantite()));
            txtNom.setEnabled(false); // On ne permet pas de modifier le nom
        }

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

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnAction, gbc);

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

    public void setActionListener(ActionListener listener) {
        btnAction.addActionListener(listener);
    }

    public boolean isModificationMode() {
        return isModificationMode;
    }

    public Produit getProduitAModifier() {
        return produitAModifier;
    }
}