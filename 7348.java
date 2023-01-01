import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.event.*;
import javax.activation.*;
import com.sun.mail.imap.*;

public class monitor {

    public static void main(String argv[]) {
        if (argv.length != 5) {
            System.out.println("Usage: monitor <host> <user> <password> <mbox> <freq>");
            System.exit(1);
        }
        System.out.println("\nTesting monitor\n");
        try {
            Properties props = System.getProperties();
            Session session = Session.getInstance(props, null);
            Store store = session.getStore("imap");
            store.connect(argv[0], argv[1], argv[2]);
            Folder folder = store.getFolder(argv[3]);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder");
                System.exit(1);
            }
            folder.open(Folder.READ_WRITE);
            folder.addMessageCountListener(new MessageCountAdapter() {

                public void messagesAdded(MessageCountEvent ev) {
                    Message[] msgs = ev.getMessages();
                    System.out.println("Got " + msgs.length + " new messages");
                    for (int i = 0; i < msgs.length; i++) {
                        try {
                            System.out.println("-----");
                            System.out.println("Message " + msgs[i].getMessageNumber() + ":");
                            msgs[i].writeTo(System.out);
                        } catch (IOException ioex) {
                            ioex.printStackTrace();
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                        }
                    }
                }
            });
            int freq = Integer.parseInt(argv[4]);
            boolean supportsIdle = false;
            try {
                if (folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    f.idle();
                    supportsIdle = true;
                }
            } catch (FolderClosedException fex) {
                throw fex;
            } catch (MessagingException mex) {
                supportsIdle = false;
            }
            for (; ; ) {
                if (supportsIdle && folder instanceof IMAPFolder) {
                    IMAPFolder f = (IMAPFolder) folder;
                    f.idle();
                    System.out.println("IDLE done");
                } else {
                    Thread.sleep(freq);
                    folder.getMessageCount();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
