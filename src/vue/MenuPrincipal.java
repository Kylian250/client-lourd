package vue;

import javax.swing.*;
import modele.Utilisateur;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuPrincipal extends JFrame {
 private Utilisateur utilisateur;
 public MenuPrincipal(Utilisateur utilisateur) {
 this.utilisateur = utilisateur;
 setTitle("Menu Principal");
 setLayout(new FlowLayout());
 setSize(400, 200);
 JButton gestionProduitButton = new JButton("Gestion Produit");
 JButton gestionVenteButton = new JButton("Gestion Ventes");
 JButton gestionFournisseurButton = new JButton("Gestion Fournisseur");
 gestionProduitButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 new ProduitView(utilisateur);
 }
 });
 gestionVenteButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 new VenteView(utilisateur);
 }
 });
 gestionFournisseurButton.addActionListener(new ActionListener() {
 @Override
 public void actionPerformed(ActionEvent e) {
 new FournisseurView(utilisateur);
 }
});
add(gestionProduitButton);
add(gestionVenteButton);
add(gestionFournisseurButton);
// Désactivation des options en fonction du rôle
if (utilisateur.getRole().equals("manager")) {
 gestionProduitButton.setEnabled(true); // Manager peut gérer les produits
 gestionVenteButton.setEnabled(true); // Manager peut gérer les ventes
 gestionFournisseurButton.setEnabled(true); // Manager peut gérer les fournisseurs
 }
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setVisible(true);
 }
}

