import org.python.core.*;
import org.python.util.PythonInterpreter;

public class embed {

    public static void main(String[] args) {
        PythonInterpreter interp = new PythonInterpreter();
        interp.set("x", new PyInteger(2));
        String expr = "x+5";
        System.out.println(expr + " = " + interp.eval(expr));
        interp.exec("y = 'foo'");
        Object y = Py.tojava(interp.get("y"), Object.class);
        System.out.println("y = " + y);
        Runnable foo = new FooRunner();
        foo.run();
    }
}
