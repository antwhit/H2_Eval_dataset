import java.util.*;

public class RoachPathFinder implements PathFinder {

    protected AStarSearch searchEngine;

    public RoachPathFinder(float brain) {
        assert (brain >= 0 && brain <= 1);
        searchEngine = new AStarSearch(brain);
    }

    public List getPathBetween(AStarNode start, AStarNode finish) {
        return searchEngine.findPath(start, finish);
    }
}

interface PathFinder {

    public List getPathBetween(AStarNode start, AStarNode finish);
}
