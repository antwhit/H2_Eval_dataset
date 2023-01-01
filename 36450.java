import java.util.*;

/**
 * This class implements depth-first search over a OPT_Graph,
 * and stores the discover and finish numbers for each vertex
 * See Cormen, Leiserson, Rivest Ch. 23
 *
 * TODO: this implementation is not terribly efficient
 * (hopefully, I have fixed this to some degree -- Julian)
 *
 * @author Stephen Fink
 * @author Julian Dolby
 */
class OPT_DFS extends OPT_Stack {

    OPT_DFS(OPT_Graph net) {
        traverse(net.enumerateNodes());
    }

    OPT_DFS(OPT_GraphNodeEnumeration nodes) {
        traverse(nodes);
    }

    /** 
    * Return the discovery time for a vertex.
    */
    public int getDiscover(OPT_GraphNode v) {
        VertexInfo vInfo = (VertexInfo) info.get(v);
        if (vInfo == null) return -1;
        return vInfo.discover;
    }

    /** 
    * Return the finish time for a vertex.
    */
    public int getFinish(OPT_GraphNode v) {
        VertexInfo vInfo = (VertexInfo) info.get(v);
        if (vInfo == null) return -1;
        return vInfo.finish;
    }

    private java.util.HashMap info = new java.util.HashMap();

    private int time;

    /**
    * Perform a DFS of net and mark the discover and finish times
    * for each vertex
    */
    private static final int WHITE = 0;

    private static final int GRAY = 1;

    private static final int BLACK = 2;

    private synchronized void traverse(OPT_GraphNodeEnumeration e) {
        time = 0;
        for (; e.hasMoreElements(); ) {
            OPT_GraphNode v = e.next();
            if (info.get(v) == null) DFSVisit(new VertexInfo(v));
        }
    }

    /**
    * Implementation: simulates recursion
    */
    protected OPT_GraphNodeEnumeration getConnected(OPT_GraphNode n) {
        return n.outNodes();
    }

    private void DFSVisit(VertexInfo v) {
        push(v);
        recurse: while (!empty()) {
            VertexInfo vInfo = (VertexInfo) peek();
            if (vInfo.color == WHITE) {
                vInfo.color = GRAY;
                vInfo.discover = time;
                time = time + 1;
            }
            for (OPT_GraphNodeEnumeration e = vInfo.pendingChildren; e.hasMoreElements(); ) {
                OPT_GraphNode n = e.next();
                VertexInfo nInfo = (VertexInfo) info.get(n);
                if (nInfo == null) {
                    nInfo = new VertexInfo(n);
                    push(nInfo);
                    continue recurse;
                }
            }
            vInfo.color = BLACK;
            vInfo.finish = time;
            time = time + 1;
            pop();
        }
    }

    /** Class that holds information for the DFS */
    private class VertexInfo {

        int discover;

        int finish;

        int color = WHITE;

        OPT_GraphNode node;

        OPT_GraphNodeEnumeration pendingChildren;

        VertexInfo(OPT_GraphNode n) {
            pendingChildren = getConnected(n);
            node = n;
            info.put(n, this);
        }
    }
}
