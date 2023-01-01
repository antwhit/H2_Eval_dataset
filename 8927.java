import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

public class Buddy {

    public static final int STATUS_OFFLINE = 0;

    public static final int STATUS_ONLINE = 1;

    public static final int STATUS_AWAY = 2;

    public static final int STATUS_BUSY = 3;

    String jid;

    String name;

    int status;

    Vector messages;

    List messageList;

    public Buddy(String id, String buddyName, int buddyStatus) {
        this.jid = id;
        if (buddyName != null) {
            this.name = buddyName;
        } else {
            this.name = id;
        }
        this.status = buddyStatus;
    }

    public List getMessageList(Command backCommand, CommandListener l) {
        if (messageList == null) {
            messages = new Vector();
            messageList = new List("Chat with " + this.name, List.IMPLICIT);
            messageList.addCommand(backCommand);
            messageList.setCommandListener(l);
            messageList.append("New message", null);
        }
        return messageList;
    }

    public void sentMessage(String msg, Image img) {
        messages.addElement(msg);
        messageList.append(msg, img);
    }

    public void receivedMessage(String msg, Image img) {
        messages.addElement(msg);
        messageList.append(msg, img);
    }
}
