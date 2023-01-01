import edu.udo.cs.ai.nemoz.plugins.Plugin;
import edu.udo.cs.ai.nemoz.plugins.PluginIdentifier;
import edu.udo.cs.ai.nemoz.plugins.restinterface.container.NemozContainer;

/**
 * A simple REST style web interface for the Nemoz N3DM.
 *
 * @author oflasch
 */
public class RestInterfacePlugin implements Plugin {

    protected static final int VERSION = 1;

    protected static final String DESCRIPTION = "A simple REST style web interface for the N3DM.";

    protected static final int PORT = 8084;

    protected NemozContainer container;

    public PluginIdentifier getIdentifier() {
        return new PluginIdentifier("RestInterfacePlugin", VERSION);
    }

    public PluginIdentifier[] getDependencies() {
        return new PluginIdentifier[] {};
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public void init() {
        container = new NemozContainer(PORT);
    }

    public void dispose() {
        container.dispose();
    }
}
