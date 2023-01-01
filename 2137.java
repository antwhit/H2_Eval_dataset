import org.codehaus.xfire.service.binding.*;
import org.codehaus.xfire.service.*;
import org.codehaus.xfire.client.*;

public class TClient {

    public static void main(String[] arguments) throws Exception {
        Service serviceModel = new ObjectServiceFactory().create(ServiceInterface.class, "instantSOAP", "http://www.cs.ncl.ac.uk/instant-soap", null);
        ServiceInterface serviceInterface = (ServiceInterface) new XFireProxyFactory().create(serviceModel, "http://localhost:8080/instant-soap/services/instant-soap");
        String str = serviceInterface.hello(arguments[0]);
        System.out.println(str);
    }
}
