class StringRegion {

    static boolean verbose = true;

    static boolean allTestPass = true;

    public static native void setVerboseOff();

    static native int testStringRegion(String s);

    static native int testStringCritical(String s);

    public static void main(String args[]) {
        System.loadLibrary("StringRegion");
        if (args.length != 0) {
            if (args[0].equals("-quiet")) {
                verbose = false;
                setVerboseOff();
            }
        }
        String inputStr = "Live Free or Die";
        int ret = testStringRegion(inputStr);
        checkTest(ret, true, "StringRegion1");
        ret = testStringCritical(inputStr);
        checkTest(ret, inputStr.equals("Free Java or Die"), "StringCritical");
        if (allTestPass) System.out.println("PASS: StringRegion"); else System.out.println("FAIL: StringRegion");
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
