import net.sf.snver.pileup.SNVerPool;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: weicheng hu
 * Date: 12/9/11
 * Time: 4:35 PM
 */
public class SNVerPoolTest {

    SNVerPool reader;

    @Before
    public void setUp() throws Exception {
        reader = new SNVerPool();
    }

    @Test
    public void testPileup() throws Exception {
        String[] args = { "-i", "test/pool", "-r", "test/test_ref.fa", "-l", "test/test.bed", "-c", "test/poolinfo.ini" };
        reader.pileup(args);
    }
}
