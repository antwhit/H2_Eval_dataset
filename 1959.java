import mucode.abstractions.*;
import mucode.*;

public abstract class AbstractBouncer extends MuAgent {

    int step = 0;

    int lastVisited = 1;

    String[] hosts = new String[2];

    public AbstractBouncer() {
        super();
    }

    public AbstractBouncer(String host1, String host2, MuServer aServer) {
        super(aServer);
        hosts[0] = host1;
        hosts[1] = host2;
    }
}
