import org.gmod.chado.gametochadx.ChadoWriter;
import org.gmod.chado.gametochadx.GameSaxReader;
import java.io.*;
import java.util.*;

public class GTC {

    public static void main(String argv[]) {
        String infile = null, outfile = null;
        int parseflags = GameSaxReader.PARSEALL;
        int modeflags = ChadoWriter.WRITEALL;
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
        ChadoWriter rd = new ChadoWriter(infile, outfile, parseflags, modeflags);
        rd.GameToChado();
    }
}
