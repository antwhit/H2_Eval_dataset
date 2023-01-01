import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TreeSet;

public class Move implements Comparable<Move> {

    private final int FROM;

    private final int TO;

    private final ArrayList<Integer> VISITED;

    static ListGraph board = new Spelplan().getBoard();

    private int quality;

    Move(ArrayList<Integer> visited, int to, int start, int goal, TreeSet<Move> oAlternatives) {
        FROM = visited.get(0);
        TO = to;
        VISITED = visited;
        if (goal < 0) {
            quality = -1000;
        } else {
            if (!oAlternatives.isEmpty()) {
                int i = 0;
                ArrayList<Integer> oVisited;
                Move move = oAlternatives.first();
                while (move != null && i < 5) {
                    oVisited = move.visited();
                    int k = 0;
                    for (int j = oVisited.size(); j > 0; j--) {
                        if (oVisited.get(k) == TO) {
                            quality += j;
                            i = 5;
                            break;
                        }
                        k++;
                    }
                    i++;
                }
            }
            int a = 1;
            int b = 1;
            int i = 0;
            int[] path = bfs(board, goal, FROM);
            if (path != null) {
                i = path[FROM];
                while (i != goal) {
                    i = path[i];
                    a++;
                }
            }
            path = bfs(board, goal, TO);
            if (path != null) {
                i = path[TO];
                while (i != goal) {
                    i = path[i];
                    b++;
                }
            }
            quality += a - b;
        }
    }

    Move(int from, int to, int[] pos) {
        FROM = from;
        TO = to;
        quality = 0;
        VISITED = null;
    }

    public int compareTo(Move other) {
        if (quality == other.quality()) return 1; else return quality > other.quality() ? -1 : 1;
    }

    private int[] bfs(ListGraph lg, int a, int b) {
        int[] traced = new int[121];
        if (a == b) {
            traced[b] = a;
            return traced;
        }
        ArrayList<Integer> visited = new ArrayList<Integer>();
        LinkedList<Integer> ll = new LinkedList<Integer>();
        VertexIterator it;
        int i;
        boolean firstrun = true;
        visited.add(a);
        while (!ll.isEmpty() || firstrun) {
            firstrun = false;
            it = lg.adjacentVertices(a);
            while (it.hasNext()) {
                i = it.next();
                if (i == b) {
                    traced[i] = a;
                    return traced;
                } else {
                    if (!visited.contains(i)) {
                        visited.add(i);
                        traced[i] = a;
                        ll.addLast(i);
                    }
                }
            }
            a = ll.removeFirst();
        }
        return null;
    }

    public int from() {
        return FROM;
    }

    public int to() {
        return TO;
    }

    private ArrayList<Integer> visited() {
        return VISITED;
    }

    public int quality() {
        return quality;
    }

    public String toString() {
        return from() + "->" + to();
    }
}
