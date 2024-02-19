package co.edu.uptc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPImages extends JPanel {

    private ArrayList<byte[]> images;

    public JPImages(ArrayList<byte[]> images) {
        this.images = images;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.fillPanel();
        this.setVisible(true);
    }

    private void fillPanel() {
        for (byte[] image : this.images) {
            try {
                // Se crea una ByteArrayInputStream para leer el vector de bytes.
                ByteArrayInputStream bais = new ByteArrayInputStream(image);
                // Se crea una BufferedImage para almacenar la imagen.
                BufferedImage getImage = ImageIO.read(bais);
                // Se cierra la ByteArrayInputStream.
                bais.close();
                BufferedImage readImage = getImage;
                ImageIcon icon = new ImageIcon(readImage);
                JLabel preview = new JLabel(icon);
                this.add(preview);
            } catch (IOException ex) {
                Logger.getLogger(JFPrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
