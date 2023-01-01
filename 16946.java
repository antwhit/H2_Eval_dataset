import java.io.IOException;
import com.sun.javadoc.*;

public class Main extends Tester.Doclet {

    private static final Tester tester = new Tester("Main", "-package", "pkg1");

    public static void main(String[] args) throws IOException {
        tester.run();
    }

    public static boolean start(RootDoc root) {
        ClassDoc operation = root.classes()[0];
        boolean ok = checkComment(operation.commentText(), "Arithmetic operations.");
        for (FieldDoc f : operation.fields()) {
            if (f.name().equals("plus")) {
                ok = checkComment(f.commentText(), "Addition") && ok;
                for (MethodDoc m : operation.methods()) {
                    if (m.name().equals("eval")) {
                        ok = checkComment(m.commentText(), "Perform arithmetic operation " + "represented by this constant.") && ok;
                        break;
                    }
                }
                break;
            }
        }
        if (!ok) {
            throw new Error("Comments don't match expectations.");
        } else {
            return true;
        }
    }

    private static boolean checkComment(String found, String expected) {
        System.out.println("expected: \"" + expected + "\"");
        System.out.println("found:    \"" + found + "\"");
        return expected.equals(found);
    }
}
