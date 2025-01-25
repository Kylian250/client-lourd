package vue;

import javax.swing.*;
import modele.Utilisateur;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import controleur.VenteController;
import java.awt.FlowLayout;


public class GestionVenteView {
    private Utilisateur utilisateur;

    private JFrame frame;

     GestionVenteView(Utilisateur utilisateur) {

        this.utilisateur = utilisateur;
   
        frame = new JFrame("Gestion des Ventes");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        JButton ajouterVenteButton = new JButton("Ajouter Vente");
        JButton modifierVenteButton = new JButton("Modifier Vente");
        JButton supprimerVenteButton = new JButton("Supprimer Vente");

        ajouterVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VenteController(new ProduitDAO(), new VenteView(utilisateur), new VenteDAO());
            }
        });

        modifierVenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un produit
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

        // Désactivation du bouton suppression si l'utilisateur est un manager
        if (utilisateur.getRole().equals("manager")) {
            supprimerVenteButton.setEnabled(false); // Grise le bouton
        }
        frame.add(ajouterVenteButton);
        frame.add(modifierVenteButton);
        frame.add(supprimerVenteButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
