package vue;

import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.FournisseurDAO;
import modele.Fournisseur;
import controleur.FournisseurController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

public class GestionFournisseurView {
    private Utilisateur utilisateur;
    private JFrame frame;

    GestionFournisseurView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des Fournisseurs");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JButton ajouterFournisseurButton = new JButton("Ajouter Fournisseur");
        JButton modifierFournisseurButton = new JButton("Modifier Fournisseur");
        JButton supprimerFournisseurButton = new JButton("Supprimer Fournisseur");
        JButton afficherFournisseursButton = new JButton("Afficher Liste Fournisseurs");

        ajouterFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FournisseurController(new FournisseurView(utilisateur), new FournisseurDAO());
            }
        });

        modifierFournisseurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un fournisseur
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

                StringBuilder listeFournisseurs = new StringBuilder();
                for (Fournisseur fournisseur : fournisseurs) {
                    listeFournisseurs.append("Nom: " + fournisseur.getName() + ", Adresse: " + fournisseur.getAddress() + ", Téléphone: " + fournisseur.getPhone() + "\n");
                }

                JOptionPane.showMessageDialog(null, listeFournisseurs.toString(), "Liste des Fournisseurs", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Ajouter les boutons à la fenêtre
        frame.add(ajouterFournisseurButton);
        frame.add(modifierFournisseurButton);
        frame.add(supprimerFournisseurButton);
        frame.add(afficherFournisseursButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}