import java.util.Arrays;
import java.util.Map;
import java.lang.reflect.Array;
import javax.vecmath.*;
import org.web3d.x3d.sai.*;

/**
 * 
 * Apply a rotation to a translation
 * @author Jesús Felipe Martínez Martínez
 *
 */
public class RotationSensor_SizeHandler implements X3DScriptImplementation, X3DFieldEventListener {

    private SFVec3f sizeField;

    private SFVec3f sizeTransformField;

    float[] size = new float[] { 0.0f, 0.0f, 0.0f };

    float[] sizeTransform = new float[] { 0.0f, 0.0f, 0.0f };

    /** A mapping for fieldName(String) to an X3DField object */
    private Map fields;

    /** The browser */
    private Browser browser;

    public RotationSensor_SizeHandler() {
    }

    @Override
    public void readableFieldChanged(X3DFieldEvent evt) {
        X3DField sourceField = (X3DField) evt.getSource();
        if (sourceField.equals(sizeField)) {
            sizeUpdated();
        }
    }

    @Override
    public void eventsProcessed() {
    }

    @Override
    public void initialize() {
        sizeField = (SFVec3f) fields.get("size");
        sizeTransformField = (SFVec3f) fields.get("sizeTransform");
        sizeField.addX3DEventListener(this);
        sizeField.getValue(size);
        sizeUpdated();
    }

    @Override
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void setFields(X3DScriptNode externalView, Map fields) {
        this.fields = fields;
    }

    @Override
    public void shutdown() {
    }

    private void sizeUpdated() {
        sizeField.getValue(size);
        sizeTransform[0] = size[0] * 0.6f;
        sizeTransform[1] = 0;
        sizeTransform[2] = size[2] * 0.6f;
        sizeTransformField.setValue(sizeTransform);
    }
}
