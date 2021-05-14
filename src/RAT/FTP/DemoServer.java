package RAT.FTP;

import java.net.ServerSocket;

public class DemoServer {
    public static void main(String args[]) throws Exception {
        ServerSocket socfd = new ServerSocket(4000);
        System.out.println("FTP Server Started on Port Number 4000");
        while (true) {
            System.out.println("Waiting for Connection ...");
            FTPServer t = new FTPServer(socfd.accept());
        }
    }
}
