import java_throws.*;

public class java_throws_runme {

    static {
        try {
            System.loadLibrary("java_throws");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        boolean pass = false;
        try {
            short s = java_throws.full_of_exceptions(10);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchFieldException e) {
            pass = true;
        } catch (InstantiationException e) {
        } catch (CloneNotSupportedException e) {
        } catch (IllegalAccessException e) {
        }
        if (!pass) throw new RuntimeException("Test 1 failed");
        pass = false;
        try {
            java_throws.throw_spec_function(100);
        } catch (IllegalAccessException e) {
            pass = true;
        }
        if (!pass) throw new RuntimeException("Test 2 failed");
        pass = false;
        try {
            java_throws.catches_function(100);
        } catch (IllegalAccessException e) {
            pass = true;
        }
        if (!pass) throw new RuntimeException("Test 3 failed");
        try {
            TestClass tc = java_throws.makeTestClass();
        } catch (NoSuchMethodException e) {
        }
        pass = false;
        try {
            int myInt = java_throws.ioTest();
        } catch (java.io.IOException e) {
            pass = true;
        }
        if (!pass) throw new RuntimeException("Test 4 failed");
        pass = false;
        try {
            FeatureTest.staticMethod();
        } catch (MyException e) {
            pass = true;
        }
        if (!pass) throw new RuntimeException("Test 5 failed");
        FeatureTest f = null;
        try {
            f = new FeatureTest();
        } catch (MyException e) {
        }
        pass = false;
        try {
            f.method();
        } catch (MyException e) {
            pass = true;
        }
        if (!pass) throw new RuntimeException("Test 6 failed");
        pass = false;
        try {
            java_throws.globalFunction(10);
        } catch (MyException e) {
            pass = true;
        } catch (ClassNotFoundException e) {
        } catch (NoSuchFieldException e) {
        }
        if (!pass) throw new RuntimeException("Test 7 failed");
        NoExceptTest net = new NoExceptTest();
        pass = false;
        try {
            net.exceptionPlease();
            pass = true;
        } catch (MyException e) {
        }
        if (!pass) throw new RuntimeException("Test 8 failed");
        net.noExceptionPlease();
    }
}
