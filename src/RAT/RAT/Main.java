package RAT.RAT;

import javax.swing.JFrame;
import RAT.RAT.Dependancies;

/*
 * @Author: Liam Gardner
 * @Date: Unknown
 * @Purpose: 'Tis the main method
 */
public class Main extends JFrame {
	private static final long serialVersionUID = -2548774145561760985L;
	public static Dependancies d;
	public Main() {
        setTitle("R.A.T");
        d = new Dependancies(this);
        add(d.getJp2());
        setJMenuBar(d.getMenubar());
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        d.jp2.requestFocus();
        setVisible(true);
    }
    public static void main(String args[]) {
    	Main m = new Main();
        m.setSize(863,700);
        m.setLocationRelativeTo(null);
        String a = Dependancies.curcmd;
        //System.out.println(a);
        System.out.println(a.substring(3));
    }
}