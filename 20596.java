import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DailyMotionMediaProvider implements MediaProvider {

    public boolean MatchURLPattern(String strLink) {
        return strLink.matches("^http\\:\\/\\/.*dailymotion.*video\\/.*$");
    }

    public String getMediaURL(String strLink) {
        try {
            String res = HTTP.post("http://megaupload.net/keepvid.php", "url=" + URLEncoder.encode(strLink, "UTF-8") + "&site=aa");
            System.out.println(res);
            String regexp = "http:\\/\\/[^\"]+\\.flv\\?[^\"]+";
            Pattern p = Pattern.compile(regexp);
            Matcher m = p.matcher(res);
            m.find();
            String strRetUrl = res.substring(m.start(), m.end());
            return strRetUrl;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName() {
        return "DailyMotion";
    }
}
