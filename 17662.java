import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ReaderWriter {

    private InputStreamReader inputStreamReader = null;

    private OutputStreamWriter outputStreamWriter = null;

    private BufferedReader breader = null;

    private BufferedWriter bwriter = null;

    public Socket socket = null;

    public String server = null;

    public String nick = null;

    public String nick2 = null;

    public int port = 0;

    public boolean dolog = false;

    public void sendPrivMessage(String from, String user, String line) throws IOException, InterruptedException {
        String dest = null;
        if (user.equals(nick)) {
            dest = from;
        } else {
            dest = user;
        }
        String sline = "PRIVMSG " + dest + " " + ":" + line;
        SendLine(sline);
        Thread.sleep(200);
    }

    public void SendLine(String line) throws IOException {
        line = line + "\r\n";
        if (socket.isClosed()) {
            throw (new SocketException());
        }
        bwriter.write(line);
        if (dolog) Trivbot.log.log(">>> " + line);
        bwriter.flush();
    }

    public String ReadLine() throws IOException {
        String line;
        line = breader.readLine();
        if (dolog) Trivbot.log.log("<<< " + line);
        return line;
    }

    public ReaderWriter(String host, int iport, String inick, String inick2) {
        server = host;
        port = iport;
        nick = inick;
        nick2 = inick2;
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public void connect() throws UnknownHostException, IOException {
        socket = new Socket(server, port);
        socket.setSoTimeout(180000);
        Trivbot.log.log(Trivbot.rb.getString("ConnectedToHost"));
        inputStreamReader = new InputStreamReader(socket.getInputStream(), "UTF-8");
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        breader = new BufferedReader(inputStreamReader);
        bwriter = new BufferedWriter(outputStreamWriter);
        String line = null;
        line = "USER " + nick + " " + nick + " " + nick + " :Trivbot";
        SendLine(line);
        line = "NICK " + nick;
        SendLine(line);
    }
}
