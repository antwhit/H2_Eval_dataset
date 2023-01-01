public class test {

    int count = 0;

    public void foo() {
        count++;
        count++;
    }

    public static void main(String args[]) {
        test t = new test();
        testhandler th = new testhandler();
        t.foo();
        if (th.counter != 0 && t.count != 2) {
            System.out.println("TEST FAILED");
            return;
        }
        System.out.println("TEST PASSED");
    }
}
