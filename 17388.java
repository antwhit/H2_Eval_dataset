import java.awt.Component;
import javax.swing.JLabel;
import java.util.ResourceBundle;

public class ResourceLabel extends JLabel implements MetadataField {

    protected String tag;

    protected String name;

    protected String desc;

    public ResourceLabel(String tag, ResourceBundle rb) {
        super();
        this.tag = tag;
        this.name = rb.getString(tag + "FieldName");
        this.desc = rb.getString(tag + "FieldDesc");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return desc;
    }

    public Object getValue() {
        return getText();
    }

    public void setValue(Object value) {
        if (value instanceof String) setText((String) value); else System.err.println("ResourceLabel.setValue: invalid type: " + value.getClass());
    }

    public boolean isSingleLine() {
        return true;
    }

    public Component getComponent() {
        return this;
    }
}
