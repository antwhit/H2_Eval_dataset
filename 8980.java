import java.io.*;
import java.net.*;

public class Reader {

    private static String url = new String();

    private static String text = new String();

    public Reader() {
    }

    public static String getTxt(String page, String searchItem) {
        if (page.equals("google")) url = "http://www.google.com/search?q=" + searchItem; else if (page.equals("yahoo")) url = "http://search.yahoo.com/bin/search?p=" + searchItem; else if (page.equals("altavista")) url = "http://altavista.com/cgi-bin/query?q=" + searchItem; else if (page.equals("freshmeat")) url = "http://freshmeat.net/search/?q=" + searchItem; else if (page.equals("music")) url = "http://music.lycos.com/default.asp?QT=S&QW=" + searchItem;
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setAllowUserInteraction(false);
            BufferedReader dis = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String nextline;
            uc.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String test = "";
            while ((test = in.readLine()) != null) {
                text = text + test;
            }
        } catch (MalformedURLException mue) {
            System.err.println("\nError: Not a valid URL\n");
        } catch (UnknownHostException uhe) {
            System.err.println("\nError: Host not found! Are you online ?\n");
        } catch (Exception e) {
            System.err.println("\nError: " + e + "\n");
        }
        return text;
    }
}
