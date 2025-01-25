package modele;

public class Produit {
    private String nom;
    private int quantite;
    private double prixUnitaire;
    private int id_produit;

    public Produit(String nom, int quantite, double prixUnitaire) {
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public Produit(int id_produit, String nom, int prixUnitaire) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.prixUnitaire = prixUnitaire;
    }

    public Produit(int id_produit, String nom, int quantite, Double prixUnitaire){
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getId_produit(){
        return id_produit;
    }
}