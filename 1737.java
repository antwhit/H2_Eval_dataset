/**
 *  Test exception handling through JNI and native code
 *
 * @author Ton Ngo, Steve Smith 
 * @date   3/24/00
 */
class NativeException {

    static boolean verbose = true;

    static boolean allTestPass = true;

    public static native void setVerboseOff();

    /**
   * Declare native methods that will call the JNI Array Functions
   */
    static native boolean testPassThrough(int[] sourceArray);

    static native boolean testExceptionOccured(int[] sourceArray);

    static native boolean testExceptionClear(int[] sourceArray);

    static native boolean testExceptionDescribe(int[] sourceArray);

    static native boolean testExceptionThrow(Throwable e);

    static native boolean testExceptionThrowNew(Class eclass);

    static native boolean testFatalError(boolean allTestPass, int[] sourceArray);

    /**
   * constructor
   */
    public NativeException() {
    }

    public static void main(String args[]) {
        int returnValue;
        boolean returnFlag;
        int intArray[] = new int[10];
        System.loadLibrary("NativeException");
        if (args.length != 0) {
            if (args[0].equals("-quiet")) {
                verbose = false;
                setVerboseOff();
            }
        }
        try {
            returnFlag = testPassThrough(intArray);
            returnFlag = false;
        } catch (RuntimeException e) {
            printVerbose("Caught exception:  expected ArrayIndexOutOfBoundsException, got " + e.toString());
            returnFlag = true;
        }
        checkTest(0, returnFlag, "Exception pass through");
        try {
            returnFlag = testExceptionOccured(intArray);
            returnFlag = false;
        } catch (RuntimeException e) {
            returnFlag = true;
        }
        checkTest(0, returnFlag, "Exception handled in native code");
        try {
            returnFlag = testExceptionClear(intArray);
        } catch (RuntimeException e) {
            returnFlag = false;
        }
        checkTest(0, returnFlag, "ExceptionClear");
        if (verbose) {
            try {
                returnFlag = testExceptionDescribe(intArray);
            } catch (RuntimeException e) {
                returnFlag = false;
            }
            checkTest(0, returnFlag, "ExceptionDescribe");
        }
        try {
            returnFlag = testExceptionThrow(new Exception("Test Throw in native"));
            returnFlag = false;
        } catch (Throwable e) {
            printVerbose("Caught exception:  got " + e.toString());
            returnFlag = true;
        }
        checkTest(0, returnFlag, "ExceptionThrow");
        try {
            Class ecls = Class.forName("java.lang.Exception");
            returnFlag = testExceptionThrowNew(ecls);
            returnFlag = false;
        } catch (ClassNotFoundException e1) {
            returnFlag = false;
        } catch (Exception e) {
            printVerbose("Caught exception:  got " + e.toString());
            returnFlag = true;
        }
        checkTest(0, returnFlag, "ExceptionThrowNew");
        if (allTestPass) System.out.println("PASS: NativeException"); else System.out.println("FAIL: NativeException");
    }

    static void printVerbose(String str) {
        if (verbose) System.out.println(str);
    }

    static void checkTest(int returnValue, boolean postCheck, String testName) {
        if (returnValue == 0 && postCheck) {
            printVerbose("PASS: " + testName);
        } else {
            allTestPass = false;
            printVerbose("FAIL: " + testName);
        }
    }
}
