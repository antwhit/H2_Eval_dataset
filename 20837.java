import static org.robotframework.javalib.util.Logger.log;
import java.io.IOException;
import java.util.Arrays;
import org.robotframework.javalib.util.ArrayUtil;
import org.robotframework.jvmconnector.server.ApplicationLauncher;
import org.robotframework.jvmconnector.server.RmiService;

public class RMILauncher {

    private static RmiService rmiService = new RmiService();

    private static ApplicationLauncher applicationLauncher = new ApplicationLauncher();

    private final String javaExecutable;

    private final String jvmArgs;

    static {
        log("RMILauncher class loaded");
    }

    public static void main(String[] args) throws Exception {
        log("RMILauncher: " + Arrays.asList(args));
        if (args.length < 2) throw new IllegalArgumentException("Usage: java RmiServiceLibrary [jvmArgs] rmiConfigFilePath applicationClassName [applicationArgs]");
        rmiService.start(args[0]);
        String[] restOfTheArgs = extractRestOfTheArgs(args);
        log("starting the application '" + args[1] + " with args '" + Arrays.asList(restOfTheArgs) + "'");
        applicationLauncher.launchApplication(args[1], restOfTheArgs);
    }

    public RMILauncher() {
        this("java");
    }

    public RMILauncher(String javaExecutable) {
        this(javaExecutable, "");
    }

    public RMILauncher(String javaExecutable, String jvmArgs) {
        this.javaExecutable = javaExecutable;
        this.jvmArgs = jvmArgs;
    }

    public void startApplicationAndRMIService(String rmiConfigFilePath, String applicationClassName, String[] args) {
        try {
            String selfExecutableCommand = createSelfExecutableCommand(rmiConfigFilePath, applicationClassName, args);
            log("self executing: (" + selfExecutableCommand + ")");
            Runtime.getRuntime().exec(selfExecutableCommand);
        } catch (IOException e) {
            log(e);
            throw new RuntimeException(e);
        }
    }

    private String createSelfExecutableCommand(String rmiConfigFilePath, String applicationClassName, String[] args) {
        return javaExecutable + " " + jvmArgs + " " + this.getClass().getName() + " " + rmiConfigFilePath + " " + applicationClassName + " " + toString(args);
    }

    private String toString(String[] args) {
        StringBuilder argBuf = new StringBuilder();
        for (String arg : args) {
            argBuf.append(arg + " ");
        }
        return argBuf.toString().trim();
    }

    private static String[] extractRestOfTheArgs(String[] args) {
        return ArrayUtil.<String>copyOfRange(args, 2, args.length);
    }
}
