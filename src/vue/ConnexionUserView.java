package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.Connexion;

public class ConnexionUserView extends JFrame {

    public ConnexionUserView() {
        setTitle("Login");
        setLayout(new FlowLayout());
        setSize(300, 150);

        JLabel nomLabel = new JLabel("Nom d'utilisateur:");
        JTextField nomField = new JTextField(15);
        JLabel motDePasseLabel = new JLabel("Mot de passe:");
        JPasswordField motDePasseField = new JPasswordField(15);
        JButton loginButton = new JButton("Se connecter");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String motDePasse = new String(motDePasseField.getPassword());
                
                // Appel direct à la méthode verifierConnexion
                Utilisateur utilisateur = Connexion.verifierConnexion(nom, motDePasse);
                
                if (utilisateur != null) {
                    new MenuPrincipal(utilisateur);
                    dispose(); // Fermer la fenêtre de login
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect !");
                }
            }
        });

        add(nomLabel);
        add(nomField);
        add(motDePasseLabel);
        add(motDePasseField);
        add(loginButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}