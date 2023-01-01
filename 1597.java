import java.io.File;

public class T6405099 {

    public static void main(String[] args) {
        File bad = new File("bad");
        bad.mkdir();
        bad.setReadable(false);
        bad.setExecutable(false);
    }
}
