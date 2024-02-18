package co.edu.uptc.net;

import co.edu.uptc.view.Dialogs;

public interface Constants {
    Dialogs dialog = new Dialogs();

    int PORT = dialog.readPort("Ingrese el número de puerto a trabajar");
    String HOST = dialog.readString("Ingrese la dirección ip o host");

}
