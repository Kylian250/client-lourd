package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FournisseurView extends JFrame {
    private Utilisateur utilisateur;

    private JFrame frame;
    private JTextField txtNom;
    private JTextField txtAdresse;
    private JTextField txtTelephone;
    private JButton btnAjouterFournisseur;

    public FournisseurView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
   
        setTitle("Gestion des Fournisseurs");
        setLayout(new FlowLayout());
        setSize(400, 200);

       

        JLabel labelNom = new JLabel("Nom du fournisseur :");
        txtNom = new JTextField(20);
        JLabel labelAdresse = new JLabel("Adresse du fournisseur :");
        txtAdresse = new JTextField(20);
        JLabel labelTelephone = new JLabel("Téléphone du fournisseur :");
        txtTelephone = new JTextField(20);
        btnAjouterFournisseur = new JButton("Ajouter Fournisseur");
        frame.add(labelNom);
        frame.add(txtNom);
        frame.add(labelAdresse);
        frame.add(txtAdresse);
        frame.add(labelTelephone);
        frame.add(txtTelephone);
        frame.add(btnAjouterFournisseur);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        

    }

    public String getNom() {
        return txtNom.getText();
    }

    public String getAdresse() {
        return txtAdresse.getText();
    }

    public String getTelephone() {
        return txtTelephone.getText();
    }

    public void setAjouterFournisseurListener(ActionListener listener) {
        btnAjouterFournisseur.addActionListener(listener);
    }

   /*  public static void main(String[] args) {
        // Exemple d'utilisation avec un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur("manager"); // Remplacer par un utilisateur réel
        new FournisseurView(utilisateur);
    }*/
}