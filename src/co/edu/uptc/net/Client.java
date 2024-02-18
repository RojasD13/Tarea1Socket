package co.edu.uptc.net;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private String HOST;
    private int PUERTO;
    private Socket client;
    private DataInputStream entrada;
    private DataOutputStream salida;


    public Client() throws IOException {
        this.HOST = "localhost";
        this.PUERTO  = 5000;
        init();
    }

    private void init() throws IOException {
        this.client = new Socket(HOST, PUERTO);
        this.entrada = new DataInputStream(client.getInputStream());
        this.salida = new DataOutputStream(client.getOutputStream());
        System.out.println("Cliente conectado al servidor " + HOST + " en el puerto " + PUERTO);
    }

    public void addImage(String path) throws IOException {
        salida.writeInt(1);


        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        byte[] bytesImagen = new byte[(int) file.length()];
        salida.writeInt(bytesImagen.length);
        salida.write(bytesImagen);
        salida.flush();

        System.out.println("Imagen subida correctamente: ");
    }

    public ArrayList<File> obtenerImagen() throws IOException {
        salida.writeInt(2);

        int folderSize=entrada.readInt();

        ArrayList<File> files= new ArrayList<>();

        for (int i = 0; i < folderSize ; i++) {
            int fileSize=entrada.readInt();
            byte[] bytes= entrada.readNBytes(fileSize);
            String copyPath = "resources/temp/copy"+System.currentTimeMillis()+".png";
            File archivo = new File(copyPath);

            try (FileOutputStream fos = new FileOutputStream(archivo)) {
                fos.write(bytes);
            }
            files.add(archivo);

        }
        return files;
    }

}
