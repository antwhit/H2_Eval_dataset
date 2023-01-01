import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class InternalFrameFactory implements MouseMotionListener, MouseListener {

    JInternalFrame window;

    InternalFrameFactory(JInternalFrame w) {
        w.setBorder(new PicDevBorder(Color.darkGray, 3));
        w.updateUI();
        w.setOpaque(false);
        w.updateUI();
        if (!(w instanceof ProjectWizard)) w.setClosable(true);
        w.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
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

    public void mouseEntered(MouseEvent e) {
        try {
            if (!Workbench.mousedown) window.setSelected(true);
        } catch (java.beans.PropertyVetoException pve) {
        }
    }

    public void mousePressed(MouseEvent e) {
        Workbench.mousedown = true;
    }

    public void mouseReleased(MouseEvent e) {
        Workbench.mousedown = false;
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent evt) {
    }
}
