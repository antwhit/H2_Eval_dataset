public class SquareRootTest {

    public static void main(String args[]) {
        int x = -2;
        debug("" + (x == -2L));
        testAccuracy();
        do {
        } while (true);
    }

    static void testSpeed() {
        int i;
        long newtime;
        long oldtime;
        int N = 10000000;
        int mask = (1 << 16) - 1;
        oldtime = System.currentTimeMillis();
        for (i = 0; i < N; i++) {
            int temp = SquareRoot.fastSqrt(i & mask);
        }
        newtime = System.currentTimeMillis();
        debug("SquareRoot.fast_sqrt:" + (newtime - oldtime));
        oldtime = System.currentTimeMillis();
        for (i = 0; i < N; i++) {
            int temp = SquareRoot.sqrt(i & mask);
        }
        newtime = System.currentTimeMillis();
        debug("SquareRoot.sqrt:" + (newtime - oldtime));
        oldtime = System.currentTimeMillis();
        for (i = 0; i < N; i++) {
            int temp = (int) (java.lang.Math.sqrt(i & mask));
        }
        newtime = System.currentTimeMillis();
        debug("java.lang.Math.sqrt:" + (newtime - oldtime));
    }

    static void testAccuracy() {
        int i;
        int a;
        int b;
        int c;
        int d;
        int e;
        int start = 0;
        int last_wrong_value = 0;
        for (i = start; ; i++) {
            a = (int) (java.lang.Math.sqrt(i));
            b = (int) (java.lang.Math.sqrt(i) + 0.5);
            c = SquareRoot.sqrt(i);
            d = SquareRoot.fastSqrt(i);
            e = SquareRoot.accurateSqrt(i);
            if (b != e) {
                last_wrong_value = a;
                debug("N:" + i + " - Math.sqrt:" + a + " - Math.sqrt+:" + b + " - accurateSqrt:" + e + " - sqrt:" + c);
            }
        }
    }

    static final void debug(String o) {
        System.out.println(o);
    }
}
