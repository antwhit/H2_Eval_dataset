/**
 * Title:        JSherlock<p>
 * Description:  JSherlock is a clone of the well known application "Sherlock"
 * on MacOS.
 * It is a frontend for querying various internet-search
 * websites such as yahoo, google, freshmeat ....
 * JSherlock is extendable via plugins.
 * This program is distributed under the General Public License
 * (GPL). Read the LICENSE-Text, shipped with this package.
 * (c) September, 10th 2000
 * Simon 'Grajm' Benten <simbente@agent.fho-emden.de>
 * Daniel von Garrel <dangarre@agent.fho-emden.de>
 * Falco Hirschenberger <hirsch@et-inf.fho-emden.de>
 *  <p>
 * Copyright:    Copyright (c) <Simon Benten> <Daniel von Garrel> <Falco Hirschenberger><p>
 * Company:      FHOOW<p>
 * @author <Simon Benten> <Daniel von Garrel> <Falco Hirschenberger>
 */
public class Main {

    private static String text = new String();

    private static String beginning = new String();

    private static String endURL = new String();

    private static String end = new String("</a>");

    public Main() {
    }

    public static void main(String[] argv) {
        Main main1 = new Main();
        String end = new String(">");
        if (argv.length < 2 || argv.length > 2) {
            System.out.println("\n\nUsage: java Main <searchengine> <searchitem>\n" + "\nPossible search engines are:\n" + "yahoo\ngoogle\naltavista\nfreshmeat\nmusic\n");
            System.exit(1);
        }
        if (argv[0].equals("yahoo")) {
            beginning = "<li><a href=\"";
            endURL = "</a>";
        } else if (argv[0].equals("google")) {
            beginning = "<p><A HREF=";
            endURL = "</A>";
        } else if (argv[0].equals("altavista")) {
            beginning = "<b><a href=\"";
            endURL = "</a>";
        } else if (argv[0].equals("freshmeat")) {
            beginning = "</small>" + "\t<a href=\"";
            endURL = "</a>";
        } else if (argv[0].equals("music")) {
            beginning = "<p>" + "<a href=\"";
            endURL = "</a>";
        } else {
            System.out.println("\nError: Not a valid search engine!\nTry: 'java Main -h'\n");
            System.exit(1);
        }
        text = Reader.getTxt(argv[0], argv[1]);
        Parser p = new Parser(text);
        p.parse(beginning, end, endURL);
    }
}
