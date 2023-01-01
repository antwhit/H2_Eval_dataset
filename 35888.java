import org.antlr.runtime.*;

public class Main {

    public static void main(String[] args) throws Exception {
        CharStream input = new ANTLRFileStream(args[0]);
        TLexer lex = new TLexer(input);
        TokenStream tokens = new TokenRewriteStream(lex);
        TParser parser = new TParser(tokens);
        parser.program();
        System.out.println(tokens);
    }
}
