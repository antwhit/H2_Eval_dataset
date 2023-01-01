import vrml.*;
import vrml.field.*;
import vrml.node.*;

public class bounce extends Script {

    private float Hoehe;

    private SFVec3f value_changedObj;

    public void initialize() {
        SFFloat floatObj = (SFFloat) getField("Hoehe");
        Hoehe = (float) floatObj.getValue();
        value_changedObj = (SFVec3f) getEventOut("value_changed");
    }

    public void processEvent(Event event) {
        ConstSFFloat flt = (ConstSFFloat) event.getValue();
        float frac = (float) flt.getValue();
        float y = (float) (4.0 * Hoehe * frac * (1.0 - frac));
        double[] changed = new double[3];
        changed[0] = (float) 0.0;
        changed[1] = y;
        changed[2] = (float) 0.0;
    }
}
