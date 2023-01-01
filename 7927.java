import com.tildemh.jgwizard.*;
import org.gnu.gdk.*;
import org.gnu.gtk.*;
import org.gnu.pango.*;
import java.util.ResourceBundle;

/**
 * The second page of the wizard, responsible for detecting the folders to be
 * scanned.
 */
class FolderSelect extends WizardPage {

    private ResourceBundle messages;

    private Replacer btr;

    /** The content container to be shown on the screen */
    private VBox content;

    private Entry dirEntry;

    /**
	 * Creates the graphical elements and sets up signals
	 */
    FolderSelect(Replacer rep, ResourceBundle rb, WizardForm parent, WizardPage parentScreen, String title) {
        super(parent, parentScreen, title, null);
        messages = rb;
        btr = rep;
        content = new VBox(false, 0);
        Label headLbl = new Label(messages.getString("Head"));
        headLbl.setLineWrap(true);
        headLbl.setJustification(Justification.FILL);
        content.add(headLbl);
        dirEntry = new Entry();
        dirEntry.setText(System.getProperty("user.dir") + System.getProperty("file.separator"));
        content.add(dirEntry);
        Button selDir = new Button(messages.getString("SelDir"));
        content.add(selDir);
        setContent(content);
    }

    public void postShow() {
        btr.setRoot(dirEntry.getText());
    }

    FileSelection selector;

    /**
	 * Sets up and displays a modified file selection dialog to act as a folder
	 * selection dialog.
	 */
    private void showSelectBox() {
        selector = new FileSelection(messages.getString("SelDirTS"));
        selector.setFilename(dirEntry.getText());
        selector.show();
    }

    /**
	 * After the file selection dialog has run, this will change the selected
	 * directory.
	 */
    private void setDir() {
        dirEntry.setText(selector.getFilename());
        rmSelector();
    }

    /**
	 * Closes the folder selection dialog
	 */
    private void rmSelector() {
        selector.destroy();
    }
}
