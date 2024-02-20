package co.edu.uptc.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    boolean isRunning;
    ArrayList <ClientManager> clients;

    public Server() {
        this.isRunning = true;
        this.clients = new ArrayList<>();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void start() {
        super.start();
        try {
            ServerSocket server = new ServerSocket(Server_Constants.PORT);
            while (isRunning){
                Socket client = server.accept();
                ClientManager c = new ClientManager(client);
                clients.add(c);
                c.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
