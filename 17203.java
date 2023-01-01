import lejos.nxt.*;
import EDU.oswego.cs.dl.util.concurrent.*;

public class Arm implements Runnable {

    public CyclicBarrier load_start, load_end, unload_start, unload_end;

    private String name;

    private Motor expander;

    private Grippers gripper;

    public Arm(String name, int ls, int le, int us, int ue, Motor expander, Grippers gripper) {
        this.name = name;
        load_start = new CyclicBarrier(ls);
        load_end = new CyclicBarrier(le);
        unload_start = new CyclicBarrier(us);
        unload_end = new CyclicBarrier(ue);
        this.expander = expander;
        this.gripper = gripper;
        try {
            expander.setSpeed(50);
            expander.backward();
        } catch (Exception e) {
        }
    }

    public void run() {
        while (true) {
            try {
                load_start.barrier();
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Extend Expander", 0, 0);
            try {
                expander.forward();
                Thread.sleep(300);
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Pick Up", 0, 0);
            try {
                gripper.take();
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Retract Expander", 0, 0);
            try {
                expander.backward();
                Thread.sleep(300);
            } catch (Exception e) {
            }
            try {
                load_end.barrier();
            } catch (Exception e) {
            }
            try {
                unload_start.barrier();
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Extend Expander", 0, 0);
            try {
                expander.forward();
                Thread.sleep(300);
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Drop", 0, 0);
            try {
                gripper.drop();
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            LCD.clear();
            LCD.drawString(name + ":" + "Retract Expander", 0, 0);
            try {
                expander.backward();
                Thread.sleep(300);
            } catch (Exception e) {
            }
            try {
                unload_end.barrier();
            } catch (Exception e) {
            }
        }
    }
}
