import java_typemaps_proxy.*;
import java.lang.reflect.*;

public class java_typemaps_proxy_runme {

    static {
        try {
            System.loadLibrary("java_typemaps_proxy");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        Greeting greet = new Greeting();
        Farewell bye = new Farewell();
        greet.sayhello();
        bye.saybye(new java.math.BigDecimal(java.math.BigInteger.ONE));
        bye.delete();
        try {
            throw new Greeting();
        } catch (Greeting g) {
            String msg = g.getMessage();
        }
        Greeting.cheerio(greet);
        Greeting.ciao(null);
        Greeting.ciao(greet);
        Farewell nullFarewell = new Farewell(0, false);
        AdieuIntPtrPtr.adieu();
        try {
            Method methodmodifiertest = nullFarewell.getClass().getDeclaredMethod("methodmodifiertest", (java.lang.Class[]) null);
            if (!Modifier.isPrivate(methodmodifiertest.getModifiers())) throw new RuntimeException("NS::Farewell::methodmodifiertest not private");
        } catch (NoSuchMethodException n) {
            throw new RuntimeException("NoSuchmethodException caught. Test failed.");
        } catch (SecurityException s) {
            throw new RuntimeException("SecurityException caught. Test failed.");
        }
        long nullPtr = 0;
        With with = new With(null);
        java_typemaps_proxyJNI.new_With(nullPtr, with);
        java_typemaps_proxyJNI.With_static_method(nullPtr, with);
        java_typemaps_proxyJNI.With_member_method(nullPtr, with, nullPtr, with);
        java_typemaps_proxyJNI.delete_With(nullPtr);
        java_typemaps_proxyJNI.global_method_with(nullPtr, with);
        java_typemaps_proxyJNI.new_Without(nullPtr);
        java_typemaps_proxyJNI.Without_static_method(nullPtr);
        java_typemaps_proxyJNI.Without_member_method(nullPtr, nullPtr);
        java_typemaps_proxyJNI.delete_Without(nullPtr);
        java_typemaps_proxyJNI.global_method_without(nullPtr);
    }
}
