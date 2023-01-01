import josx.platform.rcx.*;
import josx.rcxcomm.*;
import java.io.*;

public class SensorControl1RCX implements SensorConstants, SensorListener {

    public static void main(String[] args) {
        Sensor.S1.setTypeAndMode(SENSOR_TYPE_TOUCH, SENSOR_MODE_EDGE);
        Sensor.S1.addSensorListener(new SensorControl1RCX());
        while (true) {
        }
    }

    public void stateChanged(Sensor src, int oldValue, int newValue) {
        LCD.showNumber(newValue);
        try {
            RCXPort port = new RCXPort();
            DataOutputStream out = new DataOutputStream(port.getOutputStream());
            out.writeInt(1);
            out.flush();
            port.close();
        } catch (IOException ioe) {
        }
        ;
        LCD.refresh();
    }
}
