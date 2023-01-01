import java.io.InputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slasoi.monitoring.fbk.impl.FbkRCGServiceImpl;

/** Class to test the b6 usecase */
public class B6Test {

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
	 * 
	 */
    @Test
    public void testEventParsing() {
        try {
            System.out.println("*****************************************");
            System.out.println("START TEST testB6");
            System.out.println("*****************************************");
            FbkRCGServiceImpl fbkRcg = new FbkRCGServiceImpl();
            String slaFileName = "B6SLAAfter.xml";
            InputStream is = B6Test.class.getResourceAsStream("/" + slaFileName);
            String configuration_id = "gggg:ggggg:gggg:gggg";
            fbkRcg.startMonitoring(configuration_id);
            fbkRcg.addSLAToConfiguration(is);
            Thread.sleep(114000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
