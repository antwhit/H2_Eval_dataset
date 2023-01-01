import java.io.*;
import EA.Graph.Graph;
import EA.LocalSearch.LocalSearch;
import EA.LocalSearch.IteratedLocalSearch;
import EA.LocalSearch.MultiStartLocalSearch;
import EA.LocalSearch.Genetic.GeneticLocalSearch;

/**
 * @author Pieter Hoogestijn
 *
 */
public class Program {

    /**
	 * @param args
	 */
    public static void main(String[] args) throws IOException {
        File file = new File("File.txt");
        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        Graph u = new Graph(new File("data_U.txt"));
        Graph g = new Graph(new File("data_G.txt"));
        ProblemSolver psu = new ProblemSolver(u);
        ProblemSolver psg = new ProblemSolver(g);
        psu.setAlgoritm(new GeneticLocalSearch());
        output.write("Results for GeneticLocalSearch for dataset U with pop 25" + System.getProperty("line.separator"));
        for (int i = 1; i <= 50; i++) {
            int overallBestU = psu.solve();
            System.out.println("Best cutsize found: " + overallBestU);
            output.write(i + ".  " + overallBestU + System.getProperty("line.separator"));
            LocalSearch.numberOfFmRuns = 0;
        }
        psg.setAlgoritm(new GeneticLocalSearch());
        output.write("Results for GeneticLocalSearch for dataset G with pop 25" + System.getProperty("line.separator"));
        for (int i = 1; i <= 50; i++) {
            int overallBestG = psg.solve();
            System.out.println("Best cutsize found: " + overallBestG);
            output.write(i + ".  " + overallBestG + System.getProperty("line.separator"));
            LocalSearch.numberOfFmRuns = 0;
        }
        output.close();
    }
}
