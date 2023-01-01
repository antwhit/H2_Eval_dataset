import pl.wcislo.sbql4j.javadoc.*;
import java.util.*;

public class NestedClass extends Doclet {

    public NestedClassB b;

    public static void main(String[] args) {
        if (pl.wcislo.sbql4j.tools.javadoc.Main.execute("javadoc", "NestedClass", NestedClass.class.getClassLoader(), new String[] { System.getProperty("test.src", ".") + java.io.File.separatorChar + "NestedClass.java" }) != 0) throw new Error();
    }

    public static boolean start(pl.wcislo.sbql4j.javadoc.RootDoc root) {
        ClassDoc[] classes = root.classes();
        if (classes.length != 1) throw new Error("1 " + Arrays.asList(classes));
        ClassDoc self = classes[0];
        FieldDoc B = self.fields()[0];
        ClassDoc[] Binner = B.type().asClassDoc().innerClasses();
        return Binner.length == 1;
    }
}
