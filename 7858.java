import java.io.*;

public class stPutGet {

    static boolean run() {
        int i = test(6000);
        System.out.println("stPutGet returned: " + i);
        return true;
    }

    public static int test(int n) {
        int f1 = TestC2.tval;
        TestC2.tval += n;
        f1 = TestC2.tval;
        return TestC2.tval;
    }
}

class TestC2 {

    static int tval = 1000;

    static void ppp() {
    }
}
