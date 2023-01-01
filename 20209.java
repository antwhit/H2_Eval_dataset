import com.jrefinery.chart.*;
import java.awt.Color;
import java.awt.BasicStroke;
import pspdash.data.DateData;
import pspdash.XYDataSourceLineWrapper;

public class xy extends pspdash.CGIChartBase {

    private static Color[] NO_LINE = new Color[] { new Color(0, 0, 0, 0) };

    private static Color[] DEFAULT_COLS = new Color[] { Color.red, Color.blue, Color.green, Color.yellow, Color.cyan, Color.magenta, Color.orange, Color.pink, Color.lightGray };

    private static BasicStroke bs = new BasicStroke();

    private static BasicStroke[] DEFAULT_STROKE = new BasicStroke[] { bs, bs, bs, bs, bs, bs, bs, bs, bs };

    /** Create a  line chart. */
    public JFreeChart createChart() {
        JFreeChart chart;
        if ((data.numRows() > 0 && data.numCols() > 0 && data.getData(1, 1) instanceof DateData) || parameters.get("xDate") != null) chart = JFreeChart.createTimeSeriesChart(data.xyDataSource()); else {
            XYDataSource src = data.xyDataSource();
            String trendLine = getParameter("trend");
            if ("none".equalsIgnoreCase(trendLine)) ; else if ("average".equalsIgnoreCase(trendLine)) src = XYDataSourceLineWrapper.addAverageLine(src); else src = XYDataSourceLineWrapper.addRegressionLine(src);
            chart = JFreeChart.createXYChart(src);
        }
        if (!chromeless) {
            String label = data.getColName(1);
            chart.getPlot().getAxis(Plot.HORIZONTAL_AXIS).setLabel(label);
            label = getSetting("yLabel");
            if (label == null && data.numCols() == 2) label = data.getColName(2);
            if (label == null) label = getSetting("units");
            if (label == null) label = "Value";
            chart.getPlot().getAxis(Plot.VERTICAL_AXIS).setLabel(label);
        }
        if (data.numCols() == 2) chart.setLegend(null);
        return chart;
    }

    public void massageParameters() {
    }
}
