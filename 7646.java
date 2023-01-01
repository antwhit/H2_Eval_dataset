import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.util.Vector;
import java.io.*;

/**
 * The 'Plugin' class is the interface between jEdit and the plugin.
 * Plugins can either extend EditPlugin or EBPlugin. EBPlugins have
 * the additional property that they receive EditBus messages.
 */
public class AntFarmPlugin extends EBPlugin {

    /**
   * The 'name' of our dockable window.
   */
    public static final String NAME = "antfarm";

    private static DefaultErrorSource errorSource;

    static PrintStream out = System.out;

    static PrintStream err = System.err;

    private View theView;

    /**
   * Method called by jEdit to initialize the plugin.
   */
    public void start() {
        errorSource = new DefaultErrorSource("antfarm");
        EditBus.addToNamedList(ErrorSource.ERROR_SOURCES_LIST, errorSource);
        EditBus.addToBus(errorSource);
        EditBus.addToNamedList(DockableWindow.DOCKABLE_WINDOW_LIST, NAME);
    }

    /**
   * Method called every time a view is created to set up the
   * Plugins menu. Menus and menu items should be loaded using the
   * methods in the GUIUtilities class, and added to the list.
   * @param menuItems Add the menu item here
   */
    public void createMenuItems(Vector menuItems) {
        menuItems.addElement(GUIUtilities.loadMenuItem("antfarm"));
    }

    /**
   * Handles a message sent on the EditBus. The default
   * implementation ignores the message.
   */
    public void handleMessage(EBMessage message) {
        if (message instanceof CreateDockableWindow) {
            CreateDockableWindow cmsg = (CreateDockableWindow) message;
            theView = cmsg.getView();
            if (cmsg.getDockableWindowName().equals(NAME)) cmsg.setDockableWindow(AntFarm.setAntFarm(this, theView));
        }
    }

    /**
   * Handle all of ANT's build messages including System.out and System.err
   *
   * Each build message filename, line no, column, of the error that it is
   * reporting if it in fact is an Error.  You can use this info some how
   * to publish an event to the Error List
   *
   * TODO: handle System.err messages with a different color.
   */
    void handleBuildMessage(BuildMessage message) {
        handleBuildMessage(message, null);
    }

    void handleBuildMessage(BuildMessage message, Color lineColor) {
        if (message.isError()) {
            addError(ErrorSource.ERROR, message.getAbsoluteFilename(), message.getLine(), message.getColumn(), message.toString());
            theView.getDockableWindowManager().addDockableWindow("error-list");
        } else if (message.isWarning()) {
            addError(ErrorSource.WARNING, message.getAbsoluteFilename(), message.getLine(), message.getColumn(), message.toString());
            theView.getDockableWindowManager().addDockableWindow("error-list");
        } else {
            if (lineColor != null) AntFarm.getAntFarm().appendToTextArea(message.toString(), lineColor); else AntFarm.getAntFarm().appendToTextArea(message.toString());
        }
    }

    void addError(int type, String file, int line, int column, String message) {
        errorSource.addError(type, file, line - 1, 0, 0, message);
    }

    void clearErrors() {
        errorSource.clear();
    }
}
