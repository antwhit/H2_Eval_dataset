import org.nakedobjects.object.context.StaticContext;
import org.nakedobjects.object.persistence.objectstore.inmemory.InMemoryPersistor;
import org.nakedobjects.object.security.PasswordFileAuthenticator;
import org.nakedobjects.object.transaction.TransactionPeerFactory;
import org.nakedobjects.reflector.java.fixture.FixturesFromConfiguration;
import org.nakedobjects.reflector.java.resource.ResourcesFromProperties;
import org.nakedobjects.system.NakedObjectsSystem;
import org.nakedobjects.system.install.core.DefaultObjectLoader;
import org.nakedobjects.utility.NakedObjectRuntimeException;
import org.nakedobjects.utility.configuration.DefaultConfigurationLoader;
import org.nakedobjects.viewer.cli.Input;
import org.nakedobjects.viewer.cli.awt.AwtConsole;
import org.nakedobjects.viewer.cli.debug.FileInput;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public final class CommandLineInWindow {

    public static void main(String[] args) {
        NakedObjectsSystem e = new NakedObjectsSystem();
        e.setContext(StaticContext.createInstance());
        e.addAuthenticator(new PasswordFileAuthenticator());
        e.setInstallConfiguration(new DefaultConfigurationLoader());
        e.addReflectivePeer(new TransactionPeerFactory());
        e.setObjectLoaderFactory(new DefaultObjectLoader());
        e.setObjectPersistorFactory(new InMemoryPersistor());
        e.setInstallFixture(new FixturesFromConfiguration());
        e.setInstallResources(new ResourcesFromProperties());
        e.init();
        e.showClient(Console.class.getName(), null, false);
    }

    public static class Console extends AwtConsole {

        protected Input chainInput(Input input) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream("console.script")));
            } catch (FileNotFoundException e) {
                throw new NakedObjectRuntimeException(e);
            }
            return new FileInput(reader, input);
        }
    }
}
