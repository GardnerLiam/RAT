package RAT.RAT;

import com.sun.awt.AWTUtilities;

import javax.swing.*;

public class TransparentFrame extends JFrame {

    public TransparentFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        AWTUtilities.setWindowOpacity(this, 0.95f);
    }

    public TransparentFrame(float transparency){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        AWTUtilities.setWindowOpacity(this, transparency);
    }

}
