package RAT.RAT;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import RAT.RAT.*;

/*
 * @Author: Liam Gardner
 * @Date: Unknown
 * @Purpose: Check for mosue scrolling
 */
public class MouseWheelControl implements MouseWheelListener{
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getUnitsToScroll() < 0){
            System.out.println("Scroll Up");
            Dependancies.changePress(7);
        }else{
            System.out.println("Scroll Down");
            Dependancies.changePress(8);
        }
    }
}
