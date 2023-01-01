import pspdash.AbstractLanguageFilter;
import java.io.PrintWriter;

public class PascalFilter extends AbstractLanguageFilter {

    private static final String[] COMMENT_STARTERS = { "//", "{" };

    protected String[] getCommentStarters() {
        return COMMENT_STARTERS;
    }

    private static final String[] COMMENT_ENDERS = { "\n", "}" };

    protected String[] getCommentEnders() {
        return COMMENT_ENDERS;
    }

    private static final String[] FILENAME_ENDINGS = { ".pas" };

    protected String[] getDefaultFilenameEndings() {
        return FILENAME_ENDINGS;
    }
}
