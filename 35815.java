import java.io.*;
import org.gmod.gds.client.SrsQueryEngineClient;
import org.gmod.gds.soap.*;
import org.gmod.util.ArrayUtils;

/**
 * @author jogoodma
 * 
 */
public class SrsClient2 {

    public static void main(String[] args) {
        try {
            SrsQueryEngineClient client = new SrsQueryEngineClient();
            SrsQueryEngine service = client.getSrsService();
            service.setUseAttachment(true);
            service.setFasta(true);
            String[] accession = { "104K_THEPA", "108_LYCES", "10KD_VIGUN", "110K_PLAKN", "11S3_HELAN", "11SB_CUCMA", "120K_RICRI", "128U_DROME", "12AH_CLOS4", "12KD_FRAAN", "12KD_MYCSM", "12S1_ARATH", "12S2_ARATH", "13S1_FAGES", "13S2_FAGES", "13S3_FAGES", "13SB_FAGES", "140U_DROME", "1431_ARATH", "1431_ECHGR", "1431_ECHMU", "1431_ENTHI", "1431_LYCES", "1431_MAIZE", "1431_SCHMA", "1432_ARATH", "1432_ECHGR", "1432_ECHMU", "1432_ENTHI", "1432_LYCES", "1432_MAIZE", "1432_SCHMA", "1433_ARATH", "1433_CAEEL", "1433_CANAL", "1433_CHLRE", "1433_DICDI", "1433_EIMTE", "1433_ENTHI", "1433_FUCVE", "1433_HELAN", "1433_LILLO", "1433_LYCES", "1433_MESCR", "1433_NEOCA", "1433_OENHO", "1433_ORYSA", "1433_PEA", "1433_SOLTU", "1433_SPIOL", "1433_TOBAC", "1433_TRIHA", "1433_XENLA", "1434_ARATH", "1434_CAEEL", "1434_LYCES", "1434_SOLTU", "1435_ARATH", "1435_LYCES", "1435_SOLTU", "1436_ARATH", "1436_LYCES", "1437_ARATH", "1437_LYCES", "1438_ARATH", "1438_LYCES", "1439_ARATH", "1439_LYCES", "143A_ARATH", "143A_HORVU", "143A_LYCES", "143A_SOYBN", "143A_TOBAC", "143A_VICFA", "143B_ARATH", "143B_BOVIN", "143B_HORVU", "143B_HUMAN", "143B_MOUSE", "143B_RAT", "143B_SOYBN", "143B_TOBAC", "143B_VICFA", "143C_ARATH", "143C_SOYBN", "143C_TOBAC", "143D_SOYBN", "143D_TOBAC", "143E_DROME", "143E_HUMAN", "143E_TOBAC", "143F_HUMAN", "143F_MOUSE", "143F_TOBAC", "143G_BOVIN", "143G_HUMAN", "143S_HUMAN", "143S_MOUSE", "143S_SHEEP", "143T_HUMAN" };
            String[] result = service.bulkIdSearch("uniprot", accession);
            client.getAttachment(new File("SrsClient2a.txt"));
            ArrayUtils.printArray(result);
            SrsQueryEngineClient client2 = new SrsQueryEngineClient();
            SrsQueryEngine service2 = client2.getSrsService();
            service2.setUseAttachment(true);
            service2.setFieldList(new String[] { "des", "cc", "org" });
            String[] result2 = service2.bulkIdFieldSearch("uniprot", accession, "des", "kinase*");
            client2.getAttachment(new File("SrsClient2b.txt"));
            ArrayUtils.printArray(result2);
        } catch (java.rmi.RemoteException re) {
            System.err.println("Caught remote exception: " + re);
        } catch (Exception ex) {
            System.err.println("Caught exception: " + ex);
            ex.printStackTrace();
        }
    }
}
