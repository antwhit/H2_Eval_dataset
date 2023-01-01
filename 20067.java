import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class mover {

    static String protocol = "imap";

    static String host = null;

    static String user = null;

    static String password = null;

    static String src = null;

    static String dest = null;

    static boolean expunge = false;

    static String url = null;

    public static void main(String argv[]) {
        int start = 1;
        int end = -1;
        int optind;
        for (optind = 0; optind < argv.length; optind++) {
            if (argv[optind].equals("-T")) {
                protocol = argv[++optind];
            } else if (argv[optind].equals("-H")) {
                host = argv[++optind];
            } else if (argv[optind].equals("-U")) {
                user = argv[++optind];
            } else if (argv[optind].equals("-P")) {
                password = argv[++optind];
            } else if (argv[optind].equals("-L")) {
                url = argv[++optind];
            } else if (argv[optind].equals("-s")) {
                src = argv[++optind];
            } else if (argv[optind].equals("-d")) {
                dest = argv[++optind];
            } else if (argv[optind].equals("-x")) {
                expunge = true;
            } else if (argv[optind].equals("--")) {
                optind++;
                break;
            } else if (argv[optind].startsWith("-")) {
                System.out.println("Usage: mover [-T protocol] [-H host] [-U user] [-P password] [-L url] [-v]");
                System.out.println("\t[-s source mbox] [-d destination mbox] [-x] [msgnum1] [msgnum2]");
                System.out.println("\t The -x option => EXPUNGE deleted messages");
                System.out.println("\t msgnum1 => start of message-range; msgnum2 => end of message-range");
                System.exit(1);
            } else {
                break;
            }
        }
        if (optind < argv.length) start = Integer.parseInt(argv[optind++]);
        if (optind < argv.length) end = Integer.parseInt(argv[optind++]);
        try {
            Properties props = System.getProperties();
            Session session = Session.getInstance(props, null);
            Store store = null;
            if (url != null) {
                URLName urln = new URLName(url);
                store = session.getStore(urln);
                store.connect();
            } else {
                if (protocol != null) store = session.getStore(protocol); else store = session.getStore();
                if (host != null || user != null || password != null) store.connect(host, user, password); else store.connect();
            }
            Folder folder = store.getFolder(src);
            if (folder == null || !folder.exists()) {
                System.out.println("Invalid folder: " + src);
                System.exit(1);
            }
            folder.open(Folder.READ_WRITE);
            int count = folder.getMessageCount();
            if (count == 0) {
                System.out.println(folder.getName() + " is empty");
                folder.close(false);
                store.close();
                return;
            }
            Folder dfolder = store.getFolder(dest);
            if (!dfolder.exists()) dfolder.create(Folder.HOLDS_MESSAGES);
            if (end == -1) end = count;
            Message[] msgs = folder.getMessages(start, end);
            System.out.println("Moving " + msgs.length + " messages");
            if (msgs.length != 0) {
                folder.copyMessages(msgs, dfolder);
                folder.setFlags(msgs, new Flags(Flags.Flag.DELETED), true);
                for (int i = 0; i < msgs.length; i++) {
                    if (!msgs[i].isSet(Flags.Flag.DELETED)) System.out.println("Message # " + msgs[i] + " not deleted");
                }
            }
            folder.close(expunge);
            store.close();
        } catch (MessagingException mex) {
            Exception ex = mex;
            do {
                System.out.println(ex.getMessage());
                if (ex instanceof MessagingException) ex = ((MessagingException) ex).getNextException(); else ex = null;
            } while (ex != null);
        }
    }
}
