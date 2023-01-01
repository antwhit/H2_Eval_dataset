import java.net.URL;
import java.net.MalformedURLException;
import javamath.server.proxycas.*;
import java.util.*;
import java.io.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import CA.sfu.cecm.openmath.lang.*;

public class Test {

    private static ProxyCASession proxySession = null;

    public static void main(String args[]) {
        ProxyCAServer proxyCAServer = null;
        String proxyCAServerURL = null;
        String sysToTest = null;
        switch(args.length) {
            case 2:
                proxyCAServerURL = args[0];
                sysToTest = args[1];
                break;
            default:
                System.err.println("usage: Test <ProxyCasURL> <sysToTest(GAP/Maple)>\n");
                System.exit(1);
        }
        try {
            proxyCAServer = new ProxyCAServer(proxyCAServerURL);
            if (sysToTest.equals("GAP")) {
                System.out.println("Testing GAP...\n");
                proxySession = proxyCAServer.createSession("GAP");
            } else if (sysToTest.equals("Maple")) {
                System.out.println("Testing Maple...\n");
                proxySession = proxyCAServer.createSession("Maple");
            } else {
                System.err.println("Error: invalid sysToTest\n");
                System.exit(1);
            }
            testEvaluateOM();
            testAssignRetrieveOM();
            proxySession.closeSession();
        } catch (Exception e) {
            System.err.println("Test exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printOMError(OMError err) {
        Vector params = err.getParameters();
        OMString message = (OMString) params.elementAt(0);
        System.out.println("Error occurred: " + message.getValue());
    }

    private static void testEvaluateOM() throws Exception {
        OMInteger omInt = new OMInteger(12345);
        System.out.println("Evaluation of " + omInt.getValue());
        OMObject result = proxySession.evaluateOpenMath(omInt);
        if (result instanceof OMError) {
            printOMError((OMError) result);
        } else {
            omInt = (OMInteger) result;
            System.out.println(" is " + omInt.getValue() + "\n");
        }
    }

    private static void testAssignRetrieveOM() throws Exception {
        OMInteger omInt = new OMInteger(12345);
        System.out.println("Assignment of " + omInt.getValue() + " to varA\n");
        proxySession.assignOpenMath("varA", omInt);
        OMObject result = proxySession.retrieveOpenMath("varA");
        if (result instanceof OMError) {
            printOMError((OMError) result);
        } else {
            omInt = (OMInteger) result;
            System.out.println("Retrieved " + omInt.getValue() + " as value of varA\n");
        }
    }
}
