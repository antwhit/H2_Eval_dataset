import java.applet.Applet;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import vqwiki.AbstractSearchEngine;
import vqwiki.SearchResultEntry;

/**
 * Applet to perform a search using lucene
 *
 * @author $Author: mrgadget4711 $
 */
public class SearchApplet extends Applet {

    /** The last search result */
    private ArrayList result = null;

    /** Reference to the search Engine */
    private AbstractSearchEngine se = null;

    /** Init the applet and the search engine.
	 * @see java.applet.Applet#init()
	 */
    public void init() {
        super.init();
        BasicConfigurator.configure();
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        se = new AppletSearchEngine();
    }

    /**
	 * Actually perform a search
	 * @param term The word to find
	 * @return A String containing the number of hits found
	 */
    public String doSearch(String term) {
        result = new ArrayList(se.findMultiple("", term, true));
        return String.valueOf(result.size());
    }

    /**
	 * Actually perform a search
	 * @param term The word to find
	 * @param template The template to fill in
	 * @return A String containing the number of hits found
	 */
    public String doSearch(String term, String template) {
        result = new ArrayList(se.findMultiple("", term, true));
        StringBuffer out = new StringBuffer();
        for (Iterator iter = result.iterator(); iter.hasNext(); ) {
            SearchResultEntry searchresultentry = (SearchResultEntry) iter.next();
            out.append(replaceString(replaceString(replaceString(replaceString(template, "##FOUNDWORD##", searchresultentry.getFoundWord()), "##TEXTAFTER##", searchresultentry.getTextAfter()), "##TEXTBEFORE##", searchresultentry.getTextBefore()), "##TOPIC##", searchresultentry.getTopic()));
        }
        return out.toString();
    }

    /**
 	 * Get a topic for a specific hit
 	 * @param i Number of hit 
 	 * @return Topic for this hit or empty string.
 	 */
    public String getTopic(int i) {
        if (result == null || i >= result.size()) return "";
        return ((SearchResultEntry) result.get(i)).getTopic();
    }

    /**
	 * Get a text before word actually found for a specific hit
	 * @param i Number of hit 
	 * @return Text before word actually found for this hit or empty string.
	 */
    public String getTextBefore(int i) {
        if (result == null || i >= result.size()) return "";
        return ((SearchResultEntry) result.get(i)).getTextBefore();
    }

    /**
	 * Get a text after word actually found for a specific hit
	 * @param i Number of hit 
	 * @return Text after word actually found for this hit or empty string.
	 */
    public String getTextAfter(int i) {
        if (result == null || i >= result.size()) return "";
        return ((SearchResultEntry) result.get(i)).getTextAfter();
    }

    /**
	 * Get a the word actually found for a specific hit
	 * @param i Number of hit 
	 * @return Word actually found for this hit or empty string.
	 */
    public String getFoundWord(int i) {
        if (result == null || i >= result.size()) return "";
        return ((SearchResultEntry) result.get(i)).getFoundWord();
    }

    /**
	 * Replaces occurences of the find string with the replace string in the given text
	 * @param text
	 * @param find
	 * @param replace
	 * @return the altered string
	 */
    public static String replaceString(String text, String find, String replace) {
        int findLength = find.length();
        StringBuffer buffer = new StringBuffer();
        int i;
        for (i = 0; i < text.length() - find.length() + 1; i++) {
            String substring = text.substring(i, i + findLength);
            if (substring.equals(find)) {
                buffer.append(replace);
                i += find.length() - 1;
            } else {
                buffer.append(text.charAt(i));
            }
        }
        buffer.append(text.substring(text.length() - (text.length() - i)));
        return buffer.toString();
    }
}
