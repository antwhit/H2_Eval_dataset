import java.io.*;
import java.net.*;

/**
 * Test that we can set a timeout on receiving data from a socket
 * input stream.
 * 
 * @author David Hovemeyer
 */
public class TestReceiveTimeout implements Runnable {

    static void failure(Exception e) {
        if (e != null) e.printStackTrace();
        System.out.println("TestReceiveTimeout FAILURE");
        System.exit(1);
    }

    static void failure() {
        failure(null);
    }

    static void success() {
        System.out.println("TestReceiveTimeout SUCCESS");
        System.exit(0);
    }

    Object lock = new Object();

    boolean havePort = false;

    int port;

    public void run() {
        try {
            Thread server = new Thread() {

                public void run() {
                    try {
                        ServerSocket ss = new ServerSocket(0);
                        synchronized (lock) {
                            port = ss.getLocalPort();
                            havePort = true;
                            lock.notifyAll();
                        }
                        Socket clientConn = ss.accept();
                        System.out.println("Server: Got a connection");
                    } catch (Exception e) {
                        failure(e);
                    }
                }
            };
            Thread client = new Thread() {

                public void run() {
                    try {
                        synchronized (lock) {
                            while (!havePort) lock.wait();
                        }
                        Socket conn = new Socket("127.0.0.1", port);
                        System.out.println("Client: Connected to server");
                        conn.setSoTimeout(50);
                        InputStream in = conn.getInputStream();
                        byte[] buf = new byte[256];
                        try {
                            int n = in.read(buf);
                            failure();
                        } catch (SocketTimeoutException e) {
                            success();
                        }
                    } catch (Exception e) {
                        failure(e);
                    }
                }
            };
            Thread watchDog = new Thread() {

                public void run() {
                    try {
                        Thread.sleep(5000);
                        failure();
                    } catch (Exception e) {
                        failure(e);
                    }
                }
            };
            server.start();
            client.start();
            watchDog.start();
        } catch (Exception e) {
            failure(e);
        }
    }

    public static void main(String[] argv) {
        new TestReceiveTimeout().run();
    }
}
