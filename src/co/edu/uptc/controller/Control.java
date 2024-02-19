package co.edu.uptc.controller;

import co.edu.uptc.net.Client;
import co.edu.uptc.view.JFPrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Control implements ActionListener {
    private JFPrincipalWindow window;
    private Client client;


    public Control() throws IOException {
        window = new JFPrincipalWindow(this);
        client = new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SUBIR_IMAGEN":
                try {
                    String path = window.selectFile();
                    client.addImage(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "OBTENER_IMAGENES":
                try {
                    window.fillPanel(client.getImage());
                    window.repaint();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

}
