import javax.microedition.rms.*;

/**
 *
 * @author Administrator
 */
public class SortCompare implements RecordComparator {

    public int compare(byte[] rec1, byte[] rec2) {
        String str1 = new String(rec1);
        String str2 = new String(rec2);
        int result = str1.compareTo(str2);
        if (result == 0) return RecordComparator.EQUIVALENT; else if (result < 0) return RecordComparator.PRECEDES; else return RecordComparator.FOLLOWS;
    }
}
