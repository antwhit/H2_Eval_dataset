import com.sun.javadoc.*;
import java.io.File;
import static com.sun.tools.javadoc.Main.execute;

public class T6551367 extends com.sun.tools.doclets.standard.Standard {

    public T6551367() {
    }

    /** Here, in the javadoc for this method, I try to link to
     *  {@link #<init> a constructor}.
     */
    public static void main(String... args) {
        File testSrc = new File(System.getProperty("test.src", "."));
        File destDir = new File(System.getProperty("user.dir", "."));
        for (String file : args) {
            File source = new File(testSrc, file);
            int rc = execute("javadoc", "T6551367", T6551367.class.getClassLoader(), new String[] { source.getPath(), "-d", destDir.getAbsolutePath() });
            if (rc != 0) throw new Error("unexpected exit from javadoc: " + rc);
        }
    }
}
