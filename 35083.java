import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.URL;
import java.net.URLDecoder;
import pspdash.StringUtils;
import pspdash.data.DataRepository;
import pspdash.data.FormToHTML;

public class form2html extends pspdash.TinyCGIBase {

    protected void writeContents() throws IOException {
        String uri = getURI(), prefix = "";
        int slashPos = uri.indexOf("//");
        if (slashPos != -1) prefix = URLDecoder.decode(uri.substring(0, slashPos));
        byte[] contents = getTinyWebServer().getRequest(uri, true);
        StringBuffer results = new StringBuffer(new String(contents));
        FormToHTML.translate(results, getDataRepository(), prefix);
        out.print(results.toString());
    }

    private String getURI() {
        String referer = (String) env.get("HTTP_REFERER");
        String uri = (String) parameters.get("uri");
        if (uri != null) return uri;
        uri = (String) env.get("REQUEST_URI");
        if (uri.indexOf('?') == -1 && referer != null) try {
            return (new URL(referer)).getFile();
        } catch (MalformedURLException mue) {
        }
        return null;
    }

    private String getURL() {
        String host;
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException uhe) {
            host = "localhost";
        }
        String port = (String) env.get("SERVER_PORT");
        return "http://" + host + ":" + port + getURI();
    }
}
