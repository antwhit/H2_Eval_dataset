import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BitReader {

    public BitReader(RandomAccessFile inFile) {
        this.inFile = inFile;
    }

    public boolean read() throws IOException {
        if (pos == buf.length) {
            int bytesRead = inFile.read(buf);
            if (bytesRead < 0) {
                throw new EOFException();
            }
            pos = buf.length - bytesRead;
            System.arraycopy(buf, 0, buf, pos, bytesRead);
        }
        boolean result = (buf[pos] & bitmask) != 0;
        if (bitmask == 0x80) {
            advance();
        } else {
            bitmask <<= 1;
        }
        return result;
    }

    private void advance() {
        bitmask = 0x1;
        ++pos;
    }

    private int bitmask = 0x1;

    private final byte[] buf = new byte[1024];

    private RandomAccessFile inFile;

    private int pos = 1024;
}
