package co.edu.uptc.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    boolean isRunning;

    public Server() {
        this.isRunning = true;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void start() {
        super.start();
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Servidor iniciado");
            while (isRunning){
                Socket cliente = server.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());
                new ClientManager(cliente).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
