package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.DAO.ProduitDAO;
import modele.DAO.CommandeDAO;
import modele.Produit;
import modele.Utilisateur;
import controleur.CommandeController;

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

        modeleListe = new DefaultListModel<>();
        listeProduits = new JList<>(modeleListe);
        
        JPanel panelQuantite = new JPanel(new FlowLayout());
        JLabel lblQuantite = new JLabel("Quantité à commander:");
        txtQuantite = new JTextField(10);
        panelQuantite.add(lblQuantite);
        panelQuantite.add(txtQuantite);
        
        JButton commanderButton = new JButton("Commander");

        setLayout(new BorderLayout());
        add(new JScrollPane(listeProduits), BorderLayout.CENTER);
        add(panelQuantite, BorderLayout.NORTH);
        add(commanderButton, BorderLayout.SOUTH);

        // Initialiser le contrôleur
        this.controller = new CommandeController(this, new CommandeDAO(), new ProduitDAO());

        chargerProduitsAlerte();

        commanderButton.addActionListener(e -> {
            if (listeProduits.getSelectedValue() != null) {
                try {
                    int quantite = Integer.parseInt(txtQuantite.getText());
                    String produitInfo = listeProduits.getSelectedValue();
                    int idProduit = Integer.parseInt(produitInfo.substring(
                        produitInfo.lastIndexOf("ID: ") + 4,
                        produitInfo.lastIndexOf(")")));
                    
                    // Utiliser le contrôleur pour valider la commande
                    controller.validerCommande(idProduit, quantite);
                    dispose(); // Fermer la fenêtre après la commande réussie
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

        setVisible(true);
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
