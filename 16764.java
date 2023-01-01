import java.io.*;

public class EncodingTest {

    static String[] s = { "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16" };

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean failed = false;
        for (int i = 0; i < s.length; i++) {
            try {
                System.out.println("Testing " + s[i]);
                OutputStreamWriter o = new OutputStreamWriter(out, s[i]);
            } catch (Exception e) {
                failed = true;
                e.printStackTrace();
            }
        }
        if (!failed) System.out.println("OK"); else System.out.println("FAILED");
    }
}
