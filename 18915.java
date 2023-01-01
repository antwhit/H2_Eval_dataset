import java.io.*;

/**
 * @author Janesh Kodikara
 */
public class KeyGenerator {

    static int keyIndex = 0;

    public static void main(String[] args) throws Exception {
        final String fileName = args[0];
        System.out.println(" It's working");
        readReplace(fileName, "FOREIGN", "FKIndex");
        readReplaceWithoutKey(fileName, "INDEX \\S*_UNIndex.", "");
        readReplace(fileName, "UNIQUE", "UNIndex");
        readReplace(fileName, ",\\n", ",");
    }

    public static void readReplace(String fname, String oldPattern, String replPattern) {
        String line;
        String replaceString;
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(fname);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            while ((line = reader.readLine()) != null) {
                replaceString = replPattern + keyIndex++ + " " + oldPattern;
                line = line.replaceAll(oldPattern, replaceString);
                sb.append(line + "\n");
            }
            reader.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(fname));
            out.write(sb.toString());
            out.close();
        } catch (Throwable e) {
            System.err.println("*** exception ***" + e.getMessage());
        }
    }

    public static void readReplaceWithoutKey(String fname, String oldPattern, String replPattern) {
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(fname);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll(oldPattern, replPattern);
                sb.append(line + "\n");
            }
            reader.close();
            BufferedWriter out = new BufferedWriter(new FileWriter(fname));
            out.write(sb.toString());
            out.close();
        } catch (Throwable e) {
            System.err.println("*** exception ***");
        }
    }
}
