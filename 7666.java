import java.util.Vector;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.ServiceRecord;

public class DeviceDiscoverer implements DiscoveryListener {

    private MainApp mainApp = null;

    private Vector devices = null;

    private RemoteDevice[] rDevices = null;

    public DeviceDiscoverer(MainApp mainApp) {
        super();
        this.mainApp = mainApp;
        devices = new Vector();
    }

    public void deviceDiscovered(RemoteDevice remote, DeviceClass dClass) {
        devices.addElement(remote);
    }

    public void inquiryCompleted(int descType) {
        String message = "";
        switch(descType) {
            case DiscoveryListener.INQUIRY_COMPLETED:
                message = "SHINJILGEE_DUUSLAA";
                break;
            case DiscoveryListener.INQUIRY_TERMINATED:
                message = "SHINJILGEE_TASLAGDLAA";
                break;
            case DiscoveryListener.INQUIRY_ERROR:
                message = "SHINJILGEE_ALDAA";
                break;
        }
        rDevices = new RemoteDevice[devices.size()];
        for (int i = 0; i < devices.size(); i++) rDevices[i] = (RemoteDevice) devices.elementAt(i);
        mainApp.deviceInquiryFinished(rDevices, message);
        devices.removeAllElements();
        mainApp = null;
        devices = null;
    }

    public void servicesDiscovered(int transId, ServiceRecord[] services) {
    }

    public void serviceSearchCompleted(int transId, int respCode) {
    }
}
