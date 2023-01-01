import goodsjpi.*;
import goodslib.*;
import java.util.Random;

class Node extends SetMember {

    ArrayOfChar name;

    public int compare(Ordered rec) {
        return name.compare(((Node) rec).name);
    }

    public int compareKey(Object key) {
        return name.compare((String) key);
    }

    Node(String key) {
        name = new ArrayOfChar(key);
    }

    static final Metaobject defaultMetaobject = new OptimisticMetaobject();
}

class Wood {

    Btree[] tree;

    Wood(Database db, int nTrees, int pageSize) {
        tree = new Btree[nTrees];
        for (int i = 0; i < nTrees; i++) {
            tree[i] = new Btree(pageSize);
            tree[i].attachToStorage(db, i % db.getNumberOfStorages());
        }
    }
}

public class TestBtree extends Thread {

    static final int nTrees = 2;

    static final int nNodes = 10000;

    static final int nReads = 4;

    static final int pageSize = 8;

    Database db;

    int id;

    TestBtree(Database db, int id) {
        this.db = db;
        this.id = id;
        start();
    }

    public void run() {
        int i, j;
        String key[] = new String[nNodes];
        Random rnd = new Random(1998 + id);
        Btree tree = ((Wood) db.getRoot()).tree[id % nTrees];
        for (i = 0; i < nNodes; i++) {
            key[i] = "$" + rnd.nextLong() + "." + id;
            tree.insert(new Node(key[i]));
        }
        System.out.println("Records inserted: " + i);
        for (i = 0; i < nReads; i++) {
            System.out.println("Read iteration: " + i);
            for (j = 0; j < nNodes; j++) {
                Assert.that(tree.find(key[j]) != null);
            }
        }
        for (i = 0; i < nNodes; i++) {
            tree.remove(tree.find(key[i]));
        }
        System.out.println("Process " + id + " terminated, " + tree.getNumberOfMembers() + " nodes in tree");
    }

    public static void main(String[] args) throws Exception {
        int i, id = 0;
        int nThreads = 1;
        if (args.length > 0) {
            id = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            nThreads = Integer.parseInt(args[1]);
        }
        Database db = new Database();
        if (db.open("btree.cfg")) {
            if (db.getRoot() == null) {
                db.setRoot(new Wood(db, nTrees, pageSize));
            }
            Thread thread[] = new Thread[nThreads];
            for (i = 0; i < nThreads; i++) {
                thread[i] = new TestBtree(db, id++);
            }
            while (--i >= 0) {
                thread[i].join();
            }
            db.close();
        } else {
            System.err.println("Failed to open database");
        }
    }
}
