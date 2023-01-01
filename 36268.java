import static org.junit.Assert.*;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import org.junit.Before;
import org.junit.Test;

public class RDFDecoder_unittest {

    private RDFDecoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new RDFDecoder();
    }

    @Test
    public final void testReturnObject() {
        FileInputStream stream;
        try {
            stream = new FileInputStream("button_unittest.xml");
        } catch (FileNotFoundException e) {
            fail("Stream not opened properly");
        }
        JButton button;
        try {
            button = (JButton) decoder.returnObject(new FileInputStream("button_unittest.xml"));
            assertNotNull("Object should not be null", button);
        } catch (Exception e) {
            fail("Can't decode object.");
        }
    }
}
