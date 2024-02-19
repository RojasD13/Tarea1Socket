package co.edu.uptc.net;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager extends Thread {
    private Socket client;
    private String path;
    private boolean isRunning;

    public ClientManager(Socket client) {
        this.client = client;
        this.path = "resources/Images";
        isRunning = true;
    }

    @Override
    public void run() {
        try {

            while (isRunning) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addImage(DataInputStream input, DataOutputStream output) throws IOException {
        String nameImagePath = path + "/copy" + System.currentTimeMillis() + ".png";
        int sizeImage = input.readInt();
        byte[] bytesImage = input.readNBytes(sizeImage);
        File file = new File(nameImagePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytesImage);
        }
        output.flush();
    }

    private void getImages(DataInputStream input, DataOutputStream output) throws IOException {
        File[] directory = imagesList(this.path);
        output.writeInt(directory.length);
        byte[] bytesImage;
        for (File file : directory) {
            FileInputStream fis = new FileInputStream(file);
            long fileSize = file.length();
            bytesImage = new byte[(int) fileSize];
            int readBytes = fis.read(bytesImage);
            fis.close();
            if (readBytes != fileSize) {
                throw new IOException("No se pudo leer todo el archivo.");
            }
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
