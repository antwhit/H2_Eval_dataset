import java.util.concurrent.InterruptThread;

public class InterruptTest extends InterruptThread {

    public void isr() {
        System.platform.nativeSetData(0x25, (byte) (System.platform.nativeGetData(0x25) ^ 0x80));
    }

    public static void main(String[] args) {
        cli();
        InterruptTest inter = new InterruptTest();
        inter.initInterrupt((byte) 23);
        initTimerAndLed();
        sei();
        for (int i = 0; i < 100000; i++) {
        }
        InterruptTest.removeInterrupt((byte) 23);
    }

    public static void initTimerAndLed() {
        byte DDRB = System.platform.nativeGetData(0x24);
        DDRB |= 0x80;
        System.platform.nativeSetData(0x24, DDRB);
        System.platform.nativeSetData(0x44, (byte) 0);
        byte TCCR0B = System.platform.nativeGetData(0x45);
        TCCR0B |= (1 << 2) | (1 << 0);
        System.platform.nativeSetData(0x45, TCCR0B);
        byte TIMSK0 = System.platform.nativeGetData(0x6e);
        TIMSK0 |= (1 << 0);
        System.platform.nativeSetData(0x6e, TIMSK0);
    }
}
