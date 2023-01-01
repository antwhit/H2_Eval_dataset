import pspdash.*;
import pspdash.data.DataRepository;
import pspdash.data.DoubleData;
import pspdash.data.ResultSet;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.jrefinery.chart.JFreeChart;

public class r5b extends CGIChartBase implements DefectAnalyzer.Task {

    protected Map defectFixTimes;

    /** Create a vertical bar chart. */
    public JFreeChart createChart() {
        JFreeChart chart = JFreeChart.createVerticalBarChart(data.catDataSource());
        setupCategoryChart(chart);
        return chart;
    }

    /** create the data upon which this chart is based. */
    protected void buildData() {
        String path = getParameter("hierarchyPath");
        if (path == null) path = (String) env.get("PATH_TRANSLATED");
        initValues();
        DefectAnalyzer.run(getPSPProperties(), path, this);
        int numRows = defectFixTimes.size();
        data = new ResultSet(numRows, 1);
        data.setColName(0, "Defect Type");
        data.setColName(1, "Total Fix Time (minutes)");
        Iterator i = defectFixTimes.keySet().iterator();
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
    private float[] emptyRow() {
        float[] result = new float[1];
        result[0] = (float) 0.0;
        return result;
    }

    /** Initialize internal data structures to zero */
    private void initValues() {
        defectFixTimes = new HashMap();
        if (parameters.get("strict") != null) for (int i = PSP_DEFECT_TYPES.length; i-- > 0; ) getRow(PSP_DEFECT_TYPES[i]);
    }

    /** list of defect types used by the PSP */
    private static final String[] PSP_DEFECT_TYPES = { "Documentation", "Syntax", "Build, package", "Assignment", "Interface", "Checking", "Data", "Function", "System", "Environment" };

    /** Lookup the row for a defect type - create it if it doesn't exist. */
    private float[] getRow(String defectType) {
        float[] result = (float[]) defectFixTimes.get(defectType);
        if (result == null) defectFixTimes.put(defectType, result = emptyRow());
        return result;
    }

    /** Increment a defect count for a particular defect type */
    protected void increment(float[] row, float time) {
        row[0] += time;
    }

    public void analyze(String path, Defect d) {
        try {
            increment(getRow(d.defect_type), Float.parseFloat(d.fix_time));
        } catch (NumberFormatException nfe) {
        }
    }
}
