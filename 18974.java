import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.debug.BlankDebugEventListener;
import java.io.File;

/** Parse a java file or directory of java files using the generated parser
 *  ANTLR builds from java.g
 */
class Main {

    public static long lexerTime = 0;

    public static boolean profile = false;

    static JavaLexer lexer;

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    doFile(new File(args[i]));
                }
            } else {
                System.err.println("Usage: java Main <directory or file name>");
            }
            long stop = System.currentTimeMillis();
            System.out.println("finished parsing OK");
            if (profile) {
                System.out.println("num decisions " + profiler.numDecisions);
            }
        } catch (Exception e) {
            System.err.println("exception: " + e);
            e.printStackTrace(System.err);
        }
    }

    public static void doFile(File f) throws Exception {
        if (f.isDirectory()) {
            String files[] = f.list();
            for (int i = 0; i < files.length; i++) doFile(new File(f, files[i]));
        } else if (((f.getName().length() > 5) && f.getName().substring(f.getName().length() - 5).equals(".java")) || f.getName().equals("input")) {
            System.err.println("parsing " + f.getAbsolutePath());
            parseFile(f.getAbsolutePath());
        }
    }

    static class CountDecisions extends BlankDebugEventListener {

        public int numDecisions = 0;

        public void enterDecision(int decisionNumber) {
            numDecisions++;
        }
    }

    static CountDecisions profiler = new CountDecisions();

    public static void parseFile(String f) throws Exception {
        try {
            lexer = new JavaLexer(new ANTLRFileStream(f));
            CommonTokenStream tokens = new CommonTokenStream();
            tokens.setTokenSource(lexer);
            JavaParser parser = null;
            parser = new JavaParser(tokens);
            parser.compilationUnit();
        } catch (Exception e) {
            System.err.println("parser exception: " + e);
            e.printStackTrace();
        }
    }
}
