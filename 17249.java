import java.io.File;
import org.gmod.gds.client.SrsQueryEngineClient;
import org.gmod.gds.soap.SrsQueryEngine;
import org.gmod.util.ArrayUtils;

/**
 * @author jogoodma
 * 
 */
public class SrsClient {

    public static void main(String[] args) {
        try {
            SrsQueryEngineClient client = new SrsQueryEngineClient();
            SrsQueryEngine service = client.getSrsService();
            service.setResultLimit(300);
            service.setResultStart(200);
            service.setFasta(true);
            String[] result = service.idSearch("genbank", "AF*");
            ArrayUtils.printArray(result);
            SrsQueryEngineClient client2 = new SrsQueryEngineClient();
            SrsQueryEngine service2 = client2.getSrsService();
            service2.setUseAttachment(true);
            String[] result2 = service2.idSearch(new String[] { "genbanknew", "swissnew", "uniprot" }, "AF*");
            ArrayUtils.printArray(result2);
            client2.getAttachment(new File("SrsClient.txt"));
        } catch (java.rmi.RemoteException re) {
            System.err.println("Caught remote exception: " + re);
        } catch (Exception ex) {
            System.err.println("Caught exception: " + ex);
            ex.printStackTrace();
        }
    }
}
