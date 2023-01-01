import java.io.IOException;
import com.sun.javadoc.*;

public class Main extends Tester.Doclet {

    private static final Tester tester = new Tester("Main", "pkg1");

    public static void main(String[] args) throws IOException {
        tester.run();
        tester.verify();
    }

    public static boolean start(RootDoc root) {
        try {
            for (ClassDoc cd : root.classes()) {
                for (ConstructorDoc c : cd.constructors()) tester.printConstructor(c);
                for (MethodDoc m : cd.methods()) tester.printMethod(m);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
