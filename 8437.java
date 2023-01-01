/**
 * Test code for the ExceptionBreakpointTest.
 *
 * @author Nathan Fiedler
 */
public class ExceptionBreakpointTestCode {

    public static void main(String[] args) {
        throwIllArg();
        throwNullPt();
        throwIndexBounds();
    }

    private static void throwIllArg() {
        try {
            throw new IllegalArgumentException();
        } catch (Exception e) {
        }
    }

    private static void throwNullPt() {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
        }
    }

    private static void throwIndexBounds() {
        try {
            throw new IndexOutOfBoundsException();
        } catch (Exception e) {
        }
    }
}
