import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

class NewAlgorithmCommand implements ActionListener {

    AppMain app;

    NewAlgorithmCommand(AppMain app) {
        this.app = app;
    }

    public void actionPerformed(ActionEvent e) {
        app.new_algorithm();
        app.get_gui().prepare_edit_screen();
    }
}
