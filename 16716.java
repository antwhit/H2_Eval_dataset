import org.mozilla.javascript.*;

/**
 * Example of controlling the JavaScript with multiple scopes and threads.
 */
public class DynamicScopes {

    static boolean useDynamicScope;

    static class MyFactory extends ContextFactory {

        @Override
        protected boolean hasFeature(Context cx, int featureIndex) {
            if (featureIndex == Context.FEATURE_DYNAMIC_SCOPE) {
                return useDynamicScope;
            }
            return super.hasFeature(cx, featureIndex);
        }
    }

    static {
        ContextFactory.initGlobal(new MyFactory());
    }

    /**
     * Main entry point.
     *
     * Set up the shared scope and then spawn new threads that execute
     * relative to that shared scope. Try to run functions with and
     * without dynamic scope to see the effect.
     *
     * The expected output is
     * <pre>
     * sharedScope
     * nested:sharedScope
     * sharedScope
     * nested:sharedScope
     * sharedScope
     * nested:sharedScope
     * thread0
     * nested:thread0
     * thread1
     * nested:thread1
     * thread2
     * nested:thread2
     * </pre>
     * The final three lines may be permuted in any order depending on
     * thread scheduling.
     */
    public static void main(String[] args) {
        Context cx = Context.enter();
        try {
            String source = "" + "var x = 'sharedScope';\n" + "function f() { return x; }\n" + "function initClosure(prefix) {\n" + "    return function test() { return prefix+x; }\n" + "}\n" + "var closure = initClosure('nested:');\n" + "";
            Script script = cx.compileString(source, "sharedScript", 1, null);
            useDynamicScope = false;
            runScripts(cx, script);
            useDynamicScope = true;
            runScripts(cx, script);
        } finally {
            Context.exit();
        }
    }

    static void runScripts(Context cx, Script script) {
        ScriptableObject sharedScope = cx.initStandardObjects(null, true);
        script.exec(cx, sharedScope);
        final int threadCount = 3;
        Thread[] t = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            String source2 = "" + "function g() { var x = 'local'; return f(); }\n" + "java.lang.System.out.println(g());\n" + "function g2() { var x = 'local'; return closure(); }\n" + "java.lang.System.out.println(g2());\n" + "";
            t[i] = new Thread(new PerThread(sharedScope, source2, "thread" + i));
        }
        for (int i = 0; i < threadCount; i++) t[i].start();
        for (int i = 0; i < threadCount; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
            }
        }
    }

    static class PerThread implements Runnable {

        PerThread(Scriptable sharedScope, String source, String x) {
            this.sharedScope = sharedScope;
            this.source = source;
            this.x = x;
        }

        public void run() {
            Context cx = Context.enter();
            try {
                Scriptable threadScope = cx.newObject(sharedScope);
                threadScope.setPrototype(sharedScope);
                threadScope.setParentScope(null);
                threadScope.put("x", threadScope, x);
                cx.evaluateString(threadScope, source, "threadScript", 1, null);
            } finally {
                Context.exit();
            }
        }

        private Scriptable sharedScope;

        private String source;

        private String x;
    }
}
