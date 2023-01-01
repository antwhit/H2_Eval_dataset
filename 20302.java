import java.net.Socket;
import java.net.*;
import java.io.*;

public class AppletServer extends ServerSocket implements Runnable {

    public AppletServer(int port) throws IOException {
        super(port);
    }

    public void run() {
        try {
            setSoTimeout(0);
            while (true) {
                Socket s = accept();
                Thread t = new AppletService(s);
                t.start();
            }
        } catch (Exception e) {
            MyFrame.output.append(e.getMessage());
        }
    }
}
