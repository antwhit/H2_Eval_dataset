import java.io.*;
import java.net.*;

/**
 * This class handles all the network stuff.
 * 
 * @author namhas
 * @version 1.0
 */
public class Net extends Thread implements Runnable {

    /** Are we connected or not? */
    public boolean connected;

    private Socket socket;

    private Thread thread;

    private BufferedWriter os;

    private BufferedReader is;

    /** Start a thread. */
    public void start() {
        if (thread == null) {
            clear();
            thread = new Thread(this);
            thread.start();
        }
    }

    /** The main loop. */
    public void run() {
        String buf;
        while (Thread.currentThread() == thread) {
            try {
                buf = is.readLine();
                if (buf == null) return;
                Protocol.parse(buf);
            } catch (IOException e) {
                Jicra.text.append("Error: " + e + "\n");
                return;
            }
        }
    }

    /** Connect to the server. */
    public synchronized void connect(String host) {
        if (connected) return;
        try {
            socket = new Socket(host, Jicra.port);
        } catch (UnknownHostException e) {
            Jicra.text.append("Error: " + e + "\n");
            return;
        } catch (IOException e) {
            Jicra.text.append("Error: " + e + "\n");
            return;
        }
        try {
            os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            Jicra.text.append("Error: " + e + "\n");
            return;
        }
        if (os != null && is != null) {
            connected = true;
            notify();
            if (thread == null) start();
        }
    }

    /** Wait until we have connected. */
    private void waitConnect() {
        while (!connected) {
            try {
                wait();
            } catch (InterruptedException e) {
                Jicra.text.append("Error: " + e + "\n");
                return;
            }
        }
    }

    /** Send a message to the server. */
    public void send(String data) {
        try {
            os.write(data + "\r\n");
            os.flush();
        } catch (IOException e) {
            Jicra.text.append("Error: " + e + "\n");
            return;
        } catch (NullPointerException e) {
            Jicra.text.append("Error: " + e + "\n");
        }
    }

    /** Disconnect. */
    public synchronized void disconnect() {
        send("QUIT :Leaving");
        connected = false;
        os = null;
        is = null;
        thread.stop();
        thread = null;
        try {
            socket.close();
        } catch (IOException e) {
            Jicra.text.append("Error: " + e + "\n");
        }
        Jicra.list.removeAll();
    }

    /** Clear variables. */
    public void clear() {
    }
}
