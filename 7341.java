public class main {

    static {
        try {
            System.loadLibrary("example");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        System.out.println("Testing the pointer library");
        SWIGTYPE_p_int a = example.new_intp();
        SWIGTYPE_p_int b = example.new_intp();
        SWIGTYPE_p_int c = example.new_intp();
        example.intp_assign(a, 37);
        example.intp_assign(b, 42);
        System.out.println("     a =" + Long.toHexString(SWIGTYPE_p_int.getCPtr(a)));
        System.out.println("     b =" + Long.toHexString(SWIGTYPE_p_int.getCPtr(b)));
        System.out.println("     c =" + Long.toHexString(SWIGTYPE_p_int.getCPtr(c)));
        example.add(a, b, c);
        int res = example.intp_value(c);
        System.out.println("     37 + 42 =" + res);
        example.delete_intp(a);
        example.delete_intp(b);
        example.delete_intp(c);
        System.out.println("Trying the typemap library");
        int[] r = { 0 };
        example.sub(37, 42, r);
        System.out.println("     37 - 42 = " + r[0]);
        System.out.println("Testing return value");
        int q = example.divide(42, 37, r);
        System.out.println("     42/37 = " + q + " remainder " + r[0]);
    }
}
