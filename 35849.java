import mucode.*;
import mucode.util.*;
import mucode.abstractions.*;
import java.net.*;
import java.io.*;

public class Consumer implements Runnable, Serializable {

    private SharedInteger shared;

    private String starting = null;

    private long toWait = 3000;

    public Consumer(String starting, Long toWait) {
        this.starting = starting;
        this.toWait = toWait.longValue();
    }

    public void run() {
        ResourceServer s = (ResourceServer) ResourceServer.getServer();
        if (s != null) shared = (SharedInteger) s.resources.get("SHARED");
        System.out.println("Consumer started (spawned from " + starting.toString() + ").");
        while (shared.hasMoreData()) {
            try {
                Thread.sleep(toWait);
            } catch (InterruptedException e) {
                System.err.println("Consumer interrupted");
            }
            System.out.println("Consumer retrieved " + shared.getValue());
        }
        shared.reset();
        System.out.println("Consumer terminated");
    }

    public static void main(String[] args) {
        MuServer s = new MuServer();
        new Launcher(s).parseArgs(args, 1);
        try {
            new Relocator(s).spawnThread(args[0], "Consumer", new String[] { "Consumer" }, new Serializable[] { InetAddress.getLocalHost().toString() + s.getPort(), new Long((long) (Math.random() * 3000)) }, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
