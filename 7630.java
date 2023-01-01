import java.io.*;

public class Test {

    public static void main(String... args) throws Exception {
        new Test().run();
    }

    public void run() throws Exception {
        javadoc("Error.java", "1 error");
        javadoc("JavacWarning.java", "1 warning");
        javadoc("JavadocWarning.java", "1 warning");
        if (errors > 0) throw new Exception(errors + " errors found");
    }

    void javadoc(String path, String expect) {
        File testSrc = new File(System.getProperty("test.src"));
        String[] args = { "-source", "1.4", "-bootclasspath", System.getProperty("sun.boot.class.path"), "-classpath", ".", "-package", new File(testSrc, path).getPath() };
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        int rc = com.sun.tools.javadoc.Main.execute("javadoc", pw, pw, pw, com.sun.tools.doclets.standard.Standard.class.getName(), args);
        pw.close();
        String out = sw.toString();
        if (!out.isEmpty()) System.err.println(out);
        System.err.println("javadoc exit: rc=" + rc);
        if (!out.contains(expect)) error("expected text not found: " + expect);
    }

    void error(String msg) {
        System.err.println("Error: " + msg);
        errors++;
    }

    int errors;
}
