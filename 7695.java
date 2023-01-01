import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiegelOnlineMediaProvider implements MediaProvider {

    public boolean MatchURLPattern(String strLink) {
        return strLink.matches("http:\\/\\/www\\.spiegel\\.de\\/video\\/video\\-.*\\.html");
    }

    public String getMediaURL(String strLink) {
        String regexp = "\\-.*\\.";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(strLink);
        m.find();
        String strRetUrl = "http://video.spiegel.de/flash/" + strLink.substring(m.start() + 1, m.end() - 1) + "_420x315_VP6_576.flv";
        return strRetUrl;
    }

    public String getName() {
        return "Spiegel";
    }
}
