import antlr.collections.*;

class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        LltLexer lexer = new LltLexer(System.in);
        LltParser parser = new LltParser(lexer);
        parser.expr();
        AST t = parser.getAST();
        System.out.println(t.toStringTree());
    }
}
