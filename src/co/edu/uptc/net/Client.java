package co.edu.uptc.net;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;


    public Client() throws IOException {
        init();
    }

    private void init() throws IOException {
        this.client = new Socket(Constants.HOST, Constants.PORT);
        this.input = new DataInputStream(client.getInputStream());
        this.output = new DataOutputStream(client.getOutputStream());
        System.out.println("Cliente conectado al servidor " + client.getInetAddress().getHostName() + " en el puerto " + client.getPort());
    }

    public void addImage(String path) throws IOException {
        output.writeInt(1);
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        // Se calcula el tamaño del archivo para crear un vector de bytes del tamaño adecuado.
        long fileSize = file.length();
        byte[] bytesFile = new byte[(int) fileSize];

        // Se lee el archivo en el vector de bytes.
        int readBytes = fis.read(bytesFile);

        // Se cierra la FileInputStream.
        fis.close();

        // Se verifica que se haya leído todo el archivo.
        if (readBytes != fileSize) {
            throw new IOException("No se pudo leer todo el archivo.");
        }

        output.writeInt(bytesFile.length);
        output.write(bytesFile);
        output.flush();

        System.out.println("Imagen subida correctamente por el cliente.");
    }

    public ArrayList<File> getImage() throws IOException {
        output.writeInt(2);
        int directorySize= input.readInt();
        ArrayList<File> files= new ArrayList<>();
        for (int i = 0; i < directorySize ; i++) {
            int fileSize= input.readInt();
            byte[] bytes= input.readNBytes(fileSize);
            String copyPath = "resources/temp/copy"+System.currentTimeMillis()+".png";
            File file = new File(copyPath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(bytes);
            }
            files.add(file);
        }
        return files;
    }

}
