import java.io.*;

public class virtUnresolved {

    static boolean run() {
        int i = test(20);
        System.out.println("virtUnresolved returned: " + i);
        return true;
    }

    static int f1 = 0;

    public static int test(int n) {
        virtUnresolved vur = new virtUnresolved();
        virtTest vt = new virtTest();
        vt.ppp(n);
        vt.ppp();
        return f1;
    }
}

class virtTest {

    int tval = 0;

    void ppp(int tv) {
        tval = tv;
        virtUnresolved.f1 = tv + 100;
    }

    void ppp() {
    }
}
