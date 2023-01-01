import murlen.util.fscript.*;
import java.io.*;

public class FSTest {

    public static final void main(String args[]) throws FSException, IOException {
        BasicIO fs = new BasicIO();
        FileReader f = new FileReader(args[0]);
        fs.load(f);
        fs.run();
    }
}
