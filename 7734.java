import java.awt.event.ActionEvent;
import org.gjt.sp.jedit.*;
import org.gjt.sp.util.Log;

public class code2html_current_buffer extends EditAction {

    public code2html_current_buffer() {
        super("code2html.current-buffer");
    }

    public void actionPerformed(ActionEvent evt) {
        View v = EditAction.getView(evt);
        Buffer b = (v == null) ? null : v.getBuffer();
        if (v != null && b != null) {
            Code2HTML code2html = new Code2HTML();
            code2html.toHTML(v, b);
        }
    }
}
