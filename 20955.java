import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import com.jtheory.jdring.AlarmEntry;
import com.jtheory.jdring.AlarmListener;

public class TestListener implements AlarmListener, StatusUpdateListener {

    private CashDrawer cd;

    public TestListener(CashDrawer cd) {
        try {
            this.cd = cd;
            this.cd.open("CD554");
            this.cd.claim(1000);
            this.cd.setDeviceEnabled(true);
            cd.addStatusUpdateListener(this);
        } catch (Exception exc) {
        }
    }

    public void handleAlarm(AlarmEntry entry) {
        try {
            cd.openDrawer();
        } catch (Exception exc) {
        }
    }

    public void statusUpdateOccurred(StatusUpdateEvent sue) {
        String cdstatus = "";
        switch(sue.getStatus()) {
            case CashDrawerConst.CASH_SUE_DRAWEROPEN:
                cdstatus = "open";
                break;
            case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
                cdstatus = "closed";
                break;
        }
        System.out.println(" Drawer Status =  " + cdstatus);
    }
}
