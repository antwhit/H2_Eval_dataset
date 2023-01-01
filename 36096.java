import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;
import org.junit.Test;

/**
*
* @author grro@xsocket.org
*/
public final class RunAllJRubyTest {

    private String basepath = null;

    public RunAllJRubyTest() {
        this(RunAllJRubyTest.class.getResource("").getFile());
    }

    private RunAllJRubyTest(String basepath) {
        this.basepath = new File(basepath).getParentFile().getAbsoluteFile() + File.separator + "test-classes";
    }

    public static void main(String... args) throws Exception {
        new RunAllJRubyTest(args[0]).testAllScripts();
    }

    @Test
    public void testAllScripts() throws Exception {
    }

    public void runScript(File scriptFile) throws Exception {
        StringBuilder sb = new StringBuilder();
        LineNumberReader lnr = new LineNumberReader(new FileReader(scriptFile));
        String line = null;
        do {
            line = lnr.readLine();
            if (line != null) {
                sb.append(line + "\r\n");
            }
        } while (line != null);
    }

    private void scanSrcipts(File dir, List<String> scripts) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanSrcipts(file, scripts);
            } else {
                if (file.getName().endsWith(".rb")) {
                    scripts.add(file.getAbsolutePath());
                }
            }
        }
    }
}
