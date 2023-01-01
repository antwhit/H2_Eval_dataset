import waba.ui.Check;
import waba.ui.Container;
import waba.ui.ControlEvent;
import waba.ui.Event;
import gps.BT747_dev;
import gps.GPSFilter;

/** The purpose of this container is to set the log filter settings.
 */
public class GPSLogFilter extends Container {

    private GPSFilter m_logFilter;

    /**
     * 
     */
    public GPSLogFilter(final GPSFilter logFilter) {
        m_logFilter = logFilter;
    }

    private String[] strValid = { "No fix", "SPS", "DGPS", "PPS", "RTK", "FRTK", "Estimate", "Manual", "Sim" };

    private static final int C_VALID_COUNT = 9;

    private Check[] chkValid = new Check[C_VALID_COUNT];

    public void onStart() {
        for (int i = 0; i < C_VALID_COUNT; i++) {
            chkValid[i] = new Check(strValid[i]);
            add(chkValid[i], ((i == 0) ? LEFT : ((i == ((C_VALID_COUNT / 2))) ? getClientRect().width / 2 : SAME)), ((i == 0) || (i == ((C_VALID_COUNT / 2)))) ? TOP : AFTER - 1);
            chkValid[i].setEnabled(true);
        }
        for (int i = 0; i < BT747_dev.C_RCR_COUNT; i++) {
            chkRCR[i] = new Check(BT747_dev.C_STR_RCR[i]);
            add(chkRCR[i], LEFT, AFTER);
            chkRCR[i].setEnabled(true);
        }
    }

    /** Get the format set by the user in the user interface. */
    public int getValid() {
        int bitMask = 1;
        int valid = 0;
        for (int i = 0; i < C_VALID_COUNT; i++) {
            if (chkValid[i].getChecked()) {
                valid |= bitMask;
            }
            bitMask <<= 1;
        }
        return valid;
    }

    /** Updates the format options shown the the user.<br>
     * This is typically done when the device responded with the current settings.
     * @param p_logFormat Valid to set
     */
    public void setValid(final int valid) {
        int bitMask = 1;
        for (int i = 0; i < C_VALID_COUNT; i++) {
            chkValid[i].setChecked((valid & bitMask) != 0);
            chkValid[i].repaintNow();
            bitMask <<= 1;
        }
    }

    private Check[] chkRCR = new Check[BT747_dev.C_RCR_COUNT];

    /** Get the format set by the user in the user interface. */
    public int getRCR() {
        int bitMask = 1;
        int rcrMask = 0;
        for (int i = 0; i < BT747_dev.C_RCR_COUNT; i++) {
            if (chkRCR[i].getChecked()) {
                rcrMask |= bitMask;
            }
            bitMask <<= 1;
        }
        return rcrMask;
    }

    /** Updates the format options shown the the user.<br>
     * This is typically done when the device responded with the current settings.
     * @param p_logFormat RCR to set
     */
    public void setRCR(final int valid) {
        int bitMask = 1;
        for (int i = 0; i < BT747_dev.C_RCR_COUNT; i++) {
            chkRCR[i].setChecked((valid & bitMask) != 0);
            chkRCR[i].repaintNow();
            bitMask <<= 1;
        }
    }

    /** Handle events for this object.
     * @param event The event to be interpreted.
     */
    public void onEvent(Event event) {
        switch(event.type) {
            case ControlEvent.PRESSED:
                if (event.target == this) {
                    setValid(m_logFilter.getValidMask());
                    setRCR(m_logFilter.getRcrMask());
                    event.consumed = true;
                } else if (event.target == null) {
                } else {
                    boolean z_updated = false;
                    for (int i = 0; i < C_VALID_COUNT; i++) {
                        if (event.target == chkValid[i]) {
                            z_updated = true;
                        }
                    }
                    if (z_updated) {
                        m_logFilter.setValidMask(getValid());
                    }
                    z_updated = false;
                    for (int i = 0; i < BT747_dev.C_RCR_COUNT; i++) {
                        if (event.target == chkRCR[i]) {
                            z_updated = true;
                        }
                    }
                    if (z_updated) {
                        m_logFilter.setRcrMask(getRCR());
                    }
                }
                break;
        }
    }
}
