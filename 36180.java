import cogitant.base.Environment;
import cogitant.base.EnvironmentObject;
import cogitant.base.Graph;
import cogitant.base.GraphObject;
import cogitant.base.IOHandler;
import cogitant.base.Support;
import cogitant.base.SupportObject;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

class JavaIO {

    public static void run(String prefix) throws Exception {
        Environment environment = new cogitant.jni.Environment();
        environment.loadSupport(new FileInputStream(prefix + "/xml/bucolic/bucolic_id.cogxml"), IOHandler.Format.COGXML);
        Collection<SupportObject> concepttypes = environment.support().conceptTypes();
        System.out.println(concepttypes);
        Collection<SupportObject> relationtypes = environment.support().relationTypes();
        System.out.println(relationtypes);
        Collection<SupportObject> nestingtypes = environment.support().nestingTypes();
        System.out.println(nestingtypes);
        Collection<SupportObject> individuals = environment.support().individuals();
        System.out.println(individuals);
        Collection<EnvironmentObject> loadedgraphs = environment.loadObjects(new FileInputStream(prefix + "/bcgct/bucolic/petitsfaits.bcg"), IOHandler.Format.BCGCT);
        System.out.println("loaded graphs: " + loadedgraphs.toString());
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/bucolic/fact.bcg"), IOHandler.Format.BCGCT);
        environment.loadObjects(new FileInputStream(prefix + "/bcgct/bucolic/query.bcg"), IOHandler.Format.BCGCT);
        System.out.println("graphs:");
        Collection<Graph> graphs = environment.graphs();
        for (Iterator<Graph> i = graphs.iterator(); i.hasNext(); ) {
            Graph g = i.next();
            System.out.println(g.toString() + ": " + g.nodes().toString());
            Collection<GraphObject> nodes = g.nodes();
            for (Iterator<GraphObject> j = nodes.iterator(); j.hasNext(); ) {
                GraphObject go = j.next();
                System.out.print("  " + go + " " + go.objectType());
                if ((go.objectType() == GraphObject.Type.CONCEPT) || (go.objectType() == GraphObject.Type.RELATION) || (go.objectType() == GraphObject.Type.NESTING)) System.out.print("-" + go.type().label() + "(" + go.type().identifier() + ")");
                if (go.objectType() == GraphObject.Type.CONCEPT) if (go.referentType() == GraphObject.ReferentType.INDIVIDUAL) System.out.print(":" + go.individual().label() + "(" + go.individual().identifier() + ")"); else System.out.print(":*");
                System.out.print(" ");
                System.out.println(go.neighbourhood());
            }
            System.out.println();
        }
        System.out.println(environment.findGraph("doesnotexist"));
    }

    public static void main(String args[]) throws Exception {
        String prefix = "..";
        if (args.length == 1) prefix = args[0];
        System.out.println("prefix: " + prefix);
        run(prefix);
    }
}
