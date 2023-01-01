import iwork.icrafter.system.*;
import iwork.icrafter.util.*;
import iwork.state.*;
import java.io.*;

/**
 * Shows how to write a service that adds information to SDL. This
 * service is same as Simple2 except it adds SDL information about the
 * parameters and methods. This information can also be added by
 * editing the SDL file in the SDL repository maintained by the
 * DiscoveryService
 **/
public class InterestFinder2 extends ICrafterService implements InterestFinder {

    String logFile;

    protected void init() throws ICrafterException {
        logFile = getInitParameter("logFile");
        if (logFile == null) {
            throw new ICrafterException("logFile null!!");
        }
        try {
        } catch (Exception e) {
            throw new ICrafterException("cannot open log file for writing!! " + e.getMessage());
        }
    }

    /** 
     * In the method findInterest, the parameters interestRate can
     * only take a value between 0 and 100 and the parameter
     * interestType can take only values "simple" and "complex". This
     * additional "type information" is added to the SDL here. We also
     * add descriptive information for the methods
     **/
    protected void addSDL() throws IllegalSDLException {
        Class[] paramTypes = new Class[3];
        paramTypes[0] = Integer.TYPE;
        paramTypes[1] = Integer.TYPE;
        paramTypes[2] = String.class;
        addMethodSDL("findInterest", paramTypes, "finds the interest given the amount, rate, and interest type");
        addParameterSDL("findInterest", paramTypes, 0, "amount", StateConstants.INT, null, "amount on which interest is to be calculated");
        addParameterSDL("findInterest", paramTypes, 1, "interestRate", ICrafterConstants.INTRANGE, new int[] { 0, 100 }, "percent rate of interest");
        addParameterSDL("findInterest", paramTypes, 2, "interestType", ICrafterConstants.STRINGENUM, new String[] { "simple", "compound" }, "nature of interest");
    }

    public int findInterest(int amount, int interestRate, String interestType) {
        int interest = 0;
        System.out.println("findInterest called with: [" + amount + ", " + interestRate + ", " + interestType + "]");
        if (interestRate < 0 || interestRate > 100) throw new IllegalArgumentException("Illegal value for interestRate: " + interestRate);
        if (interestType.equals("simple")) {
            int simpleInterest = 0;
            interest = simpleInterest;
        } else if (interestType.equals("compound")) {
            int compoundInterest = 0;
            interest = compoundInterest;
        } else {
            throw new IllegalArgumentException("Illegal value for interestType: " + interestType);
        }
        return interest;
    }
}
