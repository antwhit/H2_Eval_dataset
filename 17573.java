import sparsematmult.*;
import jgfutil.*;

public class JGFSparseMatmultBenchSizeA {

    public static void main(String argv[]) {
        JGFInstrumentor.printHeader(2, 0);
        JGFSparseMatmultBench smm = new JGFSparseMatmultBench();
        smm.JGFrun(0);
    }
}
