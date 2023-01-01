import pspdash.AbstractLanguageFilter;
import java.io.PrintWriter;

public class ShFilter extends AbstractLanguageFilter {

    private static final String[] COMMENT_STARTERS = { "#" };

    protected String[] getCommentStarters() {
        return COMMENT_STARTERS;
    }

    private static final String[] COMMENT_ENDERS = { "\n" };

    protected String[] getCommentEnders() {
        return COMMENT_ENDERS;
    }

    private static final String[] FILENAME_ENDINGS = { ".pl", ".sh", ".bash" };

    protected String[] getDefaultFilenameEndings() {
        return FILENAME_ENDINGS;
    }

    protected int doubleCheckFileContents(String contents, int match) {
        if (contents.startsWith("#define") || contents.startsWith("#include") || contents.startsWith("#if")) return LANGUAGE_MISMATCH; else return match;
    }
}
