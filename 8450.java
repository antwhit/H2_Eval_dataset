import java.io.IOException;
import java.util.Arrays;
import com.sun.javadoc.*;

public class Main extends Tester.Doclet {

    private static final Tester tester = new Tester("Main", "pkg1", "-package");

    public static void main(String[] args) throws IOException {
        tester.run();
        tester.verify();
    }

    public static boolean start(RootDoc root) {
        try {
            ClassDoc[] cds = root.classes();
            Arrays.sort(cds);
            for (ClassDoc cd : cds) {
                tester.printClass(cd);
                tester.println();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
