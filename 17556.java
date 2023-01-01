import java.io.*;

public class CommonTools {

    private static final int BUFFER_SIZE = 1024;

    public static String getString(InputStream inputStream) throws IOException {
        if (inputStream == null) return null;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream), BUFFER_SIZE);
        int charsRead;
        char[] copyBuffer = new char[BUFFER_SIZE];
        StringBuffer sb = new StringBuffer();
        while ((charsRead = in.read(copyBuffer, 0, BUFFER_SIZE)) != -1) sb.append(copyBuffer, 0, charsRead);
        in.close();
        return sb.toString();
    }
}
