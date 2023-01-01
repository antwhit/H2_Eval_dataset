import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;
import info.fedora.Fedora_API_A;
import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class info_fedora_Fedora_API_A_ServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();

    private HashMap endpoints = new HashMap();

    private Service service0;

    public info_fedora_Fedora_API_A_ServiceClient() {
        create0();
        Endpoint Fedora_API_ALocalEndpointEP = service0.addEndpoint(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-ALocalEndpoint"), new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-ALocalBinding"), "xfire.local://Fedora-API-A-Service");
        endpoints.put(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-ALocalEndpoint"), Fedora_API_ALocalEndpointEP);
        Endpoint Fedora_API_A_Port_SOAPHTTPEP = service0.addEndpoint(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Port-SOAPHTTP"), new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Binding-SOAPHTTP"), "http://localhost:8080/fedora/services/access");
        endpoints.put(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Port-SOAPHTTP"), Fedora_API_A_Port_SOAPHTTPEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((info.fedora.Fedora_API_A.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-ALocalBinding"), "urn:xfire:transport:local");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Binding-SOAPHTTP"), "http://schemas.xmlsoap.org/soap/http");
        }
    }

    public Fedora_API_A getFedora_API_ALocalEndpoint() {
        return ((Fedora_API_A) (this).getEndpoint(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-ALocalEndpoint")));
    }

    public Fedora_API_A getFedora_API_ALocalEndpoint(String url) {
        Fedora_API_A var = getFedora_API_ALocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public Fedora_API_A getFedora_API_A_Port_SOAPHTTP() {
        return ((Fedora_API_A) (this).getEndpoint(new QName("http://www.fedora.info/definitions/1/0/api/", "Fedora-API-A-Port-SOAPHTTP")));
    }

    public Fedora_API_A getFedora_API_A_Port_SOAPHTTP(String url) {
        Fedora_API_A var = getFedora_API_A_Port_SOAPHTTP();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }
}
