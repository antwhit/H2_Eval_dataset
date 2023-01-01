import imports.*;

public class imports_runme {

    static {
        try {
            System.loadLibrary("imports_a");
            System.loadLibrary("imports_b");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        B b = new B();
        b.hello();
    }
}
