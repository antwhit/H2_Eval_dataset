import xdfAST.Start;
import xdfAST.Instance;
import java.util.HashSet;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class MergeActors extends XdfParser {

    public static void main(String args[]) {
        Start ast = parse(args);
        ast.mergeActors();
        try {
            String name = args[0].substring(0, args[0].indexOf('.'));
            ast.prettyPrint("", new PrintStream(name + "_new.xdf"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not generate modified xdf file");
        }
    }
}
