import java.net.URL;
import java.net.URLClassLoader;
import com.webobjects.foundation.NSLog;
import er.extensions.ERXApplication;

public class Application extends ERXApplication {

    public static void main(String argv[]) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        boolean foundSources = false;
        if (cl instanceof URLClassLoader) {
            for (URL url : ((URLClassLoader) cl).getURLs()) {
                if (url.getPath().endsWith("/Sources/")) {
                    foundSources = true;
                }
            }
        }
        if (!foundSources) {
            System.err.println("NOTE: To use rapid turnaround with this example" + " you MUST add the Sources folder to the classpath of your" + " run configuration in Eclipse");
        }
        ERXApplication.main(argv, Application.class);
    }

    public Application() {
        NSLog.out.appendln("Welcome to " + this.name() + " !");
    }
}
