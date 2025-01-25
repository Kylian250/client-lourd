package app;

import javax.swing.UIManager;

import vue.ConnexionUserView;

public class main {
    public static void main(String[] args) {
        try {
            // Appliquer le style Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    new ConnexionUserView();
    }
   } 