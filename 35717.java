import java.net.*;
import java.util.*;

public class Players {

    private static int sequenceNumberCounter = 0;

    private static List<GamePacket> queue = Collections.synchronizedList(new LinkedList<GamePacket>());

    private static List<Socket> clientSockets = new LinkedList<Socket>();

    public static List<GamePacket> getQueue() {
        return queue;
    }

    public static void setQueue(List<GamePacket> queue) {
        Players.queue = queue;
    }

    public static List<Socket> getClientSockets() {
        return clientSockets;
    }

    public static void setClientSockets(List<Socket> clientSockets) {
        Players.clientSockets = clientSockets;
    }

    public static int getSequenceNumberCounter() {
        return sequenceNumberCounter;
    }

    public static void setSequenceNumberCounter(int sequenceNumberCounter) {
        Players.sequenceNumberCounter = sequenceNumberCounter;
    }
}
