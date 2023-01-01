import java.io.*;
import antlr.collections.AST;

class Main {

    public static void main(String[] args) {
        try {
            ExprLexer lexer = new ExprLexer(new DataInputStream(System.in));
            ExprParser parser = new ExprParser(lexer);
            parser.setASTNodeType("antlr.CommonAST");
            parser.expr();
            antlr.CommonAST ast = (antlr.CommonAST) parser.getAST();
            if (ast != null) {
                System.out.println(ast.toStringList());
            } else {
                System.out.println("null AST");
            }
        } catch (Exception e) {
            System.err.println("exception: " + e);
        }
    }
}
