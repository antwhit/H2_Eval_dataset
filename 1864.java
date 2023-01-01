import genj.gedcom.Entity;
import genj.gedcom.Gedcom;
import genj.gedcom.Property;
import genj.gedcom.PropertyChoiceValue;
import genj.gedcom.PropertyName;
import genj.report.Report;
import genj.view.ViewContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A report that uses PropertyChoiceValue's referencing ability. For
 * a given PropertyChoiceValue's value it shows those properties
 * with the same value (e.g. everyone living in Rendsburg)
 *
 * 20030529: NAME*, PLAC, CITY, POST, CTRY, FORM, OCCU, RELA
 *
 * @author nils
 */
public class ReportSameValues extends Report {

    /**
   * We only accept instances of PropertyChoice and PropertyName - since
   * we're returning something more fancy than the report name this is overridden.
   * Normally implementing a start method with a compatible one arg parameter
   * is sufficient
   * @see genj.report.Report#accepts(java.lang.Object)
   */
    public String accepts(Object context) {
        String val = null;
        if (context instanceof PropertyChoiceValue) val = ((PropertyChoiceValue) context).getValue();
        if (context instanceof PropertyName) val = ((PropertyName) context).getLastName();
        if (val == null || val.length() == 0) return null;
        return translate("xname", new String[] { ((Property) context).getPropertyName(), val });
    }

    /**
   * We don't use STDOUT
   * @see genj.report.Report#usesStandardOut()
   */
    public boolean usesStandardOut() {
        return false;
    }

    /**
   * Our entry point for choices
   */
    public void start(PropertyChoiceValue choice) {
        find(choice.getGedcom(), choice.getPropertyName(), choice.getSameChoices(), choice.getDisplayValue());
    }

    /**
   * Our entry point for names
   */
    public void start(PropertyName name) {
        find(name.getGedcom(), name.getPropertyName(), name.getSameLastNames(), name.getLastName());
    }

    /**
   * our main logic
   */
    private void find(Gedcom gedcom, String propName, Property[] sameProps, String val) {
        if (val == null || val.length() == 0) return;
        List items = new ArrayList();
        for (int i = 0; i < sameProps.length; i++) {
            Property prop = sameProps[i];
            Property parent = prop.getParent();
            String txt;
            if (parent instanceof Entity) txt = prop.getEntity().toString(); else txt = parent.getPropertyName() + " | " + prop.getEntity();
            items.add(new ViewContext(prop).setText(txt));
        }
        Collections.sort(items);
        showAnnotationsToUser(gedcom, translate("xname", new String[] { propName, val }), items);
    }
}
