package game;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Whack A Mole");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            Image icon = new ImageIcon("assets/mole.png").getImage();
            setIconImage(icon);
        } catch (Exception e) {
        }
        add(new GamePanel());
        setVisible(true);
    }
}