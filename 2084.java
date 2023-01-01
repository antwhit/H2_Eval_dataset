import genj.gedcom.Fam;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.report.Report;

/**
 * GenJ -  ReportAncestors
 */
public class ReportAncestors extends Report {

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
        return "YON - Jan C. Hardenbergh";
    }

    /**
     * @see genj.report.Report#accepts(java.lang.Object)
     */
    public String accepts(Object context) {
        return context instanceof Indi || context instanceof Gedcom ? getName() : null;
    }

    /**
     * Entry point into this report - by default reports are only run on a
     * context of type Gedcom but since we've overriden accepts we're
     * ready for Gedcom AND Indi
     */
    public void start(Object context) {
        Indi indi;
        if (context instanceof Indi) {
            indi = (Indi) context;
        } else {
            Gedcom gedcom = (Gedcom) context;
            indi = (Indi) getEntityFromUser(i18n("ancestors.of"), gedcom, Gedcom.INDI);
            if (indi == null) return;
        }
        parent(indi, 1);
    }

    /**
     * parent - prints information about one parent and then recurses
     */
    private void parent(Indi indi, int level) {
        println(getIndent(level, OPTIONS.getIndentPerLevel(), null) + level + " " + format(indi));
        Fam famc = indi.getFamc();
        if (famc == null) return;
        if (famc.getWife() != null) parent(famc.getWife(), level + 1);
        if (famc.getHusband() != null) parent(famc.getHusband(), level + 1);
    }

    /**
     * resolves the information of one Indi
     */
    private String format(Indi indi) {
        if (indi == null) return "?";
        String[] msgargs = { indi.getId(), indi.getName(), indi.getBirthAsString(), indi.getDeathAsString() };
        return i18n("format", msgargs);
    }
}
