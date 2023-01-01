import java.io.*;
import java.net.*;

class HTTPWorker extends Thread {

    Socket sock;

    HTTPTalk talker;

    HTTPWorker(Socket s) throws IOException {
        sock = s;
        PrintStream out = new PrintStream(sock.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        talker = new HTTPTalk(in, out);
    }

    public void run() {
        try {
            dlhttpd.logger.info("Thread " + currentThread());
            talker.talk(this);
            dlhttpd.logger.info("DONE");
            sock.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
