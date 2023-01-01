import java.net.MalformedURLException;
import java.net.URL;

/**
 * Test code for exception breakpoints.
 *
 * @author Nathan Fiedler
 */
public class Pitcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("blah;%$@)%");
        } catch (MalformedURLException ioe) {
        }
        if (args.length == 0) {
            throw new IllegalArgumentException();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
