import com.apple.mrj.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.gui.*;
import org.gjt.sp.jedit.msg.*;
import org.gjt.sp.util.Log;

public class MacOSPlugin extends EBPlugin implements MRJQuitHandler, MRJAboutHandler, MRJOpenDocumentHandler, MRJPrefsHandler {

    private boolean onStartup = true;

    private String lastFilePath;

    private ExitThread exitThread = new ExitThread();

    private final MRJOSType defaultType = new MRJOSType(jEdit.getProperty("MacOS.default.type"));

    private final MRJOSType defaultCreator = new MRJOSType(jEdit.getProperty("MacOS.default.creator"));

    public void start() {
        if (System.getProperty("os.name").indexOf("Mac OS") != -1) {
            MRJApplicationUtils.registerQuitHandler(this);
            MRJApplicationUtils.registerAboutHandler(this);
            MRJApplicationUtils.registerPrefsHandler(this);
            MRJApplicationUtils.registerOpenDocumentHandler(this);
        } else {
            Log.log(Log.ERROR, this, "This plugin requires Mac OS.");
        }
    }

    public void handleQuit() {
        if (!exitThread.isAlive()) exitThread.start(); else Log.log(Log.DEBUG, this, "exitThread still alive.");
    }

    public void handleAbout() {
        new AboutDialog(jEdit.getLastView());
    }

    public void handlePrefs() {
        new OptionsDialog(jEdit.getLastView());
    }

    public void handleOpenFile(File file) {
        if (jEdit.openFile(jEdit.getLastView(), file.getPath()) != null) {
            lastFilePath = file.getPath();
        } else {
            Log.log(Log.ERROR, this, "Error opening file.");
        }
    }

    public void handleMessage(EBMessage message) {
        if ((message instanceof ViewUpdate) && onStartup) {
            if (((ViewUpdate) message).getWhat() == ViewUpdate.CREATED) {
                if (lastFilePath != null) {
                    jEdit.getLastView().setBuffer(jEdit.getBuffer(lastFilePath));
                }
                onStartup = false;
            }
        } else if (message instanceof BufferUpdate) {
            Buffer buffer = ((BufferUpdate) message).getBuffer();
            if (((BufferUpdate) message).getWhat() == BufferUpdate.DIRTY_CHANGED && !buffer.isDirty()) {
                try {
                    MRJFileUtils.setFileTypeAndCreator(buffer.getFile(), (MRJOSType) (buffer.getProperty("MacOS.type")), (MRJOSType) (buffer.getProperty("MacOS.creator")));
                } catch (Exception e) {
                    Log.log(Log.ERROR, this, "Error setting type/creator: file missing");
                }
            } else if (((BufferUpdate) message).getWhat() == BufferUpdate.CREATED) {
                buffer.setProperty("MacOS.type", defaultType);
                buffer.setProperty("MacOS.creator", defaultCreator);
                try {
                    MRJOSType type = MRJFileUtils.getFileType(buffer.getFile());
                    MRJOSType creator = MRJFileUtils.getFileCreator(buffer.getFile());
                    if (!type.equals(new MRJOSType("")) && !creator.equals(new MRJOSType(""))) {
                        buffer.setProperty("MacOS.type", type);
                        buffer.setProperty("MacOS.creator", creator);
                    }
                } catch (Exception e) {
                }
                Log.log(Log.DEBUG, this, "Assigned MRJOSTypes " + buffer.getProperty("MacOS.type") + "/" + buffer.getProperty("MacOS.creator") + " to " + buffer.getName());
            }
        }
    }

    class ExitThread extends Thread {

        public void run() {
            jEdit.exit(jEdit.getLastView(), false);
            exitThread = new ExitThread();
        }
    }
}
