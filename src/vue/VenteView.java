package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Vente;
import utils.WindowManager;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import components.RetourButton;

public class VenteView extends JFrame {
    private Utilisateur utilisateur;
    private JTextField txtDate_vente, txtId_produit, txtQuantite;
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
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Bouton retour
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        buttonPanel.add(btnRetour);

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Panel pour les champs de saisie
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcInput = new GridBagConstraints();
        gbcInput.insets = new Insets(5, 5, 5, 5);
        gbcInput.fill = GridBagConstraints.HORIZONTAL;

        // Création des composants avec des tooltips
        JLabel labelDate_vente = new JLabel("Date de vente :");
        txtDate_vente = new JTextField(20);
        txtDate_vente.setToolTipText("Format: YYYY-MM-DD");

        JLabel labelId_produit = new JLabel("ID du produit :");
        txtId_produit = new JTextField(20);
        txtId_produit.setToolTipText("Entrez l'identifiant numérique du produit");

        JLabel labelQuantite = new JLabel("Quantité :");
        txtQuantite = new JTextField(20);
        txtQuantite.setToolTipText("Entrez un nombre entier");

        // Ajout d'un texte d'aide en gris clair dans les champs
        txtDate_vente.setText("YYYY-MM-DD");
        txtDate_vente.setForeground(Color.GRAY);
        txtDate_vente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtDate_vente.getText().equals("YYYY-MM-DD")) {
                    txtDate_vente.setText("");
                    txtDate_vente.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtDate_vente.getText().isEmpty()) {
                    txtDate_vente.setText("YYYY-MM-DD");
                    txtDate_vente.setForeground(Color.GRAY);
                }
            }
        });

        // Organisation des composants
        gbcInput.gridx = 0; gbcInput.gridy = 0;
        inputPanel.add(labelDate_vente, gbcInput);
        gbcInput.gridx = 1;
        inputPanel.add(txtDate_vente, gbcInput);

        gbcInput.gridx = 0; gbcInput.gridy = 1;
        inputPanel.add(labelId_produit, gbcInput);
        gbcInput.gridx = 1;
        inputPanel.add(txtId_produit, gbcInput);

        gbcInput.gridx = 0; gbcInput.gridy = 2;
        inputPanel.add(labelQuantite, gbcInput);
        gbcInput.gridx = 1;
        inputPanel.add(txtQuantite, gbcInput);

        // Bouton d'action
        btnAction = new JButton(isModificationMode ? "Modifier la vente" : "Ajouter la vente");
        gbcInput.gridx = 0; 
        gbcInput.gridy = 3;
        gbcInput.gridwidth = 2;
        inputPanel.add(btnAction, gbcInput);

        // Ajout du panel de saisie
        gbc.gridy = 1;
        add(inputPanel, gbc);

        // Pré-remplir les champs en mode modification
        if (isModificationMode) {
            txtDate_vente.setText(vente.getDate().toString());
            txtDate_vente.setForeground(Color.BLACK);
            txtId_produit.setText(String.valueOf(vente.getId_produit()));
            txtQuantite.setText(String.valueOf(vente.getQuantite()));
        }

        WindowManager.switchWindow(null, this);
    }

    public Date getDate_vente() {
        String dateText = txtDate_vente.getText();
        if (dateText.equals("YYYY-MM-DD")) {
            throw new IllegalArgumentException("Veuillez entrer une date valide");
        }
        return Date.valueOf(dateText);
    }

    public int getId_produit() {
        return Integer.parseInt(txtId_produit.getText());
    }

    public int getQuantite() {
        try {
            return Integer.parseInt(txtQuantite.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La quantité doit être un nombre entier");
        }
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