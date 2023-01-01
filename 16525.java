import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import org.gjt.sp.jedit.AbstractOptionPane;
import org.gjt.sp.jedit.jEdit;

/**
 * Option pane for the Toggle Comments actions.
 *
 * @author    <a href="mailto:rfletch6@yahoo.co.uk">Robert Fletcher</a>
 * @version   $Revision: 20902 $ $Date: 2012-01-21 03:51:18 -0500 (Sat, 21 Jan 2012) $
 */
public class TextToolsCommentsOptionPane extends AbstractOptionPane {

    /** Constructor for the <code>TextToolsCommentsOptionPane</code> object. */
    public TextToolsCommentsOptionPane() {
        super("toggle-comments");
    }

    /** Initialises the option pane. */
    public void _init() {
        keepSelected = new JCheckBox(jEdit.getProperty("options.toggle-comments.keepSelected.title"), jEdit.getBooleanProperty("options.toggle-comments.keepSelected"));
        addComponent(keepSelected);
        addSeparator("options.toggle-comments.line-comments.title");
        commentAsBlock = new JCheckBox(jEdit.getProperty("options.toggle-comments.commentAsBlock.title"), jEdit.getBooleanProperty("options.toggle-comments.commentAsBlock"));
        addComponent(commentAsBlock);
        moveDown = new JCheckBox(jEdit.getProperty("options.toggle-comments.moveDown.title"), jEdit.getBooleanProperty("options.toggle-comments.moveDown"));
        addComponent(moveDown);
        indentAtLineStart = new JRadioButton(jEdit.getProperty("options.toggle-comments.indentAtLineStart.title"), jEdit.getBooleanProperty("options.toggle-comments.indentAtLineStart"));
        indentWithCode = new JRadioButton(jEdit.getProperty("options.toggle-comments.indentWithCode.title"), jEdit.getBooleanProperty("options.toggle-comments.indentWithCode"));
        indentAsBlock = new JRadioButton(jEdit.getProperty("options.toggle-comments.indentAsBlock.title"), jEdit.getBooleanProperty("options.toggle-comments.indentAsBlock"));
        addComponent(indentAtLineStart);
        addComponent(indentWithCode);
        addComponent(indentAsBlock);
        lineIndentMode = new ButtonGroup();
        lineIndentMode.add(indentAtLineStart);
        lineIndentMode.add(indentWithCode);
        lineIndentMode.add(indentAsBlock);
    }

    /** Saves properties from the option pane. */
    public void _save() {
        jEdit.setBooleanProperty("options.toggle-comments.keepSelected", keepSelected.isSelected());
        jEdit.setBooleanProperty("options.toggle-comments.commentAsBlock", commentAsBlock.isSelected());
        jEdit.setBooleanProperty("options.toggle-comments.moveDown", moveDown.isSelected());
        jEdit.setBooleanProperty("options.toggle-comments.indentAtLineStart", indentAtLineStart.isSelected());
        jEdit.setBooleanProperty("options.toggle-comments.indentWithCode", indentWithCode.isSelected());
        jEdit.setBooleanProperty("options.toggle-comments.indentAsBlock", indentAsBlock.isSelected());
    }

    private JCheckBox keepSelected;

    private JCheckBox commentAsBlock;

    private JCheckBox moveDown;

    private ButtonGroup lineIndentMode;

    private JRadioButton indentAtLineStart;

    private JRadioButton indentWithCode;

    private JRadioButton indentAsBlock;
}
