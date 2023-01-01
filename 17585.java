import javax.swing.text.MutableAttributeSet;

class CPlain extends CText {

    public void setStyle(MutableAttributeSet styles) {
    }

    public void resetStyle(MutableAttributeSet styles) {
    }

    public String getHtmlPre() {
        String html = "";
        CText obj = _parent;
        while (obj != null) {
            if (obj instanceof CPlain) {
                obj = null;
            } else {
                html += obj.getHtmlPost();
                obj = obj.getParent();
            }
        }
        return (html);
    }

    public String getHtmlPost() {
        String html = "";
        CText obj = _parent;
        while (obj != null) {
            if (obj instanceof CPlain) {
                obj = null;
            } else {
                html += obj.getHtmlPre();
                obj = obj.getParent();
            }
        }
        return (html);
    }

    public CText newInstance() {
        return (new CPlain());
    }
}
