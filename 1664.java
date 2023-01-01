import java_typemaps_typewrapper.*;

public class java_typemaps_typewrapper_runme {

    static {
        try {
            System.loadLibrary("java_typemaps_typewrapper");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        SWIGTYPE_p_Greeting greet = SWIGTYPE_p_Greeting.CreateNullPointer();
        SWIGTYPE_p_Farewell bye = SWIGTYPE_p_Farewell.CreateNullPointer();
        greet.sayhello();
        bye.saybye(new java.math.BigDecimal(java.math.BigInteger.ONE));
        try {
            throw SWIGTYPE_p_Greeting.CreateNullPointer();
        } catch (SWIGTYPE_p_Greeting g) {
            String msg = g.getMessage();
        }
        SWIGTYPE_p_Greeting.cheerio(greet);
        java_typemaps_typewrapper.solong(null);
        java_typemaps_typewrapper.solong(bye);
        SWIGTYPE_p_Farewell nullFarewell = new SWIGTYPE_p_Farewell(0, false);
    }
}
