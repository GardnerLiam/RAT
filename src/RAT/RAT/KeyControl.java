package RAT.RAT;
import RAT.RAT.Dependancies;
import RAT.RAT.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * @Date: Unknown
 * @Author: Liam Gardner
 * @Purpose: It's a KeyListener
 */
public class KeyControl implements KeyListener{
    boolean shift = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            shift = true;
        if (e.getKeyCode() != KeyEvent.VK_SHIFT && shift) {
            Dependancies.changeKey("S" + e.getKeyCode());
            shift = false;
            System.out.println("Pressed " + e.getKeyChar());
        }else if (e.getKeyCode() != KeyEvent.VK_SHIFT && !shift){
            Dependancies.changeKey(e.getKeyCode());
            System.out.println("Pressed " + e.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Dependancies.changeKey(-1);
    }
}
