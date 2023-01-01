import com.ibm.JikesRVM.*;

/**
 * Test stack resize with native methods, various scenarios:
 *  -first entry to native code:  first resize
 *  -second nested entry to native code: no resize
 *  -fill up stack and make another entry to native code:  second resize
 *
 * @author Ton Ngo 
 * @date   9/6/01
 */
class StackResize {

    static boolean verbose = true;

    static boolean allTestPass = true;

    public static native boolean expectResize(int count);

    public static native boolean expectNoResize(int count);

    public static boolean makeSecondNativeCall() {
        VM_Thread th = VM_Thread.getCurrentThread();
        int currentStackSize = VM_Magic.getArrayLength(th.stack);
        boolean resizeDidNotOccur = expectNoResize(currentStackSize);
        if (resizeDidNotOccur == false) {
            if (verbose) VM.sysWrite("> Unexpected stack resize with native frame present\n");
            return false;
        }
        return true;
    }

    public static boolean checkResizeOccurred(int previousStackSize) {
        VM_Thread th = VM_Thread.getCurrentThread();
        int currentStackSize = VM_Magic.getArrayLength(th.stack);
        if (verbose) {
            VM.sysWrite("check resize: previous ");
            VM.sysWrite(previousStackSize);
            VM.sysWrite(", current ");
            VM.sysWrite(currentStackSize);
            VM.sysWrite("\n");
        }
        if (currentStackSize == previousStackSize) return false; else return true;
    }

    public static boolean nativeWithStackAlmostFull() throws VM_PragmaNoOptCompile {
        VM_Thread th = VM_Thread.getCurrentThread();
        int fp = VM_Magic.getFramePointer();
        int spaceLeft = fp - th.stackLimit;
        if ((spaceLeft) > (500 * 4)) {
            return nativeWithStackAlmostFull();
        } else {
            int currentStackSize = VM_Magic.getArrayLength(th.stack);
            boolean resizeOccurred = expectResize(currentStackSize);
            if (resizeOccurred) {
                return true;
            } else {
                if (verbose) VM.sysWrite("> Second stack resize did not occur\n");
                return false;
            }
        }
    }

    public static void main(String args[]) {
        boolean returnValue;
        FieldAccess tempObject;
        System.loadLibrary("StackResize");
        if (args.length != 0) {
            if (args[0].equals("-quiet")) {
                verbose = false;
            }
        }
        if (verbose) VM.sysWrite("Checking stack size\n");
        VM_Thread th = VM_Thread.getCurrentThread();
        int currentStackSpace = VM_Magic.getArrayLength(th.stack);
        if (currentStackSpace > VM.STACK_SIZE_JNINATIVE) {
            if (verbose) VM.sysWrite("StackResize:  normal stack size already exceeds native requirement, stack will not get resized.\n  Set up the system configuration for smaller normal stack:  VM_StackFrameLayoutConstants.java\n");
            VM.sysWrite("FAIL: StackResize\n");
        }
        if (verbose) VM.sysWrite("Starting test 1\n");
        returnValue = expectResize(currentStackSpace);
        checkTest(0, returnValue, "first stack resize");
        if (verbose) VM.sysWrite("Starting test 2\n");
        returnValue = nativeWithStackAlmostFull();
        checkTest(0, returnValue, "second stack resize");
        if (allTestPass) System.out.println("PASS: StackResize"); else System.out.println("FAIL: StackResize");
    }

    static void checkTest(int returnValue, boolean postCheck, String testName) {
        if (returnValue == 0 && postCheck) {
            printVerbose("PASS: " + testName);
        } else {
            allTestPass = false;
            printVerbose("FAIL: " + testName);
        }
    }

    static void printVerbose(String str) {
        if (verbose) System.out.println(str);
    }
}
