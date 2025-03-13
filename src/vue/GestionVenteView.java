package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.Vente;  // Ajout de l'import manquant
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import controleur.VenteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;

public class GestionVenteView {
    private Utilisateur utilisateur;
    private JFrame frame;

    public GestionVenteView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        frame = new JFrame("Gestion des ventes");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);

        JButton ajouterVenteButton = new JButton("Ajouter une vente");
        JButton modifierVenteButton = new JButton("Modifier une vente");
        JButton supprimerVenteButton = new JButton("Supprimer une vente");
        JButton listeVentesButton = new JButton("Liste des ventes");

        ajouterVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VenteController(new ProduitDAO(), new VenteView(utilisateur), new VenteDAO());
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

        // Ajout du bouton Liste des Ventes
        listeVentesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crée une nouvelle fenêtre qui affiche la liste des ventes
                VenteDAO venteDAO = new VenteDAO();
                java.util.List<modele.Vente> ventes = venteDAO.getAllVentes(); // Méthode qui retourne toutes les ventes

                // Créer un tableau pour afficher les ventes dans une JTable
                String[] columns = {"ID Vente", "Produit", "Quantité", "Date"};
                Object[][] data = new Object[ventes.size()][4];
                for (int i = 0; i < ventes.size(); i++) {
                    modele.Vente vente = ventes.get(i);
                    data[i][0] = vente.getId_vente();
                    data[i][1] = vente.getProduit().getNom();  // Nom du produit associé
                    data[i][3] = vente.getDate();
                }

                // Créer une JTable pour afficher les données
                JTable table = new JTable(data, columns);
                JScrollPane scrollPane = new JScrollPane(table);

                JFrame listFrame = new JFrame("Liste des Ventes");
                listFrame.setSize(600, 400);
                listFrame.add(scrollPane);
                listFrame.setLocationRelativeTo(null);
                listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                listFrame.setVisible(true);
            }
        });

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerVenteButton.setEnabled(false); // Grise le bouton
        }

        // Ajouter les boutons à la fenêtre
        frame.add(ajouterVenteButton);
        frame.add(modifierVenteButton);
        frame.add(supprimerVenteButton);
        frame.add(listeVentesButton);  // Ajoute le bouton Liste des Ventes

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}