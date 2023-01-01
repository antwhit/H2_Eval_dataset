import java.io.IOException;
import java.net.URI;
import javax.lang.model.element.Element;
import pl.wcislo.sbql4j.tools.javac.api.JavacTaskImpl;
import pl.wcislo.sbql4j.tools.javac.util.Context;
import pl.wcislo.sbql4j.tools.javac.util.List;
import pl.wcislo.sbql4j.tools.javac.util.Messages;
import javax.tools.*;

public class T6457284 {

    static class MyFileObject extends SimpleJavaFileObject {

        public MyFileObject() {
            super(URI.create("myfo:/Test.java"), JavaFileObject.Kind.SOURCE);
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return "class Test {}";
        }
    }

    public static void main(String[] args) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavacTaskImpl task = (JavacTaskImpl) compiler.getTask(null, null, null, null, null, List.of(new MyFileObject()));
        MyMessages.preRegister(task.getContext());
        task.parse();
        for (Element e : task.analyze()) {
            if (!e.getEnclosingElement().toString().equals("compiler.misc.unnamed.package")) throw new AssertionError(e.getEnclosingElement());
            System.out.println("OK: " + e.getEnclosingElement());
            return;
        }
        throw new AssertionError("No top-level classes!");
    }

    static class MyMessages extends Messages {

        static void preRegister(Context context) {
            context.put(messagesKey, new MyMessages());
        }

        MyMessages() {
            super("pl.wcislo.sbql4j.tools.javac.resources.compiler");
        }

        public String getLocalizedString(String key, Object... args) {
            if (key.equals("compiler.misc.unnamed.package")) return key; else return super.getLocalizedString(key, args);
        }
    }
}
