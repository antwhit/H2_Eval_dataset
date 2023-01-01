import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class WindowFixer implements MouseMotionListener {

    JInternalFrame window;

    WindowFixer(JInternalFrame w) {
        w.addMouseMotionListener(this);
        w.setBorder(new PicDevBorder(Color.darkGray, 3));
        w.updateUI();
        w.setOpaque(false);
        w.updateUI();
        window = w;
    }

    public JInternalFrame GetWindow() {
        return window;
    }

    public void mouseMoved(MouseEvent e) {
        window.setOpaque(false);
    }

    public void mouseDragged(MouseEvent e) {
        window.setOpaque(false);
    }
}
