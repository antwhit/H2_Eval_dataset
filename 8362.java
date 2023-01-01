import java.io.*;
import java.util.*;

/**
 * This class is the main interface to a collection of build tools
 * that are used during this VM build process. <p>
 * <ul>
 *     <li> config -- generates the jvmconfig.h header file, which
 *                    controls the build options of the VM (e.g., 
 *                    ENABLE_JAVA_DEBUGGER, ENABLE_CLDC11, etc).
 * </ul>
 */
public class BuildTool {

    static void usage() {
        p("Usage: java -jar buildtool.jar <command> <args> ...");
        p("Valid options for <command> are:");
        p("");
        p("help:                                Print this help message");
        p("");
        p("config <inputfile> <outputfile>:     Create jvmconfig.h");
        p("makedep ...:                         Create dependencies files");
        p("mjpp ...:                            Preprocess Java source");
        p("romtestclasses ...:                  Create romtestclasses.zip");
        p("testcases ...:                       Create testcases.make");
        p("testjarentries ...:                  Create test JAR entries");
        System.exit(1);
    }

    public static void main(String args[]) {
        String appArgs[] = getAppArgs(args);
        String app = args[0];
        try {
            if ("config".equals(app)) {
                config.Main.main(appArgs);
            } else if ("makedep".equals(app)) {
                makedep.Main.main(appArgs);
            } else if ("testcases".equals(app)) {
                tests.CollectTestCases.main(appArgs);
            } else if ("romtestclasses".equals(app)) {
                tests.CreateRomTestClasses.main(appArgs);
            } else if ("testjarentries".equals(app)) {
                tests.CreateTestJarEntries.main(appArgs);
            } else if ("mjpp".equals(app)) {
                mjpp.Main.main(appArgs);
            } else if ("help".equals(app)) {
                usage();
            } else {
                p("Unknown option \"" + app + "\"");
                p("");
                usage();
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Get the list of arguments intended for the application (which is
     * specified by args[0] to BuildTool.main()
     */
    static String[] getAppArgs(String args[]) {
        if (args.length < 1) {
            usage();
            return null;
        } else {
            String appArgs[] = new String[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                appArgs[i - 1] = args[i];
            }
            return appArgs;
        }
    }

    static void p(String s) {
        System.out.println(s);
    }
}
