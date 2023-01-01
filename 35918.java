import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.report.PropertyList;
import genj.report.Report;

/**
 * Compute the common ancestor of two individuals
 *
 */
public class ReportCommonAncestor extends Report {

    /**
   * we're not using the console
   */
    public boolean usesStandardOut() {
        return false;
    }

    /**
   * special treatmen context argument check
   */
    public String accepts(Object context) {
        if (context instanceof Indi) return getName();
        if (context instanceof Indi[]) {
            Indi[] indis = (Indi[]) context;
            if (indis.length == 2) return getName();
        }
        return null;
    }

    /**
   * our main method for an argument individual
   */
    public void start(Indi indi) {
        Indi other = (Indi) getEntityFromUser(translate("select"), indi.getGedcom(), Gedcom.INDI);
        if (other == null) return;
        start(new Indi[] { indi, other });
    }

    /**
   * our main method for an argument of a bunch of individuals
   */
    public void start(Indi[] indis) {
        Indi indi = indis[0];
        Indi other = indis[1];
        Indi ancestor = getCommonAncestor(indi, other);
        if (ancestor == null) {
            getOptionFromUser(translate("nocommon"), Report.OPTION_OK);
            return;
        }
        PropertyList list = new PropertyList(indi.getGedcom());
        list.add(translate("result.first", indi), indi);
        list.add(translate("result.second", other), other);
        list.add(translate("result.ancestor", ancestor), ancestor);
        showPropertiesToUser(getName(), list);
    }

    private Indi getCommonAncestor(Indi indi, Indi other) {
        Indi father = indi.getBiologicalFather();
        if (father != null) {
            if (father.isAncestorOf(other)) return father;
            Indi ancestor = getCommonAncestor(father, other);
            if (ancestor != null) return ancestor;
        }
        Indi mother = indi.getBiologicalMother();
        if (mother != null) {
            if (mother.isAncestorOf(other)) return mother;
            Indi ancestor = getCommonAncestor(mother, other);
            if (ancestor != null) return ancestor;
        }
        return null;
    }
}
