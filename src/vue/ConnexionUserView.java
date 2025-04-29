package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Utilisateur;
import modele.DAO.Connexion;
import utils.WindowManager;

public class ConnexionUserView extends JFrame {
    
    public ConnexionUserView() {
        setTitle("Connexion");
        setLayout(new FlowLayout());
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
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
    
                Utilisateur utilisateur = Connexion.verifierConnexion(nom, motDePasse);
                
                if (utilisateur != null) {
                    System.out.println("Connexion réussie, ouverture du menu principal...");
                    MenuPrincipal menuPrincipal = new MenuPrincipal(utilisateur);
                    WindowManager.switchWindow(ConnexionUserView.this, menuPrincipal);
                } else {
                    JOptionPane.showMessageDialog(ConnexionUserView.this, 
                        "Nom d'utilisateur ou mot de passe incorrect !");
                }
            }
        });
    
        add(nomLabel);
        add(nomField);
        add(motDePasseLabel);
        add(motDePasseField);
        add(loginButton);
    }
}