public class ParseAbortException extends Exception {

    private int maxerrors;

    public ParseAbortException(int max) {
        super(max == ParserConstants.EOF ? "EOF reached.  Parsing stopped." : max + " syntax errors found.  Parsing failed.");
        maxerrors = max;
    }

    public boolean isEOF() {
        return (maxerrors == ParserConstants.EOF);
    }
}
