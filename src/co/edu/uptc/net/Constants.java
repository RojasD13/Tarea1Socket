package co.edu.uptc.net;

import co.edu.uptc.view.Dialogs;

public interface Constants {
    Dialogs dialog = new Dialogs();
    String host = dialog.readString("Ingrese la dirección ip o host");
    int port = dialog.readPort("Ingrese el número de puedto a trabajar");
    String HOST = host;
    String PORT = "" + port;
}
