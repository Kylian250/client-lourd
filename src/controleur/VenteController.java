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
                int quantite = vue.getQuantite();

                // Vérifier si la quantité demandée est disponible
                Produit produit = produitDAO.getProduitById(id_produit);
                if (produit == null) {
                    throw new IllegalArgumentException("Produit non trouvé");
                }
                if (produit.getQuantite() < quantite) {
                    throw new IllegalArgumentException("Stock insuffisant");
                }

                if (vue.isModificationMode()) {
                    Vente venteModifiee = new Vente(
                        vue.getVenteAModifier().getId_vente(),
                        id_produit,
                        date_vente,
                        produit,
                        quantite
                    );
                    venteDAO.modifierVente(venteModifiee);
                    JOptionPane.showMessageDialog(null, "Vente modifiée !");
                } else {
                    Vente nouvelleVente = new Vente(date_vente, id_produit, quantite);
                    venteDAO.ajouterVente(nouvelleVente);
                    JOptionPane.showMessageDialog(null, "Vente ajoutée !");
                }
                vue.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
    }
    
}
