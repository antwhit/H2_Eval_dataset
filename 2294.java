public class Logger {

    public Logger() {
    }

    static long generationNumber = 0;

    public void logGeneration(Generation g) {
        System.out.println("********************************************");
        System.out.println("Generation #" + (generationNumber++));
        Fitness f = g.getFitness();
        System.out.println("Average fitness=" + f.average());
        System.out.println("Maximum fitness=" + f.maximum());
    }
}
