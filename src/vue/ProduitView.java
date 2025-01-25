package vue;

import javax.swing.*;
import modele.Utilisateur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ProduitView extends JFrame {
    private Utilisateur utilisateur;

    private JFrame frame;
    private JTextField txtNom;
    private JTextField txtPrix;
    private JTextField txtQuantiter;
    private JTextField txtFieldStartDate;
    private JTextField txtFieldEndDate;
    private JTextField txtFieldNom;
    private JTextField txtFieldPrix;
    private JTextArea reportArea;

    private JButton btnAjouterProduit;
    private JButton btnGenererRapport;

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

        // Champs pour générer le rapport
        JLabel labelStartDate = new JLabel("Date de début (YYYY-MM-DD):");
        txtFieldStartDate = new JTextField(10);
        JLabel labelEndDate = new JLabel("Date de fin (YYYY-MM-DD):");
        txtFieldEndDate = new JTextField(10);
        btnGenererRapport = new JButton("Générer Rapport");
        // Zone de texte pour afficher le rapport
        reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        // Ajouter les éléments à la fenêtre
        frame.add(labelNom);
        frame.add(txtFieldNom);
        frame.add(labelPrix);
        frame.add(txtFieldPrix);
        frame.add(btnAjouterProduit);
        frame.add(labelStartDate);
        frame.add(txtFieldStartDate);
        frame.add(labelEndDate);
        frame.add(txtFieldEndDate);
        frame.add(btnGenererRapport);

        frame.add(new JScrollPane(reportArea));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getProduitByNom() {
        return txtFieldNom.getText();
    }

    public double getPrixUnitaire() {
        return Double.parseDouble(txtFieldPrix.getText());
    }

    public LocalDate getStartDate() {
        return LocalDate.parse(txtFieldStartDate.getText());
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(txtFieldEndDate.getText());
    }

    public void afficherRapport(String produitNom, double prix, int ventesTotales) {
        reportArea.setText("Rapport pour le produit : " + produitNom + "\n");
        reportArea.append("Prix du produit : " + prix + "\n");
        reportArea.append("Ventes totales dans la période : " + ventesTotales + "\n");
    }


    public void setGenererRapportListener(ActionListener listener) {
        btnGenererRapport.addActionListener(listener);
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
