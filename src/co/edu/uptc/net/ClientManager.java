package co.edu.uptc.net;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager extends Thread {
    private Socket client;
    private String path;

    public ClientManager(Socket client) {
        this.client = client;
        this.path = "resources/Images";
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(client.getInputStream());
            DataOutputStream output = new DataOutputStream(client.getOutputStream());

            int option = input.readInt();

            switch (option) {
                case 1:
                    addImage(input, output);
                    break;
                case 2:
                    getImages(input, output);
                    break;
                default:
                    System.out.println("Opción no válida: " + option);
                    break;
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addImage(DataInputStream input, DataOutputStream output) throws IOException {
        String nameImage = input.readUTF();
        int sizeImage = input.readInt();
        byte[] bytesImage = new byte[sizeImage];
        input.readFully(bytesImage);

        File file = new File(nameImage);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytesImage);
        }

        output.writeBoolean(true);
        output.flush();

        System.out.println("Imagen subida correctamente: " + nameImage);
    }

    private void getImages(DataInputStream input, DataOutputStream output) throws IOException {

        File[] files = imagesList(this.path);
        output.writeInt(files.length);

        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytesImage = new byte[(int) file.length()];
            fis.read(bytesImage);
            output.writeInt(bytesImage.length);
            output.write(bytesImage);
        }
        output.flush();
    }

    private File[] imagesList(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<File> images = new ArrayList<>();
        for (File file : files) {
            if (isImage(file)) {
                images.add(file);
            }
        }
        return images.toArray(new File[images.size()]);
    }

    private boolean isImage(File file) {
        String[] formats = {"jpg", "jpeg", "png", "gif"};
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        for (String format : formats) {
            if (format.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }
}
