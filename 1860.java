import sun.applet.*;
import java.applet.*;

class SunImport extends sun.security.tools.JarSigner {

    void f() {
        AppletViewer.main(null);
        sun.applet.AppletViewer.main(null);
        Applet app = new java.applet.Applet();
    }
}
