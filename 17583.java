import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class ClientSender extends Thread {

    private static ClientSender clientSender;

    public static Integer numSendSlotsOpen = 5;

    private FileInputStream fileToRead;

    private int lastSlotSent;

    private Client clientObject;

    public static ConcurrentLinkedQueue<Integer> timedOutQueue;

    public static ClientSender getClientSender(FileInputStream fileIn) {
        if (clientSender == null) clientSender = new ClientSender(fileIn);
        return clientSender;
    }

    public static ClientSender getClientSender() {
        return clientSender;
    }

    private ClientSender(FileInputStream fileIn) {
        fileToRead = fileIn;
        lastSlotSent = -1;
        clientObject = Client.getClient();
        timedOutQueue = new ConcurrentLinkedQueue<Integer>();
    }

    public void run() {
        try {
            ArrayList<byte[]> fileByteArray = new ArrayList<byte[]>();
            int slotToSend;
            while (true) {
                byte[] b = new byte[6];
                int numBytesRead = fileToRead.read(b);
                if (numBytesRead <= 0) {
                    break;
                } else if (numBytesRead < b.length) {
                    b[numBytesRead] = 0;
                }
                fileByteArray.add(b);
            }
            while (!this.isInterrupted()) {
                while (!timedOutQueue.isEmpty()) {
                    slotToSend = timedOutQueue.poll();
                    System.out.println("Resending segment #" + slotToSend);
                    clientObject.send_segment(fileByteArray.get(slotToSend), slotToSend);
                    ClientTimer.timeoutList.add(new SegmentTime(slotToSend));
                }
                while (numSendSlotsOpen > 0 && lastSlotSent < fileByteArray.size() - 1) {
                    ++lastSlotSent;
                    clientObject.send_segment(fileByteArray.get(lastSlotSent), lastSlotSent);
                    --numSendSlotsOpen;
                    synchronized (ClientTimer.timeoutList) {
                        ClientTimer.timeoutList.add(new SegmentTime(lastSlotSent));
                    }
                }
                synchronized (timedOutQueue) {
                    timedOutQueue.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTimedOut(int segmentNum) {
        timedOutQueue.add(segmentNum);
    }
}
