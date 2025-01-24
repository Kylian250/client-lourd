package modele;

public class Utilisateur {
    private int id;
    private String nom;
    private String motDePasse;
    private String role;
    

    public Utilisateur(String role) {
        this.role = role;
    }
    
    public Utilisateur(int id, String nom, String motDePasse, String role) {
        this.id = id;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getRole() {
        return role;
    }
}
