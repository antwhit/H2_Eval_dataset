import genj.gedcom.*;
import genj.report.*;
import java.io.*;

/**
 * GenJ - Report
 * @author Nils Meier nils@meiers.net
 * @version 0.1
 */
public class ReportDescendants implements Report {

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
        return "Descendants";
    }

    /**
   * Some information about this report
   * @return Information as String
   */
    public String getInfo() {
        return "This report prints out all descendants of an individual";
    }

    /**
   * Finding the earliest ancestor recursive.
   */
    public Indi findEarliest(Indi indi) {
        Indi earliest = indi;
        PropertyDate birth;
        Fam fam = indi.getFamc();
        if (fam == null) {
            return (earliest);
        }
        indi = fam.getHusband();
        if (indi != null) {
            indi = findEarliest(indi);
            birth = indi.getBirthDate();
            if ((birth != null) && (birth.compareTo(earliest.getBirthDate()) < 0)) {
                earliest = indi;
            }
        }
        indi = fam.getWife();
        if (indi != null) {
            indi = findEarliest(indi);
            birth = indi.getBirthDate();
            if ((birth != null) && (birth.compareTo(earliest.getBirthDate()) < 0)) {
                earliest = indi;
            }
        }
        return (earliest);
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
        Indi indi = (Indi) bridge.getValueFromUser("Please select an individual", gedcom.getEntities(Gedcom.INDIVIDUALS).toArray(), null);
        if (indi == null) {
            return false;
        }
        iterate(bridge, indi, 1);
        return true;
    }

    /**
   * Iterates over descendants
   */
    private void iterate(ReportBridge bridge, Indi indi, int level) {
        bridge.println(getIndent(level) + level + " " + format(indi));
        int fcount = indi.getNoOfFams();
        for (int f = 0; f < fcount; f++) {
            Fam fam = indi.getFam(f);
            Indi spouse = fam.getOtherSpouse(indi);
            bridge.println(getIndent(level) + "  + " + format(spouse));
            Indi[] children = fam.getChildren();
            for (int c = 0; c < children.length; c++) {
                iterate(bridge, children[c], level + 1);
            }
        }
    }

    /**
   * resolves the information of one Indi
   */
    private String format(Indi indi) {
        if (indi == null) {
            return "?";
        }
        String n = indi.getName();
        if (Property.isEmptyOrNull(n)) {
            n = "?";
        }
        String b = indi.getBirthAsString();
        if (!Property.isEmptyOrNull(b)) {
            b = " b: " + b;
        }
        String d = indi.getDeathAsString();
        if (!Property.isEmptyOrNull(d)) {
            d = " d: " + d;
        }
        return n + b + d;
    }

    /**
   * Helper that indents to given level
   */
    private String getIndent(int level) {
        StringBuffer buffer = new StringBuffer(256);
        while (--level > 0) {
            buffer.append("    ");
        }
        return buffer.toString();
    }
}
