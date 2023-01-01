import java.io.File;
import java.net.URL;
import org.ist.contract.store.core.DefaultUtil;
import org.ist.contract.store.webservice.IContractStoreAgent;
import org.ist.contract.store.webservice.IContractStoreAgentServiceLocator;

public class AxisClient {

    public static void main(String[] args) throws Exception {
        IContractStoreAgentServiceLocator uvws = new IContractStoreAgentServiceLocator();
        try {
            URL url = new URL("http://localhost:8800/axis/services/ContractStoreAgent");
            IContractStoreAgent lAgent = uvws.getContractStoreAgent(url);
            System.out.println(lAgent.store("sss"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
