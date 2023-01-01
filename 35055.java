import iwork.*;
import iwork.eheap.*;
import java.io.*;
import java.util.*;
import java.lang.*;

class PutEventThread extends Thread {

    EventHeap eh;

    int myID;

    long maxSleep;

    boolean separateSequencing;

    public PutEventThread(EventHeap eh, int num, long maxSleep, boolean sepSeq) {
        this.eh = eh;
        myID = num;
        this.maxSleep = maxSleep;
        separateSequencing = sepSeq;
    }

    private void init() {
        try {
            this.setName("PutEventThread" + myID);
            if (separateSequencing) this.eh = new EventHeap(eh, 0); else this.eh = new EventHeap(eh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        init();
        int i = 1 + 1000 * myID;
        try {
            Event putEvent = new Event();
            putEvent.setFieldValue(Event.TIMETOLIVE, new Integer(120 * 1000));
            putEvent.setFieldValue(Event.EVENTTYPE, new Integer(91919));
            putEvent.addField("Counter", new Integer(i));
            while (true) {
                try {
                    eh.putEvent(putEvent);
                    EventHeap.debugPrintln(0, "putEvent succeeded (" + i + ")");
                    i++;
                    putEvent.setFieldValue("Counter", new Integer(i));
                    sleep((long) (Math.random() * maxSleep));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
