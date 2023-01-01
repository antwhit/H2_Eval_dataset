public class ClientObj {

    public ClientObj(int xPriority, long xThreadId, long xTimestamp, boolean xisReader) {
        priority = xPriority;
        threadId = xThreadId;
        timestamp = xTimestamp;
        isReader = xisReader;
    }

    boolean isReader;

    int priority;

    long threadId;

    long timestamp;
}
