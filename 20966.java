import java.net.*;
import java.io.*;
import java.util.*;

/**
 * The Server Class is the Server Side Component of the Server-Client set up for
 * the JadeTalk Chat System.
 * It provides a ServerSocket on a provided port, to which clients can connect and
 * be assigned a socket and assosiated ClientHandler
 *
 * @author BluJaded Team
 * @version (see Sourceforge.net)
 */
public class Server {

    private static PrintWriter log;

    private Calendar dateTime = Calendar.getInstance();

    /**
     * a switch variable used to terminate the server cleanly
     */
    public static boolean run = true;

    /**
     * Creates a Server object and begins and infinate loop that will accept any incoming connections
     * and assign them a socket and assosiated ClientHandler object.
     * @param port the port number of an available port for use by JadeTalk.
     * @throws java.io.IOException is thrown if the serverSocket fails to initialize
     */
    public Server(int port) throws IOException {
        log = new PrintWriter(new FileWriter("log.txt", true), true);
        writeToLog("JadeTalk Server Created - port: " + port);
        ServerSocket server = new ServerSocket(port);
        server.setSoTimeout(500);
        while (run == true) {
            Socket client = null;
            try {
                client = server.accept();
            } catch (SocketTimeoutException e) {
            }
            if (client != null) {
                System.out.println("Connection to Server accepted from: " + client.getInetAddress());
                ClientHandler handler = new ClientHandler(client, this);
                writeToLog("New Client connection accepted from: " + client.getInetAddress());
                handler.start();
            }
            if (LaunchGui.Sframe != null) {
                if (LaunchGui.Sframe.isVisible() == true) {
                    LaunchGui.handleCount.setText(ClientHandler.handlers.size() + " of 20 Clients Currently Connected.");
                    LaunchGui.Sframe.validate();
                }
            }
        }
        if (run == false) {
            synchronized (ClientHandler.handlers) {
                Iterator myHandlers = ClientHandler.handlers.iterator();
                while (myHandlers.hasNext()) {
                    ClientHandler handler = (ClientHandler) myHandlers.next();
                    handler.run = false;
                }
            }
            writeToLog("Server closed");
            log.close();
            System.exit(0);
        }
    }

    /**
     * Writes the String parameter to a log file
     * @param toLog     String to be written to the Server log
     */
    public void writeToLog(String toLog) {
        dateTime = Calendar.getInstance();
        log.println(dateTime.getTime().toString() + " - " + toLog);
    }

    /**
     * main method provides an execution start and allows commandline execution
     * @param args The standard commandline arguments parameter.
     *             The expected argument is a port number of an available port for use by JadeTalk.
     * @throws java.io.IOException thrown if the Server fails to initialize
     */
    public static void main(String args[]) throws IOException {
        if (args.length != 1) {
            throw new RuntimeException("Please provide the required input. Syntax: <Port>");
        } else {
            new Server(Integer.parseInt(args[0]));
        }
    }
}
