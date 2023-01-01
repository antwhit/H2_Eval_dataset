import org.python.util.*;
import org.python.core.*;

public class test338j implements Runnable {

    public static void main(String[] args) throws Exception {
        Runnable r = (Runnable) Class.forName("test338j", true, new test338cl()).newInstance();
        r.run();
    }

    public void run() {
        String brob = "test338m";
        PythonInterpreter interp = new PythonInterpreter();
        interp.set("test338j1", test338j1.class);
        interp.execfile(brob + ".py");
        interp.exec("cl = " + brob + "()");
        Object newobj = interp.get("cl", Object.class);
        test338j1 boobj = (test338j1) newobj;
    }
}
