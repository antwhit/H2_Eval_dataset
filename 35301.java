import java.applet.*;
import java.awt.event.*;

public class InnerClassDemo3 extends Applet {

    public void init() {
        addMouseListener(new MyMouseAdapter());
    }

    class MyMouseAdapter extends MouseAdapter {

        public void mousePressed(MouseEvent me) {
            showStatus("Mouse Pressed");
        }
    }
}
