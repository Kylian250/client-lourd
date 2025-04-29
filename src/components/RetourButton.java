package components;

import javax.swing.*;
import java.awt.*;
import vue.MenuPrincipal;
import utils.WindowManager;
import modele.Utilisateur;

public class RetourButton extends JButton {
    public RetourButton(JFrame currentFrame, Utilisateur utilisateur) {
        setText("Retour");
        setBackground(new Color(220, 53, 69));
        setForeground(Color.WHITE);
        
        addActionListener(e -> {
            MenuPrincipal menuPrincipal = new MenuPrincipal(utilisateur);
            WindowManager.switchWindow(currentFrame, menuPrincipal);
        });
    }

    public RetourButton(JFrame currentFrame, JFrame previousFrame, Utilisateur utilisateur) {
        setText("Retour");
        setBackground(new Color(220, 53, 69));
        setForeground(Color.WHITE);
        
        addActionListener(e -> {
            if (previousFrame != null) {
                WindowManager.switchWindow(currentFrame, previousFrame);
            } else {
                MenuPrincipal menuPrincipal = new MenuPrincipal(utilisateur);
                WindowManager.switchWindow(currentFrame, menuPrincipal);
            }
        });
    }
}