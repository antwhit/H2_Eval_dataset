import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class bar extends pspdash.CGIChartBase {

    /** Create a bar chart. */
    public JFreeChart createChart() {
        JFreeChart chart;
        boolean vertical = true;
        String direction = getParameter("dir");
        if ((direction != null && direction.toLowerCase().startsWith("hor")) || parameters.get("horizontal") != null) vertical = false;
        chart = ChartFactory.createBarChart3D(null, null, null, data.catDataSource(), (vertical ? PlotOrientation.VERTICAL : PlotOrientation.HORIZONTAL), true, false, false);
        setupCategoryChart(chart);
        return chart;
    }
}
