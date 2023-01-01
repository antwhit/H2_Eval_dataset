import java.io.*;
import java.util.*;

class Scanner extends App {

    private final StreamTokenizer st;

    private final InputStream in;

    Scanner(InputStream in) {
        this(false, in);
    }

    Scanner(boolean verbose, InputStream in) {
        super(verbose);
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(this.in = in)));
        st.commentChar('#');
        st.ordinaryChar('.');
        st.ordinaryChar('_');
        st.slashSlashComments(true);
        st.slashStarComments(true);
    }

    public void close() throws IOException {
        in.close();
    }

    public Token nextToken() throws IOException {
        Token res = null;
        if (st.nextToken() == StreamTokenizer.TT_EOF) return null;
        switch(st.ttype) {
            case StreamTokenizer.TT_EOL:
                res = nextToken();
                break;
            case StreamTokenizer.TT_NUMBER:
                res = new Token.Number(st.nval);
                break;
            case StreamTokenizer.TT_WORD:
                res = new Token.Str(st.sval);
                break;
            default:
                res = new Token.Delim((char) st.ttype);
                break;
        }
        res.setLine(st.lineno());
        if (isVerbose()) note("read: " + res);
        return res;
    }

    public static void main(String[] args) throws Exception {
        new Scanner(new FileInputStream("spec.txt")).realMain(args);
    }

    void go() throws Exception {
        Token t = null;
        while ((t = nextToken()) != null) System.out.println(t);
        close();
    }
}
