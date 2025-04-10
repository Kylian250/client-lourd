package controleur;

import javax.swing.JOptionPane;
import modele.Commande;
import modele.DAO.CommandeDAO;
import modele.DAO.ProduitDAO;
import vue.GestionCommandeView;
import modele.Produit;

public class CommandeController {
    private GestionCommandeView vue;
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;

    public CommandeController(GestionCommandeView vue, CommandeDAO commandeDAO, ProduitDAO produitDAO) {
        this.vue = vue;
        this.commandeDAO = commandeDAO;
        this.produitDAO = produitDAO;
    }

    public void validerCommande(int idProduit, int quantite) {
        try {
            // Vérification que la quantité est positive
            if (quantite <= 0) {
                throw new IllegalArgumentException("La quantité doit être positive");
            }

            // Vérifier si le produit existe
            Produit produit = produitDAO.getProduitById(idProduit);
            if (produit == null) {
                throw new IllegalArgumentException("Produit non trouvé");
            }

            // Ajouter la commande et mettre à jour le stock
            commandeDAO.ajouterCommande(idProduit, quantite);
            
            JOptionPane.showMessageDialog(null, 
                "Commande enregistrée et stock mis à jour avec succès",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erreur lors de la création de la commande : " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}