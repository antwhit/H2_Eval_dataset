import org.ozoneDB.OzoneObject;

public class OO7_DocumentImpl extends OzoneObject implements OO7_Document {

    String theTitle;

    long theId;

    String theText;

    public OO7_DocumentImpl() {
    }

    public void setTitle(String x) {
        theTitle = x;
    }

    public String title() {
        return theTitle;
    }

    public void setId(long x) {
        theId = x;
    }

    public long id() {
        return theId;
    }

    public void setText(String x) {
        theText = x;
    }

    public String text() {
        return theText;
    }
}
