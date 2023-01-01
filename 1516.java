import jdbc.XADataSourceTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;
import naming.Naming;
import transaction.Transaction;
import util.VerboseStream;

/**
 * Test harness.
 */
public class TestHarness {

    public static void main(String args[]) {
        try {
            TestSuite main = new TestSuite("Tyrex Test Harness");
            TestSuite naming = new Naming("JNDI service provider");
            if (args.length < 1) {
                System.out.println("Usage :");
                System.out.println("    java TestHarness <domainconfig file path and name> [-verbose]");
                System.out.println();
                System.exit(0);
            }
            TestSuite jdbc = new XADataSourceTestSuite("XADataSource test", args[0]);
            TestSuite transaction = new Transaction("Transaction tests", args[0]);
            for (java.util.Enumeration e = naming.tests(); e.hasMoreElements(); ) main.addTest((Test) e.nextElement());
            for (java.util.Enumeration e = jdbc.tests(); e.hasMoreElements(); ) main.addTest((Test) e.nextElement());
            for (java.util.Enumeration e = transaction.tests(); e.hasMoreElements(); ) main.addTest((Test) e.nextElement());
            for (int i = 0; i < args.length; i++) if (args[i].equals("-verbose")) VerboseStream.verbose = true;
            junit.textui.TestRunner.run(main);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.exit(0);
    }
}
