import java.rmi.*;
import java.rmi.server.*;

public class SampleClientSimulate {

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            System.out.println("Security Manager loaded");
            String url = "//192.168.15.110/SAMPLE-SERVER";
            integratorSNew remoteObject = (integratorSNew) Naming.lookup(url);
            System.out.println("Got remote object");
            System.out.println(" 1 + 2 = " + remoteObject.sum(1, 2));
            System.out.println(" 1 + 2 = " + remoteObject.SimulateLoadTransfer());
            System.out.println(" 1 + 2 = " + remoteObject.mul(2, 3));
        } catch (RemoteException exc) {
            System.out.println("Error in lookup: " + exc.toString());
        } catch (java.net.MalformedURLException exc) {
            System.out.println("Malformed URL: " + exc.toString());
        } catch (java.rmi.NotBoundException exc) {
            System.out.println("NotBound: " + exc.toString());
        }
    }
}
