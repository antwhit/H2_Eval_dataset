import java.io.*;

public class TestLexer {

    public TestLexer() throws IOException {
        FileInputStream in = new FileInputStream("testtext3.txt");
        SearchLexer lexer = new SearchLexer(in);
        while (true) {
            String next = lexer.yylex();
            if (next == null) break;
            System.out.print(next + ", ");
        }
    }

    public static void main(String[] args) throws IOException {
        new TestLexer();
    }
}
