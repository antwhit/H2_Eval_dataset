import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.dbe.dss.DSSData;

/**
 * A sample application that demonstrates how to use the DSS library.
 *
 * @author Intel Ireland Ltd.
 * @version 0.4.0
 */
public class DistTest {

    /**
     * Writes a string into the file
     * @throws DSSException
     */
    public final String storeHello() throws IOException {
        String out;
        String now;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        now = formatter.format(new Date());
        String hash = null;
        System.out.println("printing line...");
        out = now + " - Hello Distributed Storage System!!";
        out += "\n...here is another line(!)";
        out += "\n...printing complete.";
        hash = DSSData.write(out.getBytes(), 10000);
        System.out.println("writeHello() complete.");
        return hash;
    }

    /**
     * Reads a string from the file
     * @throws DSSException
     */
    public final void retrieveHello(String hash) throws IOException {
        String message = null;
        message = new String(DSSData.read(hash));
        System.out.println("The message read from the dss was:");
        System.out.println(message);
    }

    /**
     * Runs the DistTest sample
     *
     * @param args command line arguments (should be an empty array)
     */
    public static void main(final String[] args) {
        DistTest hello;
        try {
            hello = new DistTest();
            System.out.println("Calling storeHello()");
            String hash = hello.storeHello();
            System.out.println("Calling retrieveHello()");
            hello.retrieveHello(hash);
            String remoteHash = "IUNUJNOnrB3HC0wBZfUrYbYi6IM=";
            System.out.println("Trying hash: " + remoteHash);
            hello.retrieveHello(remoteHash);
            System.out.println("done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
