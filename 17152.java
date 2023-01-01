import ret_by_value.*;

public class ret_by_value_runme {

    static {
        try {
            System.loadLibrary("ret_by_value");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        test tst = ret_by_value.get_test();
        if (tst.getMyInt() != 100 || tst.getMyShort() != 200) {
            System.err.println("Runtime test failed. myInt=" + tst.getMyInt() + " myShort=" + tst.getMyShort());
            System.exit(1);
        }
        tst.delete();
    }
}
