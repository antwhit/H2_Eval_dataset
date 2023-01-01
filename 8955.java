import org.gjt.sp.jedit.jEdit;

public class HTMLCSSGutter extends HTMLGutter {

    public HTMLCSSGutter() {
        super();
    }

    public HTMLCSSGutter(int gutterSize) {
        super(gutterSize);
    }

    public String toHTML(int lineNumber) {
        StringBuffer buf = new StringBuffer();
        String style = "gutter";
        if ((this.highlightInterval > 0) && (lineNumber % this.highlightInterval == 0)) {
            style = "gutterH";
        }
        String s = Integer.toString(lineNumber);
        buf.append("<SPAN CLASS=\"" + style + "\">").append(spacer.substring(0, this.gutterSize - s.length())).append(s).append(this.gutterBorder).append("</SPAN>");
        return buf.toString();
    }

    public String toEmptyHTML(int lineNumber) {
        boolean highlighted = ((this.highlightInterval > 0) && (lineNumber % this.highlightInterval == 0));
        StringBuffer buf = new StringBuffer();
        String style = "gutter";
        if (highlighted) {
            style = "gutterH";
        }
        buf.append("<SPAN CLASS=\"" + style + "\">").append(spacer).append(':').append("</SPAN>");
        return buf.toString();
    }

    public String toCSS() {
        StringBuffer buf = new StringBuffer();
        buf.append(".gutter {\n").append("background: " + this.bgColor + ";\n").append("color: " + this.fgColor + ";\n").append("}\n").append(".gutterH {\n").append("background: " + this.bgColor + ";\n").append("color: " + this.highlightColor + ";\n").append("}\n");
        return buf.toString();
    }
}
