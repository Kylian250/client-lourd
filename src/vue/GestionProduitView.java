package vue;

import java.awt.event.ActionListener;

import modele.Produit;
import modele.Utilisateur;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import utils.WindowManager;
import components.RetourButton;
import controleur.ProduitController;  // Ajout de cet import

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

public class GestionProduitView {
    private Utilisateur utilisateur;

    private JFrame frame;

    GestionProduitView(Utilisateur utilisateur) {

        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des produits");
        frame.setLayout(new BorderLayout(10, 10)); // Ajout de marges
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        // Création du panel pour le bouton retour
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(frame, utilisateur);
        topPanel.add(btnRetour);

        // Création du panel principal pour les boutons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 lignes, 1 colonne, gaps de 10px
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Marges internes

        JButton ajouterProduitButton = new JButton("Ajouter un produit");
        JButton modifierProduitButton = new JButton("Modifier un produit");
        JButton supprimerProduitButton = new JButton("Supprimer un produit");
        JButton afficherProduitsButton = new JButton("Afficher la liste des produits");

        ajouterProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProduitView vue = new ProduitView(utilisateur);
                new ProduitController(vue, new ProduitDAO(), new VenteDAO());
                WindowManager.switchWindow(frame, vue);
            }
        });

        modifierProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Entrez le nom du produit à modifier :");
                if (nom != null && !nom.trim().isEmpty()) {
                    ProduitDAO produitDAO = new ProduitDAO();
                    Produit produit = produitDAO.getProduitByNom(nom);
                    if (produit != null) {
                        ProduitView vue = new ProduitView(utilisateur, produit);
                        new ProduitController(vue, produitDAO, new VenteDAO());
                        WindowManager.switchWindow(frame, vue);
                    } else {
                        JOptionPane.showMessageDialog(null, "Produit non trouvé.");
                    }
                }
            }
        });

        supprimerProduitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateur.getRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                } else {
                    // Demande du nom du produit à l'utilisateur
                    String nom = JOptionPane.showInputDialog("Entrez le nom du produit à supprimer :");
        
                    // Vérification que le champ n'est pas vide ou null
                    if (nom != null && !nom.trim().isEmpty()) {
                        ProduitDAO produitDAO = new ProduitDAO();
                        produitDAO.supprimerProduit(nom);
        
                        JOptionPane.showMessageDialog(null, "Produit supprimé");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le nom du produit ne peut pas être vide.");
                    }
                }
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerProduitButton.setEnabled(false); // Grise le bouton
        }

        // Action pour afficher la liste des produits
        afficherProduitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProduitDAO produitDAO = new ProduitDAO();
                List<Produit> produits = produitDAO.getAllProduits();
                TableauProduitsView tableauView = new TableauProduitsView(produits, utilisateur);
                tableauView.setVisible(true);
            }
        });

        // Ajouter un Timer pour vérifier périodiquement les niveaux de stock
        Timer stockTimer = new Timer(60000, new ActionListener() { // Changé à 60000 ms (1 minute)
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierNiveauxStock();
            }
        });
        stockTimer.start();

        // Vérifier immédiatement les niveaux de stock à l'ouverture
        SwingUtilities.invokeLater(() -> {
            verifierNiveauxStock();
        });

        // Ajouter un Timer pour vérifier périodiquement les niveaux de stock toutes les 5 minutes
        Timer stockTimer5Min = new Timer(300000, new ActionListener() { // 300000 ms = 5 minutes
            @Override
            public void actionPerformed(ActionEvent e) {
                verifierNiveauxStock();
            }
        });
        stockTimer5Min.start();

        // Ajout des boutons au panel
        buttonPanel.add(ajouterProduitButton);
        buttonPanel.add(modifierProduitButton);
        buttonPanel.add(supprimerProduitButton);
        buttonPanel.add(afficherProduitsButton);

        // Ajout des panels au frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowManager.switchWindow(null, frame);
    }

    private void verifierNiveauxStock() {
        ProduitDAO produitDAO = new ProduitDAO();
        List<Produit> produits = produitDAO.getAllProduits();
        
        StringBuilder alertMessage = new StringBuilder("Attention! Les produits suivants sont sous le seuil d'alerte:\n\n");
        boolean stockBas = false;
        
        for (Produit produit : produits) {
            // Ajout de logs pour déboguer
            System.out.println("Vérification produit: " + produit.getNom());
            System.out.println("Quantité actuelle: " + produit.getQuantite());
            System.out.println("Seuil d'alerte: " + produit.getQteAlert());
            
            // Si la quantité est inférieure ou égale au seuil d'alerte
            if (produit.getQuantite() <= produit.getQteAlert()) {
                stockBas = true;
                alertMessage.append("- ").append(produit.getNom())
                          .append(" (Quantité actuelle: ").append(produit.getQuantite())
                          .append(", Seuil d'alerte: ").append(produit.getQteAlert())
                          .append(")\n");
            }
        }
        
        // Si au moins un produit est en stock bas
        if (stockBas) {
            // Utilisation de SwingUtilities.invokeLater pour éviter les problèmes de thread
            SwingUtilities.invokeLater(() -> {
                int reponse = JOptionPane.showConfirmDialog(frame, 
                    alertMessage.toString() + "\nVoulez-vous ouvrir la gestion des commandes?",
                    "Alerte de stock", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                    
                if (reponse == JOptionPane.YES_OPTION) {
                    new GestionCommandeView(utilisateur);
                }
            });
        }
    }
}