import goodsjpi.*;

class L2ListNode extends Persistent {

    L2ListNode next;

    L2ListNode prev;

    int value;

    void buildList(int nNodes) {
        if (next == null) {
            next = prev = this;
            for (int i = 1; i <= nNodes; i++) {
                L2ListNode node = new L2ListNode();
                node.value = i;
                link(node);
            }
        }
    }

    void reset() {
        next = prev = null;
    }

    void increment() {
        value += 1;
    }

    void decrement() {
        value -= 1;
    }

    void update() {
        increment();
        next.decrement();
    }

    void link(L2ListNode node) {
        node.next = this;
        node.prev = prev;
        prev = prev.next = node;
    }

    int calculateSum() {
        int sum = 0;
        for (L2ListNode node = next; node != this; node = node.next) {
            sum += node.value;
        }
        return sum;
    }

    void updateNode(int index) {
        L2ListNode node = next;
        while (--index >= 0) {
            node = node.next;
        }
        node.update();
    }
}

class TestThread extends PThread {

    public void run() {
        Database db = new Database();
        db.attach();
        try {
            if (db.open("testtrans.cfg")) {
                L2ListNode list = (L2ListNode) db.getRoot();
                L2ListNode node;
                int i, j;
                if (list == null) {
                    list = new L2ListNode();
                    db.setRoot(list);
                }
                list.buildList(TestTrans.nNodes);
                for (i = 0; i < TestTrans.nIterations; i++) {
                    int sum = list.calculateSum();
                    if (sum != TestTrans.nNodes * (TestTrans.nNodes + 1) / 2) {
                        System.err.println("Wrong check sum: " + sum + " instead of " + TestTrans.nNodes * (TestTrans.nNodes + 1) / 2);
                    }
                    list.updateNode(i % (TestTrans.nNodes - 1));
                }
                db.close();
            } else {
                System.err.println("Thread failed to open database");
            }
        } finally {
            db.detach();
        }
    }
}

public class TestTrans {

    static final int nNodes = 100;

    static final int nIterations = 100;

    static final int nThreadsByDefault = 10;

    public static void main(String args[]) throws Exception {
        int nThreads = nThreadsByDefault;
        int i;
        if (args.length > 0) {
            nThreads = Integer.parseInt(args[0], 10);
        }
        Thread[] threads = new Thread[nThreads];
        for (i = 0; i < nThreads; i++) {
            threads[i] = new TestThread();
            threads[i].start();
        }
        for (i = 0; i < nThreads; i++) {
            threads[i].join();
        }
        Database db = new Database();
        if (db.open("testtrans.cfg")) {
            L2ListNode list = (L2ListNode) db.getRoot();
            if (list.next.value != 1 + (nIterations + nNodes - 2) / (nNodes - 1) * nThreads || list.prev.value != nNodes - nIterations / (nNodes - 1) * nThreads) {
                System.err.println("Something is definitly wrong: list.next.value=" + list.next.value + " instead of " + (1 + (nIterations + nNodes - 2) / (nNodes - 1) * nThreads) + ", list.next.value=" + list.prev.value + " instead of " + (nNodes - nIterations / (nNodes - 1) * nThreads));
            } else {
                System.out.println("Test successfully passed");
            }
            list.reset();
            db.close();
        } else {
            System.err.println("Failed to open detabase");
        }
    }
}
