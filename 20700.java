import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.util.Vector;
import org.gjt.sp.jedit.gui.DockableWindow;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

public class XmlPlugin extends EBPlugin {

    public static final String NAME = "xml-tree";

    public void start() {
        documentHandler = new DocumentHandler();
        EditBus.addToNamedList(DockableWindow.DOCKABLE_WINDOW_LIST, NAME);
        errorSource = new DefaultErrorSource("XML");
        EditBus.addToNamedList(ErrorSource.ERROR_SOURCES_LIST, errorSource);
    }

    public void createMenuItems(Vector menuItems) {
        menuItems.addElement(GUIUtilities.loadMenuItem("xml-tree"));
    }

    public void handleMessage(EBMessage msg) {
        if (msg instanceof CreateDockableWindow) {
            CreateDockableWindow cmsg = (CreateDockableWindow) msg;
            if (cmsg.getDockableWindowName().equals(NAME)) cmsg.setDockableWindow(new XmlTree(cmsg.getView()));
        } else if (msg instanceof BufferUpdate) {
            BufferUpdate bu = (BufferUpdate) msg;
            Buffer buffer = bu.getBuffer();
            if (bu.getWhat() == BufferUpdate.MODE_CHANGED || bu.getWhat() == BufferUpdate.LOADED) {
                if (buffer.getName().toLowerCase().endsWith(".dtd")) {
                    buffer.putProperty("useXmlPlugin", Boolean.FALSE);
                }
                if (buffer.getBooleanProperty("useXmlPlugin")) buffer.addDocumentListener(documentHandler); else buffer.removeDocumentListener(documentHandler);
            } else if (bu.getWhat() == BufferUpdate.CLOSED) buffer.removeDocumentListener(documentHandler);
        }
    }

    static void parse(Buffer buffer) {
        if (!buffer.getBooleanProperty("useXmlPlugin")) {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(buffer.getName());
            DefaultTreeModel model = new DefaultTreeModel(root);
            root.insert(new DefaultMutableTreeNode(jEdit.getProperty("xml-tree.not-xml-file")), 0);
            model.reload(root);
            EditBus.send(new XmlTreeParsed(buffer, model));
            return;
        }
        if (daemon != null) {
            daemon.stop();
            daemon = null;
        }
        errorSource.clear();
        daemon = new XmlDaemon(buffer);
        daemon.start();
    }

    static void daemonFinished() {
        daemon = null;
    }

    static void addError(String file, int line, String message) {
        errorSource.addError(ErrorSource.ERROR, file, line, 0, 0, message);
    }

    private static DocumentHandler documentHandler;

    private static Timer timer;

    private static XmlDaemon daemon;

    private static DefaultErrorSource errorSource;

    private static void parseWithDelay(Buffer buffer) {
        if (timer != null) timer.stop();
        timer = new Timer(0, new XmlTimer(buffer));
        timer.setInitialDelay(500);
        timer.setRepeats(false);
        timer.start();
    }

    static class DocumentHandler implements DocumentListener {

        public void insertUpdate(DocumentEvent evt) {
            parseWithDelay((Buffer) evt.getDocument());
        }

        public void removeUpdate(DocumentEvent evt) {
            parseWithDelay((Buffer) evt.getDocument());
        }

        public void changedUpdate(DocumentEvent evt) {
        }
    }

    static class XmlTimer implements ActionListener {

        Buffer buffer;

        XmlTimer(Buffer buffer) {
            this.buffer = buffer;
        }

        public void actionPerformed(ActionEvent evt) {
            parse(buffer);
        }
    }
}
