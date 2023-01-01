import javax.swing.JTextArea;
import java.io.File;

/**
 * This class holds the master variables that may need to be changed later into
 * the project, so that the programmer will not have to go through each class
 * and change them manually
 *
 * @author Andrew Warner
 * @version 1.0
 */
public class MasterVariables {

    public static final double HOMGEN_COEFF = 7.79;

    /**
     * The input file with outgroup removed
     */
    public static final File NO_OUTGROUP = new File("noOutgroup.dat");

    /**
     * The confidence interval number for any one-tailed confidence interval.
     * IE: to see if a value is in confidence, we take our likelihood from
     * hill climbing and divide it by this number
     */
    public static final double ONETAIL_CI_NUMBER = 3.87;

    /**
     * The optimal number of successes that we want
     */
    public static final int NUM_SUCCESSES = 50;

    /**
     * The number of ideal successes for a run of the confidence interval
     */
    public static final int NUM_CI_SUCCESSES = 20;

    /**
     * The value to start from in the drift confidence interval
     */
    public static final double DRIFT_CI_START = 1.0e4;

    /**
     * the number to divide the likelihood by to get the confidence interval
     * range
     */
    public static final double CI_NUMBER = 6.83;

    /**
     * The log to output to
     */
    private static JTextArea log;

    /**
     * The index of the first value in the percentages array (in FredOutVal
     * objects) to start sorting by
     */
    private static int sortPercentage = 5;

    /**
     * The default range for omega
     */
    private static double[] omegaRange = { 0.001, 100.0 };

    /**
     * The default range for sigma
     */
    private static double[] sigmaRange = { 0.001, 100.0 };

    /**
     * The default range for drift
     */
    private static double[] driftRange = { 1.0e25, 1.0e26 };

    /**
     * The default values for xnumics
     */
    private static int[] xnumics = { 20, 20, 6, 0 };

    /**
     * The default number of reps to do of fred method
     */
    private static int nrep = 20;

    /**
     * no constructor since this class will only be used from a static
     * context
     */
    public MasterVariables() {
    }

    /**
     * Returns the percentage to start sorting at, where 2 starts at 1.5x,
     * 1 starts at 2x, etc (see FredOutVal documentation)
     */
    public static int getSortPercentage() {
        return sortPercentage;
    }

    /**
     * Set the starting percentage for comparison, the default is 2, ie, values
     * are sorted based on their 1.5x values, then by their 2x values, then by
     * 5x
     * A value of 3 here would mean that output values are sorted instead by
     * their 1.25x values, then by 1.5x, then by 2x, etc
     * @pre newVal is between 2 and 5
     * @post the new value is stored in sortPercentage
     * @param newVal the new value to be stored in sortPercentage
     */
    public static void setSortPercentage(int newVal) {
        sortPercentage = newVal;
    }

    /**
     * Get the default range for omega
     * @return The Default range of omega
     */
    public static double[] getOmegaRange() {
        return omegaRange;
    }

    /**
     * Get the default range for sigma
     * @return The Default range of sigma
     */
    public static double[] getSigmaRange() {
        return sigmaRange;
    }

    /**
     * Get the default range for drift
     * @return The Default range of drift
     */
    public static double[] getDriftRange() {
        return driftRange;
    }

    /**
     * Get the default values of xnumics
     * @return The Default values of xnumics
     */
    public static int[] getxnumics() {
        return xnumics;
    }

    /**
     * Get the default number of reps
     * @return the default number of reps
     */
    public static int getnrep() {
        return nrep;
    }

    /**
     * Return the master log file to write to
     */
    public static JTextArea getLog() {
        return log;
    }

    /**
     * Set the log value
     */
    public static void setLog(JTextArea newLog) {
        log = newLog;
    }
}
