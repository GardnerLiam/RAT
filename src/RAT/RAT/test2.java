package RAT.RAT;

import javax.swing.*;
import java.awt.*;

public class test2 extends JFrame{
    public test2() {
        super("Test translucent window");
        this.setSize(new Dimension(400, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window w = new test2();
                w.setVisible(true);
                com.sun.awt.AWTUtilities.setWindowOpacity(w, 0.75f);
            }
        });
    }
}
