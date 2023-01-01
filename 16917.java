public abstract class GraphTraversal {

    protected State init;

    GraphTraversal(State s) {
        init = s;
    }

    abstract State first();

    abstract boolean done();

    abstract State next();
}

;
