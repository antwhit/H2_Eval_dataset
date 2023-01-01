import java.io.*;
import java.util.*;

public class Globals {

    static Hashtable mappings = new Hashtable();

    static Hashtable mapTargets = new Hashtable();

    static Hashtable noChangeIds = new Hashtable();

    static Hashtable useIds = new Hashtable();

    static File inpDir, outDir;

    static boolean mainExists;

    static String map(String str) {
        Object obj = mappings.get(str);
        if (obj != null) {
            return (String) obj;
        }
        if (useIds.isEmpty()) {
            String newId = "O0" + counter++;
            mappings.put(str, newId);
            return newId;
        } else {
            obj = useIds.keys().nextElement();
            useIds.remove(obj);
            String newId = (String) obj;
            mappings.put(str, newId);
            return newId;
        }
    }

    static int counter = 0;
}
