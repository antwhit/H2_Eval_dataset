import org.nakedobjects.object.context.StaticContext;
import org.nakedobjects.object.persistence.objectstore.inmemory.InMemoryPersistor;
import org.nakedobjects.object.security.PasswordFileAuthenticator;
import org.nakedobjects.object.transaction.TransactionPeerFactory;
import org.nakedobjects.reflector.java.fixture.FixturesFromConfiguration;
import org.nakedobjects.reflector.java.resource.ResourcesFromProperties;
import org.nakedobjects.system.NakedObjectsSystem;
import org.nakedobjects.system.install.core.DefaultObjectLoader;
import org.nakedobjects.utility.configuration.DefaultConfigurationLoader;

public final class CommandLine {

    public static void main(String[] args) {
        NakedObjectsSystem e = new NakedObjectsSystem();
        e.addAuthenticator(new PasswordFileAuthenticator());
        e.setInstallConfiguration(new DefaultConfigurationLoader());
        e.setContext(StaticContext.createInstance());
        e.addReflectivePeer(new TransactionPeerFactory());
        e.setObjectLoaderFactory(new DefaultObjectLoader());
        e.setObjectPersistorFactory(new InMemoryPersistor());
        e.setInstallFixture(new FixturesFromConfiguration());
        e.setInstallResources(new ResourcesFromProperties());
        e.init();
        e.showClient("cli", null, false);
    }
}
