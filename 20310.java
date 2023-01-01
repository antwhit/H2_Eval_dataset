import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/**
 * This is a wrapper for executing tools relating to Java PathFinder, especially
 * those in the gov.nasa.jpf.tools package.  This wrapper takes care of locating
 * the JPF install root and setting up an appropriate classpath.
 * <br><br>
 * See <code>usage</code> below for more info.
 *  
 * @author peterd
 */
public class RunTool extends RunJPF {

    public static void main(String[] args) throws Throwable {
        if (args.length == 0) {
            for (String s : toolUsage) {
                System.err.println(s);
            }
            System.exit(1);
        }
        computeJpfHome(args);
        addJpfClassPath();
        File[] libs = buildToolsLib.listFiles();
        for (File lib : libs) {
            String name = lib.getName();
            if (lib.isFile() && lib.canRead() && name.startsWith("junit") && name.endsWith(".jar")) {
                addToClassPath(Collections.singletonList(lib));
            }
        }
        if (System.getenv("VERBOSE") != null) {
            System.err.println("CLASSPATH: " + cpString);
        }
        String toolClassName = args[0];
        String[] targetArgs = new String[args.length];
        targetArgs[0] = "+jpf.basedir=" + jpfHome;
        System.arraycopy(args, 1, targetArgs, 1, args.length - 1);
        if (System.getenv("VERBOSE") != null) {
            System.err.print("main & args: " + toolClassName);
            for (int i = 0; i < targetArgs.length; i++) {
                System.err.print(" ");
                System.err.print(targetArgs[i]);
            }
            System.err.println();
        }
        try {
            ClassLoader cl = getClassPathClassLoader();
            Class<?> mainClass;
            try {
                mainClass = cl.loadClass(toolClassName);
            } catch (ClassNotFoundException e) {
                try {
                    mainClass = cl.loadClass("gov.nasa.jpf.tools." + toolClassName);
                } catch (ClassNotFoundException inner) {
                    throw e;
                }
            }
            invokeMain(mainClass, targetArgs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Make sure the class name is correct and that you have compiled JPF (into build/jpf).");
            System.err.println("To compile JPF:  java RunAnt");
            System.exit(1);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InvocationTargetException e) {
            printTrimmedStackTrace(e.getTargetException());
            System.exit(1);
        }
    }

    static String[] toolUsage = { "This is a wrapper for executing tools relating to Java PathFinder, especially", "those in the gov.nasa.jpf.tools package.  This wrapper takes care of locating", "the JPF install root and setting up an appropriate classpath.", "", "Usage: java [<vm-options>..] RunTool <toolname> [<tool-args>...]", "", "For more information on <vm-options>, run \"java -help\".", "<toolname> is the name of the class to execute.  Either the full name or name", "           within gov.nasa.jpf.tools can be specified.", "<tool-args> is usually the same as <jpf-args>, as described in", "            \"java RunJPF -help\".", "", "Examples:", "  From the JPF root directory:", "    java -Xmx64m RunTool JavaJPF +vm.classpath=path/to/model MyModel", "  From directory with model classes:", "    java -Xmx1024m -cp path/to/jpf:. RunTool SearchMonitor MyModel" };
}
