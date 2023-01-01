import iwork.eheap2.*;
import iwork.state.*;
import iwork.icrafter.remote.*;

public class DiscoveryExample2 {

    public static void main(String[] args) {
        try {
            EventHeap eh = new EventHeap(System.getProperty("heapMachine"));
            ServiceDiscoveryUtils sdu = new ServiceDiscoveryUtils(eh);
            EHCallObject ehc = new EHCallObject(eh);
            StateSpace ss = StateSpaceManager.getInstance(eh);
            MatchingVariable[] mVars = new MatchingVariable[1];
            mVars[0] = new MatchingVariable("Language", StateConstants.STRING, "English");
            DiscoveredService[] svcs = sdu.locateServices("iwork.icrafter.services.DataProducer", mVars, new String[] { "UnitsProduced" }, new String[] { StateConstants.INT });
            for (int i = 0; i < svcs.length; i++) {
                System.out.println("Discovered service " + i + " : " + svcs[i].getServiceName() + " HOST: " + svcs[i].getMachineName() + " UnitsProduced: " + svcs[i].getValue("UnitsProduced"));
            }
            mVars = new MatchingVariable[2];
            mVars[0] = new MatchingVariable("DataTypeProduced", StateConstants.STRING, "Text");
            mVars[1] = new MatchingVariable(StateConstants.HOST, StateConstants.STRING, "host3");
            ehc.ehIFCall("iwork.icrafter.services.DataProducer", "produce", null, mVars);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
