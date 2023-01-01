import org.mozilla.javascript.*;

/**
 * RunScript: simplest example of controlling execution of Rhino.
 *
 * Collects its arguments from the command line, executes the
 * script, and prints the result.
 *
 * @author Norris Boyd
 */
public class RunScript {

    public static void main(String args[]) {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            String s = "";
            for (int i = 0; i < args.length; i++) {
                s += args[i];
            }
            Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
            System.err.println(Context.toString(result));
        } finally {
            Context.exit();
        }
    }
}
