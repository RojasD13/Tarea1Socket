package co.edu.uptc.view;

import javax.swing.*;

public class Dialogs {

    public String readString() {
        String input = "";
        String message = "Ingrese la dirección ip o host";
        try {
            input = JOptionPane.showInputDialog(null, message);
            if (!validateIP(input)) {
                showMessage("No se permiten campos vacíos. Intente de nuevo.");
                readString();
            } else {
                return input;
            }
        } catch (NullPointerException ne) {
            System.exit(0);
        }
        return "";
    }

    public static boolean validateIP(String input) {
        // Se comprueba si la entrada es "localhost".
        if (input.equalsIgnoreCase("localhost")) {
            return true;
        }

        // Se comprueba si la entrada es una dirección IP válida.
        try {
            // Se divide la entrada en cuatro partes.
            String[] parts = input.split("\\.");

            // Se comprueba que haya cuatro partes.
            if (parts.length != 4) {
                return false;
            }

            // Se comprueba que cada parte sea un número entero entre 0 y 255.
            for (String part : parts) {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 255) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            // La entrada no es un número entero.
            return false;
        }

        // La entrada es una dirección IP válida.
        return true;
    }

    public int readPort() {
        int num = 0;
        String message = "Ingrese el número de puerto a trabajar";
        try {
            num = Integer.parseInt(JOptionPane.showInputDialog(null, message));
        } catch (NumberFormatException e) {
            System.exit(0);
        }
        return num;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
}
