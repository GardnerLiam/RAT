package RAT.RAT;

import com.sun.awt.AWTUtilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class test {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (int i = 0 + 1; i < 25 + 1; i++) {
            String x = "Item#" + Integer.toString(i);
            l1.addElement(x);
        }
        JList<String> list = new JList<>(l1);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(100, 100));
        listScroller.setBounds(10,10,100,100);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(3);
        f.add(listScroller);
        f.setVisible(true);
    }
}
