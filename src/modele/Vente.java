package modele;

import java.sql.Date;

public class Vente {
    private Produit produit;
    private int quantiter;
    private Date date_vente;
    private int id_vente;
    private int id_produit;


    // Constructor
    public Vente(int id_produit, int quantiter, Date date_vente) {
        this.id_produit = id_produit;
        this.quantiter = quantiter;
        this.date_vente = date_vente;
    }

    
    public Vente(int id_produit, Date date_vente, int quantiter, Produit produit) {
        this.id_produit = id_produit;
        this.date_vente = date_vente;
        this.quantiter = quantiter;
        this.produit = produit;
    }


    public int getQuantiter() {
        return quantiter;
    }

    public void setQuantiter(int quantiter) {
        this.quantiter = quantiter;
    }

    public Date getDate() {
        return date_vente;
    }

    public void setDate(Date date_vente) {
        this.date_vente = date_vente;
    }

    public int getId_produit(){
        return id_produit;
    }


    // Functions

    public String toString() {
        return "Fournisseur{" +
        "id_vente=" + id_vente +
        ", date_vente='" + date_vente + '\'' +
        ", quantiter='" + quantiter + '\'' + '\'' + 
        ", id_produit=" + id_produit + 
        '}';
    }
}
