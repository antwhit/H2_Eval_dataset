import java.io.*;
import java.net.*;
import java.applet.*;
import netscape.javascript.*;

public class tattleClientThread extends Thread {

    private Socket socket = null;

    private BufferedReader in = null;

    private boolean gone = false;

    private JSObject jsroot = null;

    private String host = null;

    private int port = 9999;

    private String targetFunction = "tattle";

    tattleClientThread(Applet app) {
        super("tattleClient");
        jsroot = JSObject.getWindow(app);
        host = app.getCodeBase().getHost();
        String portStr = app.getParameter("dstPort");
        if (portStr != null) {
            try {
                port = Integer.parseInt(portStr);
            } catch (NumberFormatException e) {
            }
        }
        String funcStr = app.getParameter("tgtFunction");
        if (funcStr != null) {
            targetFunction = funcStr;
        }
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("IOE!! connect!");
            gone = true;
        }
    }

    public void run() {
        if (socket == null) return;
        while (!gone) {
            try {
                String msg = in.readLine();
                if (msg == null) {
                    gone = true;
                } else {
                    String args[] = { msg };
                    jsroot.call(targetFunction, args);
                }
            } catch (IOException e) {
                System.out.println("IOE!! read");
                gone = true;
            }
        }
    }
}
