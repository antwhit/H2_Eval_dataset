import ac.hiu.j314.elmve.*;
import java.io.*;

public class Jump extends RealElm {

    public Jump() {
        elm2DUIClass = "ac.hiu.j314.elmve.ui.EButton2DUI";
    }

    public Serializable get2DUIData() {
        return "Jump";
    }

    public Serializable get2DUIRepaintData() {
        return null;
    }

    public void catchEButtonEvent(Order o) {
        double x = 10.0 * Math.random() - 5.0;
        double y = 10.0 * Math.random() - 5.0;
        double z = 10.0 * Math.random() - 5.0;
        setPlace(new Place(x, y, z));
        repaint();
    }
}
