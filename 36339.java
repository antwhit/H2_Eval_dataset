import java.io.*;

public class T4880663 {

    public static void main(String[] args) throws Exception {
        new T4880663().run();
    }

    public void run() throws IOException {
        File javaFile = writeTestFile();
        File classFile = compileTestFile(javaFile);
        verify(classFile, "class Test {");
        if (errors > 0) throw new Error(errors + " found.");
    }

    File writeTestFile() throws IOException {
        File f = new File("Test.java");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        out.println("class Test { }");
        out.close();
        return f;
    }

    File compileTestFile(File f) {
        int rc = com.sun.tools.javac.Main.compile(new String[] { "-g", f.getPath() });
        if (rc != 0) throw new Error("compilation failed. rc=" + rc);
        String path = f.getPath();
        return new File(path.substring(0, path.length() - 5) + ".class");
    }

    String javap(File classFile) {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        int rc = com.sun.tools.javap.Main.run(new String[] { classFile.getPath() }, out);
        if (rc != 0) throw new Error("javap failed. rc=" + rc);
        out.close();
        System.out.println(sw.toString());
        return sw.toString();
    }

    void verify(File classFile, String... expects) {
        String output = javap(classFile);
        for (String expect : expects) {
            if (output.indexOf(expect) < 0) error(expect + " not found");
        }
    }

    void error(String msg) {
        System.err.println(msg);
        errors++;
    }

    int errors;
}
