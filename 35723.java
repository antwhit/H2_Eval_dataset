import intermediary_classname.*;

public class intermediary_classname_runme {

    static {
        try {
            System.loadLibrary("intermediary_classname");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        double d = intermediary_classnameModule.maxdouble(10.0, 20.0);
        if (d != 20.0) throw new RuntimeException("Test failed");
        long ptr = intermediary_classname.new_vecdouble(10);
        intermediary_classname.delete_vecdouble(ptr);
    }
}
