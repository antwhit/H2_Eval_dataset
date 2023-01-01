import java.io.*;
import java.net.*;
import java.util.*;

/**This class contains the client thread which maintains a queue of solutions obtained from other clients.
 */
public class GnubertClient extends Thread {

    int sizeImmigrantQueue = 10;

    protected LinkedList immigrantQueue = new LinkedList();

    protected LinkedList outgoingConnectionQueue = new LinkedList();

    protected HostCatcher hostCatcher;

    protected GnubertProperties propertyList;

    protected GnubertStatus currentStatus;

    /**Read the host file and store the hosts in the hostcatcher.
    */
    public GnubertClient(HostCatcher _hostCatcher, GnubertProperties _propertyList, GnubertStatus _currentStatus) {
        hostCatcher = _hostCatcher;
        propertyList = _propertyList;
        currentStatus = _currentStatus;
        ClientConnection clientConnection = null;
        for (byte counter = 0; counter < propertyList.getNumberClientConnections(); counter++) {
            clientConnection = new ClientConnection(hostCatcher, propertyList, currentStatus, counter);
            clientConnection.start();
            outgoingConnectionQueue.add(clientConnection);
        }
    }

    /**The run method of the GnubertClient class keeps the immigrantQueue full.
    *
    */
    public void run() {
        final long maxSleepTime = 30000;
        long sleepTime = 1000;
        String immigrant = null;
        while (true) {
            if (immigrantQueue.size() == sizeImmigrantQueue) {
                sizeImmigrantQueue--;
                if (sizeImmigrantQueue < 1) sizeImmigrantQueue = 1;
            } else if (immigrantQueue.size() < sizeImmigrantQueue / 2) {
                while (immigrant == null) immigrant = immigrate();
                synchronized (immigrantQueue) {
                    immigrantQueue.addLast(immigrate());
                }
                immigrant = null;
            }
            if (immigrantQueue.size() < sizeImmigrantQueue / 4) {
                sleepTime--;
                if (sleepTime < 0) {
                    sleepTime = 200;
                    sizeImmigrantQueue += 10;
                }
            } else {
                sleepTime += 100;
                if (sleepTime > maxSleepTime) sleepTime = maxSleepTime;
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.err.println("Client Thread Interrupted while sleeping,");
                }
            }
        }
    }

    /**Method for returning an immigrant from the queue
    *If the immigrant Queue is empty it returns null
    */
    String getImmigrant() {
        String immigrant = null;
        boolean hadImmigrant = false;
        synchronized (immigrantQueue) {
            if (immigrantQueue.size() > 0) {
                immigrant = (String) immigrantQueue.removeFirst();
                hadImmigrant = true;
            }
        }
        if (!hadImmigrant) {
            sizeImmigrantQueue++;
        }
        return immigrant;
    }

    String immigrate() {
        String immigrant = null;
        int counter = 0;
        ClientConnection clientConnection = null;
        while ((counter < propertyList.getNumberClientConnections()) && (immigrant == null)) {
            clientConnection = (ClientConnection) outgoingConnectionQueue.removeFirst();
            immigrant = clientConnection.getImmigrant();
            outgoingConnectionQueue.addFirst(clientConnection);
            counter++;
        }
        return immigrant;
    }
}
