import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class KeepForDetails implements Serializable {

    private String fileName = "";

    private int keepFor = 0;

    private Date created = new Date();

    public KeepForDetails(String name, int keep) {
        fileName = name;
        keepFor = keep;
    }

    public String getFileName() {
        return fileName;
    }

    public int getKeepFor() {
        return keepFor;
    }

    public Date getCreated() {
        return created;
    }
}
