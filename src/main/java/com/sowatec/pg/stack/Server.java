package main.java.com.sowatec.pg.stack;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true) {
            Socket socket = serverSocket.accept();
            ServerThread thread = new ServerThread(socket);
            thread.start();
        }
    }
}
