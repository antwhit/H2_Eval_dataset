import pspdash.*;
import pspdash.data.DataRepository;
import pspdash.data.DoubleData;
import pspdash.data.ResultSet;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class r5b extends CGIChartBase implements DefectAnalyzer.Task {

    protected Map defectFixTimes;

    /** Create a vertical bar chart. */
    public JFreeChart createChart() {
        JFreeChart chart = null;
        if (get3DSetting()) {
            chart = ChartFactory.createBarChart3D(null, null, null, data.catDataSource(), PlotOrientation.VERTICAL, false, false, false);
            chart.getPlot().setForegroundAlpha(ALPHA);
        } else {
            chart = ChartFactory.createBarChart(null, null, null, data.catDataSource(), PlotOrientation.VERTICAL, false, false, false);
        }
        setupCategoryChart(chart);
        return chart;
    }

    /** create the data upon which this chart is based. */
    protected void buildData() {
        initValues();
        DefectAnalyzer.run(getPSPProperties(), getDataRepository(), getPrefix(), parameters, this);
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
        if (parameters.get("strict") != null) {
            DefectTypeStandard dts = DefectTypeStandard.get(getPrefix(), getDataRepository());
            for (int i = dts.options.size(); i-- > 0; ) getRow((String) dts.options.elementAt(i));
        }
    }

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
