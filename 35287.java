public class stPutGet {

    static boolean run() {
        int i = test(6000);
        System.out.println("stPutGet returned: " + i);
        return true;
    }

    public static int test(int n) {
        TestC2.tval += n;
        return TestC2.tval;
    }
}

class TestC2 {

    static int tval = 1000;
}
