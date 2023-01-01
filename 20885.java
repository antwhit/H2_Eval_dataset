import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;

public class aptMine extends Applet {

    Button a, b;

    int level = 0;

    jMine assoc;

    boolean isStandalone = false;

    public String getParameter(String key, String def) {
        return isStandalone ? System.getProperty(key, def) : (getParameter(key) != null ? getParameter(key) : def);
    }

    public void init() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(a = new Button("New Window"));
        add(p);
        assoc = new jMine();
        assoc.resize(400, 520);
        assoc.show();
    }

    public String getAppletInfo() {
        return "Applet Information";
    }

    public String[][] getParameterInfo() {
        return null;
    }
}
