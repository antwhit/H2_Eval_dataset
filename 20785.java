import java.util.*;

public class UploadCompleteEvent extends EventObject {

    private String receivedData;

    public UploadCompleteEvent(Object source, String receivedData) {
        super(source);
        this.receivedData = receivedData;
    }

    public String getReceivedData() {
        return this.receivedData;
    }
}
