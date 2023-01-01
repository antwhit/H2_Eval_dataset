/**
* Main class for the server of SourMUD.
* @author Jim
* @version 2
*/
public class Server implements Runnable {

    /**
					* The main method.
					*/
    public static void main(String args[]) {
        System.out.println("SourMUD server v2");
        System.out.println("Copyright 2007, SourMUD team, jwarez.net");
        System.out.println();
        System.out.println("Using version " + version + " for updates.");
        Server s = new Server();
        Thread server = new Thread(s);
        s.thread = server;
        server.start();
    }

    /**
					* Calls initial running and then starts a repeatitive loop.
					*/
    public void run() {
        listener = new ClientListener();
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
        handler = new ClientHandler();
        while (!stopped) {
            try {
                Thread.sleep(500);
                handler.process();
            } catch (Exception e) {
            }
        }
    }

    /**
					* The thread that this server is running.
					*/
    private Thread thread;

    /**
					* Client listener that accepts connecting clients, and update requests.
					*/
    public static ClientListener listener;

    /**
					* Handles all of the clients.
					*/
    public static ClientHandler handler;

    /**
					* Whether the server should stop altogether... Triggered by an update.
					*/
    public static boolean stopped = false;

    /**
					* Data file version.
					*/
    public static int version = 7;
}
