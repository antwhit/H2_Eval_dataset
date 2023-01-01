import java.io.*;

public class Unused1 {

    public Unused1() {
        try {
            FileReader fr = new FileReader("/dev/null");
        } catch (Exception e) {
        }
    }
}
