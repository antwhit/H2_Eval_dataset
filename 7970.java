import EA.Graph.Graph;
import EA.Graph.Partition;
import EA.LocalSearch.LocalSearch;

public class ProblemSolver {

    protected LocalSearch currentAlgorithm;

    protected Graph graph;

    public ProblemSolver(Graph graph) {
        this.graph = graph;
        Partition.createRandomPartitions(this.graph);
        this.graph.calculateGains();
    }

    public void setAlgoritm(LocalSearch ls) {
        this.currentAlgorithm = ls;
        this.currentAlgorithm.setGraph(this.graph);
    }

    public int solve() {
        int bestCutSize = this.currentAlgorithm.start();
        return bestCutSize;
    }
}
