import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.PropertyEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import org.apache.commons.lang.ObjectUtils;
import org.openide.explorer.propertysheet.InplaceEditor;
import org.openide.explorer.propertysheet.PropertyEnv;
import org.openide.explorer.propertysheet.PropertyModel;

/**
 *
 * @author deniger
 */
public class InplaceEditorString extends JLabel implements InplaceEditor {

    private PropertyEditor editor = null;

    @Override
    public void connect(PropertyEditor propertyEditor, PropertyEnv env) {
        editor = propertyEditor;
        reset();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }

    @Override
    public void clear() {
        editor = null;
        model = null;
    }

    @Override
    public Object getValue() {
        return getText();
    }

    @Override
    public void setValue(Object object) {
        setText(ObjectUtils.toString(object));
    }

    @Override
    public boolean supportsTextEntry() {
        return true;
    }

    @Override
    public void reset() {
        String d = (String) editor.getValue();
        setText(ObjectUtils.toString(d));
    }

    @Override
    public KeyStroke[] getKeyStrokes() {
        return new KeyStroke[0];
    }

    @Override
    public PropertyEditor getPropertyEditor() {
        return editor;
    }

    @Override
    public PropertyModel getPropertyModel() {
        return model;
    }

    private PropertyModel model;

    @Override
    public void setPropertyModel(PropertyModel propertyModel) {
        this.model = propertyModel;
    }

    @Override
    public boolean isKnownComponent(Component component) {
        return component == this || this.isAncestorOf(component);
    }

    @Override
    public void addActionListener(ActionListener actionListener) {
    }

    public void removeActionListener(ActionListener actionListener) {
    }
}
