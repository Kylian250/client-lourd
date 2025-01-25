package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class VenteView extends JFrame {
    private Utilisateur utilisateur;

    private JFrame frame;
    private JTextField txtQuantiter;
    private JTextField txtDate_vente;
    private JTextField txtId_produit;
    private JButton btnAjouterVente;

    public VenteView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
   
        frame = new JFrame("Gestion des Ventes");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);


        JLabel labelQuantiter = new JLabel("Quantiter du produit:");
        txtQuantiter = new JTextField(20);
        JLabel labelDate_vente = new JLabel("Date de vente:");
        txtDate_vente = new JTextField(20);
        JLabel labelId_produit = new JLabel("Id produit:");
        txtId_produit = new JTextField(20);
        btnAjouterVente = new JButton("Ajouter Vente");
        frame.add(labelQuantiter);
        frame.add(txtQuantiter);
        frame.add(labelDate_vente);
        frame.add(txtDate_vente);
        frame.add(labelId_produit);
        frame.add(txtId_produit);
        frame.add(btnAjouterVente);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public int getQuantiter() {
        return Integer.parseInt(txtQuantiter.getText());
    }

    public Date getDate_vente() {
        return Date.valueOf(txtDate_vente.getText());
    }

    public int getId_produit(){
        return Integer.parseInt(txtId_produit.getText());
    }

    public void setAjouterVenteListener(ActionListener listener) {
        btnAjouterVente.addActionListener(listener);
    }
   /*  public static void main(String[] args) {
        // Exemple d'utilisation avec un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur("manager"); // Remplacer par un utilisateur réel
        new VenteView(utilisateur);
    }*/
}