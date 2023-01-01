import org.gjt.sp.jedit.*;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.util.Log;

public class InsertTextCommand extends java.lang.Object implements Command {

    public static void insertText(String text, ScriptContext context) {
        InsertTextCommand cmd = new InsertTextCommand(text);
        cmd.run(context);
    }

    public InsertTextCommand(String text) {
        this.text = text;
        this.src = text;
        this.result = new StringBuffer(src.length());
        this.i = 0;
    }

    public void run(ScriptContext context) {
        XTreeNode node = context.getNode();
        View view = context.getView();
        JEditTextArea textarea = view.getTextArea();
        Buffer buffer = view.getBuffer();
        int tabSize = buffer.getTabSize();
        char c;
        boolean braced;
        int caretpos = -1;
        for (i = 0; i < src.length(); i++) {
            c = src.charAt(i);
            switch(c) {
                case '|':
                    if (nextCharIs('|')) {
                        i++;
                        result.append(c);
                    } else {
                        result.append(context.getSelection());
                        caretpos = result.length();
                    }
                    break;
                case '\\':
                    if (nextCharIs('n')) {
                        i++;
                        result.append('\n');
                    } else if (nextCharIs('t')) {
                        i++;
                        if (jEdit.getBooleanProperty("buffer.noTabs", false)) for (int k = 0; k < tabSize; k++) result.append(" "); else result.append("\t");
                    } else if (nextCharIs('$')) {
                        i++;
                        result.append('$');
                    } else if (nextCharIs('|')) {
                        i++;
                        result.append('|');
                    } else if (nextCharIs('\\')) {
                        i++;
                        result.append(c);
                    } else result.append(c);
                    break;
                case '$':
                    braced = nextCharIs('{');
                    if (braced) i++;
                    i++;
                    int temp = i;
                    while (i < src.length() && Character.isLetterOrDigit(src.charAt(i))) i++;
                    String val = XScripter.getSubstituteFor(view, src.substring(temp, i), node);
                    if (val == null) result.append(src.substring(temp, i)); else result.append(val);
                    if (!braced) i--;
                    break;
                default:
                    result.append(c);
                    break;
            }
        }
        if (jEdit.getBooleanProperty("xtree.carriage", false)) result.append('\n');
        int caret = textarea.getCaretPosition();
        int len = result.length();
        if (len > 0) {
            textarea.setSelectedText(result.toString());
            textarea.setCaretPosition(caretpos > -1 ? caret + caretpos : caret + len);
        }
    }

    private boolean nextCharIs(char ch) {
        return (i < src.length() - 1) && (src.charAt(i + 1) == ch);
    }

    private int i;

    private String text;

    private String src;

    private StringBuffer result;
}
