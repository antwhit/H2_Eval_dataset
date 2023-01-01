import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.report.Report;

/**
 * GenJ - ReportDescendants
 */
public class ReportDescendants extends Report {

    /**
   * Returns the version of this script
   */
    public String getVersion() {
        return i18n("version");
    }

    /**
   * Returns the name of this report - should be localized.
   */
    public String getName() {
        return i18n("name");
    }

    /**
   * Some information about this report
   * @return Information as String
   */
    public String getInfo() {
        return i18n("info");
    }

    /**
   * Author
   */
    public String getAuthor() {
        return "Nils Meier <nils@meiers.net>";
    }

    /**
   * @see genj.report.Report#accepts(java.lang.Object)
   */
    public String accepts(Object context) {
        return context instanceof Indi || context instanceof Gedcom ? getName() : null;
    }

    /**
   * This method actually starts this report
   */
    public void start(Object context) {
        Indi indi;
        if (context instanceof Indi) {
            indi = (Indi) context;
        } else {
            Gedcom gedcom = (Gedcom) context;
            indi = (Indi) getEntityFromUser(i18n("descendants.of"), gedcom, Gedcom.INDI, "INDI:NAME");
            if (indi == null) return;
        }
        iterate(indi, 1);
    }

    /**
   * Iterates over descendants
   */
    private void iterate(Indi indi, int level) {
        println(getIndent(level) + level + " " + format(indi));
        int fcount = indi.getNoOfFams();
        for (int f = 0; f < fcount; f++) {
            Fam fam = indi.getFam(f);
            Indi spouse = fam.getOtherSpouse(indi);
            println(getIndent(level) + "  + " + format(spouse));
            Indi[] children = fam.getChildren();
            for (int c = 0; c < children.length; c++) {
                iterate(children[c], level + 1);
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
        String[] msgargs = { indi.getId(), indi.getName(), indi.getBirthAsString(), indi.getDeathAsString() };
        return i18n("format", msgargs);
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
