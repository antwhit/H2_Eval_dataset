import java.io.*;
import org.mozilla.javascript.*;

public class gjp_JScript {

    public void run(String FileName, Context cx, Scriptable scope) {
        String result = new String();
        try {
            StringBuffer scriptcontent = new StringBuffer();
            String line = new String();
            FileReader myFile = new FileReader(FileName);
            BufferedReader buff = new BufferedReader(myFile);
            boolean eof = false;
            System.out.println("got here");
            while (!eof) {
                line = buff.readLine();
                if (line == null) {
                    eof = true;
                    System.out.println(scriptcontent);
                } else {
                    scriptcontent.append(line);
                }
            }
            line = scriptcontent.toString();
            result = (String) cx.evaluateString(scope, line, "<cmd>", 1, null);
        } catch (JavaScriptException jse) {
        } catch (IOException ioe) {
            System.out.println("IO Error");
        }
        System.out.println(result);
    }

    public void run(Context cx, Scriptable scope) {
        Object result = new Object();
        try {
            result = cx.evaluateString(scope, "function f(x){return x+1} f(7)", "<cmd>", 1, null);
        } catch (JavaScriptException jse) {
        }
        System.out.println(cx.toString(result));
    }

    public void run(StringBuffer scriptContent, Context cx, Scriptable scope) {
        String result = new String();
        try {
            System.out.println("got here");
            result = (String) cx.evaluateString(scope, scriptContent.toString(), "<cmd>", 1, null);
        } catch (JavaScriptException jse) {
        }
        System.out.println(result);
    }
}
