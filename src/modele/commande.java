package modele;

public class Commande {
    private int id_commande;
    private int id_produit;
    private int quantite;

    // Constructeur pour création
    public Commande(int id_produit, int quantite) {
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    // Constructeur pour lecture depuis BDD
    public Commande(int id_commande, int id_produit, int quantite) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    // Getters et Setters
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
