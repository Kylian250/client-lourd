package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.DAO.ProduitDAO;
import utils.WindowManager;
import modele.DAO.CommandeDAO;
import modele.Produit;
import modele.Utilisateur;
import controleur.CommandeController;
import components.RetourButton;

public class GestionCommandeView extends JFrame {
    private Utilisateur utilisateur;
    private JList<String> listeProduits;
    private DefaultListModel<String> modeleListe;
    private JTextField txtQuantite;
    private CommandeController controller;

    public GestionCommandeView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        setTitle("Gestion des Commandes");
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Panel principal avec GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Bouton retour en haut
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        buttonPanel.add(btnRetour);

        // Panel pour la quantité
        JPanel panelQuantite = new JPanel(new FlowLayout());
        JLabel lblQuantite = new JLabel("Quantité à commander:");
        txtQuantite = new JTextField(10);
        panelQuantite.add(lblQuantite);
        panelQuantite.add(txtQuantite);

        // Liste des produits
        modeleListe = new DefaultListModel<>();
        listeProduits = new JList<>(modeleListe);
        JScrollPane scrollPane = new JScrollPane(listeProduits);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        // Bouton commander
        JButton commanderButton = new JButton("Commander");

        // Ajout des composants avec GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(buttonPanel, gbc);

        gbc.gridy = 1;
        mainPanel.add(panelQuantite, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(commanderButton, gbc);

        // Ajout du panel principal
        add(mainPanel);

        // Initialiser le contrôleur
        this.controller = new CommandeController(this, new CommandeDAO(), new ProduitDAO());

        // Charger les produits
        chargerProduitsAlerte();

        // Action du bouton commander
        commanderButton.addActionListener(e -> {
            if (listeProduits.getSelectedValue() != null) {
                try {
                    int quantite = Integer.parseInt(txtQuantite.getText());
                    String produitInfo = listeProduits.getSelectedValue();
                    int idProduit = Integer.parseInt(produitInfo.substring(
                        produitInfo.lastIndexOf("ID: ") + 4,
                        produitInfo.lastIndexOf(")")));
                    
                    controller.validerCommande(idProduit, quantite);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Veuillez entrer une quantité valide",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner un produit",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        WindowManager.switchWindow(null, this);
    }

    private void chargerProduitsAlerte() {
        ProduitDAO produitDAO = new ProduitDAO();
        List<Produit> produits = produitDAO.getAllProduits();
        
        for (Produit p : produits) {
            modeleListe.addElement(p.getNom() + 
                " (Quantité: " + p.getQuantite() + 
                ", Alerte: " + p.getQteAlert() + 
                ", Max: " + p.getQteMax() + 
                ", ID: " + p.getId_produit() + ")");
        }
    }
}
