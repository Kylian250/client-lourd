package controleur;

import javax.swing.JOptionPane;
import modele.Fournisseur;
import modele.DAO.FournisseurDAO;
import vue.FournisseurView;

public class FournisseurController {
    private FournisseurView vue;
    private FournisseurDAO fournisseurDAO;

    public FournisseurController(FournisseurView vue, FournisseurDAO fournisseurDAO) {
        this.vue = vue;
        this.fournisseurDAO = fournisseurDAO;

        // Ajout du listener pour le bouton "Ajouter le Fournisseur"
        this.vue.setAjouterFournisseurListener(e -> {
            try {
                // Récupération des données depuis la vue
                String nom = vue.getNom();
                String adresse = vue.getAdresse();
                String telephone = String.valueOf(vue.getTelephone()); // Conversion en chaîne
                
                // Validation des champs
                if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Création d'un objet Fournisseur
                Fournisseur fournisseur = new Fournisseur(nom, adresse, telephone, null);

                // Ajout du fournisseur dans la base de données
                fournisseurDAO.ajouterFournisseur(fournisseur);
                JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès !");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erreur dans le format des données. Vérifiez le numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}