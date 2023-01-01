import java.util.ArrayList;
import java.util.Collections;

class SearchDFID implements Search {

    public boolean run(State s, Cost cost, ArrayList<Action> solution) {
        m_nodesExpanded = 0;
        int depth = 0;
        boolean solved = false;
        while (!solved) {
            solved = DFS(s, depth, cost, solution);
            depth++;
        }
        Collections.reverse(solution);
        return solved;
    }

    public long getNodesExpanded() {
        return m_nodesExpanded;
    }

    private long m_nodesExpanded;

    private boolean DFS(State s, int depth, Cost cost, ArrayList<Action> solution) {
        if (s.isGoal()) {
            cost.value = 0;
            solution.clear();
            return true;
        } else if (depth <= 0) {
            return false;
        }
        ArrayList<Action> actions = new ArrayList<Action>();
        s.getActions(actions);
        m_nodesExpanded++;
        boolean solved = false;
        for (int i = 0; i < actions.size(); ++i) {
            s.make(actions.get(i));
            solved = DFS(s, depth - 1, cost, solution);
            s.retract(actions.get(i));
            if (solved) {
                cost.value += s.getCost(actions.get(i));
                solution.add(actions.get(i));
                break;
            }
        }
        return solved;
    }
}
