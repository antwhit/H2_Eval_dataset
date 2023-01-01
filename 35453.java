import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.URL;
import pspdash.StringUtils;
import pspdash.data.DataRepository;
import pspdash.data.ResultSet;

public class excel extends pspdash.TinyCGIBase {

    private static String exportMethod = pspdash.Settings.getVal("excel.exportMethod");

    protected void writeHeader() {
    }

    protected void writeContents() throws IOException {
        if (useIQY()) writeIQY(); else writeHTML();
    }

    protected void writeIQY() throws IOException {
        out.print("Content-type: application/octet-stream\r\n\r\n");
        out.println("WEB");
        out.println("1");
        out.println(getURL());
        if (parameters.get("fullPage") != null) {
            out.println();
            out.println("Selection=EntirePage");
        }
    }

    protected void writeHTML() throws IOException {
        out.print("Content-type: application/vnd.ms-excel\r\n\r\n");
        byte[] contents = getTinyWebServer().getRequest(getURI(), true);
        String results = new String(contents);
        int beginPos = results.indexOf("<TABLE");
        int endPos = results.lastIndexOf("/TABLE");
        if (endPos != -1) endPos = results.indexOf('>', endPos);
        if (parameters.get("fullPage") != null || beginPos == -1 || endPos == -1) {
            results = StringUtils.findAndReplace(results, "<link", "<notlink");
            results = StringUtils.findAndReplace(results, "<LINK", "<NOTLINK");
            out.println(results);
        } else {
            out.println("<HTML>");
            out.println(results.substring(beginPos, endPos + 1));
            out.println("</HTML>");
        }
    }

    private boolean useIQY() {
        if ("mime".equalsIgnoreCase(exportMethod)) return false; else if ("iqy".equalsIgnoreCase(exportMethod)) return true; else {
            String userAgent = (String) env.get("HTTP_USER_AGENT");
            return (userAgent.indexOf("MSIE") != -1);
        }
    }

    private String getURI() {
        String referer = (String) env.get("HTTP_REFERER");
        String uri = (String) parameters.get("uri");
        if (uri != null) return resolveRelativeURI(referer, uri);
        uri = (String) env.get("REQUEST_URI");
        if (uri.indexOf('?') == -1 && referer != null) try {
            return (new URL(referer)).getFile();
        } catch (MalformedURLException mue) {
        }
        uri = StringUtils.findAndReplace(uri, "/excel.iqy", "/table.class");
        uri = StringUtils.findAndReplace(uri, "/excel.class", "/table.class");
        if (uri.indexOf('?') == -1) uri = uri + "?qf=export.rpt";
        return uri;
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
