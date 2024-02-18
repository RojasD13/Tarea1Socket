package co.edu.uptc.view;

import javax.swing.*;

public class Dialogs {

    public String readString(String message) {
        String input;
        while (true) {
            try {
                input = JOptionPane.showInputDialog(null, message);
                if (input.isEmpty()) {
                    showMessage("No se permiten campos vacíos. Intente de nuevo.");
                }
                return input;
            } catch (java.util.InputMismatchException e) {
                showMessage("Dato inválido, intente de nuevo.");
            }
        }
    }

    public int readPort(String message) {
        int num = 0;
        try {
            num = Integer.parseInt(JOptionPane.showInputDialog(null, message));
        }catch (NumberFormatException e) {
            showMessage("Ingrese un número de puerto válido");
        }
        return num;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
}
