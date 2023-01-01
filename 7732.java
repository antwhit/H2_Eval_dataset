public class HelloWorld1 {

    HelloWorld2 h2 = new HelloWorld2();

    boolean shouldInc = true;

    void change(String str, int k, int j) {
        HelloWorld1.change2();
    }

    private static void change2() {
    }

    public static void main(String args[], Object obj, int k, long p) {
        HelloWorld1 h1 = new HelloWorld1();
        h1.change(null, 0, 0);
    }
}
