import com.dalsemi.system.*;

class StepperCW implements Runnable {

    BitPort bpa, bpb, bpled;

    int delay;

    StepperCW() {
        bpa = new BitPort(BitPort.Port1Bit7);
        bpb = new BitPort(BitPort.Port5Bit3);
        bpled = new BitPort(BitPort.Port5Bit2);
        delay = 0;
        new Thread(this).start();
        byte[] buffer = new byte[10];
        int n;
        while (true) try {
            System.out.println("speed (-100,+100) :");
            n = System.in.read(buffer);
            System.out.println("lu " + n + " car " + (int) buffer[0] + " " + (int) buffer[1] + " " + (int) buffer[2] + " " + (int) buffer[3] + " " + (int) buffer[4] + " ");
            int val = Integer.parseInt(new String(buffer, 0, n - 1));
            if (val > 0) {
                bpb.set();
                delay = val;
            } else {
                bpb.clear();
                delay = -val;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        boolean b = true;
        try {
            while (true) {
                if (b) {
                    bpa.clear();
                    Thread.sleep(delay);
                } else {
                    bpa.set();
                    Thread.sleep(delay);
                }
                b = !b;
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        System.out.println("stepper test V0.0.0");
        new StepperCW();
    }
}
