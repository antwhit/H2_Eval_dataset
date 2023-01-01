class Node2I2A {

    int data1;

    int data2;

    Node2I2A car;

    Node2I2A cdr;

    static double measuredObjectSize = 0.0;

    static int objectSize = 0;

    static Object fakeLock = new Object();

    public static void computeObjectSize() {
        int estimateSize = 1000000;
        while (true) {
            System.gc();
            long start = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            Node2I2A head = new Node2I2A();
            Node2I2A cur = head;
            for (int i = 0; i < estimateSize; i++) {
                cur.cdr = new Node2I2A();
                cur = cur.cdr;
            }
            synchronized (fakeLock) {
            }
            long end = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long used = end - start;
            if (used > 0) {
                measuredObjectSize = used / ((double) estimateSize);
                objectSize = (int) (measuredObjectSize + 0.5);
                objectSize = (objectSize + 2) / 4 * 4;
                if (objectSize > 16) break;
            }
            estimateSize = (int) (0.75 * estimateSize);
            System.out.println("GC occured since used memory decreased after allocation or implausible object size obtained.  Retrying with " + estimateSize + " objects.");
        }
    }

    public static Node2I2A createTree(int nodes) {
        if (nodes == 0) return null;
        int children = nodes - 1;
        int left = children / 2;
        int right = children - left;
        Node2I2A self = new Node2I2A();
        self.car = createTree(left);
        self.cdr = createTree(right);
        return self;
    }
}
