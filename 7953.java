import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.dbe.dss.DSSData;

/**
 * A simple client application that demonstrates how to use the DSS library.
 *
 * @author Intel Ireland Ltd.
 * @version 0.5.0
 */
public class DSSRetrieve {

    /**
     * Creates a message and stores it on the DSS.
     *
     * @return the unique identifier corresponding to the message.
     * @throws IOException if an I/O error occurs
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
        KeyGenerator keygen;
        SecretKey aesKey = null;
        try {
            keygen = KeyGenerator.getInstance("AES");
            aesKey = keygen.generateKey();
            System.out.println("AES Key:" + keygen.toString());
            String hashEnc = DSSData.writeEncrypted("AES", aesKey.getEncoded(), out.getBytes(), 10000);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Unexpected NoSuchAlgorithmException: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println("Unexpected InvalidKeyException: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println("Unexpected NoSuchPaddingException: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println("Unexpected IllegalBlockSizeException: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println("Unexpected BadPaddingException: " + e.getMessage());
        }
        System.out.println("writeHello() complete.");
        return hash;
    }

    /**
     * Reads the requested content from the DSS.
     *
     * @param hash the unique identifier of the content to be retrieved
     * @throws IOException if an I/O error occurs
     */
    public final void retrieveHello(final String hash) throws IOException {
        String message = null;
        message = new String(DSSData.read(hash));
        System.out.println("The message read from the dss was:");
        System.out.println(message);
    }

    /**
     * Runs the DSSHelloWorld example application.
     *
     * @param args command line arguments (all are ignored)
     */
    public static void main(final String[] args) {
        DSSRetrieve hello;
        try {
            hello = new DSSRetrieve();
            String hash = "YPJwB3gZ0CDuCYdwbCqTm5ACpzg=";
            System.out.println("Calling retrieveHello(" + hash + ")");
            hello.retrieveHello(hash);
            System.out.println("done!");
        } catch (Exception e) {
            System.out.println("Unexpected Exception: " + e.getMessage());
        }
    }
}
