import java.awt.Component;
import net.sf.compositor.AppMac;

public class DragDrop2 extends AppMac {

    public static void main(final String[] args) {
        new DragDrop2();
    }

    public void doAbout() {
        msgBox("Example of Compositor built in drag and drop handling");
    }

    public void main__onLoad() {
        doAbout();
    }

    public void main__onClosing() {
        final StringBuilder buf = new StringBuilder("Right hand components:");
        for (Component c : (Component[]) get("main.used").call("getComponents")) {
            buf.append("\n").append(c.getName());
        }
        msgBox(getFrame("main"), buf.toString());
    }
}
