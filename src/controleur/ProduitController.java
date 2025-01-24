package controleur;

import javax.swing.JOptionPane;
import modele.Produit;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import vue.ProduitView;
import modele.Fournisseur;

public class ProduitController {
    private ProduitView vue;
    private ProduitDAO produitDAO;
    private VenteDAO venteDAO;
    public ProduitController(ProduitView vue, ProduitDAO produitDAO, VenteDAO venteDAO) {
    this.vue = vue;
    this.produitDAO = produitDAO;
    this.venteDAO = venteDAO;
    this.vue.setAjouterProduitListener(e -> {
    String nom = vue.getNomProduit();
    double prix = vue.getPrixProduit();
    int quantiter = vue.getQuantiterProduit();
    Produit produit = new Produit(nom, quantiter, prix); // L'id sera généré par la DB
    produitDAO.ajouterProduit(produit);
    JOptionPane.showMessageDialog(null, "Produit ajouté !");
    });
 }
}