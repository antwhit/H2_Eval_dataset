import javax.mail.*;

/**
 * copier will copy a specified number of messages from one folder
 * to another folder. it demonstrates how to use the JavaMail APIs
 * to copy messages.<p>
 *
 * Usage for copier: copier <i>store urlname</i> 
 * <i>src folder</i> <i>dest folder</i> <i>start msg #</i> <i>end msg #</i><p>
 *
 */
public class copier {

    public static void main(String argv[]) {
        boolean debug = false;
        if (argv.length != 5) {
            System.out.println("usage: copier <urlname> <src folder>" + "<dest folder> <start msg #> <end msg #>");
            return;
        }
        try {
            URLName url = new URLName(argv[0]);
            String src = argv[1];
            String dest = argv[2];
            int start = Integer.parseInt(argv[3]);
            int end = Integer.parseInt(argv[4]);
            Session session = Session.getInstance(System.getProperties(), null);
            Store store = session.getStore(url);
            store.connect();
            System.out.println("Connected...");
            Folder folder = store.getFolder(src);
            folder.open(Folder.READ_WRITE);
            System.out.println("Opened source...");
            if (folder.getMessageCount() == 0) {
                System.out.println("Source folder has no messages ..");
                folder.close(false);
                store.close();
            }
            Folder dfolder = store.getFolder(dest);
            if (!dfolder.exists()) dfolder.create(Folder.HOLDS_MESSAGES);
            Message[] msgs = folder.getMessages(start, end);
            System.out.println("Got messages...");
            folder.copyMessages(msgs, dfolder);
            System.out.println("Copied messages...");
            folder.close(false);
            store.close();
            System.out.println("Closed folder and store...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
