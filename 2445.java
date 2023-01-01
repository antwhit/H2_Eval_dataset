import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class SvnProperties {

    private Map<Integer, String> tags = new Hashtable<Integer, String>();

    private Map<Integer, String> branches = new Hashtable<Integer, String>();

    SvnProperties(File props) throws IOException {
        FileReader fr = new FileReader(props);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            parsePropLine(line);
            line = br.readLine();
        }
        br.close();
    }

    private void parsePropLine(String s) {
        String[] elem = s.split(",");
        if (elem.length == 4) {
            if (elem[3].equals("tag")) {
                tags.put(Integer.parseInt(elem[0]), elem[1]);
            } else if (elem[3].equals("branch")) {
                branches.put(Integer.parseInt(elem[0]), elem[1]);
            }
        }
    }

    String getLastTag() {
        Iterator<Integer> s = tags.keySet().iterator();
        int buf = 0;
        while (s.hasNext()) {
            int valu = (int) s.next();
            if (valu > buf) {
                buf = valu;
            }
        }
        return tags.get(buf);
    }
}
