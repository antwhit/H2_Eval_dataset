import java.awt.Color;
import org.gjt.sp.jedit.GUIUtilities;
import org.gjt.sp.jedit.syntax.SyntaxStyle;
import org.gjt.sp.util.Log;

public class HTMLCSSStyle extends HTMLStyle {

    public HTMLCSSStyle(SyntaxStyle[] styles) {
        super(styles);
    }

    public String toHTML(int styleId, String text) {
        StringBuffer buf = new StringBuffer();
        buf.append("<SPAN CLASS=\"syntax" + styleId + "\">").append(text).append("</SPAN>");
        return buf.toString();
    }

    public static String toCSS(SyntaxStyle[] styles) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < styles.length; i++) {
            buf.append(".syntax" + i + " {\n").append(toCSS(styles[i])).append("}\n");
        }
        return buf.toString();
    }

    public static String toCSS(SyntaxStyle style) {
        if (style == null) {
            Log.log(Log.DEBUG, HTMLCSSStyle.class, "toCSS(SyntaxStyle style): null style");
            return "";
        }
        StringBuffer buf = new StringBuffer();
        Color c;
        if ((c = style.getBackgroundColor()) != null) {
            buf.append("background: ").append(GUIUtilities.getColorHexString(c)).append(";\n");
        }
        if ((c = style.getForegroundColor()) != null) {
            buf.append("color: ").append(GUIUtilities.getColorHexString(c)).append(";\n");
        }
        if (style.isBold()) {
            buf.append("font-weight: bold;\n");
        }
        if (style.isItalic()) {
            buf.append("font-style: italic;\n");
        }
        return buf.toString();
    }
}
