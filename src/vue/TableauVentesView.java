package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;
import modele.Vente;
import utils.WindowManager;
import modele.Utilisateur;
import components.RetourButton;

public class TableauVentesView extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public TableauVentesView(List<Vente> ventes, Utilisateur utilisateur) {
        setTitle("Liste des Ventes");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panneau de recherche
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JLabel searchLabel = new JLabel("Rechercher : ");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.NORTH);

        // Modèle de table
        String[] colonnes = {"ID Vente", "Date", "Produit", "Quantité", "ID Produit"};
        model = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table non éditable
            }
        };

        // Remplir le tableau
        for (Vente vente : ventes) {
            Object[] row = {
                vente.getId_vente(),
                vente.getDate(),
                vente.getProduit() != null ? vente.getProduit().getNom() : "N/A",
                vente.getQuantite(),
                vente.getId_produit()
            };
            model.addRow(row);
        }

        // Créer la table
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        // Configuration du tri
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Recherche en temps réel
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }

            private void filter() {
                String text = searchField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Ajouter la table dans un ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter le bouton retour
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        RetourButton btnRetour = new RetourButton(this, utilisateur);
        buttonPanel.add(btnRetour);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WindowManager.switchWindow(null, this);
    }
}