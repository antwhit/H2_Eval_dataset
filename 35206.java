/**
 * This class is used to test the results of the langtools build.
 */
public class HelloWorld {

    /**
     * The old standby!
     * @param args The parameters are ignored.
     */
    public static void main(String... args) {
        System.out.println("Hello World!");
    }

    /**
     * This declaration is for the benefit of javah tests.
     */
    public native void test();
}
