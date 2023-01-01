import iwork.eheap2.*;
import java.util.*;

public class ListDirThread extends Thread implements EventCallback {

    private long TTL = 30000;

    SecureEventHeap _seHeap;

    String _peerName;

    SecureEvent listDirectory;

    SecureEvent[] listDirectoryReply;

    SecureFileShareGUI _sfsGUI;

    EventRegistration registration;

    boolean flag;

    public ListDirThread(SecureEventHeap seHeap, String peerName, SecureFileShareGUI gui) {
        _seHeap = seHeap;
        _peerName = peerName;
        _sfsGUI = gui;
        flag = false;
        setDaemon(true);
        try {
            listDirectory = new SecureEvent();
            listDirectory.setEventType("SECURE_FILE_SHARE_LIST_DIRECTORY_QUERY");
            listDirectory.setPostValue(SecureEvent.SOURCEPERSON, seHeap.getPerson());
            listDirectory.setPostValue(SecureEvent.TARGETPERSON, peerName);
            listDirectory.setFieldValue(SecureEvent.PRIVATEFLAG, SecureEvent.FLAG_PRIVATE);
            listDirectory.setTimeToLive(TTL);
            listDirectoryReply = new SecureEvent[2];
            listDirectoryReply[0] = new SecureEvent();
            listDirectoryReply[0].setTemplateValue(SecureEvent.EVENTTYPE, "SECURE_FILE_SHARE_LIST_DIRECTORY_REPLY");
            listDirectoryReply[0].addField("DIRECTORY_LISTING", String.class, SecureEvent.VIRTUAL, SecureEvent.FORMAL);
            listDirectoryReply[0].addField(SecureEvent.TARGETPERSON, String.class, SecureEvent.VIRTUAL, SecureEvent.FORMAL);
            listDirectoryReply[1] = new SecureEvent();
            listDirectoryReply[1].setTemplateValue(SecureEvent.EVENTTYPE, "SECURE_FILE_SHARE_LIST_DIRECTORY_REJECT");
            listDirectoryReply[1].addField(SecureEvent.TARGETPERSON, String.class, SecureEvent.VIRTUAL, SecureEvent.FORMAL);
            registration = _seHeap.registerForEvents(listDirectoryReply, this);
        } catch (EventHeapException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            _seHeap.putEvent(listDirectory);
            try {
                Thread.sleep(TTL);
            } catch (Exception e) {
            }
            registration.deregister();
        } catch (EventHeapException e) {
            e.printStackTrace();
        }
        if (!flag) System.out.println("Peer " + _peerName + " unreachable");
    }

    public boolean returnEvent(Event[] retEvents) {
        Event event = retEvents[0];
        flag = true;
        try {
            String type = (String) retEvents[0].getEventType();
            if (type.equals("SECURE_FILE_SHARE_LIST_DIRECTORY_REPLY")) {
                System.out.println(event.getPostValueString("DIRECTORY_LISTING"));
                _sfsGUI.displayPeerDir(event.getPostValueString("DIRECTORY_LISTING"));
                _sfsGUI.updateTextMessage("Listing Directory...\n");
            } else {
                System.out.println("List Directory Permission Denied");
                _sfsGUI.updateTextMessage("List Directory Permission Denied\n");
            }
        } catch (EventHeapException e) {
            e.printStackTrace();
        }
        return false;
    }
}
