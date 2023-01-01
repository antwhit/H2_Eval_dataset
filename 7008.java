import java.awt.*;

public abstract class Helper extends java.applet.Applet {

    abstract String[] getNames();

    abstract Object[] getWidgets();

    protected Database db;

    protected Demo2 gui;

    public void init() {
        db = new Database("Demo2.db");
        gui = (Demo2) this;
    }

    public void getData() {
        String value = (String) db.get(gui.name.getText());
        if (value == null) {
            gui.message.setText("Key not found in database");
        } else {
            gui.phone.setText(value);
            gui.message.setText("");
        }
    }
}
