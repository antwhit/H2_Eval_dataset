import java.io.*;
import java.net.*;
import sun.net.NetworkServer;

public class DiagServer extends NetworkServer {

    public static void main(String[] args) {
        int port = 80;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
        }
        try {
            DiagServer ds = new DiagServer();
            ds.startServer(port);
            System.err.println("DiagServer started on port " + port);
            System.err.println("");
        } catch (IOException ioe) {
            System.err.println("Server failed to start: " + ioe);
        }
    }

    public void serviceRequest() throws IOException {
        int byteCount = 0;
        while (clientIsOpen() && (byteCount < 10000)) {
            byte b = (byte) clientInput.read();
            byteCount++;
            System.out.write(b);
            System.out.flush();
        }
    }
}
