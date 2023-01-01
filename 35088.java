import algorithms.*;
import SPI.*;
import Log.*;
import MACE.*;
import MACESPI.*;
import java.util.*;

/**
 * @author Pedro Colla (pcolla@frsf.utn.edu.ar)
 *
 * Apr 2007
 */
public class TestMACESPI {

    public static void main(String argv[]) {
        Trace.getTraceObject();
        Trace.getTraceObject().verbosity = false;
        Trace.getTraceObject().traceEnabled = false;
        Trace.getTraceObject().printLog(1, "Starting TestMACESPI");
        Trace.getTraceObject().printSystemInfo();
        MACESPIEnvironment spi = new MACESPIEnvironment();
        spi.REWARD = 400.0;
        int firstArg;
        if (argv.length == 0) {
            spi.DISCOUNT = 0.02;
        } else {
            firstArg = Integer.parseInt(argv[0]);
            spi.DISCOUNT = (firstArg * 0.01);
        }
        System.out.println("Operating with a Discount Rate of " + spi.DISCOUNT);
        Trace.getTraceObject().printLog(1, "Environment generated");
        MACESPIState sp = new MACESPIState(spi);
        int nbAgent = sp.spi.org.groups.size();
        Trace.getTraceObject().printLog(1, "Number of Groups is " + nbAgent);
        MACEArbitre org = new MACEArbitre(spi);
        double epsilon = 0.50;
        LinkedList ll = sp.spi.org.getGroups();
        Iterator it = ll.iterator();
        while (it.hasNext()) {
            Group g = (Group) it.next();
            Trace.getTraceObject().printLog(1, "Creating Agent(" + g.id + ")");
            MACESimpleLearningSelector q = new MACESimpleLearningSelector();
            q.setEpsilonGreedy();
            q.setEpsilon(epsilon);
            q.setGamma(1.0);
            q.setAlpha(0.1);
            q.setGeometricAlphaDecay();
            q.setDecay(1);
            q.setTau(0.5);
            MACEAgent a = new MACEAgent(g.id, spi, q, new MACESPIFilter());
            org.addAgent(a);
        }
        org.rendezvousAgent();
        MACESPIState initial = new MACESPIState(spi);
        org.setMaxIter(25);
        int NMAX = 1500;
        int totalLength = 0;
        double totalReward = 0.0;
        double totalNPV = 0.0;
        double totalRDF = 0.0;
        double totalEND = 0.0;
        MACESPIState ls;
        spi.TRUI0 = initial.computeTRUI();
        Trace.getTraceObject().printLog(1, "Initial State TRUI0(" + spi.TRUI0 + ")");
        for (int i = 1; i < NMAX; i++) {
            Trace.getTraceObject().printLog(1, "Episode(" + i + ")");
            int u = org.episode(initial);
            double reward = org.getRewardForEpisode();
            MACESPIState lsp = (MACESPIState) org.lastState;
            totalLength += u;
            totalReward += reward;
            totalNPV += lsp.NPV;
            totalRDF += lsp.RDF;
            totalEND += lsp.spi.getTask("END").end;
            if (i % 100 == 0) {
                ls = (MACESPIState) org.getcurrentState();
                System.out.println(i + " " + (totalLength / 100) + " " + (totalReward / 100) + " " + ls.spi.getTask("END").end + " " + (totalNPV / 100) + " " + (totalRDF / 100));
                totalLength = 0;
                totalReward = 0.0;
                totalNPV = 0.0;
                totalRDF = 0.0;
                totalEND = 0.0;
            }
            if (i > (NMAX / 2)) {
                epsilon *= 0.99;
                org.setEpsilon(epsilon);
            }
        }
        ls = (MACESPIState) org.getcurrentState();
        System.out.println("Plan at Last Iteration");
        ls.spi.printTask();
    }
}
