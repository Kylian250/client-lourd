package vue;

import javax.swing.*;
import modele.Utilisateur;
import utils.WindowManager;
import modele.Fournisseur;
import java.awt.*;
import java.awt.event.ActionListener;
import components.RetourButton;

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
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Bouton retour
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        topPanel.add(btnRetour);

        // Champs avec tooltips
        JLabel labelNom = new JLabel("Nom du fournisseur :");
        txtNom = new JTextField(20);
        txtNom.setToolTipText("Entrez le nom du fournisseur");

        JLabel labelAdresse = new JLabel("Adresse :");
        txtAdresse = new JTextField(20);
        txtAdresse.setToolTipText("Entrez l'adresse complète");

        JLabel labelTelephone = new JLabel("Téléphone :");
        txtTelephone = new JTextField(20);
        txtTelephone.setToolTipText("Format: XX XX XX XX XX");

        // Pré-remplir les champs si en mode modification
        if (isModificationMode) {
            txtNom.setText(fournisseur.getName());
            txtAdresse.setText(fournisseur.getAddress());
            txtTelephone.setText(fournisseur.getPhone());
            txtNom.setEnabled(false); // On ne permet pas de modifier le nom
        }

        // Organisation des composants
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(topPanel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        addFormField(mainPanel, labelNom, txtNom, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelAdresse, txtAdresse, gbc);
        gbc.gridy++;
        addFormField(mainPanel, labelTelephone, txtTelephone, gbc);

        // Bouton d'action
        btnAction = new JButton(isModificationMode ? "Modifier le fournisseur" : "Ajouter le fournisseur");
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