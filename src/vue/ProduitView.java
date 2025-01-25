package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduitView extends JFrame {
    private Utilisateur utilisateur;

    private JFrame frame;
    private JTextField txtNom;
    private JTextField txtPrix;
    private JTextField txtQuantiter;
    private JButton btnAjouterProduit;

    public ProduitView(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;

        frame = new JFrame("Gestion des produits");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        JLabel labelNom = new JLabel("Nom du produit:");
        txtNom = new JTextField(20);
        JLabel labelQuantiter = new JLabel("Quantiter du produit");
        txtQuantiter = new JTextField(20);
        JLabel labelPrix = new JLabel("Prix:");
        txtPrix = new JTextField(20);
        btnAjouterProduit = new JButton("Ajouter Produit");
        frame.add(labelNom);
        frame.add(txtNom);
        frame.add(labelPrix);
        frame.add(txtPrix);
        frame.add(labelQuantiter);
        frame.add(txtQuantiter);
        frame.add(btnAjouterProduit);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public String getNomProduit() {
        return txtNom.getText();
    }

    public double getPrixProduit() {
        return Double.parseDouble(txtPrix.getText());
    }

    public int getQuantiterProduit() {
        return Integer.parseInt(txtQuantiter.getText());
    }

    public void setAjouterProduitListener(ActionListener listener) {
        btnAjouterProduit.addActionListener(listener);
    }

}
