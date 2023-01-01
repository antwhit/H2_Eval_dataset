import pspdash.*;
import pspdash.data.DataRepository;
import pspdash.data.DoubleData;
import pspdash.data.ResultSet;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.jrefinery.chart.JFreeChart;

public class r5a extends CGIChartBase implements DefectAnalyzer.Task {

    protected Map defectCounts;

    /** Create a vertical bar chart. */
    public JFreeChart createChart() {
        JFreeChart chart = JFreeChart.createVerticalBarChart(data.catDataSource());
        setupCategoryChart(chart);
        return chart;
    }

    /** create the data upon which this chart is based. */
    protected void buildData() {
        initValues();
        DefectAnalyzer.run(getPSPProperties(), getDataRepository(), getPrefix(), parameters, this);
        int numRows = defectCounts.size();
        data = new ResultSet(numRows, 1);
        data.setColName(0, "Defect Type");
        data.setColName(1, "Total # Defects");
        Iterator i = defectCounts.keySet().iterator();
        String defectType;
        while (i.hasNext()) {
            defectType = (String) i.next();
            data.setRowName(numRows, defectType);
            data.setData(numRows, 1, new DoubleData(getRow(defectType)[0]));
            numRows--;
        }
        data.sortBy(1, true);
    }

    /** Generate an empty row of the appropriate size */
    private int[] emptyRow() {
        int[] result = new int[1];
        result[0] = 0;
        return result;
    }

    /** Initialize internal data structures to zero */
    private void initValues() {
        defectCounts = new HashMap();
        if (parameters.get("strict") != null) {
            DefectTypeStandard dts = DefectTypeStandard.get(getPrefix(), getDataRepository());
            for (int i = dts.options.size(); i-- > 0; ) getRow((String) dts.options.elementAt(i));
        }
    }

    /** Lookup the row for a defect type - create it if it doesn't exist. */
    private int[] getRow(String defectType) {
        int[] result = (int[]) defectCounts.get(defectType);
        if (result == null) defectCounts.put(defectType, result = emptyRow());
        return result;
    }

    /** Increment a defect count for a particular defect type */
    protected void increment(int[] row) {
        row[0]++;
    }

    public void analyze(String path, Defect d) {
        increment(getRow(d.defect_type));
    }
}
