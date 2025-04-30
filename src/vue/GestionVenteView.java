package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Vente;  // Ajout de l'import manquant
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import controleur.VenteController;
import components.RetourButton;
import utils.WindowManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

public class GestionVenteView {
    private Utilisateur utilisateur;
    private JFrame frame;

    public GestionVenteView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des ventes");
        frame.setLayout(new BorderLayout(10, 10));
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        // Panel pour le bouton retour
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(frame, utilisateur);
        topPanel.add(btnRetour);

        // Panel principal pour les boutons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton ajouterVenteButton = new JButton("Ajouter une vente");
        JButton modifierVenteButton = new JButton("Modifier une vente");
        JButton supprimerVenteButton = new JButton("Supprimer une vente");
        JButton afficherVentesButton = new JButton("Afficher les ventes");

        ajouterVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VenteView vue = new VenteView(utilisateur);
                new VenteController(new ProduitDAO(), vue, new VenteDAO());
                WindowManager.switchWindow(frame, vue);
            }
        });

        modifierVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idInput = JOptionPane.showInputDialog("Entrez l'ID de la vente à modifier :");
                if (idInput != null && !idInput.trim().isEmpty()) {
                    try {
                        int id = Integer.parseInt(idInput);
                        VenteDAO venteDAO = new VenteDAO();
                        Vente vente = venteDAO.getVenteById(id);
                        if (vente != null) {
                            VenteView vue = new VenteView(utilisateur, vente);
                            new VenteController(new ProduitDAO(), vue, venteDAO);
                            WindowManager.switchWindow(frame, vue);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vente non trouvée.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "L'ID doit être un nombre valide.");
                    }
                }
            }
        });

        supprimerVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateur.getRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des ventes.");
                } else {
                    // Demande de l'identifiant de la vente à l'utilisateur
                    String idInput = JOptionPane.showInputDialog("Entrez l'id de la vente à supprimer :");

                    // Vérification que le champ n'est pas vide ou null
                    if (idInput != null && !idInput.trim().isEmpty()) {
                        try {
                            // Conversion de l'identifiant en entier
                            int id = Integer.parseInt(idInput);

                            // Suppression de la vente
                            VenteDAO venteDAO = new VenteDAO();
                            venteDAO.supprimerVente(id);

                            JOptionPane.showMessageDialog(null, "Vente supprimée avec succès.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "L'id doit être un nombre valide.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "L'id de la vente ne peut pas être vide.");
                    }
                }
            }
        });

        // Ajout du bouton Afficher les Ventes
        afficherVentesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VenteDAO venteDAO = new VenteDAO();
                java.util.List<Vente> ventes = venteDAO.getAllVentes();
                TableauVentesView tableauView = new TableauVentesView(ventes, utilisateur);
                WindowManager.switchWindow(frame, tableauView);
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerVenteButton.setEnabled(false); // Grise le bouton
        }

        // Ajout des boutons au panel
        buttonPanel.add(ajouterVenteButton);
        buttonPanel.add(modifierVenteButton);
        buttonPanel.add(supprimerVenteButton);
        buttonPanel.add(afficherVentesButton);

        // Ajout des panels au frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowManager.switchWindow(null, frame);
    }
}