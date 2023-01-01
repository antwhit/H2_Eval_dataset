import org.juddi.client.*;
import org.juddi.datatype.*;
import org.juddi.datatype.business.*;
import org.juddi.datatype.request.*;
import org.juddi.datatype.response.*;
import org.juddi.error.*;
import org.juddi.registry.*;
import java.util.Vector;
import java.io.File;

/**
 * @author Steve Viens (sviens@users.sourceforge.net)
 */
public class SaveBusinessSample {

    public static void main(String[] args) {
        RegistryProxy proxy = new RegistryProxy();
        try {
            AuthToken authToken = proxy.get_authToken("sviens", "password");
            AuthInfo authInfo = authToken.getAuthInfo();
            Vector nameVector = new Vector();
            nameVector.add(new Name("Fidelity Investments"));
            nameVector.add(new Name("FISC"));
            nameVector.add(new Name("FEB"));
            nameVector.add(new Name("FCAT"));
            BusinessEntity businessEntity = new BusinessEntity();
            businessEntity.setBusinessKey(null);
            businessEntity.setNameVector(nameVector);
            Vector businessVector = new Vector();
            businessVector.add(businessEntity);
            BusinessDetail detail = proxy.save_business(authInfo, businessVector);
            System.out.println("businesses saved = " + detail.getBusinessEntityVector().size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
