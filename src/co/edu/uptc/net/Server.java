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
            ServerSocket server = new ServerSocket(Constants.PORT);
            System.out.println("Servidor iniciado");
            while (isRunning){
                Socket client = server.accept();
                System.out.println("Cliente conectado desde " + client.getInetAddress().getHostAddress());
                ClientManager c = new ClientManager(client);
                clients.add(c);
                c.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
