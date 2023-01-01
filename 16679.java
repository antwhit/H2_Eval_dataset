import org.mozilla.javascript.*;

/**
 * RunScript4: Execute scripts in an environment that includes the
 *             example Counter class.
 *
 * @author Norris Boyd
 */
public class RunScript4 {

    public static void main(String args[]) throws Exception {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            ScriptableObject.defineClass(scope, Counter.class);
            Object[] arg = { new Integer(7) };
            Scriptable myCounter = cx.newObject(scope, "Counter", arg);
            scope.put("myCounter", scope, myCounter);
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
