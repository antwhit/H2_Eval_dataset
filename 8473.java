import java.lang.System;
import waba.ui.*;
import waba.applet.Applet;
import waba.fx.Graphics;

public class VJUltima extends MainWindow {

    public final int VJ_ULTIMA_TICS = 250;

    VJUltimaObject m_oVJUltimaObject;

    VJUltimaTool m_oVJUltimaTool;

    public VJUltima() {
        if (waba.sys.Vm.getPlatform() == "Java") {
            m_oVJUltimaTool = new VJUltimaToolJava();
        }
    }

    public void onEvent(Event event) {
        if (event.type == KeyEvent.KEY_PRESS) {
            KeyEvent ke = (KeyEvent) event;
            if (((ke.modifiers & IKeys.CONTROL) != 0) && (ke.key == 17)) {
                exit(0);
            }
            if (ke.modifiers == 0) {
                m_oVJUltimaObject = m_oVJUltimaObject.onEvent(event);
            }
        }
    }

    public void onPaint(Graphics g) {
        m_oVJUltimaObject.onPaint(g);
    }

    public void onStart() {
        String bmpName = waba.applet.Applet.currentApplet.getParameter("bmp");
        System.out.println("Test");
        m_oVJUltimaObject = new VJUltimaBmp(this, bmpName);
        repaint();
    }

    public void onExit() {
    }
}
