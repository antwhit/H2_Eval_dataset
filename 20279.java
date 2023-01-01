import eprog.*;
import java.io.*;

/**
 * runlen, #1112
 *
 * Orginal Code von Ren� Sedmik ( Mat.Nr.: 0100560 )<br>
 * Das Programm liest einen String bis maximal 30 Zeichen ein und codiert diesen nach dem Runlenth-Verfahren.
 *
 * @author Hannes Eder <e9521554@stud3.tuwien.ac.at>
 */
public class testen {

    public static boolean debug = false;

    /**
   * Dieser entry point dient nur zum Debuggen
   */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) debug = true;
        }
        if (debug) test_runlen(System.err);
    }

    /**
   * testen runlen mit ein paar relevanten Daten
   * @return true, wenn alle Test ok waren
   */
    public static boolean test_runlen(PrintStream log) {
        boolean isOk = true;
        isOk &= test_runlen_helper(log, "", "", false);
        isOk &= test_runlen_helper(log, "ab", "ab", false);
        isOk &= test_runlen_helper(log, "abba", "a'b2'a", false);
        isOk &= test_runlen_helper(log, "bba", "'b2'a", false);
        isOk &= test_runlen_helper(log, "aaaaabaaaaa", "'a5'b'a5", false);
        isOk &= test_runlen_helper(log, "aaaaaaaaaa", "", true);
        isOk &= test_runlen_helper(log, "'''", "'''3", false);
        isOk &= test_runlen_helper(log, "aa'''", "'a2''3", false);
        isOk &= test_runlen_helper(log, "'aa'''", "'''a2''3", false);
        isOk &= test_runlen_helper(log, "0123456789012345678901234567890", "", true);
        log.println((isOk ? "[OK]" : "[ERR]") + " testAll");
        return isOk;
    }

    /**
   * Hilfsfunktion f�r test_runlen
   */
    public static boolean test_runlen_helper(PrintStream log, String input, String expectedOutput, boolean exceptionExpected) {
        boolean isOk = true;
        boolean exceptionOccured = false;
        boolean outputMatch = false;
        boolean exceptionMatch = false;
        String output = "";
        try {
            output = runlen(input);
        } catch (Exception e) {
            log.println(e.getMessage());
            exceptionOccured = true;
        }
        outputMatch = expectedOutput.equals(output);
        isOk &= outputMatch;
        exceptionMatch = exceptionOccured == exceptionExpected;
        isOk &= exceptionMatch;
        log.println((isOk ? "[OK]" : "[ERR]") + " runlen(\"" + input + "\") = \"" + output + "\" " + (exceptionOccured ? "[EXP] " : "[NO EXP] ") + (outputMatch ? "==" : "!=") + " exptected: \"" + expectedOutput + "\" " + (exceptionExpected ? "[EXP]" : "[NO EXP]"));
        return isOk;
    }

    /**
   * Dieser entry point wird von main.java verwendet (siehe testen.html)
   */
    public static String perform() {
        String ret = "FALSCHE EINGABE";
        try {
            String in = EprogIO.readWord();
            ret = runlen(in);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
   * F�hrt die Koomprimierung lt. Spez. durch
   */
    public static String runlen(String in) throws Exception {
        if (in.length() > 30) throw new Exception("input string to long (>30 characters)");
        StringReader sr = new StringReader(in);
        StringBuffer out = new StringBuffer(in.length() * 2);
        boolean bInMultiMode = false;
        int ch = sr.read();
        int chold = ch;
        while (ch != -1) {
            int count = 0;
            while (ch == chold) {
                count++;
                ch = sr.read();
            }
            if (count > 9) {
                throw new Exception("more than 9 repeats of the same character");
            } else if (count > 1) {
                if (!bInMultiMode) {
                    out.append("'");
                    bInMultiMode = true;
                }
                if (chold == '\'') out.append("''"); else out.append((char) chold);
                out.append(count);
            } else if (count == 1) {
                if (bInMultiMode) {
                    out.append("'");
                    bInMultiMode = false;
                }
                if (chold == '\'') out.append("''"); else out.append((char) chold);
            } else {
                throw new Exception("unexpected value of count, something is bogus");
            }
            chold = ch;
        }
        return out.toString();
    }
}
