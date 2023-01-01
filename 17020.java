import com.apple.mrj.*;
import javax.swing.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.util.Log;

public class MacOSPlugin extends EBPlugin {

    private boolean started = false;

    private boolean osok;

    private Handler handler;

    public void start() {
        if (osok = osok()) {
            handler = new MacOSHandler();
            MRJApplicationUtils.registerQuitHandler((MRJQuitHandler) handler);
            MRJApplicationUtils.registerAboutHandler((MRJAboutHandler) handler);
            MRJApplicationUtils.registerPrefsHandler((MRJPrefsHandler) handler);
            MRJApplicationUtils.registerOpenDocumentHandler((MRJOpenDocumentHandler) handler);
        }
    }

    public void createOptionPanes(OptionsDialog od) {
        if (osok) od.addOptionPane(new MacOSOptionPane());
    }

    public void handleMessage(EBMessage message) {
        if (osok) {
            if (!started && message instanceof ViewUpdate) {
                handler.handleOpenFile((ViewUpdate) message);
            } else if (message instanceof BufferUpdate) {
                handler.handleFileCodes((BufferUpdate) message);
            }
        }
    }

    /**
	 * Returns true once all initialisations have been done
	 */
    public boolean started() {
        return started;
    }

    public void started(boolean v) {
        started = v;
    }

    private boolean osok() {
        final String osname = jEdit.getProperty("MacOSPlugin.depend.os.name");
        final String mrjversion = jEdit.getProperty("MacOSPlugin.depend.mrj.version");
        if (!System.getProperty("os.name").equals(osname)) {
            Log.log(Log.ERROR, this, jEdit.getProperty("MacOSPlugin.dialog.osname.message"));
            return false;
        }
        if (!System.getProperty("mrj.version").equals(mrjversion)) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    GUIUtilities.error(null, "MacOSPlugin.dialog.mrjversion", new Object[] { mrjversion });
                }
            });
            return false;
        }
        return true;
    }
}
