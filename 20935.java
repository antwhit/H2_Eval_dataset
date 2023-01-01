import cb_commonobjects.util.StringFuncs;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author B1
 */
public class StringFuncsTester {

    public StringFuncsTester() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void BasicTests() {
        StringBuffer myRemovedStr = new StringBuffer();
        Assert.assertEquals("hallo  hier", StringFuncs.removeBrackets("hallo (du arsch) hier", "(", ")", myRemovedStr));
        Assert.assertEquals(myRemovedStr.toString(), "du arsch");
        myRemovedStr = new StringBuffer();
        Assert.assertEquals(" hier", StringFuncs.removeBrackets("(du arsch) hier", "(", ")", myRemovedStr));
        Assert.assertEquals(myRemovedStr.toString(), "du arsch");
        myRemovedStr = new StringBuffer();
        Assert.assertEquals("hallo", StringFuncs.removeBrackets("hallo(du arsch)", "(", ")", myRemovedStr));
        Assert.assertEquals(myRemovedStr.toString(), "du arsch");
        myRemovedStr = new StringBuffer();
        Assert.assertEquals("hallo", StringFuncs.removeBrackets("hallo()", "(", ")", myRemovedStr));
        Assert.assertEquals(myRemovedStr.toString(), "");
    }
}
