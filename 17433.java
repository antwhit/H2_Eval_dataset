import lejos.nxt.*;
import lejos.subsumption.*;

public class HitWall implements Behavior {

    TouchSensor touch;

    public HitWall() {
        touch = new TouchSensor(SensorPort.S2);
    }

    public void suppress() {
    }

    public boolean takeControl() {
        return touch.isPressed();
    }

    public void action() {
        try {
            Motor.A.backward();
            Motor.C.backward();
            Thread.sleep(1000);
            Motor.A.stop();
            Thread.sleep(300);
            Motor.C.stop();
        } catch (Exception e) {
        }
        Motor.A.stop();
        Motor.C.stop();
    }
}
