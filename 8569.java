import bt747.ui.Button;
import waba.ui.ComboBox;
import waba.ui.Container;
import waba.ui.ControlEvent;
import waba.ui.Event;
import waba.ui.Label;
import gps.BT747_dev;
import gps.GPSstate;
import gps.GpsEvent;

/**
 * @author Mario De Weerd
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GPSNMEAOutput extends Container {

    /** The object that is used to communicate with the GPS device. */
    private ComboBox[] chkNMEAItems = new ComboBox[BT747_dev.C_NMEA_SEN_COUNT];

    /** The button that requests to change the log format of the device */
    private static final String C_NMEA_PERIODS[] = { "0", "1", "2", "3", "4", "5" };

    private Button btSet;

    private Button btSetDefaults;

    private GPSstate m_GPSstate;

    /**
     * 
     */
    public GPSNMEAOutput(final GPSstate state) {
        m_GPSstate = state;
    }

    protected void onStart() {
        for (int i = 0; i < BT747_dev.C_NMEA_SEN_COUNT; i++) {
            chkNMEAItems[i] = new ComboBox(C_NMEA_PERIODS);
            add(chkNMEAItems[i]);
            chkNMEAItems[i].setRect(((i < ((BT747_dev.C_NMEA_SEN_COUNT / 2) + 1)) ? LEFT : (getClientRect().width / 2)), ((i == 0) || i == ((BT747_dev.C_NMEA_SEN_COUNT / 2) + 1)) ? TOP : AFTER - 1, PREFERRED, PREFERRED - 1);
            add(new Label(BT747_dev.NMEA_strings[i]), AFTER, SAME);
        }
        btSet = new Button("Set");
        add(btSet, (getClientRect().width / 2), AFTER);
        btSetDefaults = new Button("Defaults");
        add(btSetDefaults, AFTER, SAME);
    }

    private void updatePeriods() {
        for (int i = 0; i < BT747_dev.C_NMEA_SEN_COUNT; i++) {
            chkNMEAItems[i].select(m_GPSstate.NMEA_periods[i]);
        }
    }

    private void setPeriods() {
        int[] Periods = new int[BT747_dev.C_NMEA_SEN_COUNT];
        for (int i = 0; i < BT747_dev.C_NMEA_SEN_COUNT; i++) {
            Periods[i] = chkNMEAItems[i].getSelectedIndex();
        }
        m_GPSstate.setNMEAPeriods(Periods);
        m_GPSstate.getNMEAPeriods();
    }

    /** Handle events for this object.
     * @param event The event to be interpreted.
     */
    public void onEvent(Event event) {
        switch(event.type) {
            case ControlEvent.PRESSED:
                if (event.target == this) {
                    m_GPSstate.getNMEAPeriods();
                    event.consumed = true;
                } else if (event.target == btSet) {
                    setPeriods();
                    event.consumed = true;
                } else if (event.target == btSetDefaults) {
                    m_GPSstate.setNMEADefaultPeriods();
                    event.consumed = true;
                } else {
                }
                break;
            case GpsEvent.DATA_UPDATE:
                if (event.target == this) {
                    updatePeriods();
                    event.consumed = true;
                }
        }
    }
}
