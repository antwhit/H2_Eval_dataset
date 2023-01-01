import java.util.Iterator;
import java.util.LinkedList;
import qlearning.ConstantValueChooser;
import Log.Trace;
import MACE.MACEAgent;
import MACE.MACEArbitre;
import MACE.MACESimpleLearningSelector;
import MACESPI.MACESPIEnvironment;
import MACESPI.MACESPIFilter;
import MACESPI.MACESPIState;
import SPI.Group;

/**
 * @author Pedro Colla (pcolla@frsf.utn.edu.ar)
 *
 * Apr 2007
 */
public class TestMACERDF {

    public static void main(String argv[]) {
        Trace.getTraceObject();
        Trace.getTraceObject().verbosity = false;
        Trace.getTraceObject().traceEnabled = false;
        Trace.getTraceObject().printLog(1, "Starting TestMACERDF");
        Trace.getTraceObject().printSystemInfo();
        MACESPIEnvironment spi = new MACESPIEnvironment();
        spi.REWARD = 400.0;
        spi.DISCOUNT = 0.02;
        spi.PENALTY = 0;
        spi.computeMethodNPV = false;
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
            ConstantValueChooser dvc = new ConstantValueChooser(-400);
            MACESimpleLearningSelector q = new MACESimpleLearningSelector(spi, dvc);
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
        org.setMaxIter(50);
        int NMAX = 1500;
        int totalLength = 0;
        double totalReward = 0.0;
        double totalNPV = 0.0;
        double totalRDF = 0.0;
        double totalEND = 0.0;
        spi.TRUI0 = initial.computeTRUI();
        Trace.getTraceObject().printLog(1, "Initial State TRUI0(" + spi.TRUI0 + ")");
        MACESPIState ls;
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
