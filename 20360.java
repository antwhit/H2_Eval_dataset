import java.io.*;
import org.antlr.runtime.*;

public class Main {

    public static void main(String[] args) throws Exception {
        CharStream input = new ANTLRFileStream(args[0]);
        SimpleLexer lexer = new SimpleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleParser parser = new SimpleParser(tokens);
        parser.file();
    }
}
