package RAT.RAT;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import RAT.RAT.*;
/*
 * @Author: Liam Gardner
 * @Purpose: Get's mouse position
 * @Date: Unknown
 */
public class MouseMotionControl implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int btn = e.getButton() + 3;
        Dependancies.changeCPress(x,y, 4);
        System.out.println("DRAG");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dependancies.changeCoords(x,y);
        System.out.println("Move");
    }
}
