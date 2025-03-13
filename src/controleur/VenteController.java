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
        this.produitDAO = produitDAO;

        this.vue.setActionListener(e -> {
            try {
                Date date_vente = vue.getDate_vente();
                int id_produit = vue.getId_produit();

                if (vue.isModificationMode()) {
                    Vente venteModifiee = new Vente(vue.getVenteAModifier().getId_vente(), 
                                                   id_produit, 
                                                   date_vente, 
                                                   produitDAO.getProduitById(id_produit));
                    venteDAO.modifierVente(venteModifiee);
                    JOptionPane.showMessageDialog(null, "Vente modifiée !");
                } else {
                    Vente vente = new Vente(date_vente, id_produit);
                    venteDAO.ajouterVente(vente);
                    JOptionPane.showMessageDialog(null, "Vente ajoutée !");
                }
                vue.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
    }
    
}
