import java.io.*;
import org.mozilla.javascript.*;
import org.w3c.dom.*;

public class gjp_JavaScript {

    String result;

    Object o;

    Context cx = Context.enter();

    public String run(Scriptable scope, Script script) {
        try {
            Scriptable newScope = cx.newObject(scope);
            newScope.setPrototype(scope);
            o = script.exec(cx, newScope);
            if (o.getClass().getName() == "java.lang.String") {
                result = (String) o;
            } else {
                if (o.getClass().getName() == "org.mozilla.javascript.NativeJavaObject") {
                    result = (String) ((NativeJavaObject) o).unwrap();
                }
            }
        } catch (JavaScriptException jse) {
            jse.printStackTrace();
        } catch (PropertyException pe) {
            pe.printStackTrace();
        } catch (NotAFunctionException nafe) {
            nafe.printStackTrace();
        }
        return result;
    }
}
