package modele;

import java.sql.Date;

public class Vente {
    private int id_vente;
    private int id_produit;
    private Date date_vente;
    private Produit produit;


    public Vente(Date date_vente, int id_produit) {
        this.id_produit = id_produit;
        this.date_vente = date_vente;
    }


    // Constructeur standard pour la classe Vente
    public Vente(int id_vente, int id_produit, Date date_vente, Produit produit) {
        this.id_vente = id_vente;
        this.id_produit = id_produit;
        this.date_vente = date_vente;
        this.produit = produit;
    }

    // Getter et Setter
    public int getId_vente() {
        return id_vente;
    }

    public void setId_vente(int id_vente) {
        this.id_vente = id_vente;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Date getDate() {
        return date_vente;
    }

    public void setDate(Date date_vente) {
        this.date_vente = date_vente;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}