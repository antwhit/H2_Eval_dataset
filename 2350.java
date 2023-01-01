import java.io.*;
import java.util.*;
import org.imv.util.*;
import org.imv.structure.*;
import org.imv.store.*;

/**
 * Creates two threads. Each slowly makes changes to the same 
 * datanode and die.
 * Then we see attributes of the node in the end.
 */
public class test9 {

    public static void main(String args[]) {
        JDBMDataStore store = new JDBMDataStore("/var/jdbm");
        System.out.println("M> Creating data node...");
        DataNode dn;
        DataAttribute da;
        dn = store.createDataNode("rushi");
        ImvId id = dn.getId();
        dn.commit();
        System.out.println("M> Created data node " + id);
        Thread at = (Thread) new alphaThread(store, id);
        Thread bt = (Thread) new betaThread(store, id);
        try {
            at.join();
            bt.join();
        } catch (Exception e) {
            System.err.println("M> " + e);
            System.exit(0);
        }
        System.out.println("M> Threads have done their work!");
        dn = null;
        System.out.println("M> Getting data node " + id);
        dn = (DataNode) store.getEntity("rushi", id);
        if (dn == null) {
            System.out.println("Not found");
            System.exit(0);
        }
        System.out.println("M> dn.type: " + dn.getType());
        System.out.println("M> dn.id: " + dn.getId());
        System.out.println("M> dn.value: " + dn.getValue());
        System.out.println("M> dn.timeCreated: " + dn.getTimeCreation());
        System.out.println("M> dn.timeAccessed: " + dn.getTimeAccessed());
        System.out.println("M> dn.hitCount: " + dn.getHitCount());
        System.out.println("----------");
        dn.free();
        store.destroy();
    }
}

class alphaThread extends Thread {

    DataStore store;

    ImvId id;

    alphaThread(DataStore s, ImvId i) {
        super("alpha");
        store = s;
        id = i;
        System.out.println("A> Started");
        start();
    }

    public void run() {
        try {
            DataNode dn = (DataNode) store.getEntity("rushi", id);
            if (dn == null) System.out.println("A> did not find " + id + "in store.");
            dn.setValue("Hotel Kohinoor");
            System.out.println("A> Set value");
            Thread.sleep(2000);
            dn.setTimeAccessed(new Date());
            System.out.println("A> Set Time Accessed");
            Thread.sleep(2000);
            DataAttribute da;
            for (int i = 0; i < 10; i++) {
                da = new DataAttribute("alph" + i);
                da.setValue("alpha-attr-" + i);
                da.setNodeId(dn.getId());
                dn.setAttribute(da);
            }
            dn.commit();
        } catch (Exception e) {
            System.err.println("A> " + e);
            System.exit(0);
        }
    }
}

class betaThread extends Thread {

    DataStore store;

    ImvId id;

    betaThread(DataStore s, ImvId i) {
        super("beta");
        store = s;
        id = i;
        System.out.println("B> Started");
        start();
    }

    public void run() {
        try {
            if (store.deleteEntity("rushi", id)) {
                System.out.println("B> Deleted " + id);
            } else {
                System.out.println("B> Cannot delete");
            }
        } catch (Exception e) {
            System.err.println("B> " + e);
            System.exit(0);
        }
    }
}
