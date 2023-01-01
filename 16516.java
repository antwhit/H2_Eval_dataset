import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.io.EOFException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test encode/decode of requests
 */
@RunWith(Parameterized.class)
public class xG8RRequestTest extends xG8RMessageTest {

    protected String expCommand;

    protected String[] expParams;

    /**
	 * Constructor for parameterized test to set expected values
	 * 
	 * @param expCommand expected command
	 * @param expFunction expected function
	 * @param expParams expected parameters
	 * @param expCookies expected cookie value
	 * @param expSerialized expected serialized value
	 */
    public xG8RRequestTest(String expCommand, String expFunction, String[] expParams, CookieList expCookies, String expSerialized) {
        super(expFunction, expCookies, expSerialized);
        this.expCommand = expCommand;
        this.expParams = expParams;
    }

    /**
	 * Set up parameterized tests
	 * 
	 * @return constructor parameters
	 * @throws UnsupportedEncodingException if no such encoding
	 * @throws G8RException 
	 */
    @Parameters
    public static Collection<Object[]> data() throws UnsupportedEncodingException, G8RException {
        CookieList oneCookie = new CookieList();
        oneCookie.add("One", "1");
        CookieList fiveCookies = new CookieList();
        fiveCookies.add("One", "1");
        fiveCookies.add("Two", "2");
        fiveCookies.add("Three", "3");
        fiveCookies.add("Four", "4");
        fiveCookies.add("Five", "5");
        Object[][] data = new Object[][] { { "RUN", "F1", new String[] {}, new CookieList(), "G8R/1.0 RUN F1\r\n\r\n" }, { "RUN", "F1", new String[] { "P1" }, oneCookie, "G8R/1.0 RUN F1 P1\r\nOne=1\r\n\r\n" }, { "RUN", "F1", new String[] { "P1", "P2", "P3" }, fiveCookies, "G8R/1.0 RUN F1 P1 P2 P3\r\nFive=5\r\nFour=4\r\nOne=1\r\nThree=3\r\nTwo=2\r\n\r\n" } };
        return Arrays.asList(data);
    }

    /**
	 * Test incorrect command
	 * @throws EOFException if test fails
	 * @throws G8RException expected for bad command
	 */
    @Test(expected = G8RException.class)
    public void testDecodeBadCommand() throws EOFException, G8RException {
        InputStream in = makeDecodableStream(expSerialized.replace("RUN", "FUN"));
        parseMessage(in);
    }

    @Override
    protected void verifyExpectedMessage(G8RMessage msg) {
        super.verifyExpectedMessage(msg);
        G8RRequest rMsg = (G8RRequest) msg;
        assertEquals(expCommand, rMsg.getCommand());
        assertEquals(expFunction, rMsg.getFunction());
        assertArrayEquals(expParams, rMsg.getParams());
    }

    @Override
    public G8RMessage parseMessage(InputStream in) throws G8RException, EOFException {
        return new G8RRequest(in);
    }

    @Override
    public G8RMessage generateMessage() throws G8RException {
        return new G8RRequest(expCommand, expFunction, expParams, expCookies);
    }
}
