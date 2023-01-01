import com.sun.tools.doclets.Taglet;
import com.sun.javadoc.*;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Taglet that expands inline cdk.cite tags into a weblink to the CDK
 * bibliography webpage. Like all inline tags it's used in the JavaDoc
 * text as:
 * <pre>
 * This class does nothing {@cdk.cite NULL}.
 * </pre>
 * For this code a reference is created like this:
 * <pre>
 * <a href="http://cdk.sf.net/biblio.html#NULL">NULL</a>
 * </pre>
 *
 * <p>Citations can be singular, like <code>{@cdk.cite BLA}</code>,
 * and multiple, like <code>{@cdk.cite BLA,BLA2,FOO}</code>.
 */
public class CDKCiteTaglet implements Taglet {

    private static final String NAME = "cdk.cite";

    public String getName() {
        return NAME;
    }

    public boolean inField() {
        return true;
    }

    public boolean inConstructor() {
        return true;
    }

    public boolean inMethod() {
        return true;
    }

    public boolean inOverview() {
        return true;
    }

    public boolean inPackage() {
        return true;
    }

    public boolean inType() {
        return true;
    }

    public boolean isInlineTag() {
        return true;
    }

    public static void register(Map tagletMap) {
        CDKCiteTaglet tag = new CDKCiteTaglet();
        Taglet t = (Taglet) tagletMap.get(tag.getName());
        if (t != null) {
            tagletMap.remove(tag.getName());
        }
        tagletMap.put(tag.getName(), tag);
    }

    public String toString(Tag tag) {
        return "[" + expandCitation(tag.text()) + "]";
    }

    public String toString(Tag[] tags) {
        String result = null;
        if (tags.length > 0) {
            result = "[";
            for (int i = 0; i < tags.length; i++) {
                result += expandCitation(tags[i].text());
                if ((i + 1) < tags.length) result += ", ";
            }
            result += "]";
        }
        return result;
    }

    /**
     * Expands a citation into HTML code.
     */
    private String expandCitation(String citation) {
        String result = "";
        final String separator = ",";
        result += "<!-- indexOf" + citation.indexOf(separator) + " -->";
        if (citation.indexOf(separator) != -1) {
            StringTokenizer tokenizer = new StringTokenizer(citation, separator);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken().trim();
                result += "<a href=\"http://cdk.sf.net/biblio.html#" + token + "\">" + token + "</a>";
                if (tokenizer.hasMoreTokens()) {
                    result += ", ";
                }
            }
        } else {
            citation = citation.trim();
            result += "<a href=\"http://cdk.sf.net/biblio.html#" + citation + "\">" + citation + "</a>";
        }
        return result;
    }
}
