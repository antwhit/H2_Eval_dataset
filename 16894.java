public class runme {

    static {
        try {
            System.loadLibrary("example");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        int x = 42;
        int y = 105;
        int g = example.gcd(x, y);
        System.out.println("The gcd of " + x + " and " + y + " is " + g);
        System.out.println("Foo = " + example.getFoo());
        example.setFoo(3.1415926);
        System.out.println("Foo = " + example.getFoo());
    }
}
