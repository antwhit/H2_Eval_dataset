import genj.gedcom.*;
import genj.report.*;
import java.io.*;
import java.util.*;

/**
 * GenJ - Report
 * @author Nils Meier nils@meiers.net
 * @version 0.1
 */
public class ReportBirthdays implements Report {

    /** this report's version */
    public static final String VERSION = "0.1";

    /**
   * Returns the version of this script
   */
    public String getVersion() {
        return VERSION;
    }

    /**
   * Returns the name of this report - should be localized.
   */
    public String getName() {
        return "Birthdays";
    }

    /**
   * Some information about this report
   * @return Information as String
   */
    public String getInfo() {
        return "This report prints individuals with a birthday for a given month. " + "Sorting the result is possible in case you run Java2 - change the " + "code as indicated by uncommenting a three-line code-block in " + "ReportBirthdays.java (recompilation necessary).";
    }

    /**
   * Indication of how this reports shows information
   * to the user. Standard Out here only.
   */
    public boolean usesStandardOut() {
        return true;
    }

    /**
   * Author
   */
    public String getAuthor() {
        return "Nils Meier <nils@meiers.net>";
    }

    /**
   * Tells whether this report doesn't change information in the Gedcom-file
   */
    public boolean isReadOnly() {
        return true;
    }

    /**
   * This method actually starts this report
   */
    public boolean start(ReportBridge bridge, Gedcom gedcom) {
        final String months[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        String s = (String) bridge.getValueFromUser("Please select a month", months, null);
        if (s == null) {
            return false;
        }
        int month = 0;
        for (; month < months.length; month++) {
            if (months[month].equals(s)) {
                break;
            }
        }
        month++;
        Vector candidates = new Vector(100);
        EntityList indis = gedcom.getEntities(gedcom.INDIVIDUALS);
        for (int i = 0; i < indis.getSize(); i++) {
            Indi indi = indis.getIndi(i);
            PropertyDate birth = indi.getBirthDate();
            if (birth == null) {
                continue;
            }
            if (birth.getStart().getMonth(-1) == month) {
                candidates.addElement(indi);
            }
        }
        Comparator comparator = new Comparator() {

            public int compare(Object o1, Object o2) {
                PropertyDate b1 = ((Indi) o1).getBirthDate();
                PropertyDate b2 = ((Indi) o2).getBirthDate();
                int d1 = 0;
                if (b1 != null) d1 = b1.getStart().getDay(0);
                int d2 = 0;
                if (b2 != null) d2 = b2.getStart().getDay(0);
                return d1 - d2;
            }
        };
        Collections.sort(candidates, comparator);
        bridge.println("The following individuals are born in month " + s);
        Enumeration e = candidates.elements();
        while (e.hasMoreElements()) {
            Indi indi = (Indi) e.nextElement();
            bridge.println(indi.getName() + " (*" + indi.getBirthDate() + ")");
        }
        return true;
    }
}
