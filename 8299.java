import java.io.*;
import antlr.collections.AST;
import antlr.collections.impl.*;
import antlr.debug.misc.*;
import antlr.*;

class Main {

    public static TokenStreamSelector selector = new TokenStreamSelector();

    public static PParser parser;

    public static PLexer mainLexer;

    public static void main(String[] args) {
        try {
            DataInputStream input = new DataInputStream(System.in);
            mainLexer = new PLexer(input);
            selector.addInputStream(mainLexer, "main");
            selector.select("main");
            parser = new PParser(selector);
            parser.setFilename("<stdin>");
            parser.startRule();
        } catch (Exception e) {
            System.err.println("exception: " + e);
            e.printStackTrace(System.err);
        }
    }
}
