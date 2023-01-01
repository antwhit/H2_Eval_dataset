import java.applet.Applet;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * A small applet which redirects to a different page based on the version of
 * Java in which it is run. The applet takes 3 parameters:
 * <ul>
 *   <li>minRequiredJavaVersion - the minimum required version of Java.
 *   <li>goodJavaURL - the URL to redirect to if the version of Java is equal to
 *       or higher than minRequiredJavaVersion.
 *   <li>badJavaURL - The URL to redirect to the if the version of Java is lower
 *       than minRequiredJavaVersion.
 * </ul>
 */
public class DetectJava extends Applet {

    /**
   * Invokes </code>getAppletContext().showDocument()</code> on the appropriate
   * URL.
   */
    public void start() {
        String minRequiredJavaVersion = getParameter("minRequiredJavaVersion");
        String javaVersion = System.getProperty("java.version");
        String url = (javaVersion.compareTo(minRequiredJavaVersion) < 0) ? getParameter("badJavaURL") : getParameter("goodJavaURL");
        try {
            getAppletContext().showDocument(new URL(getDocumentBase(), url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
