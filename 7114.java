import java.lang.*;

public class Util {

    public static native void printInt(int i);

    public static native void printIntAndDouble(int i, double d);

    public static native void printString(String s);

    public static final int ANY_LINE = -1;

    public static native void fileTrap(String fileName, int lineNumber);

    public static native void functionTrap(String fileName, int lineNumber);

    public static native void emptyLoop(int iterCount);
}
