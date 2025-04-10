package modele;

public class Produit {
    private String nom;
    private int quantite;
    private double prixUnitaire;
    private int id_produit;
    private int qte_max;
    private int qte_alert;
    private int id_fournisseur;

    // Constructeur pour l'ajout
    public Produit(String nom, int quantite, double prixUnitaire, int qte_max, int qte_alert, int id_fournisseur) {
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.qte_max = qte_max;
        this.qte_alert = qte_alert;
        this.id_fournisseur = id_fournisseur;
    }

    // Constructeur pour la modification/récupération
    public Produit(int id_produit, String nom, int quantite, Double prixUnitaire, int qte_max, int qte_alert, int id_fournisseur) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.qte_max = qte_max;
        this.qte_alert = qte_alert;
        this.id_fournisseur = id_fournisseur;
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

    public int getQteMax() {
        return qte_max;
    }

    public void setQteMax(int qte_max) {
        this.qte_max = qte_max;
    }

    public int getQteAlert() {
        return qte_alert;
    }

    public void setQteAlert(int qte_alert) {
        this.qte_alert = qte_alert;
    }

    public int getIdFournisseur() {
        return id_fournisseur;
    }

    public void setIdFournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }
}