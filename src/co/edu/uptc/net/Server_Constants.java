package co.edu.uptc.net;

import co.edu.uptc.view.Dialogs;

public interface Server_Constants {
    Dialogs dialog = new Dialogs();

    int PORT = dialog.readPort();

}
