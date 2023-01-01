import java.io.*;
import java.util.*;

/** A demo program which uses the MSXMLParser to 
 *  read in scans from a file you specify on the
 *  command line.
 *
 */
public class TestParser {

    public static void main(String[] asArg) {
        Scan myScan = null;
        if (asArg.length > 1) {
            MSXMLParser myParser = new MSXMLParser(asArg[0]);
            for (int i = 1; i < asArg.length; i++) {
                myScan = myParser.rap(Integer.parseInt(asArg[i]));
                System.out.println(myScan);
            }
        } else {
            System.out.println("Invalid number of arguments: TestParser fileName scanNumber1 <scanNumber2>..");
        }
    }
}
