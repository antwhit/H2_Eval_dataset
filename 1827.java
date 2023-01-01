import java.io.*;
import org.apache.oro.text.regex.*;
import net.wimpi.text.*;
import net.wimpi.text.std.*;

public class PlainTextUrl extends AbstractProcessor {

    private Pattern m_MatchPattern = null;

    private ResourcePool m_ResourcePool = null;

    public PlainTextUrl() {
        try {
            m_MatchPattern = new Perl5Compiler().compile(URL_PATTERN);
            m_ResourcePool = ProcessingKernel.getReference().getResourcePool("patternmatchers");
        } catch (Exception ex) {
            throw new RuntimeException("Failed to construct processor.");
        }
    }

    public String getName() {
        return "plaintexturl";
    }

    public String process(String str) {
        StringBuffer strbuf = new StringBuffer(str);
        int relinsertend = 0;
        int relinsertbegin = 0;
        int corr = 0;
        ProcessingResource resource = m_ResourcePool.leaseResource();
        PatternMatcher matcher = ((PatternMatchingResource) resource).getPatternMatcher();
        try {
            PatternMatcherInput input = new PatternMatcherInput(str);
            while (matcher.contains(input, m_MatchPattern)) {
                MatchResult result = matcher.getMatch();
                String url = result.toString();
                relinsertend = (result.endOffset(0)) + corr;
                relinsertbegin = (result.beginOffset(0)) + corr;
                corr = corr + 4 + 25 + url.length();
                strbuf.insert(relinsertend, "</a>");
                strbuf.insert(relinsertbegin, "<a href='" + url + "' target='_top'>");
            }
        } finally {
            m_ResourcePool.releaseResource(resource);
        }
        return strbuf.toString();
    }

    private static final String URL_PATTERN = "(\\S)+://(\\S)+";
}
