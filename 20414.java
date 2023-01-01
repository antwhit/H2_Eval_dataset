import java.io.File;
import org.gmod.gds.client.SrsQueryEngineClient;
import org.gmod.gds.soap.SrsQueryEngine;
import org.gmod.util.ArrayUtils;

/**
 * @author jogoodma
 * 
 */
public class SrsClient5 {

    public static void main(String[] args) {
        try {
            SrsQueryEngineClient client = new SrsQueryEngineClient();
            client.setClient_timeout(600000);
            SrsQueryEngine service = client.getSrsService();
            service.setUseAttachment(true);
            String[] results = service.allFieldSearch("swissprot", "eotaxin*");
            ArrayUtils.printArray(results);
            client.getAttachment(new File("SrsClient5a.txt"));
        } catch (Exception ex) {
            System.err.println("Caught exception: " + ex);
            ex.printStackTrace();
        }
    }
}
