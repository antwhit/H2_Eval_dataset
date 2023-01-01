import java.net.*;
import java.io.*;

public class ForwardServerThread extends Thread {

    private Socket socket = null;

    public ForwardServerThread(Socket socket) {
        super("ForwardServerThread");
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine = "", outputLine = "";
            ForwardServerNet net = new ForwardServerNet();
            net.connect(ForwardServer.host, ForwardServer.rport, out);
            while ((inputLine = in.readLine()) != null) {
                net.send(inputLine + "\r\n");
            }
            net.disconnect();
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
