package co.edu.uptc.runner;

import co.edu.uptc.controller.Control;

import java.io.IOException;

public class RunClient {
    public static void main(String[] args) {
        try {
            new Control();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
