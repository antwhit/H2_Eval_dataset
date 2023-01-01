import java.io.*;

class SessionRecorder {

    protected FileOutputStream f;

    protected DataOutputStream df;

    protected long startTime, lastTimeOffset;

    protected byte[] buffer;

    protected int bufferSize;

    protected int bufferBytes;

    public SessionRecorder(String name, int bufsize) throws IOException {
        f = new FileOutputStream(name);
        df = new DataOutputStream(f);
        startTime = System.currentTimeMillis();
        lastTimeOffset = 0;
        bufferSize = bufsize;
        bufferBytes = 0;
        buffer = new byte[bufferSize];
    }

    public SessionRecorder(String name) throws IOException {
        this(name, 65536);
    }

    public void close() throws IOException {
        try {
            flush();
        } catch (IOException e) {
        }
        df = null;
        f.close();
        f = null;
        buffer = null;
    }

    public void writeHeader() throws IOException {
        df.write("FBS 001.000\n".getBytes());
    }

    public void writeByte(int b) throws IOException {
        prepareWriting();
        buffer[bufferBytes++] = (byte) b;
    }

    public void writeShortBE(int v) throws IOException {
        prepareWriting();
        buffer[bufferBytes++] = (byte) (v >> 8);
        buffer[bufferBytes++] = (byte) v;
    }

    public void writeIntBE(int v) throws IOException {
        prepareWriting();
        buffer[bufferBytes] = (byte) (v >> 24);
        buffer[bufferBytes + 1] = (byte) (v >> 16);
        buffer[bufferBytes + 2] = (byte) (v >> 8);
        buffer[bufferBytes + 3] = (byte) v;
        bufferBytes += 4;
    }

    public void writeShortLE(int v) throws IOException {
        prepareWriting();
        buffer[bufferBytes++] = (byte) v;
        buffer[bufferBytes++] = (byte) (v >> 8);
    }

    public void writeIntLE(int v) throws IOException {
        prepareWriting();
        buffer[bufferBytes] = (byte) v;
        buffer[bufferBytes + 1] = (byte) (v >> 8);
        buffer[bufferBytes + 2] = (byte) (v >> 16);
        buffer[bufferBytes + 3] = (byte) (v >> 24);
        bufferBytes += 4;
    }

    public void write(byte b[], int off, int len) throws IOException {
        prepareWriting();
        while (len > 0) {
            if (bufferBytes > bufferSize - 4) flush(false);
            int partLen;
            if (bufferBytes + len > bufferSize) {
                partLen = bufferSize - bufferBytes;
            } else {
                partLen = len;
            }
            System.arraycopy(b, off, buffer, bufferBytes, partLen);
            bufferBytes += partLen;
            off += partLen;
            len -= partLen;
        }
    }

    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    public void flush(boolean updateTimeOffset) throws IOException {
        if (bufferBytes > 0) {
            df.writeInt(bufferBytes);
            df.write(buffer, 0, (bufferBytes + 3) & 0x7FFFFFFC);
            df.writeInt((int) lastTimeOffset);
            bufferBytes = 0;
            if (updateTimeOffset) lastTimeOffset = -1;
        }
    }

    public void flush() throws IOException {
        flush(true);
    }

    protected void prepareWriting() throws IOException {
        if (lastTimeOffset == -1) lastTimeOffset = System.currentTimeMillis() - startTime;
        if (bufferBytes > bufferSize - 4) flush(false);
    }
}
