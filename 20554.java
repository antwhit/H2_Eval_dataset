import java.io.FileInputStream;
import java.io.IOException;

class MyErrorBase extends Throwable {
}

class MyError extends MyErrorBase {
}

class NotMyError extends Throwable {
}

class TestThrow {

    public static void main(String args[]) throws Throwable {
        run();
    }

    public static void run() throws Throwable {
        System.out.println("TestThrow");
        try {
            int a = 1;
            int b = 2;
            int c = a + b * foo();
            System.out.println(c);
        } catch (MyErrorBase e) {
            System.out.println("caught: " + e);
        }
        try {
            FileInputStream s = new FileInputStream("xyzzy");
            System.out.println(s);
        } catch (IOException e) {
            System.out.println("caught: " + e.getClass());
        }
    }

    static int foo() throws MyError, NotMyError {
        if (true) throw new MyError(); else throw new NotMyError();
    }

    static int bar() {
        int i = 1;
        int j = 0;
        return i / j;
    }
}
