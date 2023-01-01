import euler.*;
import moldyn.*;
import montecarlo.*;
import raytracer.*;
import search.*;
import jgfutil.*;

public class JGFAllSizeA {

    public static void main(String argv[]) {
        int size = 0;
        JGFInstrumentor.printHeader(3, size);
        JGFEulerBench eb = new JGFEulerBench();
        eb.JGFrun(size);
        JGFMolDynBench mdb = new JGFMolDynBench();
        mdb.JGFrun(size);
        JGFMonteCarloBench mcb = new JGFMonteCarloBench();
        mcb.JGFrun(size);
        JGFRayTracerBench rtb = new JGFRayTracerBench();
        rtb.JGFrun(size);
        JGFSearchBench sb = new JGFSearchBench();
        sb.JGFrun(size);
    }
}
