import java.util.ArrayList;
import java.util.HashMap;

public class SearchIDAStar implements Search {

    protected Heuristic heuristic = new RushHourHeuristic3();

    protected State state;

    protected ArrayList<Long> stateSequence = new ArrayList<Long>();

    public class DFS_CRetval {

        public boolean found;

        public int limit;

        public DFS_CRetval() {
            this(false, 0);
        }

        public DFS_CRetval(boolean found, int limit) {
            this.found = found;
            this.limit = limit;
        }
    }

    public boolean run(State s, Cost cost, ArrayList<Action> solution) {
        stateSequence.clear();
        m_nodesExpanded = 0;
        heuristic.init(s);
        state = s;
        long initialStateId = s.getStateID();
        int limit = heuristic.getHeuristic(s);
        DFS_CRetval dfsRet = new DFS_CRetval(false, limit);
        while (!dfsRet.found) {
            dfsRet = DFS_C(initialStateId, 0, dfsRet.limit);
        }
        int totalCost = 0;
        long lastStateId = initialStateId;
        for (int i = stateSequence.size() - 1; i >= 0; i--) {
            s.setState(lastStateId);
            ArrayList<Action> actions = new ArrayList<Action>();
            s.getActions(actions);
            for (Action action : actions) {
                int actionCost = s.getCost(action);
                s.make(action);
                if (s.getStateID() == stateSequence.get(i)) {
                    solution.add(action);
                    lastStateId = s.getStateID();
                    totalCost += actionCost;
                    break;
                } else {
                    s.retract(action);
                }
            }
        }
        cost.value = totalCost;
        return dfsRet.found;
    }

    private DFS_CRetval DFS_C(long stateId, int cost, int f_limit) {
        state.setState(stateId);
        if (state.isGoal()) {
            return new DFS_CRetval(true, cost);
        }
        int min_limit = Integer.MAX_VALUE;
        m_nodesExpanded++;
        ArrayList<Action> actions = new ArrayList<Action>();
        state.getActions(actions);
        for (Action action : actions) {
            int actionCost = state.getCost(action);
            state.make(action);
            long neighborStateId = state.getStateID();
            int g_prime = cost + actionCost;
            int f = g_prime + heuristic.getHeuristic(state);
            if (f <= f_limit) {
                DFS_CRetval retval = DFS_C(neighborStateId, g_prime, f_limit);
                if (retval.found) {
                    stateSequence.add(neighborStateId);
                    return retval;
                } else {
                    min_limit = Math.min(retval.limit, min_limit);
                }
            } else {
                int next_limit = f;
                min_limit = Math.min(next_limit, min_limit);
            }
            state.retract(action);
        }
        return new DFS_CRetval(false, min_limit);
    }

    public long getNodesExpanded() {
        return m_nodesExpanded;
    }

    private long m_nodesExpanded;
}
