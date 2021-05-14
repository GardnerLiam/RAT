package RAT.RAT;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import RAT.RAT.*;
/*
 * @Author: Liam Gardner
 * @Date: Unknown
 * @Purpose: Mouse Listener
 */
public class MouseControl implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        Dependancies.changePress(e.getButton());
        System.out.println("PRESS");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Dependancies.changePress(-1);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
