import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author  arun
 */
public class CPoller implements Runnable {

    ObjectInputStream fromserver;

    ObjectOutputStream toserver;

    private Socket s;

    private ServerListener target;

    /** Creates a new instance of CPoller */
    public CPoller() {
    }

    public String connect(String ip, int portno) {
        try {
            s = new Socket(ip, portno);
            Thread t = new Thread(this);
            t.start();
            return ("successfull");
        } catch (Exception e) {
            e.printStackTrace();
            return ("failed");
        }
    }

    public void addServerListener(Object obj) {
        target = (ServerListener) obj;
    }

    public ObjectOutputStream getPipeToServer() {
        return (toserver);
    }

    public void run() {
        try {
            fromserver = new ObjectInputStream(s.getInputStream());
            toserver = new ObjectOutputStream(s.getOutputStream());
            target.connected(toserver, s.getInetAddress().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Msg m = (Msg) fromserver.readObject();
                target.messageFromServer(m.getMessage());
                String Message = m.getMessage();
                StringTokenizer st = new StringTokenizer(Message);
                String msgtype, field1, filed2;
                msgtype = st.nextToken();
                if (msgtype.equalsIgnoreCase("shiftConnectionTo")) {
                    break;
                }
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }
    }
}
