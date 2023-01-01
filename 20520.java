import cogitant.base.Environment;
import cogitant.base.ObserverMessage;
import cogitant.base.ObserverMessageGraphRuleApplication;
import cogitant.base.IOHandler;
import cogitant.base.EnvironmentObject;
import cogitant.base.Graph;
import cogitant.base.GraphObject;
import java.io.FileInputStream;
import java.util.Collection;

class SampleObserver implements cogitant.base.Observer {

    public void messageAttached() {
        System.out.println("Attached");
    }

    public void messageDetached() {
        System.out.println("Detached");
    }

    public void message(ObserverMessage msg) {
        try {
            System.out.println("Message: " + msg.toString());
            if (msg instanceof ObserverMessageGraphRuleApplication) {
                ObserverMessageGraphRuleApplication m = (ObserverMessageGraphRuleApplication) msg;
                System.out.print("Hypothesis: ");
                Collection<GraphObject> nodes = m.rule().hypothesis().nodes();
                for (GraphObject go : nodes) System.out.print(go + ":" + go.label() + " ");
                System.out.println();
                System.out.print("Conclusion: ");
                nodes = m.rule().conclusion().nodes();
                for (GraphObject go : nodes) System.out.print(go + ":" + go.label() + " ");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean wantsMessage(ObserverMessage.Type mt) {
        return true;
    }
}

public class JavaObserver {

    public static void run(String prefix) throws Exception {
        Environment environment = new cogitant.jni.Environment();
        environment.loadSupport(new FileInputStream(prefix + "/bcgct/sisyphus/sisyphus.bcs"), IOHandler.Format.BCGCT);
        Collection<EnvironmentObject> loadedgraphs = environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/locals.bcg"), IOHandler.Format.BCGCT);
        Graph g = loadedgraphs.iterator().next().asGraph();
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_1.bcr"), IOHandler.Format.BCGCT);
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_2.bcr"), IOHandler.Format.BCGCT);
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_3.bcr"), IOHandler.Format.BCGCT);
        System.out.println("locals.bcg");
        SampleObserver so = new SampleObserver();
        g.attachObserver(so);
        environment.rulesClosureNormalize(g, null, 0, 0);
        g.detachObserver(so);
        System.out.println("locals_same_individual.bcg");
        loadedgraphs = environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/locals_same_individual.bcg"), IOHandler.Format.BCGCT);
        Graph g2 = loadedgraphs.iterator().next().asGraph();
        g2.attachObserver(so);
        environment.rulesClosureNormalize(g2, null, 0, 0);
        g2.detachObserver(so);
    }

    public static void main(String args[]) throws Exception {
        String prefix = "..";
        if (args.length == 1) prefix = args[0];
        System.out.println("prefix: " + prefix);
        run(prefix);
    }
}
