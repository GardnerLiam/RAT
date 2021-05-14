package RAT.FTP;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;


class FTPClient {
    Socket ClientSoc;

    DataInputStream din;
    DataOutputStream dout;
    BufferedReader br;
    JFileChooser jfc;
    String dir = "";

    public FTPClient(Socket soc) {

        try {
            ClientSoc = soc;
            din = new DataInputStream(ClientSoc.getInputStream());
            dout = new DataOutputStream(ClientSoc.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            jfc = new JFileChooser();
        } catch (Exception ex) {

        }
    }

    String chooseFile() {
        return "";
    }

    void SendFile() throws Exception {
        String filename, newfilename;
        System.out.println("Running [Choose File]");
        System.out.println("Creating JFileChooser");
        JFileChooser jfc = new JFileChooser();
        System.out.println("Setting visibility");
        jfc.setVisible(true);
        System.out.println("Opening Dialog");
        int returnValue = jfc.showOpenDialog(null);
        System.out.println("Resetting visibility");
        jfc.setVisible(true);
        System.out.println("Checking selected");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getAbsolutePath();
        } else {
            filename = "CANCELLED";
        }
        if (!filename.equals("CANCELLED")) {
            File f = new File(filename);
            System.out.print("Save file to: ");
            newfilename = br.readLine();
            dout.writeUTF(newfilename);

            String msgFromServer = din.readUTF();
            if (msgFromServer.compareTo("File Already Exists") == 0) {
                String Option;
                System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
                Option = br.readLine();
                if (Option.equalsIgnoreCase("Y")) {
                    dout.writeUTF("Y");
                } else {
                    dout.writeUTF("N");
                    return;
                }
            }

            System.out.println("Sending File ...");
            FileInputStream fin = new FileInputStream(f);
            int ch;
            do {
                ch = fin.read();
                dout.writeUTF(String.valueOf(ch));
            }
            while (ch != -1);
            fin.close();
            System.out.println(din.readUTF());
        }
    }

    void ReceiveFile() throws Exception {
        String fileName;
        System.out.print("Enter File Name :");
        fileName = br.readLine();
        dout.writeUTF(dir + fileName);
        String msgFromServer = din.readUTF();

        if (msgFromServer.compareTo("File Not Found") == 0) {
            System.out.println("File not found on Server ...");
            return;
        } else if (msgFromServer.compareTo("READY") == 0) {
            System.out.println("Where to save?");
            String path = br.readLine();
            path = path + "\\" + fileName;
            System.out.println("Receiving File ...");
            File f = new File(path);
            if (f.exists()) {
                String Option;
                System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
                Option = br.readLine();
                if (Option == "N") {
                    dout.flush();
                    return;
                }
            }
            FileOutputStream fout = new FileOutputStream(f);
            int ch;
            String temp;
            do {
                temp = din.readUTF();
                ch = Integer.parseInt(temp);
                if (ch != -1) {
                    fout.write(ch);
                }
            } while (ch != -1);
            fout.close();
            System.out.println(din.readUTF());

        }


    }

    void BrowseDirectory() throws Exception {
        String fileName;
        System.out.print("Enter Directory Name:");
        fileName = br.readLine();
        dout.writeUTF(dir + fileName);
        String msgFromServer = din.readUTF();

        if (msgFromServer.compareTo("File Not Found") == 0) {
            System.out.println("File not found on Server ...");
            return;
        } else {
            if (dir.equals("")) dir = fileName;
            else dir = dir + fileName + "\\";
            System.out.println(msgFromServer);
        }
    }

    void BackDirectory() throws Exception {
        if (dir.endsWith(":\\")) {
            dir = "";
            dout.writeUTF("Back");
        } else {
            int k = dir.lastIndexOf('\\');
            System.out.println("ind: " + k);
            char dst[] = new char[1000];
            dir.getChars(0, dir.length(), dst, 0);
            dst[k] = '?';
            String str = new String(dst, 0, k);
            k = str.lastIndexOf('\\');
            System.out.println("ind: " + k);
            str.getChars(0, k - 1, dst, 0);
            dir = new String(dst, 0, k + 1);
            System.out.println(dir);
            dout.writeUTF(dir);

        }
        String msgFromServer = din.readUTF();

        if (msgFromServer.compareTo("File Not Found") == 0) {
            System.out.println("File not found on Server ...");
            return;
        } else {

            System.out.println(msgFromServer);
        }
    }

    public void displayMenu() throws Exception {
        while (true) {
            System.out.println("pwd: " + dir + "\n[ MENU ]");
            System.out.println("1. Send File");
            System.out.println("2. Receive File");
            System.out.println("3. Browse All");
            System.out.println("4. Brows Directories");
            System.out.println("5. Back");
            System.out.println("6. Exit");
            System.out.print("\nEnter Choice :");
            int choice;
            choice = Integer.parseInt(br.readLine());
            if (choice == 1) {
                dout.writeUTF("SEND");
                System.out.println("Going to [Send File]");
                SendFile();
            } else if (choice == 2) {
                dout.writeUTF("RECEIVE");
                ReceiveFile();
            } else if (choice == 3) {
                dout.writeUTF("LIST");
                BrowseDirectory();
            } else if (choice == 4) {
                dout.writeUTF("LIST D");
                BrowseDirectory();
            }else if (choice == 5){
                dout.writeUTF("LIST");
                BackDirectory();
            }else {
                dout.writeUTF("DISCONNECT");
                System.exit(1);
            }
        }
    }
}