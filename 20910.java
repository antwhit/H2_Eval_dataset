import java.io.IOException;
import java.io.InputStream;

class StringInputStream extends InputStream {

    StringInputStream(String s) {
        this.buf = s.toCharArray();
    }

    public int read() throws IOException {
        if (index < buf.length) {
            return buf[index++];
        }
        return -1;
    }

    private final char[] buf;

    private int index = 0;
}
