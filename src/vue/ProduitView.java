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
        setSize(500, 400); // Augmentation de la taille
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Augmentation des marges
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Bouton retour en haut
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        topPanel.add(btnRetour);

        // Champs avec des tooltips
        JLabel labelNom = new JLabel("Nom du produit :");
        txtNom = new JTextField(20);
        txtNom.setToolTipText("Entrez le nom du produit");

        JLabel labelPrix = new JLabel("Prix :");
        txtPrix = new JTextField(20);
        txtPrix.setToolTipText("Entrez le prix unitaire");

        JLabel labelQuantiter = new JLabel("Quantité :");
        txtQuantiter = new JTextField(20);
        txtQuantiter.setToolTipText("Entrez la quantité en stock");

        JLabel labelQteMax = new JLabel("Quantité maximum :");
        txtQteMax = new JTextField(20);
        txtQteMax.setToolTipText("Entrez la quantité maximale autorisée");

        JLabel labelQteAlert = new JLabel("Quantité alerte :");
        txtQteAlert = new JTextField(20);
        txtQteAlert.setToolTipText("Entrez le seuil d'alerte");

        JLabel labelFournisseur = new JLabel("Fournisseur :");
        comboFournisseurs = new JComboBox<>();
        comboFournisseurs.setToolTipText("Sélectionnez le fournisseur");

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

        // Ajout des composants avec une meilleure organisation
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(topPanel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        addFormField(mainPanel, labelNom, txtNom, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelPrix, txtPrix, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelQuantiter, txtQuantiter, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelQteMax, txtQteMax, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelQteAlert, txtQteAlert, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelFournisseur, comboFournisseurs, gbc);

        // Bouton d'action
        btnAction = new JButton(isModificationMode ? "Modifier le produit" : "Ajouter le produit");
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        mainPanel.add(btnAction, gbc);

        add(mainPanel);

        WindowManager.switchWindow(null, this);
    }

    private void addFormField(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        panel.add(label, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
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