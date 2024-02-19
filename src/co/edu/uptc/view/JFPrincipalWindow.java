package co.edu.uptc.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JFPrincipalWindow extends JFrame  {
    private JPButtons jpButtons;
    private JPImages panelImages;
    private JFileChooser fileChooser;
    private JScrollPane scrollPane;

    public JFPrincipalWindow(ActionListener l) {
        super("Subir y Visualizar Imágenes");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents(l);
    }

    private void initComponents(ActionListener l) {
        this.setLayout(null);
        jpButtons = new JPButtons(l);
        jpButtons.setBounds(0,0,500,80);
        this.add(jpButtons);
        panelImages = new JPImages(new ArrayList<>());
        scrollPane = new JScrollPane(panelImages);
        scrollPane.setBounds(10, 100, 450, 300);
        this.add(scrollPane);

        fileChooser = new JFileChooser();
        this.setVisible(true);
    }


    public String selectFile() {
       int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    public void fillPanel(ArrayList<byte[]>images) {
        this.remove(this.scrollPane);
        this.panelImages = new JPImages(images);
        scrollPane = new JScrollPane(panelImages);
        scrollPane.setBounds(10, 100, 450, 300);

        this.scrollPane.setVisible(true);
        this.add(scrollPane);
        this.setVisible(true);
    }

}
