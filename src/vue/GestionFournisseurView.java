package vue;

import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.FournisseurDAO;
import utils.WindowManager;
import modele.Fournisseur;
import controleur.FournisseurController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.util.List;
import components.RetourButton;

public class GestionFournisseurView {
    private Utilisateur utilisateur;
    private JFrame frame;

    GestionFournisseurView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des fournisseurs");
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

        JButton ajouterFournisseurButton = new JButton("Ajouter un fournisseur");
        JButton modifierFournisseurButton = new JButton("Modifier un fournisseur");
        JButton supprimerFournisseurButton = new JButton("Supprimer un fournisseur");
        JButton afficherFournisseursButton = new JButton("Afficher la liste des fournisseurs");

        ajouterFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FournisseurView vue = new FournisseurView(utilisateur);
                new FournisseurController(vue, new FournisseurDAO());
                WindowManager.switchWindow(frame, vue);
            }
        });

        modifierFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = JOptionPane.showInputDialog("Entrez le nom du fournisseur à modifier :");
                if (nom != null && !nom.trim().isEmpty()) {
                    FournisseurDAO fournisseurDAO = new FournisseurDAO();
                    Fournisseur fournisseur = fournisseurDAO.getFournisseurByNom(nom);
                    if (fournisseur != null) {
                        FournisseurView vue = new FournisseurView(utilisateur, fournisseur);
                        new FournisseurController(vue, fournisseurDAO);
                        WindowManager.switchWindow(frame, vue);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fournisseur non trouvé.");
                    }
                }
            }
        });

        supprimerFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (utilisateur.getRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des fournisseurs.");
                } else {
                    String nom = JOptionPane.showInputDialog("Entrez le nom du fournisseur à supprimer :");
                    if (nom != null && !nom.trim().isEmpty()) {
                        FournisseurDAO fournisseurDAO = new FournisseurDAO();
                        fournisseurDAO.supprimerFournisseur(nom);
                        JOptionPane.showMessageDialog(null, "Fournisseur supprimé");
                    } else {
                        JOptionPane.showMessageDialog(null, "Le nom du fournisseur ne peut pas être vide.");
                    }
                }
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerFournisseurButton.setEnabled(false); // Grise le bouton
        }

        // Action pour afficher la liste des fournisseurs
        afficherFournisseursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FournisseurDAO fournisseurDAO = new FournisseurDAO();
                List<Fournisseur> fournisseurs = fournisseurDAO.getAllFournisseurs();
                TableauFournisseursView tableauView = new TableauFournisseursView(fournisseurs, utilisateur);
                WindowManager.switchWindow(frame, tableauView);
            }
        });

        // Ajout des boutons au panel
        buttonPanel.add(ajouterFournisseurButton);
        buttonPanel.add(modifierFournisseurButton);
        buttonPanel.add(supprimerFournisseurButton);
        buttonPanel.add(afficherFournisseursButton);

        // Ajout des panels au frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowManager.switchWindow(null, frame);
    }
}