import genj.chart.Chart;
import genj.chart.IndexedSeries;
import genj.gedcom.Gedcom;
import genj.gedcom.Indi;
import genj.gedcom.PropertyAge;
import genj.gedcom.PropertyDate;
import genj.gedcom.PropertySex;
import genj.gedcom.time.Delta;
import genj.report.Report;
import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * A report showing age distribution for males/females
 */
public class ReportDemography extends Report {

    /** how to group ages */
    private int ageGroupSize = 10;

    /**
   * Accessor - age grouping
   */
    public int getAgeGroupSize() {
        return ageGroupSize;
    }

    /**
   * Accessor - age grouping
   */
    public void setAgeGroupSize(int set) {
        ageGroupSize = Math.max(1, Math.min(25, set));
    }

    /**
   * n/a
   */
    public boolean usesStandardOut() {
        return false;
    }

    /**
   * main
   */
    public void start(Gedcom gedcom) {
        String[] categories = new String[100 / ageGroupSize + 1];
        int max = 100 / ageGroupSize * ageGroupSize;
        categories[0] = max + "+";
        for (int i = 1; i < categories.length; i++) {
            if (ageGroupSize < 5 && i % Math.ceil(5 / ageGroupSize) != 0) categories[i] = ""; else categories[i] = (max - (i * ageGroupSize)) + "+";
        }
        IndexedSeries males = new IndexedSeries(translate("men"), categories.length), females = new IndexedSeries(translate("women"), categories.length);
        Iterator indis = gedcom.getEntities(Gedcom.INDI).iterator();
        while (indis.hasNext()) {
            Indi indi = (Indi) indis.next();
            analyze(indi, males, females, max);
        }
        String title = translate("title", gedcom.getName());
        showChartToUser(new Chart(title, PropertyAge.getLabelForAge(), new IndexedSeries[] { males, females }, categories, new DecimalFormat("#; #"), true, true));
    }

    /**
   * Analyze one individual
   */
    private void analyze(Indi indi, IndexedSeries males, IndexedSeries females, int max) {
        PropertyDate birth = indi.getBirthDate();
        PropertyDate death = indi.getDeathDate();
        if (birth == null || death == null) return;
        Delta delta = Delta.get(birth.getStart(), death.getStart());
        if (delta == null || delta.getYears() < 0) return;
        int years = delta.getYears();
        int group = years >= max ? 0 : (max - years - 1) / ageGroupSize + 1;
        if (indi.getSex() == PropertySex.MALE) males.dec(group); else females.inc(group);
    }
}
