import cogitant.base.Environment;
import cogitant.base.IOHandler;
import cogitant.base.EnvironmentObject;
import cogitant.base.Graph;
import cogitant.base.Rule;
import cogitant.base.Projection;
import cogitant.base.ObserverMessage;
import cogitant.base.ObserverMessage.*;
import java.io.FileInputStream;
import java.util.Collection;

class SimpleObserver implements cogitant.base.Observer {

    public void messageAttached() {
    }

    public void messageDetached() {
    }

    public void message(ObserverMessage msg) {
        System.out.println("Message: " + msg.toString());
    }

    public boolean wantsMessage(ObserverMessage.Type mt) {
        return true;
    }
}

;

public class JavaRule {

    public static void runClosure(String prefix) throws Exception {
        Environment environment = new cogitant.jni.Environment();
        environment.loadSupport(new FileInputStream(prefix + "/bcgct/sisyphus/sisyphus.bcs"), IOHandler.Format.BCGCT);
        Collection<EnvironmentObject> loadedgraphs = environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/locals.bcg"), IOHandler.Format.BCGCT);
        Graph g = loadedgraphs.iterator().next().asGraph();
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_1.bcr"), IOHandler.Format.BCGCT);
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_2.bcr"), IOHandler.Format.BCGCT);
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_3.bcr"), IOHandler.Format.BCGCT);
        System.out.println(g.size());
        environment.rulesClosure(g, null, 2, 0);
        System.out.println(g.size());
        environment.rulesClosure(g, null, 0, 1);
        System.out.println(g.size());
        environment.rulesClosure(g, null, 0, 0);
        System.out.println(g.size());
    }

    public static void runLowLevelOperations(String prefix) throws Exception {
        Environment environment = new cogitant.jni.Environment();
        environment.loadSupport(new FileInputStream(prefix + "/bcgct/sisyphus/sisyphus.bcs"), IOHandler.Format.BCGCT);
        Collection<EnvironmentObject> loadedgraphs = environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/locals.bcg"), IOHandler.Format.BCGCT);
        Graph g = loadedgraphs.iterator().next().asGraph();
        Rule[] rules = new Rule[3];
        SimpleObserver s = new SimpleObserver();
        g.attachObserver(s);
        for (int i = 0; i < 3; i++) {
            Collection<EnvironmentObject> loadedrules = environment.loadObjects(new FileInputStream(prefix + "/bcgct/sisyphus/near_" + (i + 1) + ".bcr"), IOHandler.Format.BCGCT);
            rules[i] = loadedrules.iterator().next().asRule();
        }
        boolean onemore = true;
        System.out.println(g.size());
        while (onemore) {
            onemore = false;
            for (int i = 0; i < 3; i++) {
                Collection<Projection> cp = environment.ruleApplications(g, rules[i], true);
                for (Projection p : cp) onemore |= environment.ruleApply(g, rules[i], p, true);
                System.out.println("after rule near_" + (i + 1) + ":" + g.size());
            }
        }
        g.detachObserver(s);
    }

    public static void main(String args[]) throws Exception {
        String prefix = "..";
        if (args.length == 1) prefix = args[0];
        System.out.println("prefix: " + prefix);
        System.out.println("rulesClosure");
        runClosure(prefix);
        System.out.println("ruleApplications and ruleApply");
        runLowLevelOperations(prefix);
    }
}
