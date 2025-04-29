package utils;

import javax.swing.JFrame;

public class WindowManager {
    private static JFrame currentWindow;

    public static void switchWindow(JFrame oldWindow, JFrame newWindow) {
        if (newWindow == null) {
            throw new IllegalArgumentException("La nouvelle fenêtre ne peut pas être null");
        }
        
        if (oldWindow != null) {
            oldWindow.dispose();
        }
        
        newWindow.setVisible(true);
        currentWindow = newWindow;
    }

    public static JFrame getCurrentWindow() {
        return currentWindow;
    }
}