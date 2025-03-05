package controleur;

import javax.swing.JOptionPane;
import modele.Produit;
import modele.Vente;
import modele.DAO.ProduitDAO;
import modele.DAO.VenteDAO;
import vue.ProduitView;
import modele.Fournisseur;
import vue.VenteView;
import java.sql.Date;

public class VenteController {
    private VenteView vue;
    private VenteDAO venteDAO;
    private ProduitDAO produitDAO;
    public VenteController(ProduitDAO produitDAO, VenteView vue, VenteDAO venteDAO) {
        this.vue = vue;
        this.venteDAO = venteDAO;
        this.vue.setAjouterVenteListener(e -> {
            Date date_vente = vue.getDate_vente();
            int id_produit = vue.getId_produit();
            Vente vente = new Vente(date_vente, id_produit); // Création de la vente sans la quantité
            venteDAO.ajouterVente(vente);
            JOptionPane.showMessageDialog(null, "Vente ajoutée !");
        });
    }
    
}
