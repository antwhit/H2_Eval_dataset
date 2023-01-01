public class Mutation {

    protected final float probability;

    protected final int max;

    public Mutation(float probability, int maxDepth) {
        this.probability = probability;
        this.max = maxDepth;
    }

    public void mutate(Generation g) {
        int genSize = g.size();
        Generation g2 = new Generation(g);
        for (int i = 0; i < genSize; i += 2) {
            Parents p = g.pickParents();
            g2.add(p.tryMutation(probability));
        }
    }
}
