import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 * XSL transformation bean remote interface
 * @author Morten Jorgensen
 */
public interface TransformRemote extends EJBObject {

    public String transform(String document, String transletName) throws RemoteException;
}
