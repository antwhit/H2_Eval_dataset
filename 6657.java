import java.util.Enumeration;

/**
 * This class implements nodes (both E- and H-nodes) of the EM graph. Sets
 * up random neighbors and propagates field values among neighbors.
 */
@GroupingPolicies(self = GroupingPolicy.NEW_GROUP)
final class Node {

    /**
     * The value of the node. 节点的值
     **/
    double value;

    /**
     * Array of nodes to which we send our value. “去往”节点数组
     **/
    Node[] toNodes;

    /**
     * Array of nodes from which we receive values. “来自”节点数组
     **/
    Node[] fromNodes;

    /**
     * Coefficients on the fromNodes edges 对应“来自”节点数组的系数数组
     **/
    double[] coeffs;

    /**
     * The number of fromNodes edges 节点的入度
     **/
    int fromCount;

    /**
     * Used to create the fromEdges - keeps track of the number of edges that have
     * been added
     **/
    int fromLength;

    /**
     * A random number generator.
     **/
    private static Random rand;

    /**
     * Initialize the random number generator
     **/
    public static void initSeed(long seed) {
        rand = new Random(seed);
    }

    /**
     * Constructor for a node with given `degree'.   The value of the
     * node is initialized to a random value.
     * param degree 节点的出度
     **/
    Node(int degree) {
        value = rand.nextDouble();
        toNodes = new Node[degree];
        fromNodes = null;
        coeffs = null;
        fromCount = 0;
        fromLength = 0;
    }

    void __rvp__ctor(int degree) {
    }

    void makeUniqueNeighbors(Node[] nodeTable) {
        for (int filled = 0; filled < toNodes.length; filled++) {
            int k;
            Node otherNode;
            do {
                int index = rand.nextInt();
                if (index < 0) index = -index;
                index = index % nodeTable.length;
                otherNode = nodeTable[index];
                for (k = 0; k < filled; k++) {
                    if (otherNode == toNodes[k]) break;
                }
            } while (k < filled);
            toNodes[filled] = otherNode;
            otherNode.fromCount++;
        }
    }

    void __rvp__makeUniqueNeighbors(Node[] nodeTable) {
    }

    void makeFromNodes() {
        fromNodes = new Node[fromCount];
        coeffs = new double[fromCount];
    }

    void __rvp__makeFromNodes() {
    }

    void updateFromNodes() {
        for (int i = 0; i < toNodes.length; i++) {
            Node otherNode = toNodes[i];
            int count = otherNode.fromLength++;
            otherNode.fromNodes[count] = this;
            otherNode.coeffs[count] = rand.nextDouble();
        }
    }

    void __rvp__updateFromNodes() {
    }

    void computeNewValue() {
        for (int i = 0; i < fromCount; i++) {
            value -= coeffs[i] * fromNodes[i].value;
        }
    }

    void __rvp__computeNewValue() {
    }

    public String toString() {
        return "value " + value + ", from_count " + fromCount;
    }
}
