import java.util.*;
import java.io.*;
import javax.swing.JTextArea;

/**
 * Runs the confidence interval needed in demarcations
 * 
 * @author Andrew Warner 
 * @version 1.0
 */
public class DemarcationConfidence extends NpopConfidence {

    /**
     * The best likelihood from the demarcation confidence interval
     */
    private double bestLike;

    /**
     * Constructor for objects of class DemarcationConfidence
     */
    public DemarcationConfidence(FredOutVal value, BinningAndFred results, NarrWriter narr, JTextArea log, File input, File output, String cmd, boolean isNew) {
        super(value, results, narr, log, input, output, cmd, isNew);
    }

    /**
     * Runs demarcations using the old npop confidence interval program
     */
    public int[] demarcations() {
        writeInputDemarcations();
        runCI(type);
        int[] interval = getDemarcOutput();
        return interval;
    }

    /**
     * Writes the input file for a demarcations run
     */
    private void writeInputDemarcations() {
        if (value.getNpop() < sequenceVals[0]) upperBound = value.getNpop(); else upperBound = sequenceVals[0];
        double[] npopRange = { 1, upperBound };
        int[] xnumics = { 0, 0, 1, 0 };
        int sortPer = MasterVariables.getSortPercentage();
        double[] percentages = value.getPercentages();
        double probThresh = percentages[sortPer] / MasterVariables.CI_NUMBER;
        super.writeInput(input, type, npopRange, xnumics, probThresh);
        narr.println();
        narr.println("The input for the demarcations run: ");
        narr.writeInput(input);
    }

    /**
     * Reads the output from demarcations from the output file
     * @return an array of int such that [0] is the optimal npop, [1] is 
     * the lower bound of the confidence interval, and [2] is the upper bound
     */
    protected int[] getDemarcOutput() {
        try {
            BufferedReader input = new BufferedReader(new FileReader(output));
            bestLike = 0.0;
            double nextLike;
            String line = input.readLine();
            StringTokenizer tk;
            int[] demarcValues = new int[3];
            int nextNpop;
            while (line != null) {
                tk = new StringTokenizer(line);
                nextNpop = (int) (new Double(tk.nextToken())).doubleValue();
                tk.nextToken();
                nextLike = (-1) * (new Double(tk.nextToken())).doubleValue();
                if (nextLike > bestLike) {
                    bestLike = nextLike;
                    demarcValues[0] = nextNpop;
                }
                line = input.readLine();
            }
            input.close();
            double probThresh = bestLike / MasterVariables.CI_NUMBER;
            input = new BufferedReader(new FileReader(output));
            line = input.readLine();
            while (line != null) {
                tk = new StringTokenizer(line);
                nextNpop = (int) (new Double(tk.nextToken())).doubleValue();
                tk.nextToken();
                nextLike = (-1) * (new Double(tk.nextToken())).doubleValue();
                if (demarcValues[1] == 0) {
                    if (nextLike > probThresh) demarcValues[1] = nextNpop;
                } else if (nextLike < probThresh) {
                    demarcValues[2] = nextNpop - 1;
                    break;
                }
                line = input.readLine();
                if (line == null && !(nextLike < probThresh)) demarcValues[2] = nextNpop;
            }
            input.close();
            return demarcValues;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return the best likelihood associated with the best npop from
     * the demarcation confidence interval
     */
    public double getBestLike() {
        return bestLike;
    }
}
