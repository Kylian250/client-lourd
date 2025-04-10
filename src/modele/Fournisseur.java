package modele;

import java.util.List;

public class Fournisseur {
    private String nom;
    private String address;
    private String telephone;
    private List<Produit> produits;
    private int id_fournisseur;

    // Constructeur avec trois paramètres pour résoudre l'erreur
    public Fournisseur(String nom, String address, String telephone) {
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.produits = null;  // Si tu veux initialiser avec une valeur par défaut
    }

    // Constructeur existant avec quatre paramètres
    public Fournisseur(String nom, String address, String telephone, List<Produit> produits) {
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.produits = produits;
    }

    // Constructeur avec id_fournisseur
    public Fournisseur(int id_fournisseur, String nom, String address, String telephone) {
        this.id_fournisseur = id_fournisseur;
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.produits = null;
    }

    // Setters & Getters
    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return telephone;
    }

    public void setPhone(String telephone) {
        this.telephone = telephone;
    }

    public List<Produit> getProduits(List<Produit> produits) {
        return produits;
    }

    public void setProduit(List<Produit> produits) {
        this.produits = produits;
    }

    // Ajouter le getter pour id_fournisseur
    public int getId_fournisseur() {
        return id_fournisseur;
    }

    // Ajouter le setter pour id_fournisseur
    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    // Méthode toString
    public String toString() {
        return "Fournisseur{" +
        "id_fournisseur=" + id_fournisseur +
        ", nom='" + nom + '\'' +
        ", address='" + address + '\'' +
        ", produits=" + produits +
        '}';
    }
}