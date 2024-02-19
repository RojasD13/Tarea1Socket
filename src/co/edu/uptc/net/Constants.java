package co.edu.uptc.net;

import co.edu.uptc.view.Dialogs;

public interface Constants {
    Dialogs dialog = new Dialogs();

    int PORT = dialog.readPort();
    String HOST = dialog.readString();

}
