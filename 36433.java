import javax.microedition.lcdui.List;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import java.util.Enumeration;

/**
 * A basic screen allowing the user to select the backup root FS.
 *
 * Needed to support phones where the memory card isn't on E:/, and running in the emulator
 */
public class RootFsScreen extends List implements CommandListener {

    private final Backup m_midlet;

    private final Displayable m_caller;

    private final Command m_selectCommand = new Command("Select", Command.OK, 1);

    private final Command m_returnCommand = new Command("Back", Command.BACK, 1);

    private BackupFileFactory m_fileFactory = new BackupFileFactory();

    public RootFsScreen(Backup midlet, Displayable caller) {
        super("Select backup file root", List.IMPLICIT);
        m_midlet = midlet;
        m_caller = caller;
        addCommand(m_selectCommand);
        addCommand(m_returnCommand);
        setCommandListener(this);
        Display.getDisplay(m_midlet).setCurrent(this);
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == m_selectCommand) {
            String fsRoot = getString(getSelectedIndex());
            Backup.debug("Setting backup FS root to: " + fsRoot);
            BackupFileFactory.setFilesystemRoot(fsRoot);
            String message = "Backup file root now set to:\n" + fsRoot;
            if (Backup.DEBUG) {
                message += m_fileFactory.describeFileSystem(fsRoot);
            }
            Alert alert = new Alert("Backup root set", message, null, AlertType.INFO);
            alert.setTimeout(2000);
            Display.getDisplay(m_midlet).setCurrent(alert, m_caller);
        } else if (command == m_returnCommand) {
            Display.getDisplay(m_midlet).setCurrent(m_caller);
        }
    }

    public void selectRootFilesystem() {
        Enumeration fsRoots = m_fileFactory.getAllFilesystemRoots();
        while (fsRoots.hasMoreElements()) {
            String fsRoot = (String) fsRoots.nextElement();
            append(fsRoot, null);
            Backup.debug("FS root: " + fsRoot);
        }
    }
}
