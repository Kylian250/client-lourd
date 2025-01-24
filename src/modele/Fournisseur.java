package modele;

import java.util.List;

public class Fournisseur {
    private String nom;
    private String address;
    private String telephone;
    private List<Produit> produits;
    private int id_fournisseur;

    // Constructor
    public Fournisseur(String nom, String address, String telephone, List<Produit> produits) {
        super();
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.produits = produits;
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

    public List<Produit> getProduits(List<Produit> produits){
        return produits;
    }

    public void setProduit(List<Produit> produits){
        this.produits = produits;
    }
 
    

    // Functions

    // Méthode toString
    public String toString() {
        return "Fournisseur{" +
        "id_fournisseur=" + id_fournisseur +
        ", nom='" + nom + '\'' +
        ", address='" + address + '\'' + '\'' + 
        ", produits=" + produits + 
        '}';
    }
}