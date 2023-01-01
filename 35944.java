import java.io.File;
import junit.framework.Assert;
import org.junit.Test;

public class SimpleWriterTest {

    private SimpleWriterUsingSameFile testCandidate = new SimpleWriterUsingSameFile();

    @Test
    public void test() {
        testCandidate.write("Hallo Welt");
        Assert.assertTrue(new File("C:\\test.txt").exists());
    }
}
