import edu.berkeley.guir.gesture.*;
import edu.berkeley.guir.gesture.features.*;
import edu.berkeley.guir.util.Misc;
import java.io.File;
import java.io.PrintStream;

/** 
 * <P>
 * This software is distributed under the 
 * <A HREF="http://guir.cs.berkeley.edu/projects/COPYRIGHT.txt">
 * Berkeley Software License</A>.
 
*/
public class curviness {

    static final String progName = "curviness";

    static final String usage = "usage: " + progName + " [-max #] [gesturefile]";

    public static void main(String[] argv) {
        int i;
        for (i = 0; (i < argv.length) && (argv[i].charAt(i) == '-'); i++) {
            if (argv[i].equals("-max")) {
                try {
                    i++;
                    if (i >= argv.length) {
                        error(usage);
                        System.exit(-1);
                    }
                    Double d = Double.valueOf(argv[i]);
                    Curviness.threshold = d.doubleValue();
                } catch (NumberFormatException e) {
                    error("invalid number: " + argv[i]);
                    System.exit(-1);
                }
            } else {
                error("Unknown option: " + argv[i]);
                error(usage);
                System.exit(-1);
            }
        }
        if (i >= argv.length) {
            error(usage);
            System.exit(-1);
        }
        GestureSetFrame frame = new GestureSetFrame(progName, false);
        for (; i < argv.length; i++) {
            frame.openFile(new File(argv[i]));
            GestureSet gs = frame.getGestureSetDisplay().getGestureSet();
            printFeatureVals(System.out, gs);
        }
        System.exit(0);
    }

    static void printFeatureVals(PrintStream out, GestureSet gs) {
        for (int catNum = 0; catNum < gs.size(); catNum++) {
            GestureCategory gc = gs.categoryAt(catNum);
            out.print(gc.getName());
            double average1 = 0;
            double average2 = 0;
            for (int gNum = 0; gNum < gc.size(); gNum++) {
                Gesture g = gc.gestureAt(gNum);
                Feature f = new Curviness(g);
                average1 += f.getValue();
                f = new Curviness2(g);
                average2 += f.getValue();
            }
            out.println("\t" + average1 / gc.size() + "\t" + average2 / gc.size());
        }
    }

    static void error(String message) {
        System.err.println(progName + ": " + message);
    }
}
