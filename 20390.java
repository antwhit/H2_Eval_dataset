import org.nakedobjects.object.persistence.objectstore.NakedObjectStore;
import org.nakedobjects.object.persistence.objectstore.inmemory.TransientObjectStore;
import org.nakedobjects.utility.configuration.ConfigurationException;

public class StandaloneClientServerWithXmlStore extends StandaloneClientServer {

    public static void main(String[] args) throws ConfigurationException {
        StandaloneClientServerWithXmlStore starter = new StandaloneClientServerWithXmlStore();
        starter.init();
    }

    protected NakedObjectStore objectStore() {
        return new TransientObjectStore();
    }
}
