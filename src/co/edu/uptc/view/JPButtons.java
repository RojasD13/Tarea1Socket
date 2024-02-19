package co.edu.uptc.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JPButtons extends JPanel {
    private JButton btnAddImage;
    private JButton btnViewImages;

    public JPButtons(ActionListener l) {
        initComponents(l);
    }

    private void initComponents(ActionListener l) {
        this.setLayout(null);
        btnAddImage = new JButton("Subir Imagen");
        btnAddImage.setBounds(10, 10, 120, 30);
        btnAddImage.addActionListener(l);
        btnAddImage.setActionCommand("SUBIR_IMAGEN");
        this.add(btnAddImage);

        btnViewImages = new JButton("Visualizar Imagenes");
        btnViewImages.setBounds(150, 10, 140, 30);
        btnViewImages.addActionListener(l);
        btnViewImages.setActionCommand("OBTENER_IMAGENES");
        this.add(btnViewImages);
    }
}
