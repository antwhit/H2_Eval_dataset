import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import chord.ChordIdentifier;
import chord.ChordLogger;
import chord.ChordUtil;
import chord.ChordWaitingTable;
import chord.ChordWaitingTicket;
import chord.ChordPacket;
import chord.ChordPredecessorRequestPacket;

public class Chord {

    static class ProducingThread extends Thread {

        ChordWaitingTable theTable;

        public ProducingThread(ChordWaitingTable table) {
            theTable = table;
        }

        public void run() {
            int i;
            while (true) {
                i = ChordUtil.getRandom().nextInt() % 10;
                if (theTable.checkAndAnswer(i, null)) {
                    ChordLogger.getInstance().log("Freed " + i);
                }
            }
        }
    }

    public static void main(String args[]) throws java.net.UnknownHostException {
        dummyController msgController = new dummyController(args[0], new Integer(args[1]).intValue());
        if (args.length > 2) {
            msgController.go(args[2], new Integer(args[3]).intValue());
        } else {
            msgController.go();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringTokenizer breaker;
        String target;
        String msg;
        while (true) {
            try {
                input = br.readLine();
            } catch (IOException e) {
                ChordLogger.getInstance().error(e.getMessage());
                continue;
            }
            breaker = new StringTokenizer(input);
            try {
                target = breaker.nextToken();
                msg = breaker.nextToken("");
            } catch (NoSuchElementException e) {
                ChordLogger.getInstance().log("<name> <msg>");
                continue;
            }
            msgController.sendMessage(target, msg);
        }
    }
}

class foo {

    byte[] data = new byte[2];

    public foo() {
        data[0] = 1;
        data[1] = 2;
    }

    public byte[] getData() {
        return data;
    }

    public void bar() {
        System.out.println(data[0]);
    }
}
