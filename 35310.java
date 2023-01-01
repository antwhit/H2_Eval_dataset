import org.mozilla.javascript.*;

public class Counter extends ScriptableObject {

    private static final long serialVersionUID = 438270592527335642L;

    public Counter() {
    }

    public void jsConstructor(int a) {
        count = a;
    }

    @Override
    public String getClassName() {
        return "Counter";
    }

    public int jsGet_count() {
        return count++;
    }

    public void jsFunction_resetCount() {
        count = 0;
    }

    private int count;
}
