import bt747.ui.Button;
import waba.ui.Container;
import waba.ui.ControlEvent;
import waba.ui.Event;
import waba.ui.MessageBox;
import gps.GPSstate;

/** Implement some buttons to easily do more complex operations
 * or to do some things not done in other tabs.
 * 
 * @author Mario De Weerd
 */
public class GPSLogEasy extends Container {

    private GPSstate m_GPSstate;

    private Button m_btSet5Hz;

    private Button m_btSet2Hz;

    private Button m_btHotStart;

    private Button m_btWarmStart;

    private Button m_btColdStart;

    private Button m_btFullColdStart;

    public GPSLogEasy(GPSstate state) {
        m_GPSstate = state;
    }

    protected void onStart() {
        add(m_btSet5Hz = new Button("5Hz fix and log"), CENTER, AFTER + 3);
        add(m_btSet2Hz = new Button("2Hz fix (avoid static nav)"), CENTER, AFTER + 3);
        add(m_btHotStart = new Button("Hot start"), LEFT, AFTER + 10);
        add(m_btWarmStart = new Button("Warm start"), CENTER, SAME);
        add(m_btColdStart = new Button("Cold start"), RIGHT, SAME);
        add(m_btFullColdStart = new Button("Factory reset"), CENTER, AFTER + 2);
    }

    public void onEvent(Event event) {
        super.onEvent(event);
        switch(event.type) {
            case ControlEvent.PRESSED:
                event.consumed = true;
                if (event.target == m_btSet2Hz) {
                    m_GPSstate.setFixInterval(500);
                } else if (event.target == m_btSet5Hz) {
                    m_GPSstate.setLogTimeInterval(2);
                    m_GPSstate.setFixInterval(200);
                } else if (event.target == m_btHotStart) {
                    m_GPSstate.doHotStart();
                } else if (event.target == m_btColdStart) {
                    m_GPSstate.doColdStart();
                } else if (event.target == m_btWarmStart) {
                    m_GPSstate.doWarmStart();
                } else if (event.target == m_btFullColdStart) {
                    MessageBox mb;
                    String[] szExitButtonArray = { "Yes", "No" };
                    mb = new MessageBox("Attention", "You are about to perform a factory|" + "reset of your GPS Logger Device.|" + "||Do you confirm this reset at|" + "your own risk ???", szExitButtonArray);
                    mb.popupBlockingModal();
                    if (mb.getPressedButtonIndex() == 0) {
                        m_GPSstate.doFullColdStart();
                    }
                } else {
                    event.consumed = false;
                }
                break;
        }
    }
}
