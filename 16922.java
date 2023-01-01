import waba.ui.Button;
import waba.ui.Container;
import waba.ui.ControlEvent;
import waba.ui.Event;
import waba.ui.Label;
import waba.ui.PushButtonGroup;
import gps.GPSstate;
import gps.GpsEvent;

/**
 * @author Mario De Weerd
 */
public class GPSconctrl extends Container {

    private PushButtonGroup btnChannelSelect;

    private Button btnRestartGps;

    private GPSstate m_GPSstate;

    private Label lbFirmwareMainVersion;

    private Label lbFirmwareName;

    private Label lbModel;

    static final int C_CHN_BLUETOOTH = 0;

    static final int C_CHN_USB = 1;

    static final int C_CHN_0 = 2;

    private static final String[] txtChannel = { "BLUETOOTH", "USB", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };

    public GPSconctrl(GPSstate p_GPSstate) {
        m_GPSstate = p_GPSstate;
    }

    public void onStart() {
        btnChannelSelect = new PushButtonGroup(txtChannel, true, C_CHN_BLUETOOTH, -1, 6, 2, false, PushButtonGroup.NORMAL);
        add(btnChannelSelect, CENTER, AFTER + 2);
        btnRestartGps = new Button("Reset COM port");
        btnRestartGps.setGap(5);
        add(btnRestartGps, RIGHT - 5, BOTTOM - 5);
        add(new Label("This SW: V" + Version.NUMBER + "(" + Version.DATE + ")"), LEFT, BEFORE);
        add(lbFirmwareMainVersion = new Label(""), LEFT, BEFORE);
        add(lbFirmwareName = new Label(""), LEFT, BEFORE);
        add(lbModel = new Label(""), LEFT, BEFORE);
    }

    private void GPS_setChannel(int channel) {
        switch(channel) {
            case C_CHN_BLUETOOTH:
                m_GPSstate.setBluetooth();
                break;
            case C_CHN_USB:
                m_GPSstate.setUsb();
                break;
            default:
                m_GPSstate.setPort(channel - C_CHN_0);
                break;
        }
    }

    private void updateButtons() {
        lbFirmwareMainVersion.setText(((m_GPSstate.getMainVersion().length() != 0) ? "Main:" : "") + m_GPSstate.getMainVersion());
        lbFirmwareName.setText(((m_GPSstate.getFirmwareVersion().length() != 0) ? "Firmware:" : "") + m_GPSstate.getFirmwareVersion());
        lbModel.setText(((m_GPSstate.getModel().length() != 0) ? "Model:" : "") + m_GPSstate.getModel());
        lbFirmwareMainVersion.repaintNow();
        lbFirmwareName.repaintNow();
        lbModel.repaintNow();
    }

    public void onEvent(Event event) {
        switch(event.type) {
            case ControlEvent.PRESSED:
                if (event.target == btnChannelSelect) {
                    GPS_setChannel(btnChannelSelect.getSelected());
                } else if (event.target == this) {
                    m_GPSstate.getDeviceInfo();
                    event.consumed = true;
                } else if (event.target == btnRestartGps) {
                    m_GPSstate.GPS_restart();
                }
                break;
            case GpsEvent.DATA_UPDATE:
                if (event.target == this) {
                    updateButtons();
                    event.consumed = true;
                }
        }
    }
}
