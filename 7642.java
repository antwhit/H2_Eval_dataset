import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.jedit.EditPlugin;
import org.gjt.sp.jedit.textarea.JEditTextArea;
import org.gjt.sp.jedit.View;
import superabbrevs.SuperAbbrevs;
import superabbrevs.DisplayAbbrevs;

public class SuperAbbrevsPlugin extends EditPlugin {

    public static final String NAME = "SuperAbbrevs";

    public void start() {
        SuperAbbrevs.makeDefaults();
    }

    public static void shiftTab(View view, JEditTextArea textArea, Buffer buffer) {
        SuperAbbrevs.shiftTab(view, textArea, buffer);
    }

    public static void tab(View view, JEditTextArea textArea, Buffer buffer) {
        SuperAbbrevs.tab(view, textArea, buffer);
    }

    public static void showDialog(View view, JEditTextArea textArea, Buffer buffer) {
        SuperAbbrevs.showAbbrevDialog(view, textArea, buffer);
    }

    public static void displayModeAbbrevs(View view, JEditTextArea textArea, Buffer buffer) {
        DisplayAbbrevs.displayModeAbbrevs(view, textArea, buffer);
    }

    public static void displayAllAbbrevs(View view, JEditTextArea textArea, Buffer buffer) {
        DisplayAbbrevs.displayAllAbbrevs(view, textArea, buffer);
    }
}
