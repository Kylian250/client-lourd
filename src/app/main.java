package app;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import vue.ConnexionUserView;
import utils.WindowManager;

public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            ConnexionUserView loginWindow = new ConnexionUserView();
            WindowManager.switchWindow(null, loginWindow);
        });
    }
}