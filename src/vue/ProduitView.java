package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Produit;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import modele.DAO.FournisseurDAO;
import utils.WindowManager;
import modele.Fournisseur;
import components.RetourButton;

public class ProduitView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtNom, txtPrix, txtQuantiter, txtQteMax, txtQteAlert;
    private JButton btnAction;
    private JComboBox<String> comboFournisseurs;
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

        // Ajouter le bouton retour en haut
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        buttonPanel.add(btnRetour);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Décaler les autres composants d'une ligne vers le bas
        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel labelNom = new JLabel("Nom du produit:");
        txtNom = new JTextField(20);
        JLabel labelPrix = new JLabel("Prix:");
        txtPrix = new JTextField(20);
        JLabel labelQuantiter = new JLabel("Quantité:");
        txtQuantiter = new JTextField(20);
        JLabel labelQteMax = new JLabel("Quantité maximum:");
        txtQteMax = new JTextField(20);
        JLabel labelQteAlert = new JLabel("Quantité alerte:");
        txtQteAlert = new JTextField(20);
        JLabel labelFournisseur = new JLabel("Fournisseur:");
        comboFournisseurs = new JComboBox<>();
        btnAction = new JButton(isModificationMode ? "Modifier le produit" : "Ajouter le produit");

        // Pré-remplir les champs si en mode modification
        if (isModificationMode) {
            txtNom.setText(produit.getNom());
            txtPrix.setText(String.valueOf(produit.getPrixUnitaire()));
            txtQuantiter.setText(String.valueOf(produit.getQuantite()));
            txtQteMax.setText(String.valueOf(produit.getQteMax()));
            txtQteAlert.setText(String.valueOf(produit.getQteAlert()));
            selectFournisseur(produit.getIdFournisseur());
            txtNom.setEnabled(false); // On ne permet pas de modifier le nom
        }

        // Remplir la combobox avec les fournisseurs
        loadFournisseurs();

        gbc.gridx = 0; gbc.gridy = 1;
        add(labelNom, gbc);
        gbc.gridx = 1;
        add(txtNom, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(labelPrix, gbc);
        gbc.gridx = 1;
        add(txtPrix, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(labelQuantiter, gbc);
        gbc.gridx = 1;
        add(txtQuantiter, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(labelQteMax, gbc);
        gbc.gridx = 1;
        add(txtQteMax, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(labelQteAlert, gbc);
        gbc.gridx = 1;
        add(txtQteAlert, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(labelFournisseur, gbc);
        gbc.gridx = 1;
        add(comboFournisseurs, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        add(btnAction, gbc);

        WindowManager.switchWindow(null, this);
    }

    private void loadFournisseurs() {
        FournisseurDAO fournisseurDAO = new FournisseurDAO();
        List<Fournisseur> fournisseurs = fournisseurDAO.getAllFournisseurs();
        for (Fournisseur f : fournisseurs) {
            comboFournisseurs.addItem(f.getName() + " (ID: " + f.getId_fournisseur() + ")");
        }
    }

    private void selectFournisseur(int id_fournisseur) {
        for (int i = 0; i < comboFournisseurs.getItemCount(); i++) {
            if (comboFournisseurs.getItemAt(i).contains("ID: " + id_fournisseur + ")")) {
                comboFournisseurs.setSelectedIndex(i);
                break;
            }
        }
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

    public int getQteMax() {
        return Integer.parseInt(txtQteMax.getText());
    }

    public int getQteAlert() {
        return Integer.parseInt(txtQteAlert.getText());
    }

    public int getIdFournisseur() {
        String selected = (String) comboFournisseurs.getSelectedItem();
        return Integer.parseInt(selected.substring(selected.indexOf("ID: ") + 4, selected.indexOf(")")));
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