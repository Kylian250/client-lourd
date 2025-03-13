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

        this.vue.setActionListener(e -> {
            try {
                String nom = vue.getNom();
                String adresse = vue.getAdresse();
                String telephone = vue.getTelephone();
                
                if (nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Fournisseur fournisseur = new Fournisseur(nom, adresse, telephone);

                if (vue.isModificationMode()) {
                    fournisseurDAO.modifierFournisseur(fournisseur);
                    JOptionPane.showMessageDialog(null, "Fournisseur modifié avec succès !");
                } else {
                    fournisseurDAO.ajouterFournisseur(fournisseur);
                    JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succès !");
                }
                vue.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}