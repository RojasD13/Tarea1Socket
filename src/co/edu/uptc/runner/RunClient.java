package co.edu.uptc.runner;

import co.edu.uptc.controller.Control;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.IOException;

public class RunClient {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            new Control();
        } catch (IOException | UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
