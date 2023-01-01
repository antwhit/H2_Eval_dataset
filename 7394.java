import java.nio.channels.*;
import java.util.*;
import simple.util.lease.*;
import java.io.*;

public class PipeCleaner implements Cleaner {

    private Hashtable<String, Pipe> table = new Hashtable<String, Pipe>();

    /**
    * Register a pipe with a given name. When the lease expires.
    */
    public void connect(Pipe pipe, String name) {
        table.put(name, pipe);
    }

    public void clean(String name) {
        try {
            Pipe pipe = table.get(name);
            SocketChannel channel = pipe.getSocketChannel();
            pipe.close();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
