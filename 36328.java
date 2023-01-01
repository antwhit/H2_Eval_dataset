import conv.gametochadx.ChadoWriter;
import conv.gametochadx.GameSaxReader;
import java.io.*;
import java.util.*;

public class GTC {

    public static void main(String argv[]) {
        String infile = null, outfile = null;
        int parseflags = GameSaxReader.PARSEALL;
        int modeflags = ChadoWriter.WRITEALL;
        int seqflags = ChadoWriter.SEQOMIT;
        int tpflags = ChadoWriter.TPOMIT;
        int ulflags = 0;
        for (int i = 0; i < argv.length; i++) {
            if (argv[i] != null) {
                if (argv[i].startsWith("-")) {
                    if (argv[i].equals("-a")) {
                        parseflags = GameSaxReader.PARSEALL;
                    } else if (argv[i].equals("-c")) {
                        parseflags = GameSaxReader.PARSECOMP;
                    } else if (argv[i].equals("-g")) {
                        parseflags = GameSaxReader.PARSEGENES;
                    } else if (argv[i].equals("-t")) {
                        modeflags = ChadoWriter.WRITECHANGED;
                    } else if (argv[i].equals("-s")) {
                        seqflags = ChadoWriter.SEQINCL;
                    } else if (argv[i].equals("-p")) {
                        tpflags = ChadoWriter.TPINCL;
                    } else if (argv[i].equals("-R")) {
                        ulflags = ChadoWriter.REL4;
                    } else if (argv[i].equals("-H")) {
                        ulflags = ChadoWriter.HET;
                    }
                } else {
                    if (infile == null) {
                        infile = argv[i];
                    } else if (outfile == null) {
                        outfile = argv[i];
                    }
                }
            }
        }
        ChadoWriter rd = new ChadoWriter(infile, outfile, parseflags, modeflags, seqflags, tpflags, ulflags);
        rd.GameToChado();
    }
}
