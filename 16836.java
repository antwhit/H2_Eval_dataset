/**
 * Represent a trace record.
 * A trace record may contain HPM counter values or a user defined record.
 *
 * @author Peter F. Sweeney
 * @date 2/12/2003
 */
public abstract class TraceRecord {

    public static int COUNTER_TYPE = 1;

    public static int START_APP_TYPE = 2;

    public static int COMPLETE_APP_TYPE = 3;

    public static int START_APP_RUN_TYPE = 4;

    public static int COMPLETE_APP_RUN_TYPE = 5;

    public static int EXIT_TYPE = 6;

    public static int PADDING_TYPE = 10;

    /**
   * print trace record
   */
    public abstract boolean print();
}
