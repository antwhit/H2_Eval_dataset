import java.util.Enumeration;

final class BiGraph {

    /**
     * Nodes that represent the electrical field.
     **/
    Node eNodes;

    /**
     * Nodes that representhe the magnetic field.
     **/
    Node hNodes;

    /**
     * Construct the bipartite graph.
     * @param e the nodes representing the electric fields
     * @param h the nodes representing the magnetic fields
     **/
    BiGraph(Node e, Node h) {
        eNodes = e;
        hNodes = h;
    }

    Node hTable[];

    Node eTable[];

    BiGraph(int numNodes, int numDegree, boolean verbose) {
        if (verbose) System.out.println("making nodes (tables in orig. version)");
        hTable = new Node[numNodes];
        for (int i = 0; i < hTable.length; i++) {
            hTable[i] = new Node(numDegree);
        }
        eTable = new Node[numNodes];
        for (int i = 0; i < eTable.length; i++) {
            eTable[i] = new Node(numDegree);
        }
        if (verbose) System.out.println("updating from and coeffs");
        for (int i = 0; i < hTable.length; i++) {
            Node n = hTable[i];
            n.makeUniqueNeighbors(eTable);
        }
        for (int i = 0; i < eTable.length; i++) {
            Node n = eTable[i];
            n.makeUniqueNeighbors(hTable);
        }
        if (verbose) System.out.println("filling from fields");
        for (int i = 0; i < hTable.length; i++) {
            Node n = hTable[i];
            n.makeFromNodes();
        }
        for (int i = 0; i < eTable.length; i++) {
            Node n = eTable[i];
            n.makeFromNodes();
        }
        for (int i = 0; i < hTable.length; i++) {
            Node n = hTable[i];
            n.updateFromNodes();
        }
        for (int i = 0; i < eTable.length; i++) {
            Node n = eTable[i];
            n.updateFromNodes();
        }
    }

    /**
     * Update the field values of e-nodes based on the values of
     * neighboring h-nodes and vice-versa.
     **/
    void compute() {
        for (int i = 0; i < eTable.length; i++) {
            Node n = eTable[i];
            n.computeNewValue();
        }
        for (int i = 0; i < hTable.length; i++) {
            Node n = hTable[i];
            n.computeNewValue();
        }
    }

    void _p_slice_for_compute() {
    }

    /**
     * Override the toString method to print out the values of the e and h nodes.
     * @return a string contain the values of the e and h nodes.
     **/
    public String toString() {
        StringBuffer retval = new StringBuffer();
        for (int i = 0; i < eTable.length; i++) {
            Node n = eTable[i];
            retval.append("E: " + n + "\n");
        }
        for (int i = 0; i < hTable.length; i++) {
            Node n = hTable[i];
            retval.append("H: " + n + "\n");
        }
        return retval.toString();
    }
}
