import saf.interpret.Interpreter;
import saf.parser.*;
import saf.lexer.*;
import saf.node.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Lexer lexer = new Lexer(new PushbackReader(new FileReader(args[0]), 1024));
                Parser parser = new Parser(lexer);
                Start ast = parser.parse();
                Interpreter interp = new Interpreter();
                ast.apply(interp);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.err.println("usage: java simpleAdder inputFile");
            System.exit(1);
        }
    }
}
