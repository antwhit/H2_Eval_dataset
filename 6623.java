public class Grippers {

    PCF8574 s;

    int i;

    Grippers(PCF8574 s, int i) {
        this.s = s;
        this.i = i;
        drop();
    }

    public void take() {
        s.setSensorPortStatus(i, 1);
    }

    public void drop() {
        s.setSensorPortStatus(i, 0);
    }
}
