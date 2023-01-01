import com.tildemh.jgwizard.*;
import org.gnu.gdk.*;
import org.gnu.gtk.*;
import org.gnu.pango.*;
import java.util.ResourceBundle;

/**
 * Screen for entering text to be found and replaced.
 */
class FindReplace extends WizardPage {

    private Replacer btr;

    private VBox content;

    private ResourceBundle messages;

    private TextBuffer findBuffer;

    private TextBuffer replaceBuffer;

    private CheckButton usingRegExp;

    FindReplace(Replacer rep, ResourceBundle rb, WizardForm parent, WizardPage parentScreen, String title) {
        super(parent, parentScreen, title, null);
        messages = rb;
        btr = rep;
        content = new VBox(false, 0);
        Label msgLbl = new Label(messages.getString("FindReplaceHead"));
        msgLbl.setLineWrap(true);
        msgLbl.setJustification(Justification.FILL);
        content.add(msgLbl);
        Label findLbl = new Label(messages.getString("FindTxt"));
        findLbl.setJustification(Justification.LEFT);
        content.add(findLbl);
        Label replaceLbl = new Label(messages.getString("ReplaceTxt"));
        replaceLbl.setJustification(Justification.RIGHT);
        content.add(replaceLbl);
        Label warnLbl = new Label(messages.getString("FindReplaceWarning"));
        warnLbl.setLineWrap(true);
        warnLbl.setJustification(Justification.FILL);
        content.add(warnLbl);
        setContent(content);
    }

    public void postShow() {
        TextIter start = null;
        TextIter end = null;
    }
}
