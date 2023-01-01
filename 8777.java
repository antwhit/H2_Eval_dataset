import jcurses.widgets.*;
import jcurses.system.*;

public class OutWindow extends Window {

    public OutWindow(int arg0, int arg1, boolean arg2, String arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public void refresh() {
        this.repaint();
    }
}
