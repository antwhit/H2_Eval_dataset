import java.io.*;
import java.util.Vector;
import com.c5corp.c5gps.*;
import com.c5corp.c5gps.filters.*;

/**
 * Example demonstrating the track segmentation abilities of the TrackLog class.
 * @author Brett Stalbaum
 *
 */
public class TrackSegmentationExample {

    /** main
	 * @param args
	 */
    public static void main(String args[]) {
        File csv = new File("TRACKS/JIAYUGUAN.csv");
        CsvTrackLogListFilter filter = new CsvTrackLogListFilter(csv);
        TrackLogList inputList = filter.readFile();
        TrackLog input = null;
        if (inputList.hasMoreElements()) {
            input = inputList.nextElement();
        }
        System.out.println(input);
        TrackLog[] segmented = input.segmentTrackLog();
        for (int i = 0; i < segmented.length; i++) {
            System.out.println("Segmented " + i + ":");
            System.out.println(segmented[i]);
        }
        Vector<TrackLog> vecy = new Vector<TrackLog>();
        for (int i = 0; i < segmented.length; i++) {
            vecy.add(segmented[i]);
        }
        TrackLog rejoined = new TrackLog(vecy);
        System.out.println("Rejoined log");
        System.out.println(rejoined);
    }
}
