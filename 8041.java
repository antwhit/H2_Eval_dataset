import org.dcopolis.algorithm.*;
import org.dcopolis.problem.*;
import org.dcopolis.platform.*;
import java.util.*;

public class RandomAgent extends Agent {

    public RandomAgent(String name, String hostName, DCOP problem, HashSet<Variable> variables, Platform<AgentIdentifier<HostIdentifier>, HostIdentifier> platform) {
        super(name, hostName, problem, variables, platform);
    }

    public void run() {
        Random random = new Random();
        for (Variable v : getVariables()) {
            ArrayList<Object> domain = new ArrayList<Object>(v.getDomain());
            fireValueUpdate(v, null, domain.get(random.nextInt(domain.size())));
        }
    }
}
