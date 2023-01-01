import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * ��˵��:
 * 
 * @author ��� E-mail:qihao@taobao.com
 * @version ����ʱ�䣺2008-3-8 ����10:19:41 ByteBufferTest.java
 */
public class ByteBufferTest {

    public static void main(String[] args) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(256);
        while (true) {
            int c = System.in.read();
            if (c == -1) break;
            buf.put((byte) c);
            if (c == '\n') {
                buf.flip();
                byte[] content = new byte[buf.limit()];
                buf.get(content);
                System.out.print(new String(content));
                buf.clear();
            }
        }
    }
}
