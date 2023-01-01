import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * A text extractor for Excel files. Returns the textual content of the file,
 * suitable for indexing by something like Lucene, but not really intended for
 * display to the user. To turn an excel file into a CSV or similar, then see
 * the XLS2CSVmra example
 * 
 * @see org.apache.poi.hssf.eventusermodel.examples.XLS2CSVmra
 */
public class ExcelTxtExtractor {

    /**
	 * Command line extractor, so people will stop moaning that they can't just
	 * run this.
	 */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Use:");
            System.err.println("   java org.apache.poi.hssf.extractor.ExcelExtractor <filename>");
            System.exit(1);
        }
        FileInputStream fin = new FileInputStream(args[0]);
        ExcelExtractor extractor = new ExcelExtractor(new POIFSFileSystem(fin));
        System.out.println(extractor.getText());
    }
}
