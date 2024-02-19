package co.edu.uptc.view;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFPrincipalWindow extends JFrame  {
    private JButton btnAddImage;
    private JButton btnViewImages;
    private JPanel panel;
    private JPanel panelImages;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;

    public JFPrincipalWindow(ActionListener l) {
        super("Subir y Visualizar Imágenes");
        initComponents(l);
        repaint();
    }

    private void initComponents(ActionListener l) {
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        panel = new JPanel();
        this.setContentPane(panel);
        panel.setLayout(null);

        btnAddImage = new JButton("Subir Imagen");
        btnAddImage.setBounds(10, 10, 120, 30);
        btnAddImage.addActionListener(l);
        btnAddImage.setActionCommand("SUBIR_IMAGEN");
        panel.add(btnAddImage);

        btnViewImages = new JButton("Visualizar Imagenes");
        btnViewImages.setBounds(150, 10, 127, 30);
        btnViewImages.addActionListener(l);
        btnViewImages.setActionCommand("OBTENER_IMAGENES");

        panel.add(btnViewImages);
        panelImages = new JPanel();
        panelImages.setVisible(false);
        scrollPane = new JScrollPane(panelImages);
        scrollPane.setBounds(10, 50, 450, 300);
        panel.add(scrollPane);

        fileChooser = new JFileChooser();
    }


    public String selectFile() {
       int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    private static void copyFile(File file) {
        try {
            String copyPath = "resources/Images/copy"+System.currentTimeMillis()+".png";
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image,"png",new File(copyPath));
            System.out.println("se ha copiado la imagen");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillPanel(ArrayList<byte[]>images) {
        panelImages.setLayout(new BoxLayout(panelImages,BoxLayout.Y_AXIS));
        for (byte[] image : images) {
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
                panelImages.add(preview);
                panelImages.setVisible(true);
                this.repaint();
            } catch (IOException ex) {
                Logger.getLogger(JFPrincipalWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
