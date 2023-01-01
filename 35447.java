import java.util.Random;

public class Udsagnsord {

    private String udsagnsordet = "udsagnsord";

    private String[] strArrayUdsagnsord;

    private ReadTextFiles readTextFilesReader;

    public Udsagnsord() {
        readTextFilesReader = new ReadTextFiles();
        strArrayUdsagnsord = readTextFilesReader.getStringArrayWithVerbs();
        Random r = new Random();
        int index = r.nextInt(strArrayUdsagnsord.length);
        udsagnsordet = strArrayUdsagnsord[index] + "r";
    }

    public String toString() {
        String output = udsagnsordet;
        return output;
    }
}
