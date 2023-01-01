import javax.swing.text.StyleConstants;
import javax.swing.text.MutableAttributeSet;

class CUnderline extends CText {

    public void setStyle(MutableAttributeSet styles) {
        StyleConstants.setUnderline(styles, true);
    }

    public void resetStyle(MutableAttributeSet styles) {
        StyleConstants.setUnderline(styles, false);
    }

    public String getHtmlPre() {
        return ("<u>");
    }

    public String getHtmlPost() {
        return ("</u>");
    }

    public CText newInstance() {
        return (new CUnderline());
    }
}
