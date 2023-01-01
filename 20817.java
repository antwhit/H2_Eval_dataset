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
 * Test encode/decode of responses
 */
@RunWith(Parameterized.class)
public class G8RResponseTest extends G8RMessageTest {

    protected String expStatus;

    protected String expMessage;

    /**
     * Constructor for parameterized test to set expected values
     * 
     * @param expStatus expected status
     * @param expFunction expected function
     * @param expCookies expected cookie value
     * @param expSerialized expected serialized value
     */
    public G8RResponseTest(String expStatus, String expFunction, String expMessage, CookieList expCookies, String expSerialized) {
        super(expFunction, expCookies, expSerialized);
        this.expStatus = expStatus;
        this.expMessage = expMessage;
    }

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
        Object[][] data = new Object[][] { { "OK", "F1", "", new CookieList(), "G8R/1.0 OK F1 \r\n\r\n" }, { "OK", "F1", "Message One", oneCookie, "G8R/1.0 OK F1 Message One\r\nOne=1\r\n\r\n" }, { "OK", "F1", "Message One", fiveCookies, "G8R/1.0 OK F1 Message One\r\nFive=5\r\nFour=4\r\nOne=1\r\nThree=3\r\nTwo=2\r\n\r\n" }, { "ERROR", "F1", "", new CookieList(), "G8R/1.0 ERROR F1 \r\n\r\n" }, { "ERROR", "F1", "Message One", oneCookie, "G8R/1.0 ERROR F1 Message One\r\nOne=1\r\n\r\n" }, { "ERROR", "F1", "Message One", fiveCookies, "G8R/1.0 ERROR F1 Message One\r\nFive=5\r\nFour=4\r\nOne=1\r\nThree=3\r\nTwo=2\r\n\r\n" } };
        return Arrays.asList(data);
    }

    /**
     * Test incorrect status
     * @throws EOFException if test fails
     * @throws G8RException expected for bad status
     */
    @Test(expected = G8RException.class)
    public void testDecodeBadStatus() throws EOFException, G8RException {
        InputStream in = makeDecodableStream(expSerialized.replace("OK", "BLAH").replace("ERROR", "BLAH"));
        parseMessage(in);
    }

    /**
     * Test match of given msg and expected values
     * 
     * @param msg msg to compare
     */
    @Override
    protected void verifyExpectedMessage(G8RMessage msg) {
        super.verifyExpectedMessage(msg);
        G8RResponse rMsg = (G8RResponse) msg;
        assertEquals(expStatus, rMsg.getStatus());
        assertEquals(expMessage, rMsg.getMessage());
    }

    @Override
    public G8RMessage parseMessage(InputStream in) throws G8RException, EOFException {
        return new G8RResponse(in);
    }

    @Override
    public G8RMessage generateMessage() throws G8RException {
        return new G8RResponse(expStatus, expFunction, expMessage, expCookies);
    }
}
